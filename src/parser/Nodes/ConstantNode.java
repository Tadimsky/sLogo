package parser.Nodes;

import controller.Workspace;

public class ConstantNode implements ISyntaxNode {
    // {int}
    
    private int myValue;
    
    public ConstantNode (int val) {
        this.myValue = val;
    }
    
    public ConstantNode (String val) {
        this.myValue  = Integer.parseInt(val);        
    }

    @Override
    public int evaluate (Workspace w) {        
        return this.myValue;
    }

}
