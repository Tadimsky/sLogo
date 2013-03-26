package parser;

import java.awt.Color;
import model.ColorManager;
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

    public VariableManager getVariables ();

    public TurtleManager getTurtleManager ();

    public Turtle getTurtle ();

    public ColorManager getColors ();

    public int setBackground (int colorIndex);
}
