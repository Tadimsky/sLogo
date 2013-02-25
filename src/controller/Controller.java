package controller;

import java.util.ArrayList;
import java.util.List;
import view.Window;

public class Controller {
    List<Workspace> myWorkspaces;
    Window myWindow;
    private static final String WORKSPACE_NAME = "Workspace ";
    private int workspaceIndex = 1;

    public Controller () {
        myWorkspaces = new ArrayList<Workspace>();
        myWindow = new Window(this);      
    }
    
    public void createWorkspace(){
        Workspace workspace = new Workspace(WORKSPACE_NAME + workspaceIndex);
        workspaceIndex++;
        myWorkspaces.add(workspace);
        myWindow.setWorkspace(workspace);
    }
    
    public void processCommand(String command){
        System.out.println(command);
        myWindow.getWorkspace().getTurtle().move(100);
    }
    
    // Use to test the view
    public static void main(String[] args) {
        new Controller();
    }
}
