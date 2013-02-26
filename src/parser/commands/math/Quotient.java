package parser.commands.math;

import java.util.Deque;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

public class Quotient extends BinaryNode {
   
    private static final String DIVIDE_BY_ZERO = "Cannot divide by zero.";

    public Quotient (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        int l = getLeft().evaluate(w);
        int r = getRight().evaluate(w);
        if (r != 0)
        {
            return l/r;
        }
        
        throw new InvalidArgumentsException(DIVIDE_BY_ZERO, "");
    }

}