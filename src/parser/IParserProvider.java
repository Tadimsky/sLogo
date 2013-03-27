package parser;

import model.ColorManager;
import model.Turtle;
import model.TurtleManager;


/**
 * This is what is required for the parser to execute all its commands.
 * It is the interface that provides access to the context below.
 * 
 * @author Jonathan Schmidt
 * 
 */
public interface IParserProvider {

    /**
     * Add a custom command to the current context.
     * 
     * @param com The custom command to add
     */
    void addCommand (CustomCommand com);

    /**
     * Returns the Custom Command from the current context that
     * has the name of the command specified.
     * 
     * @param command The command name to look up
     * @return The custom command result.
     */
    CustomCommand getCommand (String command);

    /**
     * Returns the Variable Manager of the context.
     * 
     * @return The Variable Manager
     */
    VariableManager getVariables ();

    /**
     * Returns the Turtle Manager of the context.
     * 
     * @return The Turtle Manager
     */
    TurtleManager getTurtleManager ();

    /**
     * Returns the current turtle of the context.
     * This is being phased out to deal with multiple turtles.
     * 
     * @return The current turtle
     */
    Turtle getTurtle ();

    /**
     * Returns the Color Manager of the context.
     * 
     * @return The Color Manager
     */
    ColorManager getColors ();

    /**
     * Sets the background of the current context to the
     * color specified by the index.
     * 
     * @param colorIndex The index of the color
     * @return
     */
    int setBackground (int colorIndex);
}
