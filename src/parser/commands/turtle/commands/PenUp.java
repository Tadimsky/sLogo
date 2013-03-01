package parser.commands.turtle.commands;

import java.util.Deque;
import parser.Nodes.SyntaxNode;
import parser.Nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

public class PenUp extends BasicControl {

    public PenUp (Deque<SyntaxNode> stack) {
        super(stack);        
    }
    
    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        w.getTurtle().setPenWriting(false);
        return 0;
    }

}
