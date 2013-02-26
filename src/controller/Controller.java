package controller;

import java.util.ArrayList;
import java.util.List;
import view.Canvas;
import view.Window;

public class Controller {
    //List<Workspace> myWorkspaces;
    //Window myWindow;
//    private static final String WORKSPACE_NAME = "Workspace ";
//    private int workspaceIndex = 1;

    public Controller () {
        //myWorkspaces = new ArrayList<Workspace>();
        //myWindow = new Window(this);      
    }
    
//    /**
//     * Creates a new workspace and handles its place in Window
//     * This method is called whenever new is selected on the file menu
//     */
//    public void createWorkspace(){
//        Canvas canvas = new Canvas(WORKSPACE_NAME + workspaceIndex);
//        workspaceIndex++;
//        //myWorkspaces.add(workspace);
//        myWindow.setWorkspace(workspace);
//        workspace.getTurtle().update();
//    }
    
    /**
     * This method is called whenever the run button or enter is pressed 
     * @param command string in input Text Field
     */
    public void processCommand(String command, Canvas canvas){
        System.out.println(command);
        canvas.getTurtle().turnTo(90);
    }
    
//    // Use to test the view
//    public static void main(String[] args) {
//        new Controller();
//    }
}
