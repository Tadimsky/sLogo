package parser.Nodes;

import controller.Workspace;

public class VariableNode implements ISyntaxNode {
    // :{name}
    
    private String myName;     
    
    public VariableNode(String name)
    {
        this.myName = name;
    }

    @Override
    public int evaluate (Workspace w) {        
        // TODO: throw a no such variable exception            
        return w.getVariable(myName);  
    }

}
