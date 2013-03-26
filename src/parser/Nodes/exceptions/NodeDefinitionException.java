package parser.nodes.exceptions;

/**
 * This exception is for errors in the entries in the Syntax Table file.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class NodeDefinitionException extends RuntimeException {
    /**
     * Missing Property Message
     */
    public static final String REQUIRED_PROPERTY =
            "The following property was not in the properties file but is required: %s";
    /**
     * Missing Class Message
     */
    public static final String MISSING_CLASS =
            "The following class was defined in the properties file but does not exist: %s";

    /**
     * Invalid Constructor Message
     */
    public static final String MISSING_CONSTRUCTOR =
            "The class %s did not have a constructor that took in a Deque as a parameter.";

    private static final long serialVersionUID = 1L;

    /**
     * Creates the Exception
     * 
     * @param message The error message
     * @param problem The cause of the problem
     */
    public NodeDefinitionException (String message, String problem) {
        super(String.format(problem, message));
    }
}
