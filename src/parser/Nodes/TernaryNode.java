package parser.nodes;

import java.util.Deque;

public abstract class TernaryNode extends BinaryNode {    
    private SyntaxNode myMiddle;    
    
    public TernaryNode (SyntaxNode left, SyntaxNode middle, SyntaxNode right) {
        super(left, right);
        this.myMiddle = middle;        
    }   
    
    public TernaryNode (Deque<SyntaxNode> queue) {        
        this(queue.pop(), queue.pop(), queue.pop());
    }
    
    public SyntaxNode getMiddle()
    {
        return myMiddle;
    }
    

}
