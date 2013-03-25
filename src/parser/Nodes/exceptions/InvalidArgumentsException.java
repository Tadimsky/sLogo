package parser.nodes.exceptions;

public class InvalidArgumentsException extends RuntimeException {

    private static final long serialVersionUID = 1176528878943897282L;
    public static final String INCORRECT_NUMBER_ARGS =
            "Too few arguments were supplied to this function: {0}";

    public InvalidArgumentsException (String error, String function) {
        super(String.format(error, function));
    }
}
