package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import parser.reflection.ReflectionHelper;
import util.Location;
import view.components.Error;
import view.components.ErrorBox;
import view.components.Strokes;


/**
 * Manages all the properties and state of the turtles from a specific workspace
 * 
 * @author Henrique Moraes,Ziqiang Huang, Jonathan Schmidt
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
    private Map<Integer, BufferedImage> myTurtleImages;
    private BufferedImage myImage;
    private int lastIndex = 0;
    private boolean highlightEnabled = false;
    private Stroke myStroke;

    private Turtle myCurrent;

    public TurtleManager () {
        myTurtles = new TreeMap<Integer, Turtle>();
        myActiveTurtles = new TreeMap<Integer, Turtle>();
        myTurtleImages = new TreeMap<Integer, BufferedImage>();
        myStroke = Pen.DEFAULT_STROKE;
        setImageRelative(DEFAULT_IMAGE_PATH);
        activate(lastIndex);
    }

    /**
     * Activates the turtle at the specified index, if turtle does not exist
     * create a new one
     * 
     * @param index index to specify the turtle
     */
    public void activate (int index) {
        if (!myTurtles.containsKey(index)) {
            addNew(index);
        }
        activateTurtle(index);
    }

    /**
     * @param turtles Collection of turtles to be activated
     */
    public void activate (Collection<Integer> turtles) {
        for (Integer i : turtles) {
            activate(i);
        }
    }

    /**
     * 
     * @param addMethod type of activation (Odd or even)
     * @return id of last activated turtle
     */
    private int addActive (int addMethod) {
        int id = 0;
        if (addMethod < 0) {
            clearActive();
            int modulo = addMethod % 2;
            for (int i : myTurtles.keySet()) {
                if (i % 2 == modulo) {
                    activateTurtle(i);
                    id = i;
                }
            }
        }
        return id;
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

    /**
     * sets image Based on a relative path
     * 
     * @param path
     */
    private void setImageRelative (String path) {
        try {
            myImage = ImageIO.read(this.getClass().getResource(path));
        }
        catch (Exception e) {
            ErrorBox.showError(Error.INVALID_IMAGE);
        }
    }

    /**
     * Sets image based on an absolute path
     * 
     * @param path
     */
    public void setImage (String path) {
        try {
            File f = new File(path);
            myImage = ImageIO.read(f);
            for (Turtle t : myActiveTurtles.values()) {
                t.setImage(myImage);
            }
            update();
        }
        catch (Exception e) {
            ErrorBox.showError(Error.INVALID_IMAGE);
        }
    }

    /**
     * Activates Odd indexed turtles
     */
    public int activateOdd () {
        return addActive(ADD_ODD);
    }

    /**
     * Activates Even indexed turtles
     */
    public int activateEven () {
        return addActive(ADD_EVEN);
    }

    /**
     * Activates the turtle specified by the index
     */
    private void activateTurtle (int index) {
        if (!myActiveTurtles.containsKey(index)) {
            myActiveTurtles.put(index, myTurtles.get(index));
        }
    }

    /**
     * Activates the turtle specified by the index
     */
    public void deactivateTurtle (int index) {
        myActiveTurtles.remove(index);
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
                GradientPaint p =
                        new GradientPaint(point.getIntX(), point.getIntY(), INITIAL_GRAD_COLOR,
                                          point.getIntX() + t.getWidth(), point.getIntY() +
                                                                          t.getHeight(),
                                          FINAL_GRAD_COLOR);
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
        if (myActiveTurtles.isEmpty())
            return;
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

    /**
     * Returns a copy of the active turtles
     * 
     * @return
     */
    public List<Integer> copyActive () {
        Integer[] oldTurtles = new Integer[getTurtles().size()];
        oldTurtles = getTurtles().keySet().toArray(oldTurtles);
        return Arrays.asList(oldTurtles);
    }

    /**
     * Execute the provided method name on every active turtle
     * 
     * @param commandName The method to run on the turtle.
     * @param args The arguments that the methods takes.
     * @return T the type of result
     */
    @SuppressWarnings("unchecked")
    public <T> T execute (String commandName, Object ... args) {
        Method m;
        try {
            m = ReflectionHelper.findMethod(Turtle.class, commandName, args);
        }
        catch (NoSuchMethodException e) {
            return null;
        }
        T result = null;
        for (Turtle t : myActiveTurtles.values()) {
            try {
                Object r = m.invoke(t, args);
                result = (T) r;
            }
            catch (Exception e) {
                return null;
            }
        }
        update();
        return result;
    }

    /**
     * @param s stroke type to be set at the turtles
     */
    public void setStrokeType (Strokes s) {
        if (myActiveTurtles.isEmpty()) return;
        for (Turtle t : myActiveTurtles.values()) {
            t.setStrokeType(s);
        }
    }

    /**
     * Adds the specified image to the collection of turtle images
     * 
     * @param index of the image
     * @param imageDir Image directory
     */
    public void addTurtleImage (int index, String imageDir) {

        try {
            File f = new File(imageDir);
            BufferedImage result = ImageIO.read(f);
            myTurtleImages.put(index, result);
        }
        catch (IOException e) {

            ErrorBox.showError(Error.INVALID_IMAGE);

        }
    }

    /**
     * @param shapeid The ID of the shape
     * @return Buffered image associated with the id
     */
    public Object getTurtleImage (int shapeid) {
        return myTurtleImages.get(shapeid);

    }

    /**
     * @param image The Image of that the ID is required for.
     * @return id of this image
     */
    public int getShape (BufferedImage image) {
        if (myTurtleImages.containsValue(image)) {
            for (Entry<Integer, BufferedImage> in : myTurtleImages.entrySet()) {
                if (in.getValue().equals(image)) return in.getKey();
            }
        }
        return -1;
    }
}
