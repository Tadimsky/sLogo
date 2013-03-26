package parser.commands.view;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the SetBackgroud function.
 * Sets the background of the workspace to the specified color index
 * 
 * @author Jonathan Schmidt
 * 
 */
public class SetBackground extends UnaryNode {

    /**
     * Creates Random Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public SetBackground (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int colorIndex = getArgument().evaluate(w);
        return w.setBackground(colorIndex);
    }

}
