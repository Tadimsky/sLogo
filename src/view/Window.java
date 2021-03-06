package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import view.components.ErrorBox;
import view.windows.CustomCommandWindow;
import view.windows.InformationView;
import view.windows.PreviousCommandWindow;
import view.windows.VariablesWindow;
import controller.Controller;
import controller.Workspace;


/**
 * Window that holds all the user interface. Sends commands to controller
 * in case user inputs anything
 * 
 * @author Henrique Moraes, Ziqiang
 * 
 */
@SuppressWarnings("serial")
public class Window extends JFrame implements WorkspaceHandler {
    private final static int GRAY_TONE = 230;
    public final static Color INFO_BACKGROUND_COLOR = new Color(GRAY_TONE, GRAY_TONE, GRAY_TONE);
    private static final String WORKSPACE_NAME = "Workspace ";
    public static final Dimension TABBED_INFO_WINDOW_DIMENSION = new Dimension(220, 600);
    public static final Dimension WINDOW_DIMENSION = new Dimension(1030, 735);
    private static final String INFO_TAB_NAME = "Information";
    private static final String VARIABLE_TAB_NAME = "Variables";
    private static final String PRECOMMAND_TAB_NAME = "Previous Commands";
    private static final String CUSCOMMAND_TAB_NAME = "Custom Commands";

    private int workspaceIndex = 1;
    private ResourceBundle myResource;

    private Canvas myCurrentCanvas;
    private InformationView myInfoView;
    private VariablesWindow myVariablesWindow;
    private PreviousCommandWindow myPreCommandsWindow;
    private CustomCommandWindow myCusCommandsWindow;
    private JTabbedPane myTabbedPane;
    private JTabbedPane myTabbedInfoWindow;
    private JTextField myInputField;

    public Window (JTextField field, JMenuBar bar) {
        myInputField = field;
        setTitle("SLogo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        }

        myResource = Controller.RESOURCE;

        myInfoView = new InformationView();
        myVariablesWindow = new VariablesWindow();
        myPreCommandsWindow = new PreviousCommandWindow(myInputField);
        myCusCommandsWindow = new CustomCommandWindow(myInputField);
        myTabbedInfoWindow = new JTabbedPane();
        myTabbedInfoWindow.add(INFO_TAB_NAME, makeInformationView());
        myTabbedInfoWindow.add(VARIABLE_TAB_NAME, myVariablesWindow);
        myTabbedInfoWindow.add(PRECOMMAND_TAB_NAME, myPreCommandsWindow);
        myTabbedInfoWindow.add(CUSCOMMAND_TAB_NAME, myCusCommandsWindow);

        myTabbedPane = new JTabbedPane();
        ErrorBox.setWindow(this);
        makeListeners();

        getContentPane().add(myTabbedPane, BorderLayout.CENTER);
        getContentPane().add(myTabbedInfoWindow, BorderLayout.EAST);
        getContentPane().add(createInputField(), BorderLayout.SOUTH);

        setJMenuBar(bar);
        createWorkspace();

        setPreferredSize(WINDOW_DIMENSION);
        pack();
        setVisible(true);
    }

    /**
     * Sets the necessary observers for the turtle of this Workspace
     * 
     * @param turtle Observed turtle
     */
    private void setObservers (Workspace workspace) {
        workspace.addTurtleObserver(myCurrentCanvas);
        workspace.addTurtleObserver(myInfoView);
        workspace.addObserver(myVariablesWindow);
        workspace.addObserver(myPreCommandsWindow);
        workspace.addObserver(myCusCommandsWindow);
        updateObservers();
    }

    /**
     * Creates a new workspace and sets the associated canvas to it
     * on the tabbed pane
     */
    @Override
    public void createWorkspace () {
        Workspace workspace = new Workspace(WORKSPACE_NAME + workspaceIndex);
        workspaceIndex++;
        myCurrentCanvas = workspace.getCanvas();
        myTabbedPane.addTab(workspace.getName(), myCurrentCanvas);
        myTabbedPane.setSelectedComponent(myCurrentCanvas);
        setObservers(workspace);
        updateObservers();
    }

    /**
     * Updates both the Canvas and InfoView about the changes on the turtle
     */
    private void updateObservers () {
        Workspace workspace = (Workspace) myCurrentCanvas.getPaintableResource();
        workspace.update();
        workspace.updateInformation();
    }

    /**
     * @return Currently selected Canvas
     */
    public Canvas getCanvas () {
        return myCurrentCanvas;
    }

    /**
     * Creates a scrollable informationView
     * 
     * @return Information View of this program
     */
    private JComponent makeInformationView () {
        JScrollPane InfoScrollPane = new JScrollPane(myInfoView);
        InfoScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        return InfoScrollPane;
    }

    /**
     * Establishes the south area of the Window in which the user
     * writes inputs
     * 
     * @return JComponent with the structure for the input area
     */
    private JComponent createInputField () {
        JPanel inputPanel = new JPanel();
        inputPanel.add(myInputField);
        inputPanel.add(createCommandButton());
        inputPanel.add(createExpandTextButton());
        return inputPanel;
    }

    /**
     * Create a standard button to send the input in the text
     * field to Controller
     */
    protected JButton createCommandButton () {
        JButton button = new JButton(myResource.getString("RunButton"));
        button.addActionListener(myInputField.getActionListeners()[0]);
        return button;
    }

    /**
     * Create a standard button to increase the area of the text field
     */
    protected JButton createExpandTextButton () {
        JButton button = new JButton(myResource.getString("Expand"));
        return button;
    }

    /**
     * creates listeners for this window
     */
    public void makeListeners () {
        setTabListener();
        setInfoTabListener();
    }

    /**
     * Set the Listener to update the current canvas whenever
     * tabs are changed
     */
    public void setTabListener () {
        myTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged (ChangeEvent changeEvent) {
                myCurrentCanvas = (Canvas) myTabbedPane.getSelectedComponent();
                updateObservers();
                myInputField.setText("");
            }
        });
    }

    /**
     * Set the Listener to update the information panel whenever
     * tabs are changed
     */
    public void setInfoTabListener () {
        myTabbedInfoWindow.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged (ChangeEvent changeEvent) {
                updateObservers();
            }
        });
    }

    @Override
    public Workspace getWorkspace () {
        return (Workspace) myCurrentCanvas.getPaintableResource();
    }
}
