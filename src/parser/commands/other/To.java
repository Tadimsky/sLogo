package parser.commands.other;

import java.util.Deque;
import parser.CustomCommand;
import parser.IParserProvider;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;
import parser.nodes.TernaryNode;
import parser.nodes.TokenNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the To function.
 * Creates a custom function.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class To extends TernaryNode {

    private static final String FUNCTIONS_PARAM =
            "Third parameter is not a list of functions to be run.";
    private static final String VAR_PARM = "Second parameter is not a list of variables.";
    private static final String COMMAND_PARAM = "First parameter is not the name of a command.";

    /**
     * Creates To Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public To (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        checkSyntax();
        String n = ((TokenNode) getLeft()).getToken();
        ListNode parms = (ListNode) getMiddle();
        ListNode com = (ListNode) getRight();
        CustomCommand cc = w.getCommand(n);
        cc.addParserInfo(parms, com);

        return 1;
    }

    private void checkSyntax () throws InvalidArgumentsException {
        if (!(getLeft() instanceof TokenNode))
            throw new InvalidArgumentsException(COMMAND_PARAM, "");
        if (!(getMiddle() instanceof ListNode)) throw new InvalidArgumentsException(VAR_PARM, "");
        if (!(getRight() instanceof ListNode))
            throw new InvalidArgumentsException(FUNCTIONS_PARAM, "");
    }

}
