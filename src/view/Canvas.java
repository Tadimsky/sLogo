package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Canvas extends JLabel implements Observer {
    private final static Color BACKGROUND = Color.WHITE;
    private final static Dimension SIZE = new Dimension(600,400);
    
    public Canvas () {
        super();
        setPreferredSize(SIZE);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(BACKGROUND);
        g.fillRect(0,0,getWidth(),getHeight());
        
        }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub
        
    }

}
