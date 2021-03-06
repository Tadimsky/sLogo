package view.windows;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import view.components.Strokes;
import controller.Controller;
import controller.Workspace;


/**
 * Allows user to edit graphic related settings such as pen stroke and
 * canvas grid
 * 
 * @author Henrique Moraes, Ziqiang Huang
 * 
 */
@SuppressWarnings("serial")
public class GraphicsSettingsWindow extends SettingsWindow {
    private static final Strokes[] STROKES_OPTIONS =
            new Strokes[] { Strokes.SOLID, Strokes.DASHED,
                           Strokes.DASH_AND_DOT, Strokes.DOUBLE_LINE };
    private static final Integer[] THICKNESS_OPTIONS = new Integer[] { 1, 2, 3, 4 };

    private JRadioButton myPenUpButton;
    private JRadioButton myPenDownButton;
    private JCheckBox myEnableGridBox;
    private JTextField mySpacingField;
    private JTextField myImageIndex;
    private JComboBox myThicknessOption;
    private JComboBox myStrokeTypeOption;
    private JTextField myImagePath;
    private JFileChooser myChooser;
    private ResourceBundle myResource;

    public GraphicsSettingsWindow (Workspace w) {
        super(w);

        myResource = Controller.RESOURCE;
        myChooser = new JFileChooser(System.getProperties().getProperty(Controller.USER_DIR));
        myOptionsPanel.add(createPenPanel());
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(createGridPanel());
        rightPanel.add(createImagePanel());

        myOptionsPanel.add(rightPanel);
        addOkButtonListener();

        setTitle(myResource.getString("GraphicsTitle"));
        pack();
    }

    /**
     * @return Panel with Stroke options
     */
    private JPanel createPenPanel () {

        JPanel penPanel = new JPanel();
        penPanel.setLayout(new GridLayout(7, 1));
        ButtonGroup group = new ButtonGroup();
        group.add(myPenUpButton = new JRadioButton(myResource.getString("ActivePenUp")));
        group.add(myPenDownButton = new JRadioButton(myResource.getString("ActivePenDown")));
        penPanel.add(myPenUpButton);
        penPanel.add(myPenDownButton);

        penPanel.add(new JLabel(myResource.getString("Stroke")));
        penPanel.add(myStrokeTypeOption = new JComboBox(STROKES_OPTIONS));
        penPanel.add(new JLabel(myResource.getString("Thickness")));
        penPanel.add(myThicknessOption = new JComboBox(THICKNESS_OPTIONS));

        penPanel.setBorder(BorderFactory.createTitledBorder(
                                                            BorderFactory.createEtchedBorder(),
                                                            myResource.getString("PenProperties")));
        return penPanel;
    }

    /**
     * @return Panel with Canvas grid options
     */
    private JPanel createGridPanel () {
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 1));
        gridPanel.add(myEnableGridBox = new JCheckBox(myResource.getString("EnableGrid")));
        gridPanel.add(new JLabel(myResource.getString("GridSpacing")));
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
                                                             myResource.getString("GridSettings")));
        return gridPanel;
    }

    /**
     * @return Panel to choose turtle image
     */
    private JPanel createImagePanel () {
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));
        imagePanel.add(myImagePath = new JTextField());
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 1));
        inputPanel.add(new JLabel("Image Index"));
        inputPanel.add(myImageIndex = new JTextField());
        JButton browseButton = new JButton(myResource.getString("Browse"));
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent arg0) {
                try {
                    int response = myChooser.showOpenDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        myImagePath.setText(myChooser.getSelectedFile().getCanonicalPath());
                    }
                }
                catch (Exception ex) {
                    myWorkspace.showError(ex.toString());
                }
            }
        });
        inputPanel.add(browseButton);
        imagePanel.add(inputPanel);

        imagePanel.setBorder(BorderFactory.
                createTitledBorder(BorderFactory.createEtchedBorder(),
                                   myResource.getString("TurtleImage")));
        return imagePanel;
    }

    /**
     * Sets the listener that will handle all inputed information on this Window
     */
    @Override
    protected void addOkButtonListener () {
        myOkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                myWorkspace.setStrokeType((Strokes) myStrokeTypeOption.getSelectedItem());
                myWorkspace.setStroke(
                                      (Strokes) myStrokeTypeOption.getSelectedItem(),
                                      (Integer) myThicknessOption.getSelectedItem());
                checkGridPanel();
                checkImagePanel();
                dispose();
            }

            /**
             * Performs appropriate actions for the gridPanel
             */
            private void checkGridPanel () {
                if (myEnableGridBox.isSelected()) {
                    try {
                        Integer space = Integer.parseInt(mySpacingField.getText());
                        myWorkspace.getCanvas().setGrid(true);
                        myWorkspace.getCanvas().setGridSpacing(space);
                    }
                    catch (Exception ex) {
                        myWorkspace.showError(Controller.RESOURCE_ERROR.getString("InvalidSpacing"));
                    }
                }
            }

            /**
             * Takes appropriate actions based on image panel input
             */
            private void checkImagePanel () {
                if (myImagePath.getText().isEmpty()) return;
                try {
                    String path = myImagePath.getText();
                    Integer index = Integer.parseInt(myImageIndex.getText());
                    myWorkspace.addTurtleImage(index, path);
                    myWorkspace.setImage(path);
                }
                catch (Exception ex) {
                    myWorkspace.showError("Invalid Image Argument");
                }
            }
        });
    }

    @Override
    protected void setTitle () {
        setTitle(myResource.getString("GraphicsSettings"));
    }
}
