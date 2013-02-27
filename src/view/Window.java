package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import view.components.ErrorBox;
import view.components.Error;
import view.components.InputField;
import controller.Controller;
import controller.Workspace;

/**
 * Window that holds all the user interface. Sends commands to controller
 * in case user inputs anything
 * @author Henrique Moraes, Ziqiang
 *
 */
public class Window extends JFrame {
    private static final String USER_DIR = "user.dir";
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private static final int INPUT_FIELD_SIZE = 70;
    private static final String WORKSPACE_NAME = "Workspace ";
    private static final String VARIABLE_KEYWORD = "Variable";
    private static final String COMMAND_KEYWORD = "Command";
    
    private int workspaceIndex = 1;
    private JFileChooser myChooser;
    private ResourceBundle myResources;

    // Create Listeners
    private ActionListener myRunCommandListener;
    private int DEFAULT_MOVE_VALUE = 100;
    private int DEFAULT_TURN_VALUE = 220;

    private Controller myController;
    private InputField myInputField;
    
    private Canvas myCurrentCanvas;
    private InformationView myInfoView;
    private JTabbedPane myTabbedPane;

    public Window() {
    
        myController = new Controller();
        
        
        setTitle("SLogo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        myChooser = new JFileChooser(System.getProperties().getProperty(USER_DIR));
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");

        myInfoView = new InformationView();
        myTabbedPane = new JTabbedPane();
        myInputField = new InputField(INPUT_FIELD_SIZE);
        ErrorBox.setWindow(this);
        makeListeners();
        
        getContentPane().add(myTabbedPane, BorderLayout.CENTER);
        getContentPane().add(makeInformationView(), BorderLayout.EAST);
        getContentPane().add(createInputField(), BorderLayout.SOUTH);

        setJMenuBar(makeJMenuBar());
        
        pack();

        setVisible(true);
    }
    
    /**
     * Sets the necessary observers for the turtle of this Workspace
     * @param turtle Observed turtle
     */
    private void setObservers(Observable turtle){
        turtle.addObserver(myCurrentCanvas);
        turtle.addObserver(myInfoView);
    }
    
    /**
     * Defines the necessary parameter the Window needs to hold from the
     * Workspace
     * @param workspace Newly created workspace
     */
    public void createCanvas(){
        myCurrentCanvas = new Canvas(WORKSPACE_NAME + workspaceIndex);
        workspaceIndex++;
        myTabbedPane.addTab(myCurrentCanvas.getName(), myCurrentCanvas);
        myTabbedPane.setSelectedComponent(myCurrentCanvas);
        setObservers(myCurrentCanvas.getTurtle());
        myCurrentCanvas.getTurtle().update();
    }
    
    public Canvas getCanvas(){
        return myCurrentCanvas;
    }

    /**
     * Creates a scrollable informationView
     * @return Information View of this program
     */
    private JComponent makeInformationView() {
        JPanel infoPanel = new JPanel();
        JScrollPane InfoScrollPane = new JScrollPane(myInfoView);
        InfoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        infoPanel.add(InfoScrollPane);
        return infoPanel;
    }

    /**
     * Responsible for creating the menu bar
     * @return menu bar for the program
     */
    private JMenuBar makeJMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(makeFileMenu());
        menuBar.add(makeCommandMenu());
        return menuBar;
    }

    /**
     * creates the File option on the menu bar
     * Maybe separate a menu creator on its own class...
     * 
     * @return File Menu option
     */
    private JMenu makeFileMenu() {
        JMenu menu = new JMenu(myResources.getString("FileMenu"));
        menu.add(new AbstractAction(myResources.getString("NewWorkspace")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCanvas();
            }
        });
        menu.add(new AbstractAction(myResources.getString("OpenFile")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int response = myChooser.showOpenDialog(null);
                    if (response == myChooser.APPROVE_OPTION) {
                        loadWorkspace(new FileReader(myChooser.getSelectedFile()));
                    }
                }
                catch (Exception exception) {
                    showError(exception.toString());
                }
            }
        });

        menu.add(new AbstractAction(myResources.getString("SaveFile")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int response = myChooser.showSaveDialog(null);
                    if (response == myChooser.APPROVE_OPTION) {
                        saveWorkspace(new FileWriter(myChooser.getSelectedFile()));
                    }
                }
                catch (Exception exception) {
                    showError(exception.toString());
                }
            }
        });

        menu.add(new JSeparator());
        menu.add(new AbstractAction(myResources.getString("QuitProgram")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return menu;
    }

    /**
     * Sets the command menu option for this program
     * @return JMenu with Command options
     */
    // USED MOSTLY FOR TESTING!
    private JMenu makeCommandMenu() {
        JMenu menu = new JMenu(myResources.getString("CommandMenu"));
        menu.add(new AbstractAction(myResources.getString("ForwardCommand")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    myCurrentCanvas.getTurtle().move(DEFAULT_MOVE_VALUE);
                }
                catch(Exception e1){
                    ErrorBox.showError(Error.NO_WORKSPACE);
                }
            }
        });
        menu.add(new AbstractAction(myResources.getString("BackwardCommand")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    myCurrentCanvas.getTurtle().move(-DEFAULT_MOVE_VALUE);
                }
                catch(Exception e1){
                    ErrorBox.showError(Error.NO_WORKSPACE);
                }
            }
        });
        menu.add(new AbstractAction(myResources.getString("TurnRightCommand")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    myCurrentCanvas.getTurtle().turn(DEFAULT_TURN_VALUE);
                }
                catch(Exception e1){
                    ErrorBox.showError(Error.NO_WORKSPACE);
                }
            }
        });
        menu.add(new AbstractAction(myResources.getString("TurnLeftCommand")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    myCurrentCanvas.getTurtle().turn(-DEFAULT_TURN_VALUE);
                }
                catch(Exception e1){
                    ErrorBox.showError(Error.NO_WORKSPACE);
                }
            }
        });
        menu.add(new JSeparator());
        menu.add(new AbstractAction(myResources.getString("ShowCommand")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                myCurrentCanvas.getTurtle().setHiding(false);
            }
        });
        menu.add(new AbstractAction(myResources.getString("HideCommand")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                myCurrentCanvas.getTurtle().setHiding(true);
            }
        });
        menu.add(new AbstractAction(myResources.getString("PenUpCommand")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                myCurrentCanvas.getTurtle().setPenWriting(false);
            }
        });
        menu.add(new AbstractAction(myResources.getString("PenDownCommand")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                myCurrentCanvas.getTurtle().setPenWriting(true);
            }
        });
        menu.add(new JSeparator());
        menu.add(new AbstractAction(myResources.getString("UndoAction")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO implement undo (maybe)
            }
        });
        return menu;
    }

    /**
     * Establishes the south area of the Window in which the user
     * writes inputs
     * 
     * @return JComponent with the structure for the input area
     */
    private JComponent createInputField() {
        JPanel inputPanel = new JPanel();
        myInputField.addActionListener(myRunCommandListener);
        inputPanel.add(myInputField);
        inputPanel.add(createCommandButton());
        inputPanel.add(createExpandTextButton());
        return inputPanel;
    }

    /**
     * Create a standard button to send the input in the text
     * field to Controller
     */
    protected JButton createCommandButton() {
        JButton button = new JButton(myResources.getString("RunButton"));
        button.addActionListener(myRunCommandListener);
        return button;
    }

    /**
     * Create a standard button to increase the area of the text field
     */
    protected JButton createExpandTextButton() {
        JButton button = new JButton(myResources.getString("Expand"));
        return button;
    }
    
    /**
     * creates listeners for this window
     */
    public void makeListeners(){
        setRunCommandListener();
        setTabListener();
    }
    
    /**
     * set Listener to send the input string to controller whenever the 
     * run button or enter is pressed
     */
    public void setRunCommandListener(){
        myRunCommandListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myController.processCommand(myInputField.getText(), myCurrentCanvas);
                myInputField.setText("");
            }
        };
    }
    
    /**
     * Set the Listener to update the current canvas whenever 
     * tabs are changed
     */
    public void setTabListener(){
        myTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                myCurrentCanvas = (Canvas) myTabbedPane.getSelectedComponent();
                myCurrentCanvas.update();
                myInputField.setText("");
              }
            });
    }
    
   
    /**
     * load a file of variable and command to a current workspace
     */
    private void loadWorkspace (Reader r) {
        try {
            BufferedReader input = new BufferedReader(r);
            String line = input.readLine();
            while (line != null) {
                String[] str = line.split(" ");
                if(str[0].equals(VARIABLE_KEYWORD)) { 
                    myCurrentCanvas.getWorkspace().getVariableMap().put(str[0], Integer.parseInt(str[1]));
                }
                if(str[0].equals(COMMAND_KEYWORD)) { 
                    myCurrentCanvas.getWorkspace().getCommandMap().put(str[0], Integer.parseInt(str[1]));
                }
                System.out.println(str[0] + str[1]);
                line = input.readLine();
            }
        }
        catch (IOException e) {
            showError(e.toString());
        }
    }
    
    /**
     * save the variables and commands from the current workspace to a file
     */
    private void saveWorkspace (Writer w) {
        PrintWriter output = new PrintWriter(w);
        Map<String,Integer> varMap = myCurrentCanvas.getWorkspace().getVariableMap();
        Map<String,Integer> comMap = myCurrentCanvas.getWorkspace().getCommandMap();
        
        for(String varName : varMap.keySet()) {
            output.println(VARIABLE_KEYWORD + varName + " " + varMap.get(varName));
        }
        
        for(String comName : comMap.keySet()) {
            output.println(COMMAND_KEYWORD + comName + " " + varMap.get(comName));
        }
        output.flush();
        output.close();
    }
    
    /**
     * Display any string message in a popup error dialog.
     */
    public void showError (String message) {
        JOptionPane.showMessageDialog(this, message, 
                                      myResources.getString("ErrorTitle"),
                                      JOptionPane.ERROR_MESSAGE);
    }
    
    
    
    
}


