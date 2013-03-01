package parser.commands.math;

import java.util.Deque;
import parser.Nodes.BinaryNode;
import parser.Nodes.SyntaxNode;
import parser.Nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

/**
 * Implements the Remainder (%) function.
 * Returns the modulus of the left parameter's value and the right parameter's value.
 *  
 * @author Jonathan Schmidt
 *
 */
public class Remainder extends BinaryNode {
   
    private static final String DIVIDE_BY_ZERO = "Cannot divide by zero.";

    public Remainder (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        int l = getLeft().evaluate(w);
        int r = getRight().evaluate(w);
        if (r != 0)
        {
            return l % r;
        }
        throw new InvalidArgumentsException(DIVIDE_BY_ZERO, "");
    }

}
