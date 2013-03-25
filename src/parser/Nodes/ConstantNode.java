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

    public ConstantNode (int val) {
        myValue = val;
    }

    public ConstantNode (String val) throws InvalidSemanticsException {
        try
        {
            myValue = Integer.parseInt(val);
        }
        catch (NumberFormatException e)
        {
            throw new InvalidSemanticsException(INVALID_FORMAT, val);
        }
    }

    public ConstantNode (Deque<SyntaxNode> stack) throws InvalidSemanticsException
    {
        this(((TokenNode) stack.pop()).getToken());
    }

    @Override
    public int evaluate (IParserProvider w) {
        return myValue;
    }
}
