package parser.nodes;

import parser.IParserProvider;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * This class is used to store the current token we are dealing with on the stack.
 * Used for elements that have no arguments.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class TokenNode extends SyntaxNode {

    private String myToken;

    /**
     * Creates a Token Node from a String
     * 
     * @param token The Token
     */
    public TokenNode (String token) {
        myToken = token;
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        return 0;
    }

    /**
     * Returns the token.
     * 
     * @return the token
     */
    public String getToken () {
        return myToken;
    }

}
