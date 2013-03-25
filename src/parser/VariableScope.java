package parser;

import java.util.HashMap;
import java.util.Map;
import parser.nodes.exceptions.InvalidArgumentsException;


public class VariableScope {
    private String myName;

    private Map<String, Integer> myVariables;

    /**
     * @return the name
     */
    public String getName () {
        return myName;
    }

    public VariableScope (String name)
    {
        myName = name;
        myVariables = new HashMap<String, Integer>();
    }

    @Override
    public boolean equals (Object o)
    {
        if (o instanceof VariableScope)
        {
            VariableScope ot = (VariableScope) o;
            return myName.equals(ot.getName());
        }
        if (o instanceof String) return myName.equals(o);
        return false;
    }

    public void setVariable (String variable, int value) {
        myVariables.put(variable, value);
    }

    public Integer getVariable (String var) throws InvalidArgumentsException
    {
        if (myVariables.containsKey(var)) return myVariables.get(var);

        throw new InvalidArgumentsException("This variable does not exist: %s", var);
    }

    public Integer removeVariable (String var)
    {
        return myVariables.remove(var);
    }

    public Map<String, Integer> getVariables () {
        return myVariables;
    }

    public boolean containsVariable (String name) {
        return myVariables.containsKey(name);
    }
}
