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
 * @author Henrique Moraes, Ziqiang Huang
 * 
 */
public class Canvas extends JComponent implements Observer {
    private final static Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    public final static Dimension DEFAULT_CANVAS_DIMENSION = new Dimension(830, 575);
    public static final Color DEFAULT_BACKGROUND = Color.white;
    
    private Paintable myPaintingResource;
    private Color myBackgroundColor;

    /**
     * Constructor for this class, automatically creates a workspace
     * and a turtle
     */
    public Canvas (Paintable workspace) {
        setPreferredSize(DEFAULT_CANVAS_DIMENSION);
        myBackgroundColor = DEFAULT_BACKGROUND;
        myPaintingResource = workspace;
        setBackground(DEFAULT_BACKGROUND_COLOR);
    }

    /**
     * Paints the Canvas with its Turtle and lines
     */
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.setColor(myBackgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        myPaintingResource.paint((Graphics2D) g);
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

    /**
     * @param c Background color to be set on this canvas
     */
    public void setBackgroundColor(Color c) {
        myBackgroundColor = c;
        repaint();
    }
}
