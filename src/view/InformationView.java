
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import model.IState;

public class InformationView extends JPanel implements Observer {
    
    private final static Color BACKGROUND = Color.WHITE;
    private final static Dimension VIEW_DIMENSION = new Dimension(200, 600);
    private static final String[] LABEL_DESCRIPTIONS = 
        {"Absolute Heading: ","X Position: ", "Y Position: ",
         "Pen Down? ","Is Hiding? "};
    private List<JLabel> myLabelList;
    
    public InformationView(){
        setSize(VIEW_DIMENSION);
        setPreferredSize(VIEW_DIMENSION);
        myLabelList = new ArrayList<JLabel>();
        setLayout(new GridBagLayout());
        
        
        for (int i = 0; i< LABEL_DESCRIPTIONS.length; i++){
            JLabel label = new JLabel(LABEL_DESCRIPTIONS[i]);
            label.setHorizontalTextPosition(SwingConstants.LEFT);
            myLabelList.add(label);
            add(label,setGridPosition(i));
        }
         
    }
    
    private GridBagConstraints setGridPosition(int y){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = y;
        c.ipady = 20;
        //c.anchor = GridBagConstraints.;
        //c.fill = c.BOTH;
        return c;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(BACKGROUND);
        g.fillRect(0,0,getWidth(),getHeight());

    }

    @Override
    public void update (Observable object, Object arg) {
         IState turtle = (IState) object;
         for (int i = 0; i< myLabelList.size(); i++){
             JLabel j = myLabelList.get(i);
             j.setText(LABEL_DESCRIPTIONS[i]+turtle.getAbsoluteHeading());
         }
    }

}