package parser.commands.turtle.queries;

import java.util.Deque;
import parser.IParserProvider;
import parser.commands.turtle.commands.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


public class PenColor extends BasicControl {

    public PenColor (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        return w.getTurtle().getPenColor();

    }
}
