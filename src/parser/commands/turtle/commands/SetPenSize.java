package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class SetPenSize extends UnaryNode {
   
    public SetPenSize (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int size = getArgument().evaluate(w);
        // set the size of the pen        
    }

}
