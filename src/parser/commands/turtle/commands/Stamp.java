package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

/**
 * Implements the Stamp function.
 * Stamps an image of the turtle at the current position.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Stamp extends BasicControl {

    /**
     * Creates Stamp Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public Stamp (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        w.getTurtleManager().execute("stamp");
        return 0;
    }
}
