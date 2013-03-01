package parser.commands.bool;

import java.util.Deque;
import parser.Nodes.BinaryNode;
import parser.Nodes.SyntaxNode;
import parser.Nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

/**
 * This implements a less than command.
 * Returns 1 if left parameter is less than the right parameter.
 * Otherwise returns 0.
 * 
 * @author Jonathan Schmidt
 *
 */
public class LessThan extends BinaryNode {

    public LessThan (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        if ((getLeft().evaluate(w) < getRight().evaluate(w))) { return 1; }
        return 0;
    }

}
