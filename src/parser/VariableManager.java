package parser;


import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import parser.nodes.exceptions.InvalidArgumentsException;


public class VariableManager {

    private VariableScope myGlobal = new VariableScope("GLOBAL");
    
    private Deque<VariableScope> myScopes;

    public VariableManager () {
        myScopes = new LinkedList<VariableScope>();               
        myScopes.add(myGlobal);
    }

    /**
     * Sets the specified variable to be the specified value.
     * 
     * @param variable
     * @param value
     */
    public void setVariable (String variable, int value) {
        myScopes.getFirst().setVariable(variable, value);
    }
    /**
     * Returns the value of the variable specified.
     * The variable is returned from the most local scope.
     * 
     * @param var
     * @return
     * @throws InvalidArgumentsException
     */
    public Integer getVariable (String  var) throws InvalidArgumentsException
    {
        // Get from current scope first, if it is local.
        Iterator<VariableScope> i = myScopes.iterator();
        while (i.hasNext())
        {            
            try 
            {          
                return i.next().getVariable(var);
            }
            catch (InvalidArgumentsException ie)
            {   
                continue;
            }
        }
        throw new InvalidArgumentsException("Argument does not exist", var);
    }

    /**
     * Creates a new scope for the Variable Manager.
     * Makes sure it has a unique value.
     * 
     * @param vs
     */
    public void createVariableScope (String  vs) {        
        String curName = vs;
        int index = 0;
        while (myScopes.contains(curName))
        {
            index++;
            curName = vs + "_" + index;
        }        
        myScopes.addFirst(new VariableScope(curName));
    }
    
    /**
     * Sets the scope to be the previous scope.
     * This will be called at the end of custom function.
     * 
     * @param prev
     */
    public void revertVariableScope()
    {
        myScopes.removeFirst();
    }

}
