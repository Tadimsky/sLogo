package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Towards function.
 * Makes the turtle face towards the specified position.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Towards extends BinaryNode {

    /**
     * Creates Towards Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public Towards (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int x = getLeft().evaluate(w);
        int y = getRight().evaluate(w);
        return w.getTurtleManager().execute("faceTowards", x, y);
    }

}
