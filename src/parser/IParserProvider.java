package parser;

import model.Turtle;
import parser.VariableManager;

/**
 * 
 * @author Jonathan Schmidt
 *
 */
public interface IParserProvider {
    
    public void addCommand (CustomCommand com);
    
    public CustomCommand getCommand (String command);
    
    public VariableManager getVariables();
    
    public Turtle getTurtle ();
}
