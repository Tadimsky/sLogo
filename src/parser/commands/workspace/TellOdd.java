package parser.commands.workspace;

import java.util.Deque;
import parser.IParserProvider;
import parser.commands.turtle.commands.BasicControl;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

/**
 * Implements the Tell Odd function.
 * Sets the turtles with an odd ID as active.
 * 
 * @author Jonathan Schmidt
 *
 */
public class TellOdd extends BasicControl {
    public TellOdd (Deque<SyntaxNode> queue) {
        super(queue);       
         
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        w.getTurtleManager().clearActive();
        return w.getTurtleManager().activateOdd();        
    }
}
