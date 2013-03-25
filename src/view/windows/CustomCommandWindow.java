package view.windows;

import java.util.Observable;
import java.util.Observer;
import controller.Controller;
import controller.Workspace;

@SuppressWarnings("serial")
public class CustomCommandWindow extends JListCommandWindow implements Observer {

    public CustomCommandWindow(Controller c) {
        super(c);

    }

    @Override
    public void update(Observable object, Object arg1) {
        Workspace w = (Workspace) object;
        super.myCommandsVector.clear();
        for(String commands : w.getCommandMap().keySet()) {
            addCommand(commands);
        }
        
        
    }

}
