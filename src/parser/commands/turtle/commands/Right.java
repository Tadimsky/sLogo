package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Right function.
 * Turns the turtle right by the amount specified in the first argument.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Right extends UnaryNode {

    /**
     * Creates Right Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public Right (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int deg = getArgument().evaluate(w);
        return w.getTurtleManager().execute("turn", deg);
    }

}
