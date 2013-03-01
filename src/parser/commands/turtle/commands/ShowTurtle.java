package parser.commands.turtle.commands;

import java.util.Deque;
import parser.Nodes.SyntaxNode;
import parser.Nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

public class ShowTurtle extends BasicControl {

    public ShowTurtle (Deque<SyntaxNode> stack) {
        super(stack);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        w.getTurtle().setHiding(false);
        return 1;
    }
}
