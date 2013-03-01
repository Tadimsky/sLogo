package parser.commands.bool;

import java.util.Deque;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

/**
 * This implements a greater than command.
 * Returns 1 if left parameter is greater than the right parameter.
 * Otherwise returns 0.
 * 
 * @author Jonathan Schmidt
 *
 */
public class GreaterThan extends BinaryNode {

    public GreaterThan (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        if ((getLeft().evaluate(w) > getRight().evaluate(w))) { return 1; }
        return 0;
    }

}
