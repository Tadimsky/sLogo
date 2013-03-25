package parser.commands.turtle.queries;

import java.awt.Color;
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
        Color c = w.getTurtleManager().<Color>execute("getPenColor");
        return w.getColors().getColorID(c);
    }
}
