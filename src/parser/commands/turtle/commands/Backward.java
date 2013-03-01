package parser.commands.turtle.commands;

import java.util.Deque;
import parser.Nodes.SyntaxNode;
import parser.Nodes.UnaryNode;
import parser.Nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

public class Backward extends UnaryNode {
   
    public Backward (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        int amnt = getArgument().evaluate(w);
        return w.getTurtle().move(-amnt);
    }

}
