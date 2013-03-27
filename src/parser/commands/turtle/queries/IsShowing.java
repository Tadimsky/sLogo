package parser.commands.turtle.queries;

import java.util.Deque;
import model.IState;
import parser.IParserProvider;
import parser.nodes.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import view.ILabelInformation;


/**
 * Implements the Is Showing function.
 * Returns 1 if the turtle is showing or 0 otherwise.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class IsShowing extends BasicControl implements ILabelInformation {

    /**
     * Creates Is Showing Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public IsShowing (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        if (w.getTurtleManager().<Boolean> execute("isHiding")) return 1;
        return 0;
    }

    @Override
    public String evaluateFromTurtle (IState t) throws InvalidArgumentsException {
        if (t.isHiding()) return "Yes";
        return "No";
    }
}
