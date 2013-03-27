package parser.commands.bool;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * This implements a less than command.
 * Returns 1 if left parameter is less than the right parameter.
 * Otherwise returns 0.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class LessThan extends BinaryNode {
    /**
     * Creates Less Than Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public LessThan (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        if (getLeft().evaluate(w) < getRight().evaluate(w)) return 1;
        return 0;
    }

}
