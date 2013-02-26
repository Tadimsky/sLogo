package view.components;

import java.util.ResourceBundle;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import view.Window;

public abstract class ErrorBox extends JComponent{
    private static Window WINDOW = null;
    private static final String DEFAULT_ERROR_RESOURCE_PACKAGE = "resources.error."; 
    public static final ResourceBundle ERROR_RESOURCE = 
            ResourceBundle.getBundle(DEFAULT_ERROR_RESOURCE_PACKAGE + "ErrorEnglish");
    
    public static void showError(Error e){
        JOptionPane.showMessageDialog(WINDOW, e.getMessage(), 
                                      ERROR_RESOURCE.getString("ErrorTitle"), 
                                      JOptionPane.ERROR_MESSAGE);
    }
    
    public static void setWindow(Window w){
        WINDOW = w;
    }

}
