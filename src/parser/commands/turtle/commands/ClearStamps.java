package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Clear Stamps function.
 * Clears all the stamps off the screen
 * 
 * @author Jonathan Schmidt
 * 
 */
public class ClearStamps extends BasicControl {

    /**
     * Creates ClearStamps Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public ClearStamps (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        w.getTurtleManager().execute("clearStamp");
        return 0;
    }
}
