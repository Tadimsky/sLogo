package parser.commands.math;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Quotient function.
 * Returns the left parameter's value / right parameter's value
 * Throws an error if the right parameter's value is 0.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Quotient extends BinaryNode {

    private static final String DIVIDE_BY_ZERO = "Cannot divide by zero.";

    /**
     * Creates Quotient Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public Quotient (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int l = getLeft().evaluate(w);
        int r = getRight().evaluate(w);
        if (r != 0) { return l / r; }

        throw new InvalidArgumentsException(DIVIDE_BY_ZERO, "");
    }

}
