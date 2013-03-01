package parser.commands.turtle.commands;

import java.util.Deque;
import parser.Nodes.SyntaxNode;
import parser.Nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

public class ClearScreen extends BasicControl {

    public ClearScreen (Deque<SyntaxNode> stack) {
        super(stack);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        return w.getTurtle().clear();
        
    }
}
