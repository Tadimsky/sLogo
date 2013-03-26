package view.windows;

import java.util.Observable;
import java.util.Observer;
import view.components.InputField;
import controller.Workspace;


/**
 * Window that show commands previously run in the workspace, 
 * directly clickable to execute
 * 
 * @author Ziqiang Huang
 * 
 */

@SuppressWarnings("serial")
public class PreviousCommandWindow extends JListCommandWindow implements Observer{

    public PreviousCommandWindow(InputField myInputField) {
        super(myInputField);
    }

    @Override
    public void update(Observable object, Object arg) {
        Workspace w = (Workspace) object;
        super.myCommandsVector.clear();
        for(String commands : w.getHistory()) {
            addCommand(commands);
        }
        
    }

}
