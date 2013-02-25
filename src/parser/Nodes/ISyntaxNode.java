package parser.Nodes;

import controller.Workspace;


/**
 * Base class for the tree that is built to represent an expression.
 * 
 * @author Jonathan Schmidt
 * 
 */
public interface ISyntaxNode {

    /**
     * Evaluates the current node of the expression. 
     * @param w The active workspace.
     * @return The integer value of this expression.
     */
    public int evaluate (Workspace w);
}
