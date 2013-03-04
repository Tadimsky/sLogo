package parser.nodes;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.exceptions.InvalidArgumentsException;
import parser.nodes.exceptions.InvalidSemanticsException;

/**
 * Represents a variable in the tree.
 * Gets the value from the workspace when it is evaluated.
 * 
 * @author Jonathan Schmidt
 *
 */
public class VariableNode extends SimpleNode{
        
    private static final String INVALID_VARIABLE = "The variable {0} does not exist in the current workspace.";
    
    private String myName;     
    
    public VariableNode(String name) throws InvalidSemanticsException
    {   
        if (name.indexOf(':') == 0)
        {
            this.myName = name;
        }
        else
        {
            throw new InvalidSemanticsException(INVALID_FORMAT, name);
        }
    }
    
    public VariableNode (Deque<SyntaxNode> stack)
    {        
        this(((TokenNode)stack.pop()).getToken());
    } 

    @Override
    public int evaluate (IParserProvider w) {
        
        Integer val = w.getVariables().getVariable(myName);
        if (val == null)
        {
            throw new InvalidArgumentsException(INVALID_VARIABLE, myName);            
        }
        return val;
    }
    
    /**
     * Returns the name of the variable.
     * 
     * @return
     */
    public String getName()
    {
        return myName;
    }

}
