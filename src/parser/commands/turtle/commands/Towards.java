package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class Towards extends BinaryNode {
   
    public Towards (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int x = getLeft().evaluate(w);
        int y = getRight().evaluate(w);
        w.getTurtle().faceTowards(x,y);
        return 0; // value from function
    }

}
