package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import model.Turtle;
import parser.Parser;
import parser.SemanticsTable;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidSemanticsException;
import view.Window;
import view.WorkspaceHandler;
import view.components.InputField;
import view.windows.GraphicsSettingsWindow;
import view.windows.HelpWindow;
import view.windows.WorkspaceSettingsWindow;
import controller.support.StayOpenCheckBoxMenuItem;


/**
 * Holds list of all workspaces, able to create new workspace
 * Receives input from view and pass them to Parser
 * Receives parsed input and send it to Executor with current workspace as parameter.
 * 
 * @author Xu Rui, Henrique Moraes, Ziqiang Huang
 * 
 */

public class Controller {
    private static final String LANGUAGE = "English";
    private int DEFAULT_MOVE_VALUE = 100;
    private int DEFAULT_TURN_VALUE = 90;
    public static final String USER_DIR = "user.dir";
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    protected static final String DEFAULT_URL =
            "http://www.cs.duke.edu/courses/spring13/compsci308/assign/03_slogo/commands.php";
    protected static final String HELP_TITLE = "Command Description for SLogo";
    protected static final Object[] DEFAULT_PEN_COLOR_OPTION = { "red", "blue", "black" };
    public static final ResourceBundle RESOURCE = ResourceBundle
            .getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
    public static final ResourceBundle RESOURCE_ERROR = ResourceBundle
            .getBundle(DEFAULT_RESOURCE_PACKAGE + "error.Error"+LANGUAGE);
    private static final int INPUT_FIELD_SIZE = 70;

    private Parser myParser;
    private WorkspaceHandler myWorkHandler;
    private JFileChooser myChooser;
    private HelpWindow myHelpWindow;
    private InputField myInputField;

    
    /**
     * Constructor for controller responsible for initializing the view
     * and the parser
     */
    public Controller () {
        myChooser = new JFileChooser(System.getProperties().getProperty(USER_DIR));
        myInputField = new InputField(INPUT_FIELD_SIZE);
        myInputField.addActionListener(createRunCommandListener ());
        myWorkHandler = new Window(myInputField, createJMenuBar());
        myParser = new Parser();
    }

    /**
     * Parses command using parser, executes every command, update workspace command list
     * 
     * @param command string in input Text Field
     */
    public void processCommand (String command) {
        SemanticsTable.getInstance().setContext(getWorkspace());
        List<SyntaxNode> commandList = null;
        try {
            commandList = myParser.parseCommand(command);
        }
        catch (InvalidSemanticsException e) {
            getWorkspace().showError(e.getMessage());
            SemanticsTable.getInstance().setContext(null);
            return;
        }
        getWorkspace().execute(commandList);
        getWorkspace().addHistory(command);
        getWorkspace().addExecutableHistory(commandList);
        SemanticsTable.getInstance().setContext(null);
    }
    
    /**
     * set Listener to send the input string to controller whenever the
     * run button or enter is pressed
     */
    public ActionListener createRunCommandListener () {
        return new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                processCommand(myInputField.getText());
                myInputField.setText("");
            }
        };
    }

    /**
     * This method is set private so the Window does not have access to it
     * 
     * @return the current workspace selected on the tab used internally
     *         so this class can handle its functions
     */
    private Workspace getWorkspace () {
        return myWorkHandler.getWorkspace();
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
        menuBar.add(createSettingMenu());
        menuBar.add(createHelpMenu());
        menuBar.add(createEditMenu());
        return menuBar;
    }

    /**
     * creates the File option on the menu bar
     * 
     * @return File Menu option
     */
    private JMenu createFileMenu () {
        JMenu menu = new JMenu(RESOURCE.getString("FileMenu"));
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
        menu.add(new AbstractAction(RESOURCE.getString("New")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                myWorkHandler.createWorkspace();
            }
        });
        menu.add(new AbstractAction(RESOURCE.getString("OpenFile")) {
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
        menu.add(new AbstractAction(RESOURCE.getString("SaveFile")) {
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
        menu.add(new AbstractAction(RESOURCE.getString("QuitProgram")) {
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
        JMenu menu = new JMenu(RESOURCE.getString("CommandMenu"));
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
        menu.add(new AbstractAction(RESOURCE.getString("ForwardCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                Map<Integer, Turtle> turtles = getWorkspace().getTurtleManager().getTurtles();
                for (Turtle t : turtles.values()) {
                    t.move(DEFAULT_MOVE_VALUE);
                }
                getWorkspace().update();
                getWorkspace().addHistory("forward " + DEFAULT_MOVE_VALUE);
            }
        });
        menu.add(new AbstractAction(RESOURCE.getString("BackwardCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                Map<Integer, Turtle> turtles = getWorkspace().getTurtleManager().getTurtles();
                for (Turtle t : turtles.values()) {
                    t.move(-DEFAULT_MOVE_VALUE);
                }
                getWorkspace().update();
                getWorkspace().addHistory("back " + DEFAULT_MOVE_VALUE);
            }
        });
        menu.add(new AbstractAction(RESOURCE.getString("TurnRightCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                Map<Integer, Turtle> turtles = getWorkspace().getTurtleManager().getTurtles();
                for (Turtle t : turtles.values()) {
                    t.turn(DEFAULT_TURN_VALUE);
                }
                getWorkspace().update();
                getWorkspace().addHistory("right " + DEFAULT_TURN_VALUE);
            }
        });
        menu.add(new AbstractAction(RESOURCE.getString("TurnLeftCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                Map<Integer, Turtle> turtles = getWorkspace().getTurtleManager().getTurtles();
                for (Turtle t : turtles.values()) {
                    t.turn(-DEFAULT_TURN_VALUE);
                }
                getWorkspace().update();
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
        menu.add(new AbstractAction(RESOURCE.getString("ShowCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                Map<Integer, Turtle> turtles = getWorkspace().getTurtleManager().getTurtles();
                for (Turtle t : turtles.values()) {
                    t.setHiding(false);
                }
                getWorkspace().update();
                getWorkspace().addHistory("showturtle");
            }
        });
        menu.add(new AbstractAction(RESOURCE.getString("HideCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                Map<Integer, Turtle> turtles = getWorkspace().getTurtleManager().getTurtles();
                for (Turtle t : turtles.values()) {
                    t.setHiding(true);
                }
                getWorkspace().update();
                getWorkspace().addHistory("hideturtle");
            }
        });
        menu.add(new AbstractAction(RESOURCE.getString("PenUpCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                Map<Integer, Turtle> turtles = getWorkspace().getTurtleManager().getTurtles();
                for (Turtle t : turtles.values()) {
                    t.setPenWriting(false);
                }
                getWorkspace().update();
                getWorkspace().addHistory("penup");
            }
        });
        menu.add(new AbstractAction(RESOURCE.getString("PenDownCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                Map<Integer, Turtle> turtles = getWorkspace().getTurtleManager().getTurtles();
                for (Turtle t : turtles.values()) {
                    t.setPenWriting(true);
                }
                getWorkspace().update();
                getWorkspace().addHistory("pendown");
            }
        });
    }

    /**
     * creates the setting options on the menu bar,
     * enabling user to set certain properties of the workspace,
     * such as background image, turtle image, pen color, etc
     * 
     * @return
     */
    private JMenu createSettingMenu () {
        JMenu menu = new JMenu(RESOURCE.getString("SettingMenu"));

        menu.add(new AbstractAction(RESOURCE.getString("NewTurtle")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                JTextField turtle = new JTextField();
                Object[] message = { RESOURCE.getString("ChooseIndex"), turtle };
                int option =
                        JOptionPane.showConfirmDialog(null, message,
                                                      RESOURCE.getString("NewTurtle"),
                                                      JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    try {
                        int index = Integer.parseInt(turtle.getText());
                        if (!getWorkspace().getTurtleManager().getTurtles().containsKey(index)) {
                            getWorkspace().addTurtle(index);
                        }
                        else {
                            getWorkspace().showError(RESOURCE_ERROR.getString("TurtleExists"));
                        }
                    }
                    catch (Exception e1) {
                        getWorkspace().showError(RESOURCE_ERROR.getString("NotIndex"));
                    }
                }
            }
        });

        JMenu tellMenu = new JMenu(RESOURCE.getString("Tell"));
        tellMenu.addMouseListener(createTellMenuListener(tellMenu));
        menu.add(tellMenu);

        menu.add(new AbstractAction(RESOURCE.getString("SetPenColor")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                JTextField id = new JTextField();
                JTextField R = new JTextField();
                JTextField G = new JTextField();
                JTextField B = new JTextField();
                Object[] message =
                        { RESOURCE.getString("ColorIndex"), id, RESOURCE.getString("Rval"), R,
                         RESOURCE.getString("Gval"), G, RESOURCE.getString("Bval"), B };
                int option =
                        JOptionPane.showConfirmDialog(null, message,
                                                      RESOURCE.getString("EnterColor"),
                                                      JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        int colorIndex = Integer.parseInt(id.getText());
                        int Rvalue = Integer.parseInt(R.getText());
                        int Gvalue = Integer.parseInt(G.getText());
                        int Bvalue = Integer.parseInt(B.getText());
                        Color c = new Color(Rvalue, Gvalue, Bvalue);
                        getWorkspace().getColors().setColor(colorIndex, c);
                        for (Turtle t : getWorkspace().getTurtleManager().getTurtles().values()) {
                            t.setColor(c);
                        }
                    }
                    catch (Exception e1) {
                        getWorkspace().showError(RESOURCE_ERROR.getString("InvalidColor"));
                    }
                }
            }
        });

        menu.add(new AbstractAction(RESOURCE.getString("SetBackgroundImage")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    int response = myChooser.showOpenDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        getWorkspace().getCanvas().setBackgroundImage(ImageIO.read(myChooser
                                                                              .getSelectedFile()));
                    }
                }
                catch (Exception exception) {
                    getWorkspace().showError(exception.toString());
                }
            }
        });

        menu.add(new JSeparator());
        menu.add(new AbstractAction(RESOURCE.getString("Graphics")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                new GraphicsSettingsWindow(getWorkspace());
            }
        });

        menu.add(new AbstractAction(RESOURCE.getString("Workspace")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                new WorkspaceSettingsWindow(getWorkspace());
            }
        });

        return menu;
    }

    /**
     * @param menu menu to take checkbox items
     * @return mouse listener for this menu item
     */
    private MouseListener createTellMenuListener (final JMenu menu) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered (MouseEvent e) {
                Map<Integer, Turtle> map = getWorkspace().getAllTurtles();
                Map<Integer, Turtle> activeMap = getWorkspace().getTurtleManager().getTurtles();
                menu.removeAll();
                for (Integer i : map.keySet()) {
                    final StayOpenCheckBoxMenuItem item =
                            new StayOpenCheckBoxMenuItem(RESOURCE.getString("Turtle") + i);

                    item.addItemListener(createCheckBoxItemListener(item));
                    if (activeMap.containsKey(i)) {
                        item.setSelected(true);
                    }
                    menu.add(item);
                }
            }
        };
    }

    /**
     * @param item menu item to add the listener
     * @return item listener for this menu item
     */
    private ItemListener createCheckBoxItemListener (final StayOpenCheckBoxMenuItem item) {
        return new ItemListener() {
            @Override
            public void itemStateChanged (ItemEvent e) {
                Scanner s = new Scanner(item.getText());
                s.skip(RESOURCE.getString("Turtle"));
                int index = s.nextInt();
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    getWorkspace().activateTurtle(index);
                }
                else {
                    getWorkspace().deactivateTurtle(index);
                }
            }
        };
    }

    /**
     * creates the help page option on the menu bar
     * 
     * @return help Menu option
     */
    private JMenu createHelpMenu () {
        JMenu menu = new JMenu(RESOURCE.getString("HelpMenu"));
        menu.add(new AbstractAction(RESOURCE.getString("CommandDescription")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                URL helpPage = null;
                try {
                    helpPage = new URL(DEFAULT_URL);
                }
                catch (MalformedURLException exception) {
                    getWorkspace().showError(exception.toString());
                }
                new HelpWindow(HELP_TITLE, helpPage);
            }
        });
        return menu;
    }

    /**
     * Creates Edit menu with undo and redo options.
     * 
     * @return help Menu option
     */
    private JMenu createEditMenu () {
        JMenu menu = new JMenu(RESOURCE.getString("EditMenu"));
        menu.add(new AbstractAction(RESOURCE.getString("UndoCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                getWorkspace().getUndoManager().undo();
                getWorkspace().addHistory("Undo");

                }
                catch (Exception exception) {
                	getWorkspace().showError("Undo is not allowed.");
                }
            }
        });

        menu.add(new AbstractAction(RESOURCE.getString("RedoCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                	getWorkspace().getUndoManager().redo();
                    getWorkspace().addHistory("Redo");
                }
                catch (Exception exception) {
                    getWorkspace().showError("Redo is not allowed.");
                }
            }
        });
        return menu;
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
        List<SyntaxNode> commandList = null;
        try {
            commandList = myParser.parseCommand(inputFile);
        }
        catch (InvalidSemanticsException e) {
            getWorkspace().showError(e.getMessage());
        }
        SemanticsTable.getInstance().setContext(null);
        getWorkspace().execute(commandList);
    }
}
