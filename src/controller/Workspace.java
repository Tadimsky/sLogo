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
//	private Map<variable,value> myVariableMap;
//	private Map<command,syntax> myCommandMap;
	public static final String WORKSPACE_NAME = "Workspace ";
	//test
        private Map<String,Integer> myVariableMap;
        private Map<String,Integer> myCommandMap;
	private Turtle myTurtle;
	private int myIndex;

        public Workspace() {
          //test save and load
            myVariableMap = new HashMap<String, Integer>();
            myCommandMap = new HashMap<String, Integer>();
            myVariableMap.put("var1", 1);
            myVariableMap.put("var2", 2);
        }

	public Workspace(int index){
	        this();
		myIndex = index;
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

	public void addCommand(String command, Integer syntax) {
		myCommandMap.put(command, syntax);
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
