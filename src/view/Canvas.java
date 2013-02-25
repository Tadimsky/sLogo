
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import model.Paintable;
import model.Turtle;
import controller.Workspace;

public class Canvas extends JComponent implements Observer{
    private final static Color BACKGROUND_COLOR = Color.WHITE;
    public final static Dimension CANVAS_DIMENSION = new Dimension(700, 600);
    private Workspace myWorkspace;
    private Turtle myTurtle;
    private String myName;
    
    /**
     * Constructor for this class, automatically creates a workspace 
     * and a turtle
     * @param name name of this canvas
     */
    public Canvas(String name){
        this();
        myName = name;
    }
    
    /**
     * Constructor for this class, automatically creates a workspace 
     * and a turtle
     */
    public Canvas(){
        setPreferredSize(CANVAS_DIMENSION);
        myWorkspace = new Workspace();
        myTurtle = new Turtle();
    }
    
    /**
     * Paints the Canvas with its Turtle and lines
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(BACKGROUND_COLOR);
        pen.fillRect(0,0,getWidth(),getHeight());
        myTurtle.paint(pen);
    }
    
    /**
     * @return The Workspace associated to this Canvas
     */
    public Workspace getWorkspace(){
        return myWorkspace;
    }
    
    /**
     * @return The Turtle associated to this Canvas
     */
    public Turtle getTurtle(){
        return myTurtle;
    }
    
    @Override
    public void update (Observable object, Object arg) {
        repaint();   
    }
    
    public void update () {
        repaint();   
    }
    
    public String getName(){
        return myName;
    }

}
