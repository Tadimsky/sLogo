package parser.nodes;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * This represents the end of a list.
 * It is added to the stack but never to the tree.
 * Used to keep track of where the end of a tree is.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class ListEndNode extends TokenNode {

    /**
     * Create the Node with a String value.
     * 
     * @param token The String token
     */
    public ListEndNode (String token) {
        super(token);
    }

    /**
     * Creates a List End Node from a stack of parameters
     * 
     * @param stack The list of parameters
     */
    public ListEndNode (Deque<SyntaxNode> stack) {
        this(((TokenNode) stack.pop()).getToken());
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        return 0;
    }

}
