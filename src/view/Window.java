package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Observable;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import view.components.Error;
import view.components.ErrorBox;
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
    private static final int INPUT_FIELD_SIZE = 70;
    private static final String WORKSPACE_NAME = "Workspace ";
    
    private int workspaceIndex = 1;
    private ResourceBundle myResource;

    private ActionListener myRunCommandListener;

    private Controller myController;
    
    private Canvas myCurrentCanvas;
    private InformationView myInfoView;
    private JTabbedPane myTabbedPane;
    private InputField myInputField;

    public Window(Controller control) {
    
        myController = control;
        
        setTitle("SLogo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        myResource = myController.getResource();

        myInfoView = new InformationView();
        myTabbedPane = new JTabbedPane();
        myInputField = new InputField(INPUT_FIELD_SIZE);
        ErrorBox.setWindow(this);
        makeListeners();
        
        getContentPane().add(myTabbedPane, BorderLayout.CENTER);
        getContentPane().add(makeInformationView(), BorderLayout.EAST);
        getContentPane().add(createInputField(), BorderLayout.SOUTH);

        setJMenuBar(myController.createJMenuBar());
        
        pack();

        setVisible(true);
        createWorkspace();
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
     * Creates a new workspace and sets the associated canvas to it
     * on the tabbed pane
     */
    public void createWorkspace () {
        Workspace workspace = new Workspace(WORKSPACE_NAME + workspaceIndex);
        workspaceIndex++;
        myCurrentCanvas = new Canvas(workspace);
        myTabbedPane.addTab(workspace.getName(), myCurrentCanvas);
        myTabbedPane.setSelectedComponent(myCurrentCanvas);
        setObservers(workspace.getTurtle());
        myCurrentCanvas.repaint();
    }

    /**
     * @return Currently selected Canvas
     */
    public Canvas getCanvas () {
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
        JButton button = new JButton(myResource.getString("RunButton"));
        button.addActionListener(myRunCommandListener);
        return button;
    }

    /**
     * Create a standard button to increase the area of the text field
     */
    protected JButton createExpandTextButton() {
        JButton button = new JButton(myResource.getString("Expand"));
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
                myCurrentCanvas.repaint();
                myInputField.setText("");
              }
            });
    }
}


