package parser.nodes;

import parser.nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

/**
 * This class is used to store the current token we are dealing with on the stack.
 * Used for elements that have no arguments.
 * 
 * @author Jonathan Schmidt
 *
 */
public class TokenNode extends SyntaxNode {

    private String myToken;
   
    public TokenNode (String token) {
        myToken = token;
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {        
        return 0;
    }
    
    /**
     * @return the token
     */
    public String getToken () {
        return myToken;
    }


}
