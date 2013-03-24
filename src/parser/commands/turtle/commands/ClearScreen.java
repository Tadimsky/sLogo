package parser.commands.turtle.commands;

import java.util.Deque;
import java.util.Map;
import model.Turtle;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class ClearScreen extends BasicControl {

    public ClearScreen (Deque<SyntaxNode> stack) {
        super(stack);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int result = 0;
        Map<Integer, Turtle> turtles = w.getTurtles();
        for (Turtle t : turtles.values()) {
            result = t.clear();
        }
        w.update();
        return result;
        
    }
}
