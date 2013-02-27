package controller;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import view.Canvas;
import view.InformationView;
import model.Turtle;


public class Workspace  {
	// some methods and instances (such as the variable map) are commmented
	// out, once figure out how to implement, uncommenting them might save
	// some time
	private Map<variable,value> myVariableMap;
	private Map<command,syntax> myCommandMap;
	public static final String WORKSPACE_NAME = "Workspace ";
	private Turtle myTurtle;
	private int myIndex;



	public Workspace(int index){
		myIndex = index;
		myVariableMap = new HashMap<variable, value>();
	}
	public void handleCommand() {
		// TODO
	}
	public Map getVariableMap(){
		return myVariableMap;
	}

	public Map getCommandMap() {
		return myCommandMap;
	}

	public void addVariable(String variable, int value) {
		myVariableMap.put(variable, value);
	}

	public void addCommand(String command, Node syntax) {
		return myCommandMap.put(command, syntax);
	}

	public Turtle getTurtle(){
		return myTurtle;
	}
	public Integer getVariable(String var)
	{
		return 0;
	}

	public void setVariable(String var, Integer val)
	{

	}

	public int getIndex(){
		return myIndex;
	}
}
