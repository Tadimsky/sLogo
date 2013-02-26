package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import util.Location;
import util.PenLine;

/**
 * Pen object that hold the lines that has to be drawn on the canvas
 * @author Henrique Moraes
 *
 */
public class Pen {
    List<PenLine> myLines;
    private boolean penWriting = true;
    private Color myCurrentColor = Color.BLACK;
    
    public void addLine(Location initialLocation, Location finalLocation){
        // for now, when the pen is up, lines simply get eliminated
        // if it is necessary to store them, then we need to devise a 
        // new approach
        if (!penWriting) return;
        myLines.add(new PenLine(initialLocation, finalLocation, myCurrentColor));
    }
    
    public Pen(){
        myLines = new ArrayList<PenLine>();
    }
    
    public void setPenColor(Color color){
        myCurrentColor = color;
    }
    
    public Color getPenColor(){
        return myCurrentColor;
    }
    
    public void paint(Graphics2D pen){
        for(PenLine line : myLines){
            line.paint(pen);
        }
    }
    
    public void setPenWriting(boolean write){
        penWriting = write;
    }
    
    public boolean isPenWriting(){
        return penWriting;
    }
}
