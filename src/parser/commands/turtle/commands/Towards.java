package parser.commands.turtle.commands;

import java.util.Deque;
import parser.Nodes.BinaryNode;
import parser.Nodes.SyntaxNode;
import parser.Nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

public class Towards extends BinaryNode {
   
    public Towards (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        int x = getLeft().evaluate(w);
        int y = getRight().evaluate(w);
        
        w.getTurtle().faceTowards(x,y);
        return 0; // value from function
    }

}
