package parser.nodes;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import parser.CustomCommand;
import parser.IParserProvider;
import parser.SemanticsTable;
import parser.VariableManager;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * The base class for all custom commands
 * 
 * @author Jonathan Schmidt
 * 
 */
public class CommandNode extends SimpleNode {

    private String myName;
    private CustomCommand myCommand;

    private List<SyntaxNode> myArguments;

    /**
     * Creates a custom command with the specified name
     * 
     * @param val The name of the custom command.
     */
    public CommandNode (String val) {
        myName = val;
        myCommand = SemanticsTable.getInstance().getCommand(myName);
        myArguments = new ArrayList<SyntaxNode>();
    }

    /**
     * Creates a custom command
     * 
     * @param stack List of all parameters in the parser
     */
    public CommandNode (Deque<SyntaxNode> stack) {
        this(((TokenNode) stack.pop()).getToken());

        // Command does not exist right now - return
        if (myCommand == null) { 
            return;
        }

        if (myCommand.getNumArgs() <= stack.size()) {
            for (int i = 0; i < myCommand.getNumArgs(); i++) {
                myArguments.add(stack.pop());
            }
        }
    }

    /**
     * 
     * @return The list of Arguments that the command has.
     */
    public List<SyntaxNode> getArguments () {
        return myArguments;
    }

    /**
     * 
     * @return The name of the Custom Command
     */
    public String getName () {
        return myName;
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        VariableManager vm = w.getVariables();

        // Evaluate variables with current context.
        int[] curVariables = new int[myCommand.getNumArgs()];
        for (int i = 0; i < myCommand.getNumArgs(); i++) {
            int val = myArguments.get(i).evaluate(w);
            curVariables[i] = val;
        }

        // Create new Scope of Variables
        vm.createVariableScope(getName());

        for (int i = 0; i < myCommand.getNumArgs(); i++) {
            VariableNode vn = myCommand.getArgs().get(i);
            vm.setVariable(vn.getName(), curVariables[i]);
        }

        int ret = myCommand.getCommands().evaluate(w);

        vm.revertVariableScope();
        return ret;
    }

}
