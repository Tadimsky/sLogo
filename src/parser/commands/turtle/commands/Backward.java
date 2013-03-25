package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class Backward extends UnaryNode {
   
    public Backward (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int amnt = getArgument().evaluate(w);
        return w.getTurtle().move(-amnt);
    }

}
