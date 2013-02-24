
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;

public class Canvas extends JComponent implements Observer{
    private final static Color BACKGROUND_COLOR = Color.WHITE;
    public final static Dimension CANVAS_DIMENSION = new Dimension(700, 600);
    
    public Canvas(){
        setPreferredSize(CANVAS_DIMENSION);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0,0,getWidth(),getHeight());
    }
    
    @Override
    public void update (Observable turtle, Object arg1) {
        // TODO Auto-generated method stub
        
    }

}
