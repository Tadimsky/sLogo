package parser;

import java.awt.Color;
import java.util.Map;
import view.ColorManager;
import model.Turtle;

/**
 * 
 * @author Jonathan Schmidt
 *
 */
public interface IParserProvider {

    public void addCommand (CustomCommand com);
    
    public CustomCommand getCommand (String command);
    
    public VariableManager getVariables();
    
    public ColorManager getColors();
    
    public Turtle getTurtle ();    

    public int setBackground(int colorIndex);
}
