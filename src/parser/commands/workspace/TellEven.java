package parser.commands.workspace;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Tell Even function.
 * Sets the turtles with an even ID as active.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class TellEven extends BasicControl {
    /**
     * Creates Tell Even Command Node
     * 
     * @param queue
     *        The list of nodes that come before this command
     */
    public TellEven (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        w.getTurtleManager().clearActive();
        return w.getTurtleManager().activateEven();
    }

}
