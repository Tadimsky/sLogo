package view;

import java.awt.Dimension;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * This class allows users to access an HTML formatted help page
 * 
 * @author Ziqiang Huang
 *
 */

public class HelpWindow extends JFrame {
    private static final Dimension DEFAULT_DIMENSION = new Dimension(1200,800);
    private JEditorPane editorpane;
    private URL helpURL;

    public HelpWindow(String title, URL hlpURL) {
        super(title);
        helpURL = hlpURL; 
        editorpane = new JEditorPane();
        editorpane.setPreferredSize(DEFAULT_DIMENSION);
        editorpane.setEditable(false);
        try {
            editorpane.setPage(helpURL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        getContentPane().add(new JScrollPane(editorpane));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
