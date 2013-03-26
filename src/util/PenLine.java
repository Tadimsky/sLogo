package util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import model.Pen;


/**
 * Contains an instance of a line that will be painted on the canvas
 * 
 * @author Henrique Moraes
 * 
 */
public class PenLine {
    private int myX1;
    private int myX2;
    private int myY1;
    private int myY2;
    private Color myColor;
    private Stroke myStroke;
    public static final Color DEFAULT_COLOR = Color.BLACK;

    public PenLine (Location initialLocation, Location finalLocation,
                    Color color, Stroke stroke) {
        this(initialLocation, finalLocation);
        myColor = color;
        myStroke = stroke;
        Location loc;
    }

    public PenLine (Location initialLocation, Location finalLocation) {
        myX1 = (int) initialLocation.getX();
        myY1 = (int) initialLocation.getY();
        myX2 = (int) finalLocation.getX();
        myY2 = (int) finalLocation.getY();
        myColor = DEFAULT_COLOR;
        myStroke = Pen.DEFAULT_STROKE;
    }

    public Location getInitialLocation () {
        return new Location(myX1, myY1);
    }

    public Location getFinalLocation () {
        return new Location(myX2, myY2);
    }

    public void paint (Graphics2D pen) {
        pen.setColor(myColor);
        pen.setStroke(myStroke);
        pen.drawLine(myX1, myY1, myX2, myY2);
    }
}
