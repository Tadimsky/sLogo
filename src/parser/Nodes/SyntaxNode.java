package parser.nodes;

import java.util.Deque;
import parser.nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;


/**
 * Base class for the tree that is built to represent an expression.
 * 
 * @author Jonathan Schmidt
 * 
 */
public abstract class SyntaxNode {    
    
    /**
     * Evaluates the current node of the expression. 
     * @param w The active workspace.
     * @return The integer value of this expression.
     */
    public abstract int evaluate (Workspace w) throws InvalidArgumentsException;
    
}
