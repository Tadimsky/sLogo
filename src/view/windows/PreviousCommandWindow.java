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
import controller.Controller;


/**
 * Window that show commands previously run in the workspace
 * 
 * @author Ziqiang Huang
 * 
 */

public class PreviousCommandWindow extends JPanel {

    public static final Dimension DEFAULT_DIMENSION =
            new Dimension(Window.TABBED_INFO_WINDOW_DIMENSION.width,
                          Window.TABBED_INFO_WINDOW_DIMENSION.height - 30);

    private JList myPreviousCommands;
    private Vector<String> myCommandsVector;
    private Controller myController;

    public PreviousCommandWindow (Controller c) {
        myController = c;
        myCommandsVector = new Vector<String>();
        myPreviousCommands = new JList(myCommandsVector);
        myPreviousCommands.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addListSelectionListener(myPreviousCommands);
        add(createCommandsWindow());
        add(createClearPanel());
    }

    /**
     * 
     * @return JButton which clear commands in this window
     */

    private JPanel createClearPanel () {
        JPanel clearPanel = new JPanel();
        JButton clearButton = new JButton(Controller.RESOURCE.getString("Clear"));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent arg0) {
                myCommandsVector.clear();
                myPreviousCommands.setListData(myCommandsVector);
            }
        });
        clearPanel.add(clearButton);
        return clearPanel;
    }

    /**
     * 
     * @return JScrollPane as the Display window
     */

    private JScrollPane createCommandsWindow () {
        JScrollPane InfoScrollPane = new JScrollPane(myPreviousCommands);
        InfoScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        InfoScrollPane.setPreferredSize(DEFAULT_DIMENSION);
        return InfoScrollPane;
    }

    /**
     * Add actionListener to the JList
     * 
     * @param list
     */
    private void addListSelectionListener (final JList list) {
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                myController.processCommand((String) list.getSelectedValue());
            }
        });
    }

    /**
     * Add command into this window
     * 
     * @param text
     */
    public void addCommand (String text) {
        myCommandsVector.add(text);
        myPreviousCommands.setListData(myCommandsVector);
    }

}
