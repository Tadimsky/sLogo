package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.TurtleManager;
import view.labels.TitleLabel;
import controller.Workspace;

/**
 * Window that holds settings in which the user can choose 
 * for a given workspace
 * @author Henrique
 *
 */
public class SettingsWindow extends JFrame{
    private static final Dimension DEFAULT_DIMENSION = new Dimension(450,300);
    
    private JComboBox myBackgroundColor;
    private JComboBox myPenColor;
    private JCheckBox myHideOption;
    private JCheckBox myPenUpOption;
    private JCheckBox myHighlightOption;
    private Workspace myWorkspace;
    
    public SettingsWindow(Workspace w) {
        this.setPreferredSize(DEFAULT_DIMENSION);
        
        myWorkspace = w;
        ColorManager manager = myWorkspace.getColors();
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1,2));
        
        optionsPanel.add(createColorPanel(manager));   
        optionsPanel.add(createCheckBoxPanel());
        
        add(createTitle());
        add(optionsPanel);
        add(createButtonPanel());    
  
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        centralize();
        setResizable(false);
        setTitle("Workspace Settings");
        pack();
        setVisible(true);
    }
    
    /**
     * @param colorMap Map to reference colors
     * @return Panel with color options
     */
    private JPanel createColorPanel(ColorManager manager) {
        myBackgroundColor = new JComboBox(manager.getColorMap().values().toArray());
        myPenColor = new JComboBox(manager.getColorMap().values().toArray());
        
        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(4,1));
        colorPanel.add(new JLabel("Background Color"));
        colorPanel.add(myBackgroundColor);
        colorPanel.add(new JLabel("Pen Color"));
        colorPanel.add(myPenColor);
        
        colorPanel.setBorder(BorderFactory.createTitledBorder(
                   BorderFactory.createEtchedBorder(), "Colors"));
        return colorPanel;
    }
    
    /**
     * @return Panel with checkbox options
     */
    private JPanel createCheckBoxPanel() {
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(3,1));
        checkBoxPanel.add(myHighlightOption = new JCheckBox("Highlight Active Turtles"));
        checkBoxPanel.add(myHideOption = new JCheckBox("Hide All Turtles"));
        checkBoxPanel.add(myPenUpOption = new JCheckBox("Raise Pen of All Turtles"));

        checkBoxPanel.setBorder(BorderFactory.createTitledBorder(
                   BorderFactory.createEtchedBorder(), "Turtle Settings"));
        return checkBoxPanel;
    }
    
    /**
     * @return Panel with ok button
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(createButton(this));
        return buttonPanel;
    }
    
    /**
     * @return Panel containing title for this frame
     */
    private JPanel createTitle() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        JLabel title = new JLabel("Choose Your Options");
        title.setFont(TitleLabel.TITLE_FONT);
        titlePanel.add(title);
        titlePanel.add(Box.createRigidArea(new Dimension(0,50)));
        return titlePanel;
    }
    
    /**
     * Sets the opening location of this window
     */
    private void centralize() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (dim.width-DEFAULT_DIMENSION.width)/2;
        int y = (dim.height-DEFAULT_DIMENSION.height)/2;

        setLocation(x, y);
    }
    
    /**
     * Creates a button and adds an actionlistener to it
     * @param s this settings window
     * @return Ok Button for this window
     */
    private JButton createButton(final SettingsWindow s) {
        JButton button = new JButton("Ok");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                TurtleManager manager = s.myWorkspace.getTurtleManager();
                s.myWorkspace.getCanvas().setBackgroundColor((Color) s.myBackgroundColor.getSelectedItem());
                manager.setHighlighted(s.myHighlightOption.isSelected());
                s.dispose();       
            }   
        });
        return button;
    }

}
