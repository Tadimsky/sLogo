package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Left function.
 * Turns the turtle left by the amount specified in the first argument.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Left extends UnaryNode {

    /**
     * Creates Left Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public Left (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int deg = getArgument().evaluate(w);
        return w.getTurtleManager().execute("turn", -deg);
    }

}
