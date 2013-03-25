package parser.nodes.exceptions;

public class InvalidSemanticsException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidSemanticsException (String error, String cause)
    {
        super(String.format(error, cause));
    }

}
