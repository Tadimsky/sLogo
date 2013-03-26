package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Pen Up function.
 * Sets the turtle's pen to be up.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class PenUp extends BasicControl {

    /**
     * Creates Pen Up Command Node
     * 
     * @param stack The list of nodes that come before this command
     */
    public PenUp (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        w.getTurtleManager().execute("setPenWriting", false);
        return 0;
    }

}
