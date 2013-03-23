package parser;

import java.awt.Color;
import java.util.Map;
import model.Turtle;
import model.TurtleManager;

/**
 * 
 * @author Jonathan Schmidt
 *
 */
public interface IParserProvider {
    
    public Map<Integer, Color> getPalette();

    public void addCommand (CustomCommand com);
    
    public CustomCommand getCommand (String command);
    
    public VariableManager getVariables();
    
    public TurtleManager getTurtleManager ();

    public void addColor(int colorIndex, Color color);

    public int setBackground(int colorIndex);
}
