package parser.Nodes;

import controller.Workspace;

public abstract class BinaryNode implements ISyntaxNode {
    private ISyntaxNode myLeft;
    private ISyntaxNode myRight;
    
    public BinaryNode (ISyntaxNode left, ISyntaxNode right) {
        this.myLeft = left;
        this.myRight = right;
    }

    @Override
    public abstract int evaluate (Workspace w);
    
    public ISyntaxNode getLeft()
    {
        return myLeft;
    }
    
    public ISyntaxNode getRight()
    {
        return myRight;
    }
    

}
