package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Backward function.
 * Moves the turtles backwards an amount specified by the first argument.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Backward extends UnaryNode {

    /**
     * Creates Backward Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public Backward (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int amnt = getArgument().evaluate(w);
        return w.getTurtleManager().execute("move", -amnt);
    }

}
