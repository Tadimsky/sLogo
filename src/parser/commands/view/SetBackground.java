package parser.commands.view;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class SetBackground extends UnaryNode {
   
    public SetBackground (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int colorIndex = getArgument().evaluate(w);
        return w.setBackground(colorIndex);
    }

}
