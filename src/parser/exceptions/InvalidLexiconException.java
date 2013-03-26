package parser.exceptions;

/**
 * The InvalidLexiconException is for when there is an error reading in the table of commands.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class InvalidLexiconException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates the Lexicon Exception
     * 
     * @param error The error message.
     * @param cause The cause of the error.
     */
    public InvalidLexiconException (String error, String cause) {
        super(String.format(error, cause));
    }

}
