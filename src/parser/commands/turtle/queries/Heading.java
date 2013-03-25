package parser.commands.turtle.queries;

import java.util.Deque;
import model.IState;
import parser.IParserProvider;
import parser.commands.turtle.commands.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class Heading extends BasicControl implements ILabelInformation {

    public Heading (Deque<SyntaxNode> stack) {
        super(stack);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        return w.getTurtle().getHeading();        
    }
    
    @Override
    public String evaluateFromTurtle (IState t) throws InvalidArgumentsException {
        return ""+t.getHeading();        
    }
}
