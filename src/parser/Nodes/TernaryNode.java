package parser.nodes;

import java.util.Deque;

public abstract class TernaryNode extends BinaryNode {
    
    private static final int MIDDLE_PARAM = 3;
    
    public TernaryNode (SyntaxNode left, SyntaxNode middle, SyntaxNode right) {
        super(left, right);
        addParameter(MIDDLE_PARAM, middle);
    }   
    
    public TernaryNode (Deque<SyntaxNode> queue) {        
        this(queue.pop(), queue.pop(), queue.pop());
    }
    
    public SyntaxNode getMiddle()
    {
        return getParameter(MIDDLE_PARAM);
    }
    

}
