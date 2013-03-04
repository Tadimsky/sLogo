package parser;

import java.util.List;
import java.util.Scanner;
import parser.nodes.SyntaxNode;

/**
 * The main class for parsing commands into usable things.
 * Uses the Lex Checker and Semantics Checker to build abstract syntax trees.
 * 
 * @author Jonathan Schmidt
 *
 */
public class Parser {

    private SemanticsChecker mySemantics;
    
    /**
     * Creates a new instance of the parser.
     */
    public Parser () {
        mySemantics = new SemanticsChecker();
    }
 
    /**
     * Parses a string command and turns it into a list of trees.
     * Each SyntaxNode is the head of a tree that represents a command.
     *  
     * @param command The string input that the parser parses.
     * @return a list of roots of each tree.
     */
    public List<SyntaxNode> parseCommand(String command)
    {
        if (command.isEmpty()) {
            return null;
        }
        
        List<String> tokens = LexChecker.splitTokens(command);
        List<SyntaxNode> nodes = mySemantics.evaluateExpression(tokens);
        return nodes;
    }
    
    public List<SyntaxNode> parseCommand(Scanner s)
    {
        StringBuilder fullCommand = new StringBuilder();
        while (s.hasNext())            
        {
            String l = s.nextLine();
            if (l.contains("#"))
            {
                continue;
            }       
            fullCommand.append(l.trim());
            fullCommand.append(" ");            
        }
        return parseCommand(fullCommand.toString());
    }

}
