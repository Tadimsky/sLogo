package parser.nodes;

import java.util.Deque;
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
    
    public ConstantNode (Deque<SyntaxNode> stack)
    {        
        this(((TokenNode)stack.pop()).getToken());
    }   

    @Override
    public int evaluate (Workspace w) {        
        return this.myValue;
    }   
}
