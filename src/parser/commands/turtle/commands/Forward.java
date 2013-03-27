package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Forward function.
 * Moves the turtles forwards an amount specified by the first argument.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Forward extends UnaryNode {

    /**
     * Creates Forward Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public Forward (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int amnt = getArgument().evaluate(w);
        return w.getTurtleManager().execute("move", amnt);
    }

}
