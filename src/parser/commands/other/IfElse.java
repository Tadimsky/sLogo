package parser.commands.other;

import java.util.Deque;
import parser.Nodes.SyntaxNode;
import parser.Nodes.TernaryNode;
import parser.Nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

/**
 * Implements the If Else control structure.
 * If the left parameter's value is not 0, the list of commands in the middle runs.  
 * Else, the list of commands to the right runs.
 * 
 * @author Jonathan Schmidt
 *
 */
public class IfElse extends TernaryNode {

    public IfElse (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        int times = getLeft().evaluate(w);
        if (times != 0) { return getMiddle().evaluate(w); }
        return getRight().evaluate(w);
    }

}
