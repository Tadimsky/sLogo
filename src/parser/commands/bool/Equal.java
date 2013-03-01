package parser.commands.bool;

import java.util.Deque;
import parser.Nodes.BinaryNode;
import parser.Nodes.SyntaxNode;
import parser.Nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

/**
 * This implements an EQUAL boolean command.
 * Returns 1 if both parameters have equal value.
 * Otherwise returns 0.
 * 
 * @author Jonathan Schmidt
 *
 */
public class Equal extends BinaryNode {

    public Equal (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        if (getLeft().evaluate(w) == getRight().evaluate(w)) { return 1; }
        return 0;
    }

}
