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
public class VariableNode extends SimpleNode {

    private String myName;

    public VariableNode (String name)
    {
        if (name.indexOf(':') == 0)
        {
            myName = name;
        }
        // else throw new InvalidSemanticsException(INVALID_FORMAT, name);
    }

    public VariableNode (Deque<SyntaxNode> stack) throws InvalidSemanticsException
    {
        this(((TokenNode) stack.pop()).getToken());
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {

        Integer val = w.getVariables().getVariable(myName);
        if (val == null) return 0; 
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
