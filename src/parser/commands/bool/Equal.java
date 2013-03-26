package parser.commands.bool;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * This implements an EQUAL boolean command.
 * Returns 1 if both parameters have equal value.
 * Otherwise returns 0.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Equal extends BinaryNode {

    /**
     * Creates Equal Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public Equal (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        if (getLeft().evaluate(w) == getRight().evaluate(w)) { return 1; }
        return 0;
    }

}
