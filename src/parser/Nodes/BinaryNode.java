package parser.nodes;

public abstract class BinaryNode implements ISyntaxNode {
    private ISyntaxNode myLeft;
    private ISyntaxNode myRight;
    
    public BinaryNode (ISyntaxNode left, ISyntaxNode right) {
        this.myLeft = left;
        this.myRight = right;
    }    
    
    public ISyntaxNode getLeft()
    {
        return myLeft;
    }
    
    public ISyntaxNode getRight()
    {
        return myRight;
    }
    

}
