package parser.commands.turtle.queries;

import java.util.Deque;
import java.util.Map;
import model.IState;
import model.Turtle;
import parser.IParserProvider;
import parser.commands.turtle.commands.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class IsShowing extends BasicControl implements ILabelInformation{

    public IsShowing (Deque<SyntaxNode> stack) {
        super(stack);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        boolean result = false;
        Map<Integer, Turtle> turtles = w.getTurtles();
        for (Turtle t : turtles.values()) {
            result = t.isHiding();
        }
        w.update();
        if (!result)
        {
            return 1;
        }
        return 0;
    }
    
    @Override
    public String evaluateFromTurtle (IState t) throws InvalidArgumentsException {
        if (t.isHiding())
        {
            return "Yes";
        }
        return "No";
    }
}
