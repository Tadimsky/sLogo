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
    
    private static final int PARAM_INDEX = 0;

    public UnaryNode (Deque<SyntaxNode> queue) {        
        this(queue.pop());
    }

    public UnaryNode (SyntaxNode arg) {
        if (arg != null)
        {
            addParameter(PARAM_INDEX, arg);
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
        return getParameter(PARAM_INDEX);
    }

}
