package parser.nodes;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.exceptions.InvalidSemanticsException;


/**
 * Represents a node that stores a constant integer value.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class ConstantNode extends SimpleNode {

    private int myValue;

    /**
     * Creates a constant node with a specified integer value
     * 
     * @param val the value of the constant
     */
    public ConstantNode (int val) {
        myValue = val;
    }

    /**
     * Creates a constant node from a string value
     * 
     * @param val the value of the constant node in string form.
     * @throws InvalidSemanticsException If there is an error with the format of the integer.
     */
    public ConstantNode (String val) throws InvalidSemanticsException {
        try {
            myValue = Integer.parseInt(val);
        }
        catch (NumberFormatException e) {
            throw new InvalidSemanticsException(INVALID_FORMAT, val);
        }
    }

    /**
     * Creates the Constant Node from a list of parameters
     * 
     * @param stack The stack of parameters.
     * @throws InvalidSemanticsException
     */
    public ConstantNode (Deque<SyntaxNode> stack) throws InvalidSemanticsException {
        this(((TokenNode) stack.pop()).getToken());
    }

    @Override
    public int evaluate (IParserProvider w) {
        return myValue;
    }
}
