package parser.commands.bool;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * This implements a NOT EQUAL command.
 * Returns 1 if left parameter and right parameter are not equal.
 * Otherwise returns 0.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class NotEqual extends BinaryNode {

    /**
     * Creates Not Equal Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public NotEqual (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        if (getLeft().evaluate(w) != getRight().evaluate(w)) { return 1; }
        return 0;
    }

}
