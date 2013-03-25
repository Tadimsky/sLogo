package parser.nodes;

import parser.IParserProvider;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Base class for the tree that is built to represent an expression.
 * 
 * @author Jonathan Schmidt
 * 
 */
public abstract class SyntaxNode {

    /**
     * Evaluates the current node of the expression.
     * 
     * @param w The active workspace.
     * @return The integer value of this expression.
     */
    public abstract int evaluate (IParserProvider w) throws InvalidArgumentsException;

}
