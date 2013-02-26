package util;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import view.Canvas;

public class Location extends Point2D.Double {
    private static final Dimension myCanvasDimension = Canvas.CANVAS_DIMENSION;
    private static final double X_OFFSET = Canvas.CANVAS_DIMENSION.getWidth()/2;
    private static final double Y_OFFSET = Canvas.CANVAS_DIMENSION.getHeight()/2;

    /**
     * Create a location at the origin.
     */
    public Location () {
        super(X_OFFSET, Y_OFFSET);
    }

    /**
     * Create a location at given (x, y) coordinates.
     */
    public Location (double x, double y) {
        super(x + X_OFFSET, -y + Y_OFFSET);
    }

    /**
     * Create a location that is identical to the given other location.
     */
    public Location (Location source) {
        super(source.getX(), source.getY());
    }

    /**
     * Reset this location to origin.
     */
    public void reset () {
        setLocation(X_OFFSET, Y_OFFSET);
    }
    
    public int getIntX(){
        return (int) super.getX();
    }
    
    public int getIntY(){
        return (int) super.getY();
    }

    /**
     * Move this location by given vector.
     * 
     * @see java.awt.Point#translate(int, int)
     */
    public void translate (Vector amount) {
        setLocation(getX() + amount.getXChange(), getY() + amount.getYChange());
    }
    
    /**
     * Move this location by given coordinates.
     */
    public void translate (double x, double y) {
        super.x = super.x + x;
        super.y = super.y - y;
    }

    /**
     * Returns a vector that is the difference between this location and
     * the given other location.
     */
    public Vector difference (Point2D other) {
        return new Vector(this, other);
    } 
    
    public void setX(double x){
        super.x = x;
    }
    
    public void setY(double y){
        super.y = y;
    }
}