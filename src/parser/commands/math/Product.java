package parser.commands.math;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Product function.
 * Returns the left parameter's value * right parameter's value
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Product extends BinaryNode {

    /**
     * Creates Product Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public Product (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        return getLeft().evaluate(w) * getRight().evaluate(w);
    }

}
