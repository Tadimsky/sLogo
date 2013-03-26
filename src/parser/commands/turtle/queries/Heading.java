package parser.commands.turtle.queries;

import java.util.Deque;
import model.IState;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Heading function.
 * Returns the direction the turtle is facing.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Heading extends BasicControl implements ILabelInformation {

    /**
     * Creates Heading Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public Heading (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        return w.getTurtleManager().execute("getHeading");
    }

    @Override
    public String evaluateFromTurtle (IState t) throws InvalidArgumentsException {
        return "" + t.getHeading();
    }
}
