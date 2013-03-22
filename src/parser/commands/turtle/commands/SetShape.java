package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class SetShape extends UnaryNode {
   
    public SetShape (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int shapeid = getArgument().evaluate(w);
        // set the shape of the turtle        
    }

}
