package view.components;

public enum Error {
    INVALID_FILE (ErrorBox.ERROR_RESOURCE.getString("InvalidFile")),
    INVALID_IMAGE (ErrorBox.ERROR_RESOURCE.getString("InvalidImage")),
    NO_WORKSPACE(ErrorBox.ERROR_RESOURCE.getString("NoWorkspaceYet"));
    
    private final String errorMessage;
    Error(String error){
        errorMessage = error;
    }
    String getMessage(){
        return errorMessage;
    }
}
