package parser;

import java.awt.Color;
import java.util.Map;
import view.ColorManager;
import model.Turtle;
import model.TurtleManager;

/**
 * 
 * @author Jonathan Schmidt
 *
 */
public interface IParserProvider {

    public void addCommand (CustomCommand com);
    
    public CustomCommand getCommand (String command);
    
    public VariableManager getVariables();
    
    public TurtleManager getTurtleManager();
    
    public Turtle getTurtle ();

    public ColorManager getColors();

    public int setBackground(int colorIndex);

    public void update();

    void addColor (int colorIndex, Color color);

    void addHistory (String s);    
}
