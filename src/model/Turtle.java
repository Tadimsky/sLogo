package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.imageio.ImageIO;
import util.Location;
import util.Vector;
import view.Canvas;
import view.components.Error;
import view.components.ErrorBox;


/**
 * Creates a Turtle object that moves on the Canvas according to the user's
 * input
 * 
 * @author Henrique Moraes, Ziqiang Huang, Xu Rui
 * 
 */

public class Turtle extends Observable implements Paintable, IState {
    private Location myCenter;
    private Vector myHeading;
    private Pen myPen;
    public static final String IMAGE_PATH = "/images/turtleSideways.png";
    private BufferedImage myImage;
    private int myWidth;
    private int myHeight;
    private boolean amHiding = false;
    private List<Location> myStamp;

    public Turtle () {
        myPen = new Pen();
        myCenter = new Location(0, 0);
        myHeading = new Vector(270, 0);
        try {
            myImage = ImageIO.read(this.getClass().getResource(IMAGE_PATH));
        }
        catch (Exception e) {
            ErrorBox.showError(Error.INVALID_IMAGE);
        }
        ;
        myWidth = myImage.getWidth();
        myHeight = myImage.getHeight();
        myStamp = new ArrayList<Location>();
    }

    /**
     * Moves this turtle by the amount of pixels on the direction it is
     * currently heading
     * 
     * @param pixels pixels to move
     */
    public int move (int pixels) {
        myHeading.setMagnitude(pixels);
        Location initialPosition = new Location(myCenter);
        myCenter.translate(myHeading);
        myPen.addLine(initialPosition, myCenter);
        if (wrapOnBoundary(initialPosition, pixels)) {
            move((int) myHeading.getMagnitude());
        }
        update();
        return pixels;
    }

    /**
     * Checks for top and bottom bounds
     */
    public boolean wrapOnBoundary (Location initialPosition, int pixels) {
        if (boundsExceeded()) {
            int remainingDistance = 0;
            if (myCenter.getIntY() > Canvas.DEFAULT_CANVAS_DIMENSION.height) { // exceeded bottom
                                                                               // bound
                remainingDistance =
                        pixels -
                                (int) Vector
                                        .distanceBetween(initialPosition,
                                                         (new Location(
                                                                       myCenter.getIntX(),
                                                                       Canvas.DEFAULT_CANVAS_DIMENSION.height))
                                                                 .getVisualLocation());
                myCenter.setY(0);
            }
            if (myCenter.getIntY() < 0) { // exceeded top bound
                remainingDistance =
                        pixels -
                                (int) Vector.distanceBetween(initialPosition,
                                                             (new Location(myCenter.getIntX(), 0))
                                                                     .getVisualLocation());
                // myCenter.setY(Canvas.CANVAS_DIMENSION.height);//move turtle to bottom bound
                /*
                 * System.out.println(myCenter.getIntX());
                 * System.out.println((Canvas.CANVAS_DIMENSION.width-myCenter.getIntX()));
                 * System.out.println((Canvas.CANVAS_DIMENSION.width-myCenter.getIntX())*Math.tan(Math
                 * .PI/4));
                 * double num = Canvas.CANVAS_DIMENSION.width -
                 * ((Canvas.CANVAS_DIMENSION.width-myCenter.getIntX())*Math.tan(Math.PI/4)+Canvas.
                 * CANVAS_DIMENSION.height)
                 * /Math.tan(Math.PI/4);
                 * 
                 * myCenter.setX(62);
                 * System.out.println(num);
                 * //myCenter.setX(300);
                 */
                myCenter.setLocation(getComplementPoint(myCenter, myHeading));

                System.out.println(myCenter.getIntX());
            }
            if (myCenter.getIntX() > Canvas.DEFAULT_CANVAS_DIMENSION.width) { // exceeded right
                                                                              // bound
                remainingDistance =
                        pixels -
                                (int) Vector
                                        .distanceBetween(initialPosition,
                                                         (new Location(
                                                                       Canvas.DEFAULT_CANVAS_DIMENSION.width,
                                                                       myCenter.getIntY()))
                                                                 .getVisualLocation());
                myCenter.setX(0 + myWidth / 2);
            }
            if (myCenter.getIntX() < myWidth / 2) { // exceeded left bound
                remainingDistance =
                        pixels -
                                (int) Vector.distanceBetween(initialPosition,
                                                             (new Location(myWidth / 2, myCenter
                                                                     .getIntY()))
                                                                     .getVisualLocation());
                myCenter.setX(Canvas.DEFAULT_CANVAS_DIMENSION.width);
            }
            myHeading.setMagnitude(remainingDistance);
            return true;
        }
        return false;
    }

    public Location getComplementPoint (Location loc, Vector heading) {
        Vector oppHeading = new Vector(heading.getDirection() + 180, heading.getMagnitude());
        Location tempLoc = new Location(loc);
        tempLoc.translate(oppHeading);
        return tempLoc;
    }

    /**
     * Checks if turtle exceeds bounds.
     * 
     * @return
     */
    public boolean boundsExceeded () {
        return (myCenter.getIntY() > Canvas.DEFAULT_CANVAS_DIMENSION.height ||
                myCenter.getIntY() < 0 ||
                myCenter.getIntX() > Canvas.DEFAULT_CANVAS_DIMENSION.width || myCenter.getIntX() < myWidth / 2);
    }

    /**
     * Turns the turtle by the number of degrees
     * 
     * @param degrees degrees to turn
     */
    public int turn (int degrees) {
        myHeading.turn(degrees);
        update();
        return degrees;
    }

    /**
     * @return Rectangle containing the bounds of this object
     */
    public Rectangle getBounds () {
        Location point = getPaintingPoint();
        return new Rectangle(point.getIntX(), point.getIntY(), myWidth, myHeight);
    }

    /**
     * Turns the turtle to the specified heading in degrees. Returns the number of degrees it turned
     * by.
     * 
     * @param degrees Absolute heading to turn to. this number is
     *        on the perspective of the user
     */
    public int setHeading (int degrees) {
        Vector absolute = new Vector(viewerDegreeConversion(degrees), 0);
        int angle = (int) myHeading.getAngleBetween(absolute);
        myHeading.turn(angle);
        update();
        return angle;
    }

    /**
     * Turns the turtle to face towards the specified location. Returns the number of degrees the
     * turtle turned by.
     * 
     * @param point
     * @return
     */
    public int faceTowards (int x, int y) {
        Location toFace = new Location(x, y);
        Vector toTurn = new Vector(myCenter, toFace);
        int angle = (int) myHeading.getAngleBetween(toTurn);
        myHeading.turn(angle);
        update();
        return angle;
    }

    /**
     * Moves the turtle to the a point on the screen. Returns the number of pixels moved.
     * 
     * @param point
     * @return
     */
    public int setPosition (int x, int y) {
        faceTowards(x, y);
        Location point = new Location(x, y);
        int distanceToMove = (int) Vector.distanceBetween(point, myCenter);
        Vector toMove = new Vector(myHeading.getDirection(), distanceToMove);
        myCenter.translate(toMove);
        // this.move(distanceToMove);
        return distanceToMove;
    }

    /**
     * Moves the turtle to the center of the screen. Returns the number of pixels moved.
     */
    public int goHome () {
        int distanceMoved = setPosition(0, 0);
        setHeading(90);
        return distanceMoved;
    }

    /**
     * Updates this object by notifying its observers
     */
    public void update () {
        setChanged();
        notifyObservers();
    }

    /**
     * @return the location reference of the upper left corner of this image
     */
    Location getPaintingPoint () {
        Location point = new Location(myCenter);
        point.translate(-myWidth / 2, myHeight / 2);
        return point;
    }

    @Override
    public void paint (Graphics2D pen) {
        myPen.paint(pen);
        if (amHiding) return;
        Location point = getPaintingPoint();
        AffineTransformOp op = transform();
        paintTurtle(pen, point, op);
        paintStamp(pen);
    }

    public void paintTurtle (Graphics2D pen, Location point, AffineTransformOp op) {
        pen.drawImage(op.filter(myImage, null), point.getIntX(),
                      point.getIntY(), null);
    }

    private void paintStamp (Graphics2D pen) {
        AffineTransformOp op = transform();
        for (Location point : myStamp) {
            paintTurtle(pen, point, op);
        }

    }

    public AffineTransformOp transform () {
        AffineTransform tx =
                AffineTransform.getRotateInstance(Math.toRadians(myHeading.getDirection()),
                                                  myWidth / 2, myHeight / 2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op;
    }

    @Override
    public Location getCenter () {
        return myCenter.getVisualLocation();
    }

    @Override
    public int getHeading () {
        return viewerDegreeConversion((int) myHeading.getDirection());
    }

    /**
     * Switches the values of the degrees because java works with a different
     * perspective of the subject
     * 
     * @param degrees degrees to invert perspective
     * @return degrees of Absolute Heading from the opposite perspective
     *         if the program uses its heading to display to the user, the degrees
     *         are converted to the user's perspective and vice-versa
     */
    private int viewerDegreeConversion (int degrees) {
        return (360 - degrees) % 360;
    }

    /**
     * Checks for left and right bounds
     */

    public void setPenWriting (boolean write) {
        myPen.setPenWriting(write);
    }

    @Override
    public boolean isPenWriting () {
        return myPen.isPenWriting();
    }

    public void setHiding (boolean hide) {
        amHiding = hide;
        update();
    }
    
    public void setHiding (int hide) {
        if (hide == 1)
        {
            setHiding(true);
        }
        else
        {
            setHiding(false);
        }
    }

    @Override
    public boolean isHiding () {
        return amHiding;
    }

    public void setColor (Color color) {
        myPen.setPenColor(color);
    }

    /**
     * Erases turtle's trails and sends it to the home position
     * 
     * @return the distance turtle moved
     */
    public int clear () {
        int distanceMoved = goHome();
        myPen.myLines.clear();
        System.out.println(distanceMoved);
        return distanceMoved;
    }

    public int getWidth () {
        return myWidth;
    }

    public int getHeight () {
        return myHeight;
    }

    public void setImage (BufferedImage myImage2) {
        myImage = myImage2;

    }

    public void setStroke (Stroke stroke) {
        myPen.setStroke(stroke);
    }

    public Color getPenColor () {
        return myPen.getPenColor();
    }

    public void stamp () {
        myStamp.add(getPaintingPoint());
    }

    public void clearStamp () {
        myStamp.clear();

    }
}
