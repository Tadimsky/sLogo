
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
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import view.labels.TempNode;
import model.IState;
import view.labels.*;

public class InformationView extends JPanel implements Observer {
    private final static int GRAY_TONE = 230;
    private final static Color BACKGROUND_COLOR = new Color(GRAY_TONE,GRAY_TONE,GRAY_TONE);
    private final static Dimension VIEW_DIMENSION = new Dimension(200, 600);
    private static final String TITLE = "Turtle Information";
    
    private static final String[] LABEL_DESCRIPTIONS = 
        {"Absolute Heading: ","X Position: ", "Y Position: ",
         "Pen Down? ","Is Hiding? "};
    private static final TempNode[] NODES = {new TempHead(), new TempLocX(),new TempLocY(), 
                                       new TempPenUp(), new TempHiding()};
    
    private List<LogoLabel> myLabelList;
    
    public InformationView(){
        setSize(VIEW_DIMENSION);
        setPreferredSize(VIEW_DIMENSION);
        
        myLabelList = new ArrayList<LogoLabel>();
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createMatteBorder(0, 3, 5, 0, Color.GRAY));
          
        setLabels();
         
    }
    
    public void setLabels(){
        TitleLabel title = new TitleLabel(TITLE);
        myLabelList.add(title);
        add(title, title.getGridBagConstraints());
        
        for (int i = 0; i< LABEL_DESCRIPTIONS.length; i++){
            InformationLabel label = new InformationLabel(i+1,LABEL_DESCRIPTIONS[i],NODES[i]);
            myLabelList.add(label);
            add(label,label.getGridBagConstraints());
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0,0,getWidth(),getHeight());

    }

    @Override
    public void update (Observable object, Object arg) {
         IState turtle = (IState) object;
         for (int i = 0; i< myLabelList.size(); i++){
             LogoLabel j = myLabelList.get(i);
             j.setText(turtle);
         }
    }

}