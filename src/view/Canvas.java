
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import model.Paintable;
import model.Turtle;
import controller.Workspace;

/**
 * Canvas in which the interface gets painted. It displays the turtle
 * and its respective lines
 * @author Henrique Moraes
 * 
 */

public class Canvas extends JComponent implements Observer{
    private final static Color BACKGROUND_COLOR = Color.WHITE;
    public final static Dimension CANVAS_DIMENSION = new Dimension(850, 600);
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
        checkTurtleBounds();
        repaint();   
    }
    
    //WARNING THESE IF STATEMENTS ARE HORRIBLE, THIS SOLUTION SHOULD
    // BE TEMPORARY IF ANYONE THINK OF SOMETHING EASIER PLEASE IMPLEMENT
    private void checkTurtleBounds(){
        Rectangle turtleBounds = myTurtle.getBounds();
        
        if(turtleBounds.getMaxX()>=CANVAS_DIMENSION.width || 
                turtleBounds.getMinX()<=0)
            myTurtle.wrapOnX();
        if(turtleBounds.getMinY()>=CANVAS_DIMENSION.height || 
                turtleBounds.getMaxY()<=0)
            myTurtle.wrapOnY();     
    }
    
    public void update () {
        repaint();   
    }
    
    public String getName(){
        return myName;
    }

}
