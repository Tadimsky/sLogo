package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Set Pen Color function.
 * Sets the color of the turtle's pen to be the color referenced by the index provided.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class SetPenColor extends UnaryNode {

    /**
     * Creates Set Pen Color Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public SetPenColor (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int color = getArgument().evaluate(w);
        w.getTurtleManager().execute("setColor", w.getColors().getColor(color));
        return color;
    }

}
