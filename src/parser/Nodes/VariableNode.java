package parser.nodes;

import parser.nodes.exceptions.InvalidArgumentsException;
import parser.nodes.exceptions.InvalidSemanticsException;
import controller.Workspace;

public class VariableNode extends SimpleNode{
        
    private static final String INVALID_VARIABLE = "The variable {0} does not exist in the current workspace.";
    
    private String myName;     
    
    public VariableNode(String name) throws InvalidSemanticsException
    {   
        if (name.indexOf(':') == 0)
        {
            this.myName = name.substring(1);
        }
        else
        {
            throw new InvalidSemanticsException(INVALID_FORMAT, name);
        }
    }

    @Override
    public int evaluate (Workspace w) {
        
        Integer val = w.getVariable(myName);
        if (val == null)
        {
            throw new InvalidArgumentsException(INVALID_VARIABLE, myName);            
        }
        return val;
    }
    
    public String getName()
    {
        return myName;
    }

}
