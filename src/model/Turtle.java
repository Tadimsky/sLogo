package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
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
 * @author Henrique Moraes, Ziqiang Huang, Xu Rui
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
    

   public int move(int pixels){
        myHeading.setMagnitude(pixels);
        Location initialPosition = new Location(myCenter);
      	System.out.println(myCenter.getIntX());
      	System.out.println(myCenter.getIntY());
      	myCenter.translate(myHeading);
      	myPen.addLine(initialPosition, myCenter);
      	if (wrapOnBoundary(initialPosition, pixels)){
      		//System.out.printf("my X position is %d\n", myCenter.getIntX());
        	//System.out.printf("my Y position is %d\n", myCenter.getIntY());
      		Location newInitialPosition = new Location(myCenter);
      		myCenter.translate(myHeading);
      		//System.out.printf("my X position is now %d\n", myCenter.getIntX());
        	//System.out.printf("my Y position is now %d\n", myCenter.getIntY());
      		myPen.addLine(newInitialPosition, myCenter);
      	}

        
        update();
        return pixels;
    }
    
    /**
     * Checks for top and bottom bounds
     */
    public boolean wrapOnBoundary(Location initialPosition, int pixels){    	
        if (myCenter.getIntY() > Canvas.CANVAS_DIMENSION.height){
        	System.out.println("exceeded bottom bound");
        	int distanceMoved = (int) Vector.distanceBetween(initialPosition, new Location(myCenter.getIntX(),Canvas.CANVAS_DIMENSION.height)); 
        	int remainingDistance = pixels - distanceMoved;
        	myHeading.setMagnitude(remainingDistance);
        	myCenter.setY(0);
        	return true;
        	
        }
        if (myCenter.getIntY() < 0){
        	System.out.println("exceeded top bound");
        	System.out.println(initialPosition);
        	System.out.println((new Location(myCenter.getIntX(),0)).getVisualLocation());
        	int distanceMoved = (int) Vector.distanceBetween(initialPosition, (new Location(myCenter.getIntX(),0)).getVisualLocation()); 
        	//System.out.printf("boundary distance moved is %d\n", distanceMoved);
        	int remainingDistance = pixels - distanceMoved;
        	//System.out.printf("remaining distance is %d\n", remainingDistance);
        	myHeading.setMagnitude(remainingDistance);
        	myCenter.setY(Canvas.CANVAS_DIMENSION.height-myHeight);//move turtle to bottom bound
        	return true;
        }
        if (myCenter.getIntX() > Canvas.CANVAS_DIMENSION.width ){
        	System.out.println("exceeded right bound");
        	int distanceMoved = (int) Vector.distanceBetween(initialPosition, (new Location(Canvas.CANVAS_DIMENSION.width, myCenter.getIntY())).getVisualLocation()); 
        	int remainingDistance = pixels - distanceMoved;
        	myHeading.setMagnitude(remainingDistance);
        	myCenter.setX(0 + myWidth/2);
        	return true;
        }
        if (myCenter.getIntX() < myWidth/2){ 
        	System.out.println("exceeded left bound");
        	int distanceMoved = (int) Vector.distanceBetween(initialPosition, (new Location(myWidth/2, myCenter.getIntY())).getVisualLocation()); 
        	int remainingDistance = pixels - distanceMoved;
        	myHeading.setMagnitude(remainingDistance);        	
        	myCenter.setX(Canvas.CANVAS_DIMENSION.width);
        	return true;
        }
        return false;
    }
    
    /**
     * Turns the turtle by the number of degrees
     * @param degrees degrees to turn
     */
    public int turn(int degrees){
        myHeading.turn(degrees);
        update();
        return degrees;
    }
    
    /**
     * @return Rectangle containing the bounds of this object
     */
    public Rectangle getBounds(){
        Location point = getPaintingPoint();
        return new Rectangle(point.getIntX(),point.getIntY(),myWidth,myHeight); 
    }
    
    /**
     * Turns the turtle to the specified heading in degrees. Returns the number of degrees it turned by.
     * @param degrees Absolute heading to turn to. this number is 
     * on the perspective of the user
     */
    public int setHeading(int  degrees){
        Vector absolute = new Vector(viewerDegreeConversion(degrees), 0);
        int  angle = (int) myHeading.getAngleBetween(absolute);
        myHeading.turn(angle);
        update();
        return angle;
    }
    
    /**
     * Turns the turtle to face towards the specified location. Returns the number of degrees the turtle turned by.
     * @param point
     * @return
     */
    public int faceTowards(int x, int y){  	
        Location toFace = new Location(x,y);
        Vector toTurn = new Vector(myCenter, toFace);
        int angle = (int) myHeading.getAngleBetween(toTurn);
        myHeading.turn(angle);
        update();
        return angle;
    }
    
    /**
     * Moves the turtle to the a point on the screen. Returns the number of pixels moved.

     * @param point
     * @return
     */
    public int setPosition(int x, int y){
    	this.faceTowards(x, y);
    	Location point = new Location(x,y);
    	int  distanceToMove = (int) Vector.distanceBetween(point, myCenter);
    	Vector toMove = new Vector(myHeading.getDirection(), distanceToMove);
    	myCenter.translate(toMove);
    	//this.move(distanceToMove);
    	return distanceToMove;
    }
    /**
     * Moves the turtle to the center of the screen. Returns the number of pixels moved.
     */
    public int goHome(){
    	int distanceMoved = this.setPosition(0,0);
    	setHeading(90);
    	return distanceMoved;
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
        return myCenter.getVisualLocation();
    }
    

    @Override
    public int getHeading () {
        return viewerDegreeConversion((int) myHeading.getDirection());
    }
    
    /**
     * Switches the values of the degrees because java works with a different
     * perspective of the subject
     * @param degrees degrees to invert perspective
     * @return degrees of Absolute Heading from the opposite perspective
     * if the program uses its heading to display to the user, the degrees
     * are converted to the user's perspective and vice-versa
     */
    private int  viewerDegreeConversion(int  degrees){
        return (360 - degrees) % 360;
    }
    
 
    
    /**
     * Checks for left and right bounds
     */
    
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

    /**
     * Erases turtle's trails and sends it to the home position
     * 
     * @return the distance turtle moved
     */
    public int clear() {
        int distanceMoved = goHome();
        myPen.myLines.clear();
        System.out.println(distanceMoved);
        return distanceMoved;
    }
}
