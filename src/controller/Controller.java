package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import parser.*;
import parser.nodes.SyntaxNode;
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
    private int workspaceIndex = 1;
    private Workspace myWorkspace;
    private ArrayList<Workspace> myWorkspaces;
    private Window myWindow;
    private Parser myParser;

    public Controller () {
        myWorkspaces = new ArrayList<Workspace>();
        // myWindow = new Window(this);
    }

    /**
     * This method is called whenever the run button or enter is pressed
     * 
     * @param command string in input Text Field
     */
    public void processCommand (String command, Canvas canvas) {
        System.out.println(command);
        canvas.getTurtle().setColor(Color.RED);
        myParser.parseCommand(command);
    }
    
    public Workspace selectWorkspace (int index) {
        try {
            for (Workspace ws : myWorkspaces) {
                if (ws.getIndex() == index) { return ws; }
            }
        }
        catch (Exception e) {
            System.out.println("no such workspace exists");
        }
        return null;
    }
   
    /**
     * Parses command and returns a list of syntax nodes.
     * @return
     */

    public void executeCommand(SyntaxNode syntaxNode) {
    	syntaxNode.evaluate(myWorkspace);
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
