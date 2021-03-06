package view.windows;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ColorManager;
import controller.Workspace;


/**
 * Window that holds settings in which the user can choose
 * for a given workspace
 * 
 * @author Henrique Moraes
 * 
 */
@SuppressWarnings("serial")
public class WorkspaceSettingsWindow extends SettingsWindow {
    private JComboBox myBackgroundColor;
    private JComboBox myPenColor;

    private JCheckBox myHighlightOption;

    public WorkspaceSettingsWindow (Workspace w) {
        super(w);

        ColorManager manager = myWorkspace.getColors();

        myOptionsPanel.add(createColorPanel(manager));
        myOptionsPanel.add(createCheckBoxPanel());
        addOkButtonListener();

        pack();
    }

    /**
     * @param colorMap Map to reference colors
     * @return Panel with color options
     */
    private JPanel createColorPanel (ColorManager manager) {
        myBackgroundColor = new JComboBox(manager.getColorMap().values().toArray());
        myPenColor = new JComboBox(manager.getColorMap().values().toArray());

        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(4, 1));
        colorPanel.add(new JLabel("Background Color"));
        colorPanel.add(myBackgroundColor);
        colorPanel.add(new JLabel("Pen Color"));
        colorPanel.add(myPenColor);

        colorPanel.setBorder(BorderFactory.createTitledBorder(
                                                              BorderFactory.createEtchedBorder(),
                                                              "Colors"));
        return colorPanel;
    }

    /**
     * @return Panel with checkbox options
     */
    private JPanel createCheckBoxPanel () {
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(3, 1));
        checkBoxPanel.add(myHighlightOption = new JCheckBox("Highlight Active Turtles"));
        myHighlightOption.setSelected(myWorkspace.getHighlighted());
        checkBoxPanel.add(new JCheckBox("Hide All Turtles"));
        checkBoxPanel.add(new JCheckBox("Raise Pen of All Turtles"));

        checkBoxPanel
                .setBorder(BorderFactory.createTitledBorder(
                                                            BorderFactory.createEtchedBorder(),
                                                            "Turtle Settings"));
        return checkBoxPanel;
    }

    @Override
    protected void addOkButtonListener () {
        myOkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                myWorkspace.getCanvas().setBackgroundColor((Color) myBackgroundColor
                        .getSelectedItem());
                myWorkspace.setHighlighted(myHighlightOption.isSelected());
                dispose();
            }
        });
    }

    @Override
    protected void setTitle () {
        setTitle("Workspace Settings");
    }

}
