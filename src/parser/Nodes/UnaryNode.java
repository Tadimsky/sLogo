package parser.nodes;

import java.util.Deque;
import parser.nodes.exceptions.InvalidArgumentsException;

public abstract class UnaryNode extends ParameterNode {
    
    private SyntaxNode myArgument;  
    
    public UnaryNode (Deque<SyntaxNode> queue) {        
        this(queue.pop());
    }

    public UnaryNode (SyntaxNode arg) {
        if (arg != null)
        {
            myArgument = arg;
        }
        else
        {
            throw new InvalidArgumentsException(InvalidArgumentsException.INCORRECT_NUMBER_ARGS, "");
        }
    }       
    
    public SyntaxNode getArgument()
    {
        return myArgument;
    }

}
