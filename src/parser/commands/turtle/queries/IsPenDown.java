package parser.commands.turtle.queries;

import java.util.Deque;
import parser.commands.turtle.commands.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

public class IsPenDown extends BasicControl {

    public IsPenDown (Deque<SyntaxNode> stack) {
        super(stack);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        if (w.getTurtle().isPenWriting())
        {
            return 1;
        }
        return 0;
    }
}
