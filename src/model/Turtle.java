package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import util.CompositeStroke;
import util.Location;
import util.Vector;
import view.Canvas;
import view.components.Strokes;


/**
 * Creates a Turtle object that moves on the Canvas according to the user's
 * input
 * 
 * @author Henrique Moraes, Ziqiang Huang, Xu Rui
 * 
 */

public class Turtle implements Paintable, IState {
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
        while (wrapOnBoundary(initialPosition, pixels)) {
            move((int) myHeading.getMagnitude());
        }
        return pixels;

    }

    /**
     * Checks for top and bottom bounds
     */
    public boolean wrapOnBoundary (Location initialPosition, int pixels) {
        if (boundsExceeded()) {
            int remainingDistance = 0;
            if (myCenter.getIntY() > Canvas.DEFAULT_CANVAS_DIMENSION.height) {// exceeded bottom
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
                myCenter.setY(Canvas.DEFAULT_CANVAS_DIMENSION.height);

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
        myWidth = myImage.getWidth();
        myHeight = myImage.getHeight();
    }

    public BufferedImage getShape () {
        return myImage;
    }

    public void setStroke (Stroke stroke) {
        myPen.setStroke(stroke);
    }

    public void setPenSize (int pixels) {
        Strokes current = myPen.getStrokeType();
        switch (current) {
            case SOLID:
                myPen.setStroke(new BasicStroke(pixels));
                return;
            case DASHED:
                myPen.setStroke(new BasicStroke(pixels, BasicStroke.CAP_SQUARE,
                                                BasicStroke.JOIN_BEVEL, 1, new float[] { 5f }, 0));
                return;
            case DOTTED:
                myPen.setStroke(new BasicStroke(pixels, BasicStroke.CAP_SQUARE,
                                                BasicStroke.JOIN_BEVEL, 1,
                                                new float[] { .5f, 10f }, 0));
                return;
            case DASH_AND_DOT:
                myPen.setStroke(new BasicStroke(pixels, BasicStroke.CAP_SQUARE,
                                                BasicStroke.JOIN_BEVEL, 1, new float[] { .5f, 10,
                                                                                        7, 10 }, 0));
                return;
            case DOUBLE_LINE:
                myPen.setStroke(new CompositeStroke(4, 1));
                return;
        }

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

    public void setStrokeType (Strokes s) {
        myPen.setStrokeType(s);

    }
}
