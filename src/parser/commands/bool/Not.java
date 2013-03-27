package parser.commands.bool;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * This implements a NOT command.
 * Returns 1 if the parameter is 0.
 * Otherwise returns 0.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Not extends UnaryNode {

    /**
     * Creates Not Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public Not (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        if (getArgument().evaluate(w) == 0) return 1;
        return 0;
    }

}
