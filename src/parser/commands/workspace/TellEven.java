package parser.commands.workspace;

import java.util.Deque;
import parser.IParserProvider;
import parser.commands.turtle.commands.BasicControl;
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
    public TellEven (Deque<SyntaxNode> queue) {
        super(queue);

    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        return w.getTurtleManager().activateEven();
    }

}
