package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import parser.exceptions.InvalidLexiconException;

/**
 * Validates the input format of the input and splits into tokens.
 * 
 * @author Jonathan Schmidt
 *
 */
public class LexChecker {
    
    /**
     * Takes in a string of commands and splits it into tokens.
     * Checks for invalid (non-ASCII) characters.
     * 
     * @param s The string to parse.
     * @return A list of String tokens.
     * @throws Exception 
     */
    public static List<String> splitTokens(String s)
    {
        ArrayList<String> tokens = new ArrayList<String>();       
        
        // Regex check for non ASCII character
        Pattern val = Pattern.compile("[^\\x00-\\x7F]");
        Matcher m = val.matcher(s);
        if (m.matches())
        {
            String problem = s.substring(m.start(), m.end());
            throw new InvalidLexiconException("You have entered invalid characters: {0}", problem);
        }
        
        StringTokenizer split = new StringTokenizer(s, " ");
        while (split.hasMoreTokens())            
        {   
            tokens.add(split.nextToken());
        } 
        return tokens;
    }
    
}
