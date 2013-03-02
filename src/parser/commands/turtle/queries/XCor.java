package parser.commands.turtle.queries;

import java.util.Deque;
import parser.commands.turtle.commands.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

public class XCor extends BasicControl {

    public XCor (Deque<SyntaxNode> stack) {
        super(stack);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        return w.getTurtle().getCenter().getIntX();        
    }
}
