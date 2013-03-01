package parser;

import java.util.ArrayList;
import java.util.List;
import parser.Nodes.ListNode;
import parser.Nodes.SyntaxNode;
import parser.Nodes.VariableNode;
import parser.Nodes.exceptions.InvalidArgumentsException;

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
        myArgs = new ArrayList<>();
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
