package parser.nodes;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Represents a variable in the tree.
 * Gets the value from the workspace when it is evaluated.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class VariableNode extends SimpleNode {

    public static final String INVALID_VARIABLE =
            "The variable %s does not exist in the current workspace.";

    private String myName;

    public VariableNode (String name)
    {
        if (name.indexOf(':') == 0)
        {
            myName = name;
        }
        // else throw new InvalidSemanticsException(INVALID_FORMAT, name);
    }

    public VariableNode (Deque<SyntaxNode> stack)
    {
        this(((TokenNode) stack.pop()).getToken());
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {

        Integer val = w.getVariables().getVariable(myName);
        if (val == null) return 0; // throw new InvalidArgumentsException(INVALID_VARIABLE, myName);
        return val;
    }

    /**
     * Returns the name of the variable.
     * 
     * @return
     */
    public String getName ()
    {
        return myName;
    }

}
