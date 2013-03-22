package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class SetPenColor extends UnaryNode {
   
    public SetPenColor (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int color = getArgument().evaluate(w);
        w.getTurtle().setColor(w.getPalette().get(color));      
        return color;
    }

}
