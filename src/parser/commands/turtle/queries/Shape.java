package parser.commands.turtle.queries;

import java.awt.image.BufferedImage;
import java.util.Deque;
import parser.IParserProvider;
import parser.commands.turtle.commands.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


public class Shape extends BasicControl {

    public Shape (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        BufferedImage i = w.getTurtleManager().<BufferedImage>execute("getShape");
        return w.getTurtleManager().getShape(i);        
    }
}
