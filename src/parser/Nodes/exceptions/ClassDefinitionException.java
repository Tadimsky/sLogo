package parser.nodes.exceptions;

public class ClassDefinitionException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String INVALID_CONSTRUCTOR =
            "The following class did not have a constructor that took in a Deque as a parameter: {0}";

    public ClassDefinitionException (String arg0) {
        super(String.format(INVALID_CONSTRUCTOR, arg0));
    }
}
