package parser.commands.other;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

/**
 * Implements the If control structure.
 * If the left parameter's value is not 0, the list of commands to the right runs. 
 * Otherwise nothing happens.
 * 
 * @author Jonathan Schmidt
 *
 */
public class If extends BinaryNode {

    public If (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int times = getLeft().evaluate(w);
        if (times != 0)
        {
            return getRight().evaluate(w);
        }        
        return 0;
    }

}
