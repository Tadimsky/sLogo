package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import javax.imageio.ImageIO;
import util.Location;
import util.Vector;
import view.Canvas;
import view.components.ErrorBox;
import view.components.Error;

/**
 * Creates a Turtle object that moves on the Canvas according to the user's
 * input
 * @author Henrique Moraes
 *
 */
public class Turtle extends Observable implements Paintable, IState{
    private Location myCenter;
    private Vector myHeading;
    private Pen myPen;
    public static final String IMAGE_PATH = "/images/turtleSideways.png";
    private BufferedImage myImage;
    private int myWidth;
    private int myHeight;
    private boolean amHiding = false;
    
    public Turtle () {
        myPen = new Pen();
        myCenter = new Location(0,0);
        myHeading = new Vector(270,0);
        try {
            myImage = ImageIO.read(this.getClass().getResource(IMAGE_PATH));
        }
        catch (Exception e) { ErrorBox.showError(Error.INVALID_IMAGE); };
        myWidth = myImage.getWidth();
        myHeight = myImage.getHeight();
        
    }

    /**
     * Moves this turtle by the amount of pixels on the direction it is 
     * currently heading
     * @param pixels pixels to move
     */
    public void move(int pixels){
        myHeading.setMagnitude(pixels);
        Location initialPosition = new Location(myCenter);
        myCenter.translate(myHeading);
        Location finalPosition = new Location(myCenter);
        myPen.addLine(initialPosition, finalPosition);

        update();
    }
    
    /**
     * Turns the turtle by the number of degrees
     * @param degrees degrees to turn
     */
    public void turn(int degrees){
        myHeading.turn(degrees);
        update();
    }
    
    /**
     * @return Rectangle containing the bounds of this object
     */
    public Rectangle getBounds(){
        Location point = getPaintingPoint();
        return new Rectangle(point.getIntX(),point.getIntY(),myWidth,myHeight); 
    }
    
    /**
     * Turns this turtle to the specified absolute heading
     * @param degrees Absolute heading to turn to. this number is 
     * on the perspective of the user
     */
    public void turnTo(int degrees){
        Vector absolute = new Vector(viewerDegreeConversion(degrees), 0);
        double angle = myHeading.getAngleBetween(absolute);
        myHeading.turn(angle);
        update();
    }
    
    /**
     * Updates this object by notifying its observers
     */
    public void update(){
        setChanged();
        notifyObservers();
    }
    
    /**
     * @return the location reference of the upper left corner of this image
     */
    private Location getPaintingPoint(){
        Location point = new Location(myCenter);
        point.translate(-myWidth/2,myHeight/2);
        return point;
    }

    @Override
    public void paint (Graphics2D pen) {
        myPen.paint(pen);
        if (amHiding) return;
        Location point = getPaintingPoint();
        AffineTransform tx = 
                AffineTransform.getRotateInstance(Math.toRadians(myHeading.getDirection()),
                                                  myWidth/2, myHeight/2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        pen.drawImage(op.filter(myImage,null),point.getIntX(), 
                       point.getIntY(), null);
        
    }

    @Override
    public Location getCenter () {
        return myCenter;
    }
    
    @Override
    public double getAbsoluteHeading () {
        return viewerDegreeConversion(myHeading.getDirection());
    }
    
    /**
     * Switches the values of the degrees because java works with a different
     * perspective of the subject
     * @param degrees degrees to invert perspective
     * @return degrees of Absolute Heading from the opposite perspective
     * if the program uses its heading to display to the user, the degrees
     * are converted to the user's perspective and vice-versa
     */
    private double viewerDegreeConversion(double degrees){
        return (360 - degrees) % 360;
    }
    
    public void wrapOnX(){
        if(myCenter.getX() < Canvas.CANVAS_DIMENSION.width/2)
            myCenter.setX(Canvas.CANVAS_DIMENSION.width);
        else
            myCenter.setX(0);
    }
    
    public void wrapOnY(){
        if(myCenter.getY() < Canvas.CANVAS_DIMENSION.height/2)
            myCenter.setY(Canvas.CANVAS_DIMENSION.height);
        else
            myCenter.setY(0);
    }
    
    public void setPenWriting(boolean write){
        myPen.setPenWriting(write);
    }
    
    @Override
    public boolean isPenWriting(){
        return myPen.isPenWriting();
    }
    
    public void setHiding(boolean hide){
        amHiding = hide;
        update();
    }
    
    @Override
    public boolean isHiding(){
        return amHiding;
    }
    
    public void setColor(Color color){
        myPen.setPenColor(color);
    }
}
