package parser.commands.turtle.queries;

import java.util.Deque;
import java.util.Map;
import model.IState;
import model.Turtle;
import parser.IParserProvider;
import parser.commands.turtle.commands.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class XCor extends BasicControl implements ILabelInformation {

    public XCor (Deque<SyntaxNode> stack) {
        super(stack);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int result = 0;
        Map<Integer, Turtle> turtles = w.getTurtles();
        for (Turtle t : turtles.values()) {
            result = t.getCenter().getIntX();
        }
        w.update();
        return result;        
    }
    
    @Override
    public String evaluateFromTurtle (IState t) throws InvalidArgumentsException {
        return ""+t.getCenter().getIntX();        
    }
}
