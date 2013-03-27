package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Clear Screen function.
 * Clears the screen and moves the turtles back to the center
 * 
 * @author Jonathan Schmidt
 * 
 */
public class ClearScreen extends BasicControl {

    /**
     * Creates ClearScreen Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public ClearScreen (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        return w.getTurtleManager().execute("clear");
    }
}
