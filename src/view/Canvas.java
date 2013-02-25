
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import model.Paintable;
import controller.Workspace;

public class Canvas extends JComponent implements Observer{
    private final static Color BACKGROUND_COLOR = Color.WHITE;
    public final static Dimension CANVAS_DIMENSION = new Dimension(700, 600);
    private Workspace myWorkspace;
    
    
    public Canvas(Workspace workspace){
        setPreferredSize(CANVAS_DIMENSION);
        myWorkspace = workspace;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(BACKGROUND_COLOR);
        pen.fillRect(0,0,getWidth(),getHeight());
    }
    
    public Workspace getWorkspace(){
        return myWorkspace;
    }
    
    @Override
    public void update (Observable object, Object arg) {
        paintComponent(this.getGraphics());
        Graphics2D pen = (Graphics2D) this.getGraphics();
        Paintable turtle = (Paintable) object;
        turtle.paint(pen);
    }

}
