package parser.commands.bool;

import java.util.Deque;
import controller.Workspace;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


public class And extends BinaryNode {

    public And (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        if ((getLeft().evaluate(w) == 1) && (getRight().evaluate(w) == 1)) { return 1; }
        return 0;
    }

}
