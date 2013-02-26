package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import util.Location;
import util.Vector;

public class Turtle extends Observable implements Paintable, IState{
    private Location myLocation;
    private Vector myHeading;
    private Pen myPen;
    public static final String IMAGE_PATH = "/images/turtle.png";
    //private BufferedImage myImage;
    private ImageIcon myImage;
    private int myWidth;
    private int myHeight;
    
    // Trying to make image rotate... buffered image is not showing on screen
    public Turtle () {
        myPen = new Pen();
        myLocation = new Location(0,0);
        myHeading = new Vector(300,0);
//        try {
//            myImage = ImageIO.read(this.getClass().getResource(IMAGE_PATH));
//        }
//        catch (IOException e) { };
        myImage = new ImageIcon(this.getClass().getResource(IMAGE_PATH));
        myWidth = myImage.getIconWidth();
        myHeight = myImage.getIconHeight();
    }

    public void move(int pixels){
        myHeading.setMagnitude(pixels);
        Location initialPosition = new Location(myLocation);
        myLocation.translate(myHeading);
        Location finalPosition = new Location(myLocation);
        myPen.addLine(initialPosition, finalPosition);

        update();
    }
    
    public void update(){
        setChanged();
        notifyObservers();
    }
    
    private Location getCenter(){
        return new Location(myLocation.getX()+myWidth/2, 
                            myLocation.getY()+myHeight/2);
    }

    @Override
    public void paint (Graphics2D pen) {
        Location center = getCenter();
//        AffineTransform tx = 
//                AffineTransform.getRotateInstance(Math.toRadians(myHeading.getDirection()),
//                                                  center.getX(), center.getY());
//        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        //ImageIcon icon = new ImageIcon(op.filter(myImage, null));
        pen.drawImage(myImage.getImage(), (int) myLocation.getX(), 
                      (int) myLocation.getY(), null);
        myPen.paint(pen);
    }

    @Override
    public Location getLocation () {
        return myLocation;
    }

    @Override
    public Vector getHeading () {
        return myHeading;
    }
    
    public void setPenWriting(boolean write){
        myPen.setPenWriting(write);
    }
    
    public boolean isPenWriting(){
        return myPen.isPenWriting();
    }
}
