package parser;

import java.util.ArrayList;
import java.util.List;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;
import parser.nodes.VariableNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * 
 * @author Jonathan Schmidt
 * 
 */
public class CustomCommand {

    private String myName;

    private int myArgCount;
    private List<VariableNode> myArgs;
    private ListNode myCommands;

    public CustomCommand (String name, int argcount)
    {
        myName = name;
        myArgCount = argcount;
    }

    public void addParserInfo (ListNode params, ListNode commands)
    {
        myArgs = new ArrayList<VariableNode>();
        myCommands = commands;
        for (SyntaxNode s : params.getContents())
        {
            // check syntax here
            if (!(s instanceof VariableNode))
                throw new InvalidArgumentsException(
                                                    "The list of arguments does not exclusively contain variables.",
                                                    "");
            myArgs.add((VariableNode) s);
        }
    }

    /**
     * @return the name
     */
    public String getName () {
        return myName;
    }

    /**
     * @return the commands
     */
    public ListNode getCommands () {
        return myCommands;
    }

    public int getNumArgs ()
    {
        return myArgCount;
    }

    /**
     * @return the args
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
    public boolean isCreated ()
    {
        return myArgs != null;
    }

}
