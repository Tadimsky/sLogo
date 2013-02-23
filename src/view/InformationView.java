package View;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;

public class InformationView extends JComponent implements Observer {
    
    public final static Dimension VIEW_DIMENSION = new Dimension(300, 600);
    
    public InformationView(){
        setSize(VIEW_DIMENSION);
        setPreferredSize(VIEW_DIMENSION);
    }

    @Override
    public void update (Observable o, Object arg) {
        // TODO Auto-generated method stub
        
    }

}
