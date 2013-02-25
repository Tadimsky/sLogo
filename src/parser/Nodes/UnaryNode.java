package parser.nodes;

public abstract class UnaryNode implements ISyntaxNode {
    
    private ISyntaxNode myArgument;    

    public UnaryNode (ISyntaxNode arg) {
        this.myArgument = arg;
    }    
    
    public ISyntaxNode getArgument()
    {
        return myArgument;
    }

}
