package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Set Shape function.
 * Sets the shape of the turtle to be the shape specified by the index provided.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class SetShape extends UnaryNode {

    /**
     * Creates Set Shape Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public SetShape (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int shapeid = getArgument().evaluate(w);
        w.getTurtleManager().execute("setImage", w.getTurtleManager().getTurtleImage(shapeid));
        return shapeid;
    }

}
