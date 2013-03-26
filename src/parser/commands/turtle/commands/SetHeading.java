package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Set Heading function.
 * Makes the turtle face the specified number of degrees.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class SetHeading extends UnaryNode {

    /**
     * Creates Set Heading Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public SetHeading (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int deg = getArgument().evaluate(w);
        return w.getTurtleManager().execute("setHeading", deg);
    }

}
