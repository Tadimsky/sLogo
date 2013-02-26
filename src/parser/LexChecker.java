package parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates the input format of the input and splits into tokens.
 * 
 * @author Jonathan Schmidt
 *
 */
public class LexChecker {
    
    public static List<String> splitTokens(String s)
    {
        ArrayList<String> tokens = new ArrayList<String>();        
        
        // Regex check for non ASCII character
        Pattern val = Pattern.compile("[^\\x00-\\x7F]");
        Matcher m = val.matcher(s);
        if (m.matches())
        {
            // Throw Exception
            String problem = s.substring(m.start(), m.end());
        }
        
        StringTokenizer split = new StringTokenizer(s, " ");
        while (split.hasMoreTokens())            
        {   
            tokens.add(split.nextToken());
        } 
        return tokens;
    }
    
}
