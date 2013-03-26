package parser.nodes.exceptions;

/**
 * This exception is for errors in the creation of nodes in the parser.
 * *
 * 
 * @author Jonathan Schmidt
 * 
 */
public class InvalidSemanticsException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Creates the Exception.
     * 
     * @param error The error message.
     * @param cause The cause of the error.
     */
    public InvalidSemanticsException (String error, String cause) {
        super(String.format(error, cause));
    }

}
