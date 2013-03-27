package parser.commands.workspace;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Tell function.
 * Sets the turtles provided in the list as active.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Tell extends UnaryNode {
    private static final String TURTLE_LIST = "The parameter of Tell is not a list of turtles.";

    /**
     * Creates Tell Command Node
     * 
     * @param queue
     *        The list of nodes that come before this command
     */
    public Tell (Deque<SyntaxNode> queue) {
        super(queue);

    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        checkSyntax();
        int val = 0;
        w.getTurtleManager().clearActive();
        for (SyntaxNode sn : ((ListNode) getArgument()).getContents()) {
            val = sn.evaluate(w);
            w.getTurtleManager().activate(val);
        }
        return val;
    }

    private void checkSyntax () throws InvalidArgumentsException {
        if (!(getArgument() instanceof ListNode))
            throw new InvalidArgumentsException(TURTLE_LIST, "");
    }

}
