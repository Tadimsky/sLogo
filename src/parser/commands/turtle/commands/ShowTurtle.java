package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

/**
 * Implements the Show Turtle function.
 * Shows the turtle.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class ShowTurtle extends BasicControl {

    /**
     * Creates Show Turtle Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public ShowTurtle (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        w.getTurtleManager().execute("setHiding", false);
        return 1;
    }
}
