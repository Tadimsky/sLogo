package parser.commands.turtle.queries;

import java.util.Deque;
import model.IState;
import parser.IParserProvider;
import parser.nodes.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import util.Location;
import view.ILabelInformation;


/**
 * Implements the Y Cor function.
 * Returns the Y coordinate of the turtle
 * 
 * @author Jonathan Schmidt
 * 
 */
public class YCor extends BasicControl implements ILabelInformation {

    /**
     * Creates Y Cor Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public YCor (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        Location loc = w.getTurtleManager().execute("getCenter");
        return loc.getIntY();
    }

    @Override
    public String evaluateFromTurtle (IState t) throws InvalidArgumentsException {
        return "" + t.getCenter().getIntY();
    }
}
