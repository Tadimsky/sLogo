package parser.nodes;

import java.util.Deque;

public abstract class BinaryNode extends SyntaxNode {
    private SyntaxNode myLeft;
    private SyntaxNode myRight;
    
    public BinaryNode (SyntaxNode left, SyntaxNode right) {
        this.myLeft = left;
        this.myRight = right;
    }   
    
    public BinaryNode (Deque<SyntaxNode> queue) {        
        this(queue.pop(), queue.pop());
    }
    
    public SyntaxNode getLeft()
    {
        return myLeft;
    }
    
    public SyntaxNode getRight()
    {
        return myRight;
    }
    

}
