package parser.nodes;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import parser.CustomCommand;
import parser.IParserProvider;
import parser.SemanticsTable;
import parser.VariableManager;
import parser.nodes.exceptions.InvalidArgumentsException;
import parser.nodes.exceptions.InvalidSemanticsException;


/**
 * 
 * @author Jonathan Schmidt
 * 
 */
public class CommandNode extends SimpleNode {

    private String myName;
    private CustomCommand myCommand;

    List<SyntaxNode> myArguments;

    public CommandNode (String val) {
        myName = val;
        myCommand = SemanticsTable.getInstance().getCommand(myName);
        myArguments = new ArrayList<SyntaxNode>();
    }

    public CommandNode (Deque<SyntaxNode> stack)
    {
        this(((TokenNode) stack.pop()).getToken());

        if (myCommand == null) // Command does not exist right now - return
            return;

        if (myCommand.getNumArgs() <= stack.size())
        {
            for (int i = 0; i < myCommand.getNumArgs(); i++)
            {
                myArguments.add(stack.pop());
            }
        }
        else throw new InvalidArgumentsException(InvalidArgumentsException.INCORRECT_NUMBER_ARGS,
                                                 myName);
    }

    public List<SyntaxNode> getArguments ()
    {
        return myArguments;
    }

    public String getName ()
    {
        return myName;
    }

    @Override
    public int evaluate (IParserProvider w) {        
        VariableManager vm = w.getVariables();

        // Evaluate variables with current context.
        int[] curVariables = new int[myCommand.getNumArgs()];
        for (int i = 0; i < myCommand.getNumArgs(); i++)
        {
            int val = myArguments.get(i).evaluate(w);
            curVariables[i] = val;
        }

        // Create new Scope of Variables
        vm.createVariableScope(getName());

        for (int i = 0; i < myCommand.getNumArgs(); i++)
        {
            VariableNode vn = myCommand.getArgs().get(i);
            vm.setVariable(vn.getName(), curVariables[i]);
        }

        int ret = myCommand.getCommands().evaluate(w);

        vm.revertVariableScope();
        return ret;
    }

}
