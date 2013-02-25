package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Observable;
import view.Location;
import view.Paintable;
import view.Vector;

public class Turtle extends Observable implements Paintable{
    private Location myLocation;
    private Vector myHeading;
    private static final Dimension TURTLE_DIMENSION = new Dimension(20,10);
    
    public Turtle () {
        myLocation = new Location();
        myHeading = new Vector(270,0);
        this.setChanged();
    }
    
    public void move(int pixels){
        myHeading.setMagnitude(pixels);
        myLocation.translate(myHeading);
        updatePosition();
    }
    
    public void updatePosition(){
        setChanged();
        notifyObservers();
    }

    @Override
    public void paint (Graphics2D pen) {
        //I dont know why, on the instance it is created, this method is 
        //not painting the turtle
        pen.setColor(Color.GREEN);
        System.out.println("X "+myLocation.getX()+" Y "+myLocation.getY());
        pen.fillOval((int) myLocation.getX(), (int) myLocation.getY(), 
                     TURTLE_DIMENSION.width,TURTLE_DIMENSION.height); 
    }
    

}
