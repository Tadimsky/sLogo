package controller;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import parser.nodes.CommandNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import view.Canvas;
import view.InformationView;
import model.Turtle;


public class Workspace implements IParserProvider  {
	// some methods and instances (such as the variable map) are commmented
	// out, once figure out how to implement, uncommenting them might save
	// some time
	public static final String WORKSPACE_NAME = "Workspace ";
	//test
    private Map<String,Integer> myVariableMap;
    private Map<String,Integer> myCommandMap;
	private Turtle myTurtle;
	private int myIndex;

    public Workspace () {
        // test save and load
        myVariableMap = new HashMap<String, Integer>();
        myCommandMap = new HashMap<String, Integer>();
        myTurtle = new Turtle();
    }

    public Workspace (int index) {
        this();
        myIndex = index;
    }

    public void handleCommand () {
        // TODO
    }

    public Map getVariableMap () {
        return myVariableMap;
    }

    public Map getCommandMap () {
        return myCommandMap;
    }

    public Turtle getTurtle () {
        return myTurtle;
    }

    public Integer getVariable (String var) throws InvalidArgumentsException
    {
        if (myVariableMap.containsKey(var))
        {
            return myVariableMap.get(var);
        }
        throw new InvalidArgumentsException("This variable does not exist.", "");
    }

    public void setVariable (String var, Integer val)
    {
        myVariableMap.put(var, val);
    }

    public int getIndex () {
        return myIndex;
    }

    @Override
    public void addCommand (String command, CommandNode com) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public CommandNode getCommand (String command) {
        // TODO Auto-generated method stub
        return null;
    }
}
