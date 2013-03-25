package parser.commands.workspace;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import model.TurtleManager;
import parser.IParserProvider;
import parser.nodes.BinaryNode;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

/**
 * Implements the Sum function.
 * Returns the left parameter's value + right parameter's value.
 * 
 * @author Jonathan Schmidt
 *
 */
public class Ask extends BinaryNode {
   
    public Ask (Deque<SyntaxNode> queue) {
        super(queue);        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        checkSyntax();
        TurtleManager tm = w.getTurtleManager();
        // Build List of turtles in First Parameter
        ListNode turtles = (ListNode)getLeft();
        Integer[] ids = new Integer[turtles.getContents().size()];
        for (int i = 0; i < ids.length; i++)
        {
            ids[i] = turtles.getContents().get(i).evaluate(w);
        }
        
        // Get old list of Turtles
        List<Integer> oldTurtles = tm.copyActive();
        
        // Activate New Turtles
        tm.clearActive();
        tm.activate(Arrays.asList(ids));
        int val = getRight().evaluate(w);        
        
        // Activate old Turtles
        tm.activate(oldTurtles);
        
        return val;        
    }
    
    private void checkSyntax()
    {
        if (!(getLeft() instanceof ListNode))
        {
            throw new InvalidArgumentsException("First parameter must be a list of turtles to run the commands on." , "ask");
        }
        
        if (!(getRight() instanceof ListNode))
        {
            throw new InvalidArgumentsException("Second parameter must be a list of commands to run on the turtles." , "ask");
        }
    }

}
