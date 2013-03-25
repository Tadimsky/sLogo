package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import util.Location;
import view.components.Error;
import view.components.ErrorBox;


/**
 * Manages all the properties and state of the turtles from a specific workspace
 * 
 * @author Henrique Moraes
 * 
 */
public class TurtleManager extends Observable implements Paintable {
    public static final String DEFAULT_IMAGE_PATH = "/images/turtleSideways.png";
    public static final Color INITIAL_GRAD_COLOR = new Color(255, 170, 0);
    public static final Color FINAL_GRAD_COLOR = new Color(255, 255, 0);
    private static final Stroke ACTIVE_STROKE = new BasicStroke(3, BasicStroke.CAP_ROUND,
                                                                BasicStroke.JOIN_ROUND);

    private static final int ADD_ODD = -1;
    private static final int ADD_EVEN = -2;

    private Map<Integer, Turtle> myActiveTurtles;
    private Map<Integer, Turtle> myTurtles;
    private BufferedImage myImage;
    private int lastIndex = 0;
    private boolean highlightEnabled = false;
    private Stroke myStroke;

    private Turtle myCurrent;
    private Iterator<Turtle> myIterator;

    public TurtleManager () {
        myTurtles = new TreeMap<Integer, Turtle>();
        myActiveTurtles = new TreeMap<Integer, Turtle>();
        myStroke = Pen.DEFAULT_STROKE;
        setImage(DEFAULT_IMAGE_PATH);
        addNew(lastIndex);
    }

    /**
     * Activates the turtle at the specified index, if turtle does not exist
     * create a new one
     * 
     * @param index index to specify the turtle
     */
    public void activate (int index) {
        if (!myTurtles.containsKey(index))
        {
            addNew(index);
        }
        activateTurtle(index);
    }

    private void addActive (int addMethod) {
        if (addMethod < 0) {
            clearActive();
            int modulo = addMethod % 2;
            for (int i : myTurtles.keySet()) {
                if (i % 2 == modulo) activateTurtle(i);
            }
        }
    }

    /**
     * Updates this object by notifying its observers and painting the turtles
     */
    public void update () {
        setChanged();
        notifyObservers();
    }

    /**
     * Adds a new turtle with the specified index
     * 
     * @param index index o the turtle
     */
    public void addNew (int index) {
        Turtle turtle = createTurtle(index);
        turtle.setStroke(myStroke);
        myTurtles.put(index, turtle);
        // TODO not rely on last index
        lastIndex = myTurtles.size();
    }

    /**
     * Clears the active turtles for this workspace;
     */
    public void clearActive () {
        myActiveTurtles.clear();
    }

    /**
     * Creates a new turtle with parameters specified by this manager
     * 
     * @return Turtle with manager settings
     */
    private Turtle createTurtle (int index) {
        Turtle t = new Turtle();
        t.setImage(myImage);
        return t;
    }

    public void setImage (String path) {
        try {
            myImage = ImageIO.read(this.getClass().getResource(path));
        }
        catch (Exception e) {
            ErrorBox.showError(Error.INVALID_IMAGE);
        }
        ;
    }

    /**
     * Activates Odd indexed turtles
     */
    public void activateOdd () {
        addActive(ADD_ODD);
    }

    /**
     * Activates Even indexed turtles
     */
    public void activateEven () {
        addActive(ADD_EVEN);
    }

    /**
     * Activates the turtle specified by the index
     */
    private void activateTurtle (int index) {
        if (!myActiveTurtles.containsKey(index))
            myActiveTurtles.put(index, myTurtles.get(index));
        myIterator = myActiveTurtles.values().iterator();
    }

    /**
     * Activates the turtle specified by the index
     */
    public void deactivateTurtle (int index) {
        myActiveTurtles.remove(index);
        myIterator = myActiveTurtles.values().iterator();
    }

    /**
     * Paints each turtle and stamp from the workspace
     * and highlights the active ones
     */
    @Override
    public void paint (Graphics2D pen) {
        for (Integer i : myTurtles.keySet()) {
            Turtle t = myTurtles.get(i);
            if (myActiveTurtles.containsKey(i) && highlightEnabled) {
                Location point = t.getPaintingPoint();
                GradientPaint p = new
                        GradientPaint(point.getIntX(), point.getIntY(), INITIAL_GRAD_COLOR,
                                      point.getIntX() + t.getWidth(),
                                      point.getIntY() + t.getHeight(), FINAL_GRAD_COLOR);
                pen.setPaint(p);
                pen.setStroke(ACTIVE_STROKE);
                pen.drawRect(point.getIntX(), point.getIntY(), t.getWidth(), t.getHeight());
            }
            t.paint(pen);
        }
    }

    /**
     * @param active boolean representing whether currently active turtles
     *        should be highlighted or not
     */
    public void setHighlighted (boolean active) {
        highlightEnabled = active;
        update();
    }

    /**
     * @param stroke Stroke to be set on each active turtle
     */
    public void setStroke (Stroke stroke) {
        myStroke = stroke;
        if (myActiveTurtles.isEmpty()) return;
        for (Turtle t : myActiveTurtles.values()) {
            t.setStroke(stroke);
        }
    }

    /**
     * @return active boolean representing whether currently active turtles
     *         should be highlighted or not
     */
    public boolean getHighlighted () {
        return highlightEnabled;
    }

    /**
     * @return Currently active turtles associated with this manager
     */
    public Map<Integer, Turtle> getTurtles () {
        return myActiveTurtles;
    }

    /**
     * @return All the turtles associated with this manager
     */
    public Map<Integer, Turtle> getAllTurtles () {
        return myTurtles;
    }

    
    // TODO: Combine the Iterator and Current turtle together.
    /**
     * @return the current Turtle
     */
    public Turtle getCurrent () {
        return myCurrent;
    }

    /**
     * @param current the Turtle to be set as current
     */
    public void setCurrent (Turtle current) {
        myCurrent = current;
    }

    public Iterator<Turtle> iterator ()
    {
        return myIterator;
    }
}
