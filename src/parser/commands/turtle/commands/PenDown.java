package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Pen Down function.
 * Puts the turtle's pen down.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class PenDown extends BasicControl {

    /**
     * Creates Pen Down Screen Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public PenDown (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        w.getTurtleManager().execute("setPenWriting", true);
        return 1;
    }
}
