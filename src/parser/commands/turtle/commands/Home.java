package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Home function.
 * Moves the turtles back to their home position
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Home extends BasicControl {

    /**
     * Creates Home Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public Home (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        return w.getTurtleManager().execute("goHome");
    }
}
