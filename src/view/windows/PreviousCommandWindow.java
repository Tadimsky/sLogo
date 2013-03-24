package view.windows;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import controller.Controller;
import view.Window;

public class PreviousCommandWindow extends JPanel {
    
    public static final Dimension DEFAULT_DIMENSION = 
            new Dimension(Window.TABBED_INFO_WINDOW_DIMENSION.width-50,
                          Window.TABBED_INFO_WINDOW_DIMENSION.height);
    
    private JList<String> myPreviousCommands;
    private Vector<String> myCommandsVector;
    private Controller myController;
    
    public PreviousCommandWindow(Controller c) {
        myController = c;
        myCommandsVector = new Vector<String>();
        myPreviousCommands = new JList<String>(myCommandsVector);
        myPreviousCommands.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addListSelectionListner(myPreviousCommands);
        JScrollPane InfoScrollPane = new JScrollPane(myPreviousCommands);
        InfoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        InfoScrollPane.setPreferredSize(DEFAULT_DIMENSION);
        add(InfoScrollPane);
    }
    
    private void addListSelectionListner(final JList<String> list) {
        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                myController.processCommand(list.getSelectedValue());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
  
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
 
            }
        });
        
    }

    public void addCommand(String text) {
        myCommandsVector.add(text);
        myPreviousCommands.setListData(myCommandsVector);
         
    }

}
