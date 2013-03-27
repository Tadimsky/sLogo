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

    private String myName;

    /**
     * Creates a Variable Node from a String variable name
     * 
     * @param name The variable name
     */
    public VariableNode (String name) {
        if (name.indexOf(':') == 0) {
            myName = name;
        }
        // else throw new InvalidSemanticsException(INVALID_FORMAT, name);
    }

    /**
     * Creates the Variable Node from a stack of parameters
     * 
     * @param stack The stack of parameters
     */
    public VariableNode (Deque<SyntaxNode> stack) {
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
    public String getName () {
        return myName;
    }

}
