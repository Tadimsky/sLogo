package parser;

import java.util.List;

public class Parser {

    private SemanticsChecker mySemantics;
    
    public Parser () {
        mySemantics = new SemanticsChecker();
    }
    
    public void parseCommand(String command)
    {
        if (command.isEmpty()) {
            return;
        }
        
        List<String> tokens = LexChecker.splitTokens(command);
        mySemantics.evaluateExpression(tokens);
    }

}
