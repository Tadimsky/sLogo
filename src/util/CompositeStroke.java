package util;

import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.Stroke;


/**
 * Creates a composite stroke, useful for double lines
 * 
 * @author Henrique Moraes
 * 
 */
public class CompositeStroke implements Stroke {
    private Stroke stroke1, stroke2;

    public CompositeStroke (int thickness1, int thickness2) {
        stroke1 = new BasicStroke(thickness1);
        stroke2 = new BasicStroke(thickness2);
    }

    @Override
    public Shape createStrokedShape (Shape shape) {
        return stroke2.createStrokedShape(stroke1.createStrokedShape(shape));
    }
}
