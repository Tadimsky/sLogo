package parser.commands.math;

import java.util.Deque;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

/**
 * Implements the Difference function.
 * Returns the left parameter's value - right parameter's value
 * 
 * @author Jonathan Schmidt
 *
 */
public class Difference extends BinaryNode {
   
    public Difference (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        return getLeft().evaluate(w) - getRight().evaluate(w);
    }

}
