package parser.commands.other;

import java.util.Deque;
import controller.Workspace;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class If extends BinaryNode {

    public If (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        int times = getLeft().evaluate(w);
        if (times != 0)
        {
            return getRight().evaluate(w);
        }        
        return 0;
    }

}
