package parser.commands.other;

import java.util.Deque;
import parser.IParserProvider;
import parser.VariableManager;
import parser.nodes.BinaryNode;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;
import parser.nodes.VariableNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Do Times control structure.
 * Runs the specified command list a certain number of times. Assigns the current
 * count of the loop to the specified variable name. *
 * 
 * @author Jonathan Schmidt
 * 
 */
public class DoTimes extends BinaryNode {

    private static final String FIRST_ARGUMENT = "First argument in first list must be a variable.";
    private static final String FIRST_LIST_CONTENT =
            "The first list must contain both a " +
                    "variable and a number of times for the loop to run.";
    private static final String FIRST_LIST = "First argument must be a list.";
    private static final String SECOND_LIST = "Second argument must be a list of commands to run.";

    /**
     * Creates Do Times Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public DoTimes (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        checkSyntax();

        VariableManager vm = w.getVariables();
        vm.createVariableScope("Dotimes");

        // evaluates the list and returns the final value
        int times = getLeft().evaluate(w);
        String varName = ((VariableNode) ((ListNode) getLeft()).getContents().get(0)).getName();

        int val = 0;
        for (int i = 0; i < times; i++) {
            vm.setVariable(varName, i + 1);
            val = getRight().evaluate(w);
        }
        vm.revertVariableScope();
        return val;
    }

    private void checkSyntax () throws InvalidArgumentsException {
        if (!(getLeft() instanceof ListNode)) throw new InvalidArgumentsException(FIRST_LIST, "");

        if (!(getRight() instanceof ListNode))
            throw new InvalidArgumentsException(SECOND_LIST, "");

        // Check the First List
        ListNode ln = (ListNode) getLeft();
        if (ln.getContents().size() != 2)
            throw new InvalidArgumentsException(FIRST_LIST_CONTENT, "");
        if (!(ln.getContents().get(0) instanceof VariableNode))
            throw new InvalidArgumentsException(FIRST_ARGUMENT, "");
    }

}
