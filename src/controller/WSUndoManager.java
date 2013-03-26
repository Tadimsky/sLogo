package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.undo.UndoManager;

import model.Turtle;
import parser.CustomCommand;
import parser.nodes.SyntaxNode;


/**
 * Manages undo and redo commands within workspace. Note that undo function may apply to multiple
 * turtles.
 * 
 * @author XuRui
 * 
 */
// Might use string instead of CustomCommand

@SuppressWarnings("serial")
public class WSUndoManager extends UndoManager {

    private Stack<List<SyntaxNode>> undoneCommands; 
    private Stack<List<SyntaxNode>> myActiveCommands; 
    private Workspace myWorkspace;

    public WSUndoManager (Workspace workspace) {
        super();
        myActiveCommands = new Stack<List<SyntaxNode>>();
        undoneCommands = new Stack<List<SyntaxNode>>();
        myWorkspace = workspace;
    }

    /**
     * Returns last undone command to be redone in workspace.
     * 
     * @return
     */
    public List<SyntaxNode> getLastUndoneCommand () {
        return undoneCommands.pop();

    }
    public void addEditToHistory(List<SyntaxNode> command){
    	myActiveCommands.add(command);
    }
    
    @Override
    public void undo(){
    	undoneCommands.push(myActiveCommands.pop());
    	for (Turtle turtle: myWorkspace.getTurtleManager().getActiveTurtles()){	
    		turtle.clear();
    		executeHistory();
    	}
		myWorkspace.getTurtleManager().update();

    }
    
    public void executeHistory(){
    	for (List<SyntaxNode> command: myActiveCommands){
			myWorkspace.execute(command);
		}
    }

    @Override
    public void redo(){
    	List<SyntaxNode> redoCommand = getLastUndoneCommand();
    	myActiveCommands.add(redoCommand);
    	myWorkspace.execute((redoCommand));
		myWorkspace.getTurtleManager().update();
    }
}
