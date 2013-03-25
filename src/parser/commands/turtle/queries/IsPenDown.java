package parser.commands.turtle.queries;

import java.util.Deque;
import model.IState;
import parser.IParserProvider;
import parser.commands.turtle.commands.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


public class IsPenDown extends BasicControl implements ILabelInformation {

    public IsPenDown (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        if (w.getTurtle().isPenWriting()) return 1;
        return 0;
    }

    @Override
    public String evaluateFromTurtle (IState t) throws InvalidArgumentsException {
        if (t.isPenWriting()) return "Yes";
        return "No";
    }
}
