package parser.nodes;

import java.util.Deque;

/**
 * Implements the Basic Control node
 * Removes the first argument from the stack.
 * 
 * @author Jonathan Schmidt
 * 
 */
public abstract class BasicControl extends SimpleNode {

    /**
     * Creates Basic Control
     * 
     * @param stack The list of nodes that come before this command
     */
    public BasicControl (Deque<SyntaxNode> stack) {
        // remove the token
        if (stack != null) {
            stack.pop();
        }
    }

}
