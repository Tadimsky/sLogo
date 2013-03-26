package parser.commands.turtle.queries;

import java.awt.image.BufferedImage;
import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

/**
 * Implements the Shape function.
 * Returns the index of the shape that the turtle is.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Shape extends BasicControl {

    /**
     * Creates Shape Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public Shape (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        BufferedImage i = w.getTurtleManager().<BufferedImage> execute("getShape");
        return w.getTurtleManager().getShape(i);
    }
}
