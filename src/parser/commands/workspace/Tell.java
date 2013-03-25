package parser.commands.workspace;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;

/**
 * Implements the Random function.
 * Returns a random value with a maximum value provided by the parameter.
 * 
 * @author Jonathan Schmidt
 *
 */
public class Tell extends UnaryNode{
    public Tell (Deque<SyntaxNode> queue) {
        super(queue);       
         
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        checkSyntax();
        int val = 0;
        for (SyntaxNode sn : ((ListNode)getArgument()).getContents()) {
            val = sn.evaluate(w);
            w.getTurtleManager().activate(val);
        }
        return val;
    }
    
    private void checkSyntax() throws InvalidArgumentsException {
        if (!(getArgument() instanceof ListNode)) {
            throw new InvalidArgumentsException("The parameter of Tell is not a list of turtles.", "tell");
        }       
    }
    
}
