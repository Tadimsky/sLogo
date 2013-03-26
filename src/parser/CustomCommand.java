package parser;

import java.util.ArrayList;
import java.util.List;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;
import parser.nodes.VariableNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Contains all the Information of a custom command.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class CustomCommand {

    private static final String LIST_ARGS_VAR = "The list of arguments does not exclusively contain variables.";

    private String myName;

    private int myArgCount;
    private List<VariableNode> myArgs;
    private ListNode myCommands;

    /**
     * Create a Custom Command with the specified name and number of
     * arguments.
     * 
     * @param name The name of the command
     * @param argcount The number of arguments
     */
    public CustomCommand (String name, int argcount) {
        myName = name;
        myArgCount = argcount;
    }

    /**
     * Adds the information from the parser to this method.
     * 
     * @param params ListNode of parameters
     * @param commands ListNode of commands to run that define this command
     * @throws InvalidArgumentsException
     */
    public void addParserInfo (ListNode params, ListNode commands) throws InvalidArgumentsException {
        myArgs = new ArrayList<VariableNode>();
        myCommands = commands;
        for (SyntaxNode s : params.getContents()) {
            // check syntax here
            if (!(s instanceof VariableNode)) {
                throw new InvalidArgumentsException(LIST_ARGS_VAR, "");
            }
            myArgs.add((VariableNode) s);
        }
    }

    /**
     * @return the name of the custom command
     */
    public String getName () {
        return myName;
    }

    /**
     * @return the commands to run for this command
     */
    public ListNode getCommands () {
        return myCommands;
    }

    /**
     * 
     * @return The number of arguments
     */
    public int getNumArgs () {
        return myArgCount;
    }

    /**
     * @return the Arguments of this command
     */
    public List<VariableNode> getArgs () {
        return myArgs;
    }

    /**
     * Returns true if this command has actually been created.
     * As in all the properties have been added to it.
     * 
     * @return
     */
    public boolean isCreated () {
        return myArgs != null;
    }
}
