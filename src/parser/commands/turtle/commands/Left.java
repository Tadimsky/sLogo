package parser.commands.turtle.commands;

import java.util.Deque;
import java.util.Map;
import model.Turtle;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class Left extends UnaryNode {
   
    public Left (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int deg = getArgument().evaluate(w);
        Map<Integer, Turtle> turtles = w.getTurtles();
        for (Turtle t : turtles.values()) {
            t.turn(-deg);
        }
        w.update();
        return deg;
    }

}
