package parser.exceptions;

public class InvalidLexiconException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidLexiconException (String error, String cause)
    {
        super(String.format(error, cause));
    }

}
