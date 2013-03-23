package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import parser.Parser;
import parser.SemanticsTable;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import view.Canvas;
import view.HelpWindow;
import view.Window;


/**
 * Holds list of all workspaces, able to create new workspace
 * Receives input from view and pass them to Parser
 * Receives parsed input and send it to Executor with current workspace as parameter.
 * 
 * @author Xu Rui, Henrique Moraes, Ziqiang Huang
 * 
 */

public class Controller {
    private int DEFAULT_MOVE_VALUE = 100;
    private int DEFAULT_TURN_VALUE = 90;
    private static final String USER_DIR = "user.dir";
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    protected static final String DEFAULT_URL = "http://www.cs.duke.edu/courses/spring13/compsci308/assign/03_slogo/commands.php";
    protected static final String HELP_TITLE = "Command Description for SLogo";
    protected static final Object[] DEFAULT_PEN_COLOR_OPTION = {"red","blue","black"};

    private Parser myParser;
    private Window myWindow;
    private ResourceBundle myResource;
    private JFileChooser myChooser;
    private HelpWindow myHelpWindow;
   
    /**
     * Constructor for controller responsible for initializing the view
     * and the parser
     */
    public Controller () {
        myChooser = new JFileChooser(System.getProperties().getProperty(USER_DIR));
        myResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
        myWindow = new Window(this);
        myParser = new Parser();
    }

    /**
     * Parses command using parser, executes every command, update workspace command list
     * 
     * @param command string in input Text Field
     */
    public void processCommand (String command) {
        SemanticsTable.getInstance().setContext(getWorkspace());
        List<SyntaxNode> commandList = myParser.parseCommand(command);
        SemanticsTable.getInstance().setContext(null);
        try {
            for (SyntaxNode node : commandList) {
                int returnValue = node.evaluate(getWorkspace());
                System.out.printf("my return value is %d", returnValue);
                getWorkspace().addHistory(command);
            }
        }
        catch (NullPointerException ne) {
            getWorkspace().showError("You entered an invalid command.");
        }
        catch (InvalidArgumentsException e) {            
            getWorkspace().showError("Invalid Input: " + e.getMessage());
        }
    }
    
    /**
     * Executes list of commands (for undo option)
     * 
     * @param commandList
     */
    public void processCommands(List<String> commandList){
        for (String command: commandList){
        	processCommand(command);
        }
    }
    
    /**
     * This method is set private so the Window does not have access to it
     * 
     * @return the current workspace selected on the tab used internally
     *         so this class can handle its functions
     */
    private Workspace getWorkspace () {
        return (Workspace) myWindow.getCanvas().getPaintableResource();
    }

    /**
     * @return resource bundle associated with the language of the interface
     */
    public ResourceBundle getResource () {
        return myResource;
    }

    /**
     * Responsible for creating the menu bar, it needs to be called from
     * window because once window calls pack(), the menu cannot be added
     * The bar is created in controller so the view does not get access
     * to important information from the model
     * 
     * @return menu bar for the program
     */
    public JMenuBar createJMenuBar () {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createCommandMenu());
        menuBar.add(createHelpMenu());
        menuBar.add(createSettingMenu());
        return menuBar;
    }
    
    
    /**
     * creates the setting options on the menu bar,
     * enabling user to set certain properties of the workspace,
     * such as background image, turtle image, pen color, etc
     * 
     * @return
     */
    private JMenu createSettingMenu() {
        JMenu menu = new JMenu(myResource.getString("SettingMenu"));
        menu.add(new AbstractAction(myResource.getString("SetPenColor")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                Object[] options = DEFAULT_PEN_COLOR_OPTION;
                int response = JOptionPane.showOptionDialog(null, 
                                                            myResource.getString("SetPenDialog"), 
                                                            "", 
                                                            JOptionPane.YES_OPTION, 
                                                            JOptionPane.INFORMATION_MESSAGE, 
                                                            null, 
                                                            options, 
                                                            null);

                Color color;
                try {
                    Field field = Color.class.getField(options[response].toString());
                    color = (Color)field.get(null);
                } catch (Exception e1) {
                    color = null;
                }
                getWorkspace().getTurtle().setColor(color);

            }
        });
        
        menu.add(new AbstractAction(myResource.getString("SetBackgroundImage")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    int response = myChooser.showOpenDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        getWorkspace().getCanvas().setBackgroundImage(ImageIO.read(myChooser.getSelectedFile()));
                    }
                }
                catch (Exception exception) {
                    getWorkspace().showError(exception.toString());
                }
            }
        });
        return menu;
    }

    /**
     * creates the help page option on the menu bar
     * 
     * @return help Menu option
     */
    private JMenu createHelpMenu() {
        JMenu menu = new JMenu(myResource.getString("HelpMenu"));
        menu.add(new AbstractAction(myResource.getString("CommandDescription")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                URL helpPage = null;
                try {
                    helpPage = new URL(DEFAULT_URL);
                } catch (MalformedURLException exception) {
                    getWorkspace().showError(exception.toString());
                }
                new HelpWindow(HELP_TITLE,helpPage);
            }
        });
        return menu;
    }

    /**
     * creates the File option on the menu bar
     * 
     * @return File Menu option
     */
    private JMenu createFileMenu () {
        JMenu menu = new JMenu(myResource.getString("FileMenu"));
        createFileCommands(menu);
        menu.add(new JSeparator());
        createQuitCommand(menu);
        return menu;
    }

    /**
     * Creates menu items that handles file manipulation such as creating
     * a new one, saving and loading files
     * 
     * @return File menu for the view
     */
    private void createFileCommands (JMenu menu) {
        menu.add(new AbstractAction(myResource.getString("NewWorkspace")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                myWindow.createWorkspace();
            }
        });
        menu.add(new AbstractAction(myResource.getString("OpenFile")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    int response = myChooser.showOpenDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        loadWorkspace(new FileReader(myChooser.getSelectedFile()));
                    }
                }
                catch (Exception exception) {
                    getWorkspace().showError(exception.toString());
                }
            }
        });
        menu.add(new AbstractAction(myResource.getString("SaveFile")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    int response = myChooser.showSaveDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        getWorkspace().saveWorkspace(new FileWriter(myChooser.getSelectedFile()));
                    }
                }
                catch (Exception exception) {
                    getWorkspace().showError(exception.toString());
                }
            }
        });
    }

    /**
     * Creates a command to quit the program
     * 
     * @return File menu for the view
     */
    private void createQuitCommand (JMenu menu) {
        menu.add(new AbstractAction(myResource.getString("QuitProgram")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Creates the command menu for the Menu Bar, this menu is created in
     * control because it handles turtle instances that need to be separated
     * from the view
     * 
     * @return Command menu for the view
     */
    private JMenu createCommandMenu () {
        JMenu menu = new JMenu(myResource.getString("CommandMenu"));
        createPhysicalCommands(menu);
        menu.add(new JSeparator());
        createVisualCommands(menu);
        menu.add(new JSeparator());
        return menu;
    }

    /**
     * Adds physical turtle commands to the menu item, such as moving and
     * turning
     * 
     * @param menu menu to have the items added
     */
    private void createPhysicalCommands (JMenu menu) {
        menu.add(new AbstractAction(myResource.getString("ForwardCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                getWorkspace().getTurtle().move(DEFAULT_MOVE_VALUE);
                getWorkspace().addHistory("forward " + DEFAULT_MOVE_VALUE);
            }
        });
        menu.add(new AbstractAction(myResource.getString("BackwardCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                getWorkspace().getTurtle().move(-DEFAULT_MOVE_VALUE);
                getWorkspace().addHistory("back " + DEFAULT_MOVE_VALUE);
            }
        });
        menu.add(new AbstractAction(myResource.getString("TurnRightCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                getWorkspace().getTurtle().turn(DEFAULT_TURN_VALUE);
                getWorkspace().addHistory("right " + DEFAULT_TURN_VALUE);
            }
        });
        menu.add(new AbstractAction(myResource.getString("TurnLeftCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                getWorkspace().getTurtle().turn(-DEFAULT_TURN_VALUE);
                getWorkspace().addHistory("left " + DEFAULT_TURN_VALUE);
            }
        });
    }

    /**
     * Adds visual turtle commands to the menu item, such as hiding itself and
     * putting pen up or down
     * 
     * @param menu menu to have the items added
     */
    private void createVisualCommands (JMenu menu) {
        menu.add(new AbstractAction(myResource.getString("ShowCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                getWorkspace().getTurtle().setHiding(false);
                getWorkspace().addHistory("showturtle");
            }
        });
        menu.add(new AbstractAction(myResource.getString("HideCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                getWorkspace().getTurtle().setHiding(true);
                getWorkspace().addHistory("hideturtle");
            }
        });
        menu.add(new AbstractAction(myResource.getString("PenUpCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                getWorkspace().getTurtle().setPenWriting(false);
                getWorkspace().addHistory("penup");
            }
        });
        menu.add(new AbstractAction(myResource.getString("PenDownCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                getWorkspace().getTurtle().setPenWriting(true);
                getWorkspace().addHistory("pendown");
            }
        });
    }

    /**
     * Loads a file of variable and command to a current workspace.
     */
    public void loadWorkspace (Reader r) throws IOException {
        BufferedReader input = new BufferedReader(r);
        Scanner file = new Scanner(input);
        processFile(file);
    }

    /**
     * Parses file using parser, executes every command in file
     * 
     * @param input
     */
    public void processFile (Scanner inputFile) {
        SemanticsTable.getInstance().setContext(getWorkspace());
        List<SyntaxNode> commandList = myParser.parseCommand(inputFile);
        SemanticsTable.getInstance().setContext(null);
        for (SyntaxNode node : commandList) {
            node.evaluate(getWorkspace());
        }
    }
}
