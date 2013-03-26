package view.windows;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import view.Window;
import view.components.InputField;
import controller.Controller;

/**
 * SuperClass for PreviousCommandWindow and CustomCommandWindow,
 * Commands directly clickable to execute, implemented using JList
 * 
 * @author Ziqiang Huang
 *
 */
@SuppressWarnings("serial")
public class LogoListWindow extends JPanel {

    public static final Dimension DEFAULT_DIMENSION =
            new Dimension(Window.TABBED_INFO_WINDOW_DIMENSION.width,
                          Window.TABBED_INFO_WINDOW_DIMENSION.height - 30);

    @SuppressWarnings("rawtypes")
    private JList myCommands;
    protected Vector<String> myCommandsVector;
    private InputField myInputField;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public LogoListWindow (InputField inputField) {
        myInputField = inputField;
        myCommandsVector = new Vector<String>();
        myCommands = new JList(myCommandsVector);
        myCommands.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addListSelectionListener(myCommands);
        add(createCommandsWindow());
        add(createClearPanel());
    }

    /**
     * 
     * @return JButton which clear commands in this window
     */

    protected JPanel createClearPanel () {
        JPanel clearPanel = new JPanel();
        JButton clearButton = new JButton(Controller.RESOURCE.getString("Clear"));
        clearButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void actionPerformed (ActionEvent arg0) {
                myCommandsVector.clear();
                myCommands.setListData(myCommandsVector);
            }
        });
        clearPanel.add(clearButton);
        return clearPanel;
    }

    /**
     * 
     * @return JScrollPane as the Display window
     */

    protected JScrollPane createCommandsWindow () {
        JScrollPane InfoScrollPane = new JScrollPane(myCommands);
        InfoScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        InfoScrollPane.setPreferredSize(DEFAULT_DIMENSION);
        return InfoScrollPane;
    }

    /**
     * Add actionListener to the JList
     * 
     * @param list
     */
    @SuppressWarnings("rawtypes")
    protected void addListSelectionListener (final JList list) {
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                myInputField.setText(((String) list.getSelectedValue()));
                myInputField.getActionListeners()[1].actionPerformed(null);
            }
        });
    }

    /**
     * Add command into this window
     * 
     * @param text
     */
    @SuppressWarnings("unchecked")
    public void addCommand (String text) {
        myCommandsVector.add(text);
        myCommands.setListData(myCommandsVector);
    }




}
