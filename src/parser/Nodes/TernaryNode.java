package parser.nodes;

import java.util.Deque;


/**
 * This is a super class for any command that has 3 parameters.
 * Extends the Binary Node
 * 
 * @author Jonathan Schmidt
 * 
 */
public abstract class TernaryNode extends BinaryNode {

    private static final int MIDDLE_PARAM = 3;

    /**
     * Create the Ternary Node 
     * 
     * @param left Syntax Node 
     * @param middle Syntax Node 
     * @param right Syntax Node 
     */
    public TernaryNode (SyntaxNode left, SyntaxNode middle, SyntaxNode right) {
        super(left, right);
        addParameter(MIDDLE_PARAM, middle);
    }

    /**
     * Creates a Ternary Node from a stack of nodes
     * 
     * @param queue The stack of parameters
     */
    public TernaryNode (Deque<SyntaxNode> queue) {
        this(queue.pop(), queue.pop(), queue.pop());
    }
    /**
     * 
     * @return The middle parameter
     */
    public SyntaxNode getMiddle () {
        return getParameter(MIDDLE_PARAM);
    }

}
