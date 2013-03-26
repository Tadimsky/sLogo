package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

/**
 * Implements the Hide Turtle function.
 * Hides the turtles
 * 
 * @author Jonathan Schmidt
 * 
 */
public class HideTurtle extends BasicControl {

    /**
     * Creates HideTurtle Screen Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public HideTurtle (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        w.getTurtleManager().execute("setHiding", true);
        return 0;
    }
}
