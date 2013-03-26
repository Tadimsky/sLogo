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
 * Implements the X Cor function.
 * Returns the X coordinate of the turtle
 * 
 * @author Jonathan Schmidt
 * 
 */
public class XCor extends BasicControl implements ILabelInformation {

    /**
     * Creates X Cor Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public XCor (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        Location loc = w.getTurtleManager().execute("getCenter");
        return loc.getIntX();
    }

    @Override
    public String evaluateFromTurtle (IState t) throws InvalidArgumentsException {
        return "" + t.getCenter().getIntX();
    }
}
