package parser.commands.bool;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * This implements an OR command.
 * Returns 1 if left parameter equals 1 or right parameter equals 1.
 * Otherwise returns 0.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Or extends BinaryNode {

    /**
     * Creates Or Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public Or (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        if ((getLeft().evaluate(w) == 1) || (getRight().evaluate(w) == 1)) return 1;
        return 0;
    }

}
