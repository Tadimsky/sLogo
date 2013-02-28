package parser.commands.turtle.commands;

import java.util.Deque;
import controller.Workspace;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

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
