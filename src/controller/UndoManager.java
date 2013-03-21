package controller;

import java.util.List;
import java.util.Stack;

import parser.CustomCommand;

//Might use string instead of CustomCommand
public class UndoManager {
    private Stack<CustomCommand> undoneCommands; //stores all undone commands
    private Stack<CustomCommand> myActiveCommands; //stores list of commands
    
    public UndoManager(){
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
