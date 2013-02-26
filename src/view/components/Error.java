package view.components;

public enum Error {
    INVALID_FILE (ErrorBox.ERROR_RESOURCE.getString("InvalidFile")),
    INVALID_IMAGE (ErrorBox.ERROR_RESOURCE.getString("InvalidImage"));
    
    private final String errorMessage;
    Error(String error){
        errorMessage = error;
    }
    String getMessage(){
        return errorMessage;
    }
}
