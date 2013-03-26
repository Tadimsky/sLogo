package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;

/**
 * Implements the Set Pen Size function.
 * Sets the pen size of the turtle's pen to be the thickness specified.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class SetPenSize extends UnaryNode {

    /**
     * Creates Set Pen Size Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public SetPenSize (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int size = getArgument().evaluate(w);
        w.getTurtleManager().execute("setPenSize", size);
        return size;
    }

}
