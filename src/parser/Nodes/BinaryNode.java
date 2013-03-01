package parser.nodes;

import java.util.Deque;

/** 
 * This is the base class for a command that has two arguments.
 * 
 * @author Jonathan Schmidt
 *
 */
public abstract class BinaryNode extends ParameterNode {
    private SyntaxNode myLeft;
    private SyntaxNode myRight;
    
    public BinaryNode (SyntaxNode left, SyntaxNode right) {
        this.myLeft = left;
        this.myRight = right;
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
        return myLeft;
    }
    
    /**
     * Returns the right argument.
     * 
     * @return
     */
    public SyntaxNode getRight()
    {
        return myRight;
    }
    

}
