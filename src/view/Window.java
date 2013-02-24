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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
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
    
    private JTextField myCommandField;

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
                        echo(new FileReader(myChooser.getSelectedFile()));
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
                        echo(new FileWriter(myChooser.getSelectedFile()));
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
        myCommandField = new JTextField(INPUT_FIELD_SIZE);

        // Get the command after press enter
        myCommandField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = myCommandField.getText();
                // test
                System.out.println(command);
            }
        });

        return myCommandField;
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
    
   
    /**
     * Echo data read from reader to display
     */
    private void echo (Reader r) {
        try {
            String s = "";
            BufferedReader input = new BufferedReader(r);
            String line = input.readLine();
            while (line != null) {
                s += line + "\n";
                line = input.readLine();
            }
            myCommandField.setText(s);
        }
        catch (IOException e) {
            showError(e.toString());
        }
    }
    
    /**
     * Echo display to writer
     */
    private void echo (Writer w) {
        PrintWriter output = new PrintWriter(w);
        output.println(myCommandField.getText());
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


    // Use to test the view
    public static void main(String[] args) {
        new Window();
    }
}
