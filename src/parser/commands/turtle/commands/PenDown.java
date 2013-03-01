package parser.commands.turtle.commands;

import java.util.Deque;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

public class PenDown extends BasicControl {

    public PenDown (Deque<SyntaxNode> stack) {
        super(stack);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        w.getTurtle().setPenWriting(true);
        return 1;
    }
}
