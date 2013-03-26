package controller;

import java.util.List;
import java.util.Stack;
import javax.swing.undo.UndoManager;
import model.Turtle;
import parser.nodes.SyntaxNode;


/**
 * Manages undo and redo commands within workspace. Note that undo function may apply to multiple
 * turtles.
 * 
 * @author XuRui
 * 
 */

public class WSUndoManager extends UndoManager {
	private static final long serialVersionUID = 1L;
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
    private List<SyntaxNode> getLastUndoneCommand () {
        return undoneCommands.pop();
    }
    
    /**
     * Returns last command in active commands list
     * @return
     */
    private List<SyntaxNode> getLastCommand(){
    	return myActiveCommands.pop();
    }
    
    /**
     * Called by workspace to add commands to myActiveCommands
     * 
     * @param command
     */
    public void addEditToHistory(List<SyntaxNode> command){
    	myActiveCommands.add(command);
    }
    
    /**
     * Undo the latest command on every single applicable turtle
     */
    @Override
    public void undo(){
        undoneCommands.push(getLastCommand());
        for (Turtle turtle: myWorkspace.getTurtleManager().getTurtles().values()){	
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
    
    /**
     * Redo the last undone command
     */
    @Override
    public void redo(){
    	List<SyntaxNode> redoCommand = getLastUndoneCommand();
    	myActiveCommands.add(redoCommand);
    	myWorkspace.execute((redoCommand));
		myWorkspace.getTurtleManager().update();
    }
}
