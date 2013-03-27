package view.windows;

import java.util.Observable;
import javax.swing.JTextField;
import controller.Workspace;


/**
 * Window shows user-defined procedures currently available in a workspace,
 * directly clickable to execute
 * 
 * @author Ziqiang Huang
 * 
 */
@SuppressWarnings("serial")
public class CustomCommandWindow extends LogoListWindow {

    public CustomCommandWindow (JTextField inputField) {
        super(inputField);

    }

    @Override
    public void update (Observable object, Object arg1) {
        Workspace w = (Workspace) object;
        super.myCommandsVector.clear();
        for (String commands : w.getCommandMap().keySet()) {
            addCommand(commands);
        }

    }

}
