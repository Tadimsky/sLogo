package parser.nodes;

import parser.nodes.exceptions.InvalidSemanticsException;
import controller.Workspace;

public class ConstantNode extends SimpleNode {
    // {int}
    
    private int myValue;
    
    public ConstantNode (int val) {
        this.myValue = val;
    }
    
    public ConstantNode (String val) {
        try 
        {
            myValue = Integer.parseInt(val);
        }
        catch (NumberFormatException e)
        {            
            throw new InvalidSemanticsException(INVALID_FORMAT, val);
        }
    }

    @Override
    public int evaluate (Workspace w) {        
        return this.myValue;
    }   
}
