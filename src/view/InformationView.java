
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextArea;

public class InformationView extends JTextArea implements Observer {
    
    private final static Color BACKGROUND = Color.WHITE;
    private final static Dimension VIEW_DIMENSION = new Dimension(200, 600);
    
    public InformationView(){
        setSize(VIEW_DIMENSION);
        setPreferredSize(VIEW_DIMENSION);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(BACKGROUND);
        g.fillRect(0,0,getWidth(),getHeight());

    }

    @Override
    public void update (Observable o, Object arg) {
       // TODO 
        
    }

}