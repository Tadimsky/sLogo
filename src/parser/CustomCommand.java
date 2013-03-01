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
    
    

    private List<VariableNode> myArgs;
    private ListNode myCommands;
    
    public CustomCommand (String name, ListNode params, ListNode commands) {
        myName = name;
        myArgs = new ArrayList<VariableNode>();
        myCommands = commands;
        for (SyntaxNode s : params.getContents())
        {
            // check syntax here
            if (!(s instanceof VariableNode))
            {
                throw new InvalidArgumentsException("The list of arguments does not exclusively contain variables.", "");
            }
            myArgs.add((VariableNode)s);
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
    
    public int getNumArgs()
    {
        return myArgs.size();
    }

}
