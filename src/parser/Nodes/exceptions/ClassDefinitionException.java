package parser.nodes.exceptions;

/**
 * This exception is for when an item is not defined correctly.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class ClassDefinitionException extends Exception {

    /**
     * Invalid Constructor
     */
    public static final String INVALID_CONSTRUCTOR =
            "The following class did not have a constructor that " +
                    "took in a Deque as a parameter: %s";

    private static final long serialVersionUID = 1L;

    /**
     * Create Exception
     * 
     * @param arg0 The error message.
     */
    public ClassDefinitionException (String arg0) {
        super(String.format(INVALID_CONSTRUCTOR, arg0));
    }
}
