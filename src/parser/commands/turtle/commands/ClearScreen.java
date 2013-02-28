package parser.commands.turtle.commands;

import java.util.Deque;
import controller.Workspace;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class ClearScreen extends BasicControl {

    public ClearScreen (Deque<SyntaxNode> stack) {
        super(stack);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        return w.getTurtle().clear();
        
    }
}
