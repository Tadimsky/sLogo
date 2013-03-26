package view.windows;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.labels.TitleLabel;
import controller.Controller;
import controller.Workspace;


/**
 * Super class for other settings windows that allow the user to change the
 * settings of the current workspace
 * 
 * @author Henrique Moraes
 * 
 */
@SuppressWarnings("serial")
public abstract class SettingsWindow extends JFrame {
    private static final Dimension DEFAULT_DIMENSION = new Dimension(420, 330);

    protected Workspace myWorkspace;
    protected JPanel myOptionsPanel;
    protected JButton myOkButton;

    protected SettingsWindow (Workspace w) {
        setPreferredSize(DEFAULT_DIMENSION);
        myWorkspace = w;

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        myOptionsPanel = new JPanel();
        myOptionsPanel.setLayout(new GridLayout(1, 2));

        add(createTitlePanel());
        add(myOptionsPanel);
        add(createButtonPanel());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        centralize();
        setResizable(false);
        setVisible(true);
    }

    /**
     * @return Panel containing title for this frame
     */
    private JPanel createTitlePanel () {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        JLabel title = new JLabel(Controller.RESOURCE.getString("ChooseOptions"));
        title.setFont(TitleLabel.TITLE_FONT);
        titlePanel.add(title);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 50)));
        return titlePanel;
    }

    /**
     * Sets the opening location of this window
     */
    private void centralize () {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (dim.width - DEFAULT_DIMENSION.width) / 2;
        int y = (dim.height - DEFAULT_DIMENSION.height) / 2;

        setLocation(x, y);
    }

    /**
     * @return Panel with ok button
     */
    private JPanel createButtonPanel () {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(myOkButton = new JButton(Controller.RESOURCE.getString("Ok")));
        myOkButton.setSelected(true);
        return buttonPanel;
    }

    /**
     * Creates a adds an action listener to the Ok Button
     */
    protected abstract void addOkButtonListener ();

    /**
     * Sets the title for this Window
     */
    protected abstract void setTitle ();
}
