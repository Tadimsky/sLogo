package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import util.Location;
import util.PenLine;
import view.components.Strokes;


/**
 * Pen object that hold the lines that has to be drawn on the canvas
 * 
 * @author Henrique Moraes
 * 
 */
public class Pen {
    public static final Stroke DEFAULT_STROKE = new BasicStroke(1);

    List<PenLine> myLines;
    private boolean penWriting = true;
    private Color myCurrentColor = Color.BLACK;
    private Stroke myStroke;
    private Strokes myStrokeType;

    public void addLine (Location initialLocation, Location finalLocation) {
        if (!penWriting) return;
        myLines.add(new PenLine(initialLocation, finalLocation, myCurrentColor, myStroke));
    }

    public Pen () {
        myLines = new ArrayList<PenLine>();
        myStroke = DEFAULT_STROKE;
    }

    public void setPenColor (Color color) {
        myCurrentColor = color;
    }

    public Color getPenColor () {
        return myCurrentColor;
    }

    public void paint (Graphics2D pen) {
        for (PenLine line : myLines) {
            line.paint(pen);
        }
    }

    public void setPenWriting (boolean write) {
        penWriting = write;
    }

    /**
     * Sets the stroke for the pen of this turtle
     */
    public void setStroke (Stroke stroke) {
        myStroke = stroke;
    }

    public Stroke getStroke () {
        return myStroke;
    }

    public boolean isPenWriting () {
        return penWriting;
    }

    public void setStrokeType (Strokes s) {
        myStrokeType = s;
    }

    public Strokes getStrokeType () {
        return myStrokeType;
    }
}
