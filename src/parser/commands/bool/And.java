package parser.commands.bool;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * This implements an AND boolean command.
 * Returns 1 if both parameters are equal to 1.
 * Otherwise returns 0.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class And extends BinaryNode {

    public And (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        if ((getLeft().evaluate(w) == 1) && (getRight().evaluate(w) == 1)) return 1;
        return 0;
    }

}
