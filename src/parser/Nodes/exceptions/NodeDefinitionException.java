package parser.nodes.exceptions;

public class NodeDefinitionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public static final String REQUIRED_PROPERTY =
            "The following property was not in the properties file but is required: %s";
    public static final String MISSING_CLASS =
            "The following class was defined in the properties file but does not exist: %s";
    public static final String MISSING_CONSTRUCTOR =
            "The following class did not have a constructor that took in a Deque as a parameter: %s";

    public NodeDefinitionException (String message, String problem) {
        super(String.format(problem, message));
    }
}
