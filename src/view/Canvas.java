package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import model.Paintable;


/**
 * Canvas in which the interface gets painted. It displays the turtle
 * and its respective lines
 * 
 * @author Henrique Moraes
 * 
 */
public class Canvas extends JComponent implements Observer {
    private final static Color BACKGROUND_COLOR = Color.WHITE;
    public final static Dimension CANVAS_DIMENSION = new Dimension(830, 590);
    private Paintable myPaintingResource;

    /**
     * Constructor for this class, automatically creates a workspace
     * and a turtle
     */
    public Canvas (Paintable workspace) {
        setPreferredSize(CANVAS_DIMENSION);
        myPaintingResource = workspace;
    }

    /**
     * Paints the Canvas with its Turtle and lines
     */
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(BACKGROUND_COLOR);
        pen.fillRect(0, 0, getWidth(), getHeight());
        myPaintingResource.paint(pen);
    }

    /**
     * Repaints all the components associated to this view
     */
    @Override
    public void update (Observable object, Object arg) {
        repaint();
    }
    
    /**
     * @return the base informational components to paint on this canvas
     * such as the workspace, as a Paintable
     */
    public Paintable getPaintableResource(){
        return myPaintingResource;
    }

}
