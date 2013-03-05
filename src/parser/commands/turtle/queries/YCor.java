package parser.commands.turtle.queries;

import java.util.Deque;
import model.IState;
import parser.IParserProvider;
import parser.commands.turtle.commands.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public class YCor extends BasicControl implements ILabelInformation{

    public YCor (Deque<SyntaxNode> stack) {
        super(stack);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        return w.getTurtle().getCenter().getIntY();        
    }
    
    @Override
    public String evaluateFromTurtle (IState t) throws InvalidArgumentsException {
        return ""+t.getCenter().getIntY();        
    }
}
