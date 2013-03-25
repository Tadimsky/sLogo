package view.windows;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import view.components.Strokes;
import controller.Workspace;


/**
 * Allows user to edit graphic related settings such as pen stroke and
 * canvas grid
 * 
 * @author Henrique
 * 
 */
public class GraphicsSettingsWindow extends SettingsWindow {
    private static final Strokes[] STROKES_OPTIONS =
            new Strokes[] { Strokes.SOLID, Strokes.DASHED,
                           Strokes.DASH_AND_DOT, Strokes.DOUBLE_LINE };
    private static final Integer[] THICKNESS_OPTIONS = new Integer[] { 1, 2, 3, 4 };

    private JRadioButton myPenUpButton;
    private JRadioButton myPenDownButton;
    private JCheckBox myEnableGridBox;
    private JTextField mySpacingField;
    private JComboBox myThicknessOption;
    private JComboBox myStrokeTypeOption;

    public GraphicsSettingsWindow (Workspace w) {
        super(w);

        myOptionsPanel.add(createPenPanel());
        myOptionsPanel.add(createGridPanel());
        addOkButtonListener();

        setTitle("Graphics Settings");
        pack();
    }

    /**
     * @return Panel with Stroke options
     */
    private JPanel createPenPanel () {

        JPanel penPanel = new JPanel();
        penPanel.setLayout(new GridLayout(7, 1));
        ButtonGroup group = new ButtonGroup();
        group.add(myPenUpButton = new JRadioButton("Set Active Pens Up"));
        group.add(myPenDownButton = new JRadioButton("Set Active Pens Down"));
        penPanel.add(myPenUpButton);
        penPanel.add(myPenDownButton);

        penPanel.add(new JLabel("Stroke: "));
        penPanel.add(myStrokeTypeOption = new JComboBox(STROKES_OPTIONS));
        penPanel.add(new JLabel("Thickness: "));
        penPanel.add(myThicknessOption = new JComboBox(THICKNESS_OPTIONS));

        penPanel.setBorder(BorderFactory.createTitledBorder(
                                                            BorderFactory.createEtchedBorder(),
                                                            "Pen Properties"));
        return penPanel;
    }

    /**
     * @return Panel with Canvas grid options
     */
    private JPanel createGridPanel () {
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 1));
        gridPanel.add(myEnableGridBox = new JCheckBox("Enable Grid"));
        gridPanel.add(new JLabel("Grid Spacing"));
        gridPanel.add(mySpacingField = new JTextField());
        if (myWorkspace.getCanvas().isGridEnabled()) {
            myEnableGridBox.setSelected(true);
            mySpacingField.setText(Integer.toString(myWorkspace.getCanvas().getGridSpacing()));
        }
        else {
            mySpacingField.setEnabled(false);
        }

        myEnableGridBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged (ChangeEvent e) {
                if (myEnableGridBox.isSelected()) {
                    mySpacingField.setEnabled(true);
                }
                else {
                    mySpacingField.setEnabled(false);
                }
            }

        });

        gridPanel.setBorder(BorderFactory.createTitledBorder(
                                                             BorderFactory.createEtchedBorder(),
                                                             "Grid Settings"));
        return gridPanel;
    }

    @Override
    protected void addOkButtonListener () {
        myOkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                myWorkspace.setStroke(
                                      (Strokes) myStrokeTypeOption.getSelectedItem(),
                                      (Integer) myThicknessOption.getSelectedItem());
                checkGridPanel();

                // TODO implement in the most effective way raising and lowering the pen from active
                // turtles. Waiting until model finds a solution

                dispose();
            }

            /**
             * Performs appropriate actions for the gridPanel
             */
            private void checkGridPanel () {
                if (myEnableGridBox.isSelected()) {
                    myWorkspace.getCanvas().setGrid(true);
                    try {
                        myWorkspace.getCanvas().setGridSpacing(Integer.parseInt(mySpacingField
                                                                       .getText()));
                    }
                    catch (Exception ex) {
                        myWorkspace.showError("Invalid Spacing Argument");
                    }
                }
            }
        });
    }

    @Override
    protected void setTitle () {
        setTitle("Graphics Settings");
    }
}
