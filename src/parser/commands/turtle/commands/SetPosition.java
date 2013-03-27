package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Set Position function.
 * Moves the turtles to the specified X and Y position
 * 
 * @author Jonathan Schmidt
 * 
 */
public class SetPosition extends BinaryNode {

    /**
     * Creates Set Position Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public SetPosition (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int x = getLeft().evaluate(w);
        int y = getRight().evaluate(w);
        return w.getTurtleManager().execute("setPosition", x, y);
    }

}
