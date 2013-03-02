package parser.commands.turtle.queries;

import java.util.Deque;
import parser.commands.turtle.commands.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

public class IsShowing extends BasicControl {

    public IsShowing (Deque<SyntaxNode> stack) {
        super(stack);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        if (!w.getTurtle().isHiding())
        {
            return 1;
        }
        return 0;
    }
}
