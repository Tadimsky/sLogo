package parser.nodes;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * This represents the end of a list of parameters.
 * It is added to the stack but never to the tree.
 * Used to keep track of where the end of the list is.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class ParamListEndNode extends TokenNode {

    /**
     * Creates an instance of this node
     * 
     * @param token The token that represents it.
     */
    public ParamListEndNode (String token) {
        super(token);
    }

    /**
     * Create the Node.
     * 
     * @param stack The stack of parameters.
     */
    public ParamListEndNode (Deque<SyntaxNode> stack) {
        this(((TokenNode) stack.pop()).getToken());
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        return 0;
    }

}
