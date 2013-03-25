package parser.commands.turtle.queries;

import java.util.Deque;
import model.IState;
import parser.IParserProvider;
import parser.commands.turtle.commands.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


public class IsShowing extends BasicControl implements ILabelInformation {

    public IsShowing (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        if (w.getTurtleManager().<Boolean>execute("isHiding")) return 1;
        return 0;
    }

    @Override
    public String evaluateFromTurtle (IState t) throws InvalidArgumentsException {
        if (t.isHiding()) return "Yes";
        return "No";
    }
}
