package parser.commands.other;

import java.util.Deque;
import controller.Workspace;
import parser.nodes.SyntaxNode;
import parser.nodes.TernaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;


public class To extends TernaryNode {

    public To (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        int times = getLeft().evaluate(w);
        if (times != 0) { return getMiddle().evaluate(w); }
        return getRight().evaluate(w);
    }

}
