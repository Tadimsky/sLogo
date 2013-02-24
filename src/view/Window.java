package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import controller.Workspace;


public class Window extends JFrame {
    private static final String USER_DIR = "user.dir";
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private static final int INPUT_FIELD_SIZE = 70;
    private JFileChooser myChooser;
    private ResourceBundle myResources;
    // private Workspace myCurrentWorkspace;

    // Create Listeners
    private ActionListener myActionListener;
    private KeyListener myKeyListener;
    private MouseListener myMouseListener;

    public Window() {
        setTitle("SLogo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        myChooser = new JFileChooser(System.getProperties().getProperty(USER_DIR));
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");

        createListeners();

        getContentPane().add(makeWorkspace(), BorderLayout.CENTER);
        getContentPane().add(createInputField(), BorderLayout.SOUTH);

        setJMenuBar(makeJMenuBar());

        pack();

        setVisible(true);

    }

    private JComponent makeWorkspace() {
        JPanel result = new JPanel();
        Workspace w = new Workspace();
        result.add(w.getMyCanvas());
        JScrollPane InfoScrollPane = new JScrollPane(w.getMyInformationView());
        InfoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        result.add(InfoScrollPane);
        return result;
    }

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
        menu.add(new AbstractAction(myResources.getString("OpenFile")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int response = myChooser.showOpenDialog(null);
                    if (response == myChooser.APPROVE_OPTION) {
                        // TODO open file with file reader
                    }
                }
                catch (Exception exception) {
                    // TODO implement exception to messageBox
                }
            }
        });

        menu.add(new AbstractAction(myResources.getString("SaveFile")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // TODO implement saving file (no idea how to do)
                }
                catch (Exception exception) {
                    // TODO implement exception to messageBox
                }
            }
        });

        menu.add(new AbstractAction(myResources.getString("CloseFile")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // TODO implement saving file (no idea how to do)
                }
                catch (Exception exception) {
                    // TODO implement exception to messageBox
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

    private JMenu makeCommandMenu() {
        JMenu menu = new JMenu(myResources.getString("CommandMenu"));
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
        inputPanel.add(new JLabel("Command>"));
        inputPanel.add(createTextInput());
        inputPanel.add(createCommandButton());
        inputPanel.add(createExpandTextButton());
        return inputPanel;
    }

    private JTextField createTextInput() {
        final JTextField textField = new JTextField(INPUT_FIELD_SIZE);

        // Get the command after press enter
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = textField.getText();
                // test
                System.out.println(command);
            }
        });

        return textField;
    }

    /**
     * Create a standard button to send the input in the text
     * field to Controller
     */
    protected JButton createCommandButton() {
        JButton button = new JButton(myResources.getString("RunButton"));
        button.addActionListener(myActionListener);
        button.addMouseListener(myMouseListener);
        return button;
    }

    /**
     * Create a standard button to increase the area of the text field
     */
    protected JButton createExpandTextButton() {
        JButton button = new JButton(myResources.getString("Expand"));
        button.addActionListener(myActionListener);
        button.addMouseListener(myMouseListener);
        return button;
    }

    private void createListeners() {
        myActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO implement action
            }
        };

        myKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // TODO implement key
            }
        };

        myMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO implement
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO implement
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
            }
        };
    }

    // Use to test the view
    public static void main(String[] args) {
        new Window();
    }
}
