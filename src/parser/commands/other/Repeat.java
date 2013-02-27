package parser.commands.other;

import java.util.Deque;
import controller.Workspace;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class Repeat extends BinaryNode {

    public Repeat (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        int times = getLeft().evaluate(w);
        int val = 0;
        for (int i = 0; i < times; i++)
        {
            val = getRight().evaluate(w);            
        }
        return val;
    }

}
