package parser.commands.workspace;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map.Entry;
import model.Turtle;
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
public class AskWith extends BinaryNode {

    private static final String COMMAND_LIST =
            "Second parameter must be a list of commands to run on the turtles.";
    private static final String DETERMINE_LIST =
            "First parameter must be a list of commands to run in order to determine" +
                    " whether a turtle is valid to run the second paramter's commands on.";

    /**
     * Creates Ask With Command Node
     * 
     * @param queue
     *        The list of nodes that come before this command
     */
    public AskWith (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        checkSyntax();
        TurtleManager tm = w.getTurtleManager();
        List<Integer> oldTurtles = tm.copyActive();

        // Build List of turtles in First Parameter
        ListNode determine = (ListNode) getLeft();
        List<Integer> valid = new ArrayList<Integer>();

        for (Entry<Integer, Turtle> entry : tm.getAllTurtles().entrySet()) {
            tm.clearActive();
            tm.activate(entry.getKey());
            if (determine.evaluate(w) == 1) {
                valid.add(entry.getKey());
            }
        }

        // Activate New Turtles
        tm.clearActive();
        tm.activate(valid);
        int val = getRight().evaluate(w);

        // Activate old Turtles
        tm.activate(oldTurtles);

        return val;
    }

    private void checkSyntax () throws InvalidArgumentsException {
        if (!(getLeft() instanceof ListNode))
            throw new InvalidArgumentsException(DETERMINE_LIST, "");

        if (!(getRight() instanceof ListNode))
            throw new InvalidArgumentsException(COMMAND_LIST, "");
    }

}
