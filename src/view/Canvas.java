package View;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;

public class Canvas extends JComponent implements Observer{
    public final static Dimension CANVAS_DIMENSION = new Dimension(700, 600);
    
    public Canvas(){
        setSize(CANVAS_DIMENSION);
        setPreferredSize(CANVAS_DIMENSION);
    }
    
    @Override
    public void update (Observable arg0, Object arg1) {
        // TODO Auto-generated method stub
        
    }

}
