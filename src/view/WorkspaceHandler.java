package view;

import controller.Workspace;

/**
 * Allows access to methods of creating and getting workspaces
 * @author Henrique Moraes
 *
 */
public interface WorkspaceHandler {
    
    /**
     * @return currently active workspace
     */
    public Workspace getWorkspace();
    
    /**
     * Creates a new Workspace
     */
    public void createWorkspace();
}
