package parser.commands.other;

import java.util.Deque;
import controller.Workspace;
import parser.nodes.CommandNode;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;
import parser.nodes.TernaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;


public class To extends TernaryNode {

    private static final String FUNCTIONS_PARAM = "Third parameter is not a list of functions to be run.";
    private static final String VAR_PARM = "Second parameter is not a list of variables.";
    private static final String COMMAND_PARAM = "First parameter is not the name of a command.";

    public To (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {        
        checkSyntax();
        
        
        
        return 0;
    }
    
    private void checkSyntax() throws InvalidArgumentsException
    {
        if (!(getLeft() instanceof CommandNode))
        {
            throw new InvalidArgumentsException(COMMAND_PARAM, "");
        }
        if (!(getMiddle() instanceof ListNode))
        {
            throw new InvalidArgumentsException(VAR_PARM, "");
        }
        
        if (!(getRight() instanceof ListNode))
        {
            throw new InvalidArgumentsException(FUNCTIONS_PARAM, "");
        }  
    }

}
