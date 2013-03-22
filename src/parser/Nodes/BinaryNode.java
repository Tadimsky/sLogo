package parser.nodes;

import java.util.Deque;

/** 
 * This is the base class for a command that has two arguments.
 * 
 * @author Jonathan Schmidt
 *
 */
public abstract class BinaryNode extends ParameterNode {
    
    
    private static final int LEFT_PARAM = 0;
    private static final int RIGHT_PARAM = 1;

    public BinaryNode (SyntaxNode left, SyntaxNode right) {
        addParameter(LEFT_PARAM, left);
        addParameter(RIGHT_PARAM, right);
    }   
    
    public BinaryNode (Deque<SyntaxNode> queue) {        
        this(queue.pop(), queue.pop());
    }
    
    /**
     * Returns the left argument.
     * 
     * @return
     */
    public SyntaxNode getLeft()
    {
        return getParameter(LEFT_PARAM);
    }
    
    /**
     * Returns the right argument.
     * 
     * @return
     */
    public SyntaxNode getRight()
    {
        return getParameter(RIGHT_PARAM);
    }
    

}
