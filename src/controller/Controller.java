package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import view.Canvas;
import view.Window;

/**
 * Holds list of all workspaces, able to create new workspace
 * Receives input from view and pass them to Parser
 * Receives parsed input and send it to Executor with current workspace as parameter.
 * 
 * @author Xu Rui
 *
 */
public class Controller {

 //   private static final String WORKSPACE_NAME = "Workspace ";
    private int workspaceIndex = 1;
    private Workspace myWorkspace;
   // private ArrayList<Workspace> myWorkspaces;
    private Window myWindow;

    public Controller () {
        myWorkspace = new Workspace();
        //myWindow = new Window(this);      
    }
    
    /**
     * This method is called whenever the run button or enter is pressed 
     * @param command string in input Text Field
     */
    public void processCommand(String command, Canvas canvas){
        System.out.println(command);
        canvas.getTurtle().setColor(Color.RED);
    }
    
    public void executeCommand(){
    	
    }
   /* public Workspace createWorkspace(int index){
    	Workspace newWorkspace = new Workspace(index);
    	myWorkspaces.add(newWorkspace);
    	return newWorkspace;
    }
    
    public Workspace selectWorkspace(int index){
    	try {
    	for (Workspace ws: myWorkspaces){
    		if (ws.getIndex() == index){
    			return ws;
    		}
    	}
    	}catch(Exception e){
    		System.out.println("no such workspace exists");
    	}
		return null;
    }*/
}
