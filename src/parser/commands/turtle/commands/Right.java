package parser.commands.turtle.commands;

import java.util.Deque;
import parser.Nodes.SyntaxNode;
import parser.Nodes.UnaryNode;
import parser.Nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

public class Right extends UnaryNode {
   
    public Right (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        int deg = getArgument().evaluate(w);
        return w.getTurtle().turn(deg);
    }

}
