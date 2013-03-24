package controller;

import java.util.List;
import java.util.Stack;

import javax.swing.undo.UndoManager;

import parser.CustomCommand;

/**
 * Manages undo and redo commands within workspace. Note that undo function may apply to multiple turtles.
 * 
 * @author XuRui
 *
 */
//Might use string instead of CustomCommand

public class WSUndoManager extends UndoManager {
    private Stack<CustomCommand> undoneCommands; //stores all undone commands
    private Stack<CustomCommand> myActiveCommands; //stores list of commands
    
    public WSUndoManager(){
    	super();
    	myActiveCommands = new Stack<CustomCommand>();
    	undoneCommands = new Stack<CustomCommand>();
    }
    
    /**
     * Returns list of all active commands to be reloaded in undo.
     * @return
     */
    public List<CustomCommand> getActiveCommands(){
    	//undo command i.e. reload all commands in commandHistory
    	undoneCommands.add(myActiveCommands.pop());
    	return myActiveCommands;
    }
    
    /**
     * Returns last undone command to be redone in workspace.
     * @return
     */
    public CustomCommand getLastUndoneCommand(){
    	CustomCommand lastUndo = undoneCommands.pop();  
    	return lastUndo;
    	
    }
}
