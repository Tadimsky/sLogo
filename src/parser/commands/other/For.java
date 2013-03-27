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
 * Implements the For loop control structure.
 * Runs the specified command list a certain number of times.
 * The start, end and increment values of the loop are given in the first list.
 * The current loop number is assigned to the specified variable name.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class For extends BinaryNode {
    private static final String FIRST_ARGUMENT_VARIABLE =
            "First argument in first list must be a variable.";
    private static final int NUM_ARGS_FIRST = 4;
    private static final String FIRST_LIST_CONTENTS =
            "The first list must contain a variable, the starting value," +
                    " the end value and the amount to increment by.";
    private static final String SECOND_ARGUMENT =
            "Second argument must be a list of commands to run.";
    private static final String FIRST_ARGUMENT = "First argument must be a list.";
    private static final int START = 0;
    private static final int END = 1;
    private static final int INCREMENT = 2;

    /**
     * Creates For Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public For (Deque<SyntaxNode> queue) {
        super(queue);

    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        checkSyntax();

        VariableManager vm = w.getVariables();
        vm.createVariableScope("For");

        // evaluates the list and returns the final value
        ListNode ln = (ListNode) getLeft();

        int start = ln.getContents().get(START).evaluate(w);
        int end = ln.getContents().get(END).evaluate(w);
        int increment = ln.getContents().get(INCREMENT).evaluate(w);

        String varName = ((VariableNode) ((ListNode) getLeft()).getContents().get(0)).getName();

        int val = 0;
        for (int i = start; i < end; i += increment) {
            vm.setVariable(varName, i);
            val = getRight().evaluate(w);
        }
        vm.revertVariableScope();
        return val;
    }

    private void checkSyntax () throws InvalidArgumentsException {
        if (!(getLeft() instanceof ListNode))
            throw new InvalidArgumentsException(FIRST_ARGUMENT, "");

        if (!(getRight() instanceof ListNode))
            throw new InvalidArgumentsException(SECOND_ARGUMENT, "");

        // Check the First List
        ListNode ln = (ListNode) getLeft();
        if (ln.getContents().size() != NUM_ARGS_FIRST)
            throw new InvalidArgumentsException(FIRST_LIST_CONTENTS, "");
        if (!(ln.getContents().get(0) instanceof VariableNode))
            throw new InvalidArgumentsException(FIRST_ARGUMENT_VARIABLE, "");
    }

}
