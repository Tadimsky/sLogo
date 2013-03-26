package view.windows;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.IState;
import model.Turtle;
import model.TurtleManager;
import parser.commands.turtle.queries.Heading;
import parser.commands.turtle.queries.IsPenDown;
import parser.commands.turtle.queries.IsShowing;
import parser.commands.turtle.queries.XCor;
import parser.commands.turtle.queries.YCor;
import view.Window;
import view.labels.InformationLabel;
import view.labels.LogoLabel;
import view.labels.TitleLabel;


@SuppressWarnings("serial")
public class InformationView extends JPanel implements Observer {
    private static final String TITLE = "Turtle Information";

    private static final String[] LABEL_DESCRIPTIONS =
    { "Absolute Heading: ", "X Position: ", "Y Position: ",
     "Pen Down? ", "Is Hiding? " };
    private static final ILabelInformation[] TURTLE_QUERIES = { new Heading(null),
                                                               new XCor(null), new YCor(null),
                                                               new IsPenDown(null),
                                                               new IsShowing(null) };

    private List<LogoLabel> myLabelList;

    public InformationView () {
        setPreferredSize(Window.TABBED_INFO_WINDOW_DIMENSION);

        myLabelList = new ArrayList<LogoLabel>();
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createMatteBorder(0, 3, 5, 0, Color.GRAY));

        setLabels();

    }

    public void setLabels () {
        TitleLabel title = new TitleLabel(TITLE);
        myLabelList.add(title);
        add(title, title.getGridBagConstraints());

        for (int i = 0; i < LABEL_DESCRIPTIONS.length; i++) {
            InformationLabel label =
                    new InformationLabel(i + 1, LABEL_DESCRIPTIONS[i], TURTLE_QUERIES[i]);
            myLabelList.add(label);
            add(label, label.getGridBagConstraints());
        }
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.setColor(Window.INFO_BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

    }

    @Override
    public void update (Observable object, Object arg) {
        TurtleManager manager = (TurtleManager) object;
        TreeMap<Integer, Turtle> map = (TreeMap<Integer, Turtle>) manager.getTurtles();
        IState turtle = (!map.isEmpty()) ? (IState) map.get(map.lastKey()) : null;
        for (int i = 0; i < myLabelList.size(); i++) {
            LogoLabel j = myLabelList.get(i);
            if (turtle != null) {
                j.setText(turtle);
            }
            else {
                j.setText();
            }
        }
    }

}
