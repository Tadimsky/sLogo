package parser.commands.bool;

import java.util.Deque;
import controller.Workspace;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


public class LessThan extends BinaryNode {

    public LessThan (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        if ((getLeft().evaluate(w) < getRight().evaluate(w))) { return 1; }
        return 0;
    }

}
