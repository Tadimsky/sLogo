package parser.commands.math;

import java.util.Deque;
import parser.Nodes.SyntaxNode;
import parser.Nodes.UnaryNode;
import parser.Nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

/**
 * Implements the Minus function.
 * Returns the negative value of the parameter's value.
 * 
 * @author Jonathan Schmidt
 *
 */
public class Minus extends UnaryNode {
   
    public Minus (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        return -1 * getArgument().evaluate(w);
    }

}
