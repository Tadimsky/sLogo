package parser.commands.other;

import java.util.Deque;
import controller.Workspace;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.VariableNode;
import parser.nodes.exceptions.InvalidArgumentsException;

/**
 * Implements the Make function.
 * Creates a variable and sets its value to the provided value.
 * 
 * @author Jonathan Schmidt
 *
 */
public class Make extends BinaryNode {        
    private static final String PARAMETER_TYPE = "The first parameter must be in the format :<var_name>.";

    public Make (Deque<SyntaxNode> queue) {
        super(queue); 
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {        
        // This is required
        if (!(getLeft() instanceof VariableNode))
        {
            throw new InvalidArgumentsException(PARAMETER_TYPE, this.getClass().getCanonicalName());
        }        
        VariableNode vn = (VariableNode)getLeft();
        
        int val = getRight().evaluate(w);
        w.setVariable(vn.getName(), val);
        return val;
    }

}
