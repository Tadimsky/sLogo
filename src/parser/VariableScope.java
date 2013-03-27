package parser;

import java.util.HashMap;
import java.util.Map;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Represents a Variable Scope for use in the Variable Manager.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class VariableScope {
    private String myName;

    private Map<String, Integer> myVariables;

    /**
     * Creates a new instance of a Variable Scope
     * 
     * @param name The name of the scope
     */
    public VariableScope (String name) {
        myName = name;
        myVariables = new HashMap<String, Integer>();
    }

    @Override
    public boolean equals (Object o) {
        if (o instanceof VariableScope) {
            VariableScope ot = (VariableScope) o;
            return myName.equals(ot.getName());
        }
        if (o instanceof String) return myName.equals(o);
        return false;
    }

    /**
     * Set the specified variable to the value.
     * 
     * @param variable The name of the variable.
     * @param value The value to set.
     */
    public void setVariable (String variable, int value) {
        myVariables.put(variable, value);
    }

    /**
     * Returns the value of the variable.
     * 
     * @param var The name of the variable
     * @return The value of the variable
     * @throws InvalidArgumentsException When the variable does not exist
     */
    public Integer getVariable (String var) throws InvalidArgumentsException {
        if (myVariables.containsKey(var)) return myVariables.get(var);

        throw new InvalidArgumentsException("This variable does not exist: %s", var);
    }

    /**
     * Removes a variable from the scope.
     * 
     * @param var The name of the variable to remove.
     * @return
     */
    public Integer removeVariable (String var) {
        return myVariables.remove(var);
    }

    /**
     * Returns the map of variables for use externally.
     * 
     * @return The map of variables.
     */
    public Map<String, Integer> getVariables () {
        return myVariables;
    }

    /**
     * Returns whether the scope contains a variable.
     * 
     * @param name The name of the variable.
     * @return Whether it exists or not.
     */
    public boolean containsVariable (String name) {
        return myVariables.containsKey(name);
    }

    /**
     * @return the name of the scope
     */
    public String getName () {
        return myName;
    }
}
