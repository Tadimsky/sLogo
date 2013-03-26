package parser.nodes;

import java.util.Deque;


/**
 * The base class for a function that takes only one argument.
 * 
 * @author Jonathan Schmidt
 * 
 */
public abstract class UnaryNode extends ParameterNode {

    private static final int PARAM_INDEX = 0;

    /**
     * Creates the Unary Node
     * 
     * @param queue The queue of parameters.
     */
    public UnaryNode (Deque<SyntaxNode> queue) {
        this(queue.pop());
    }

    /**
     * Create a Unary Node
     * 
     * @param arg The Syntax Node that is this node's argument
     */
    public UnaryNode (SyntaxNode arg) {
        if (arg != null) {
            addParameter(PARAM_INDEX, arg);
        }
    }

    /**
     * Returns the argument for this function.
     * 
     * @return
     */
    public SyntaxNode getArgument () {
        return getParameter(PARAM_INDEX);
    }

}
