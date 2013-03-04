package parser.commands.other;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.TernaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;

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
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int times = getLeft().evaluate(w);
        if (times != 0) { return getMiddle().evaluate(w); }
        return getRight().evaluate(w);
    }

}
