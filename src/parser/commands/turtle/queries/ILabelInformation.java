package parser.commands.turtle.queries;

import model.IState;
import parser.nodes.exceptions.InvalidArgumentsException;

public interface ILabelInformation {
    
    /**
     * Used by the view to extract the respective turtle information to 
     * be displayed in the labels
     * @param t IState Turtle to get information
     * @return string value representing the result of the query
     * @throws InvalidArgumentsException
     */
    public String evaluateFromTurtle (IState t) throws InvalidArgumentsException;
}
