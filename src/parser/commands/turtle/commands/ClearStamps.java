package parser.commands.turtle.commands;

import java.util.Deque;
import java.util.Map;
import model.Turtle;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class ClearStamps extends BasicControl {

    public ClearStamps (Deque<SyntaxNode> stack) {
        super(stack);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        Map<Integer, Turtle> turtles = w.getTurtles();
        for (Turtle t : turtles.values()) {
            t.clearStamp();
        }
        w.update();
        return 0; 
        
    }
}
