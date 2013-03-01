package parser.commands.other;

import java.util.Deque;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

/**
 * Implements the Repeat control structure.
 * Runs the specified command list a certain number of times.
 * The number of times to run is provided by the left node's value.
 * 
 * @author Jonathan Schmidt
 *
 */
public class Repeat extends BinaryNode {

    public Repeat (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        int times = getLeft().evaluate(w);
        int val = 0;
        for (int i = 0; i < times; i++)
        {
            val = getRight().evaluate(w);            
        }
        return val;
    }

}
