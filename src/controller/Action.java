package controller;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

public class Action implements UndoableEdit{
	
	public Action(){
		
	}

	@Override
	public boolean addEdit(UndoableEdit anEdit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canRedo() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canUndo() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPresentationName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRedoPresentationName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUndoPresentationName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSignificant() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void redo() throws CannotRedoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean replaceEdit(UndoableEdit anEdit) {
		return false;
	}

	@Override
	public void undo() throws CannotUndoException {
		// TODO Auto-generated method stub
		
	}
	

}
