package parser.commands.turtle.queries;

import java.awt.Color;
import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Pen Color function.
 * Returns the color index that the turtle's pen is drawing in.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class PenColor extends BasicControl {

    /**
     * Creates Pen Color Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public PenColor (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        Color c = w.getTurtleManager().<Color> execute("getPenColor");
        return w.getColors().getColorID(c);
    }
}
