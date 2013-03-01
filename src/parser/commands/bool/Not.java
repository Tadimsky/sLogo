package parser.commands.bool;

import java.util.Deque;
import parser.Nodes.SyntaxNode;
import parser.Nodes.UnaryNode;
import parser.Nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

/**
 * This implements a NOT command.
 * Returns 1 if the parameter is 0. 
 * Otherwise returns 0.
 * 
 * @author Jonathan Schmidt
 *
 */
public class Not extends UnaryNode {

    public Not (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        if (getArgument().evaluate(w) == 0) { return 1; }
        return 0;
    }

}
