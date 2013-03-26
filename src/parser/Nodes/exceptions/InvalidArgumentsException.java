package parser.nodes.exceptions;

/**
 * Exception for incorrect arguments in the parser.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class InvalidArgumentsException extends Exception {

    /**
     * Too few arguments message
     */
    public static final String INCORRECT_NUMBER_ARGS =
            "Too few arguments were supplied to this function: %s";

    private static final long serialVersionUID = 1176528878943897282L;

    /**
     * Creates an InvalidArgumentsException from two strings.
     * 
     * @param error The error message
     * @param function The token that caused the error
     */
    public InvalidArgumentsException (String error, String function) {
        super(String.format(error, function));
    }
}
