package parser.nodes;

import java.util.Deque;
import parser.nodes.exceptions.InvalidArgumentsException;

/**
 * The base class for a fuunction that takes only one argument.
 * 
 * @author Jonathan Schmidt
 *
 */
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
    
    /**
     * Returns the argument for this function.
     *     
     * @return
     */
    public SyntaxNode getArgument()
    {
        return myArgument;
    }

}
