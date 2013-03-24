package parser.commands.turtle.commands;

import java.util.Deque;
import java.util.Map;
import model.Turtle;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class SetHeading extends UnaryNode {
   
    public SetHeading (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int result = 0;
        int deg = getArgument().evaluate(w);
        Map<Integer, Turtle> turtles = w.getTurtles();
        for (Turtle t : turtles.values()) {
            result = t.setHeading(deg);
        }
        w.update();
        return result;
    }

}
