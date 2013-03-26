package parser.commands.turtle.queries;

import java.util.Deque;
import model.IState;
import parser.IParserProvider;
import parser.nodes.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import view.ILabelInformation;


/**
 * Implements the Is Pen Down function.
 * Returns 1 if the pen is down or 0 otherwise.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class IsPenDown extends BasicControl implements ILabelInformation {

    /**
     * Creates Is Pen Down Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public IsPenDown (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        if (w.getTurtleManager().<Boolean> execute("isPenWriting")) {
            return 1;
        }
        return 0;
    }

    @Override
    public String evaluateFromTurtle (IState t) throws InvalidArgumentsException {
        if (t.isPenWriting()) {
            return "Yes";
        }
        return "No";
    }
}
