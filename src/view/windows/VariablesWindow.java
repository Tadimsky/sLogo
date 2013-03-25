package view.windows;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import parser.VariableManager;
import parser.VariableScope;
import view.Window;
import view.components.LogoTable;
import controller.Workspace;
import controller.support.IError;


public class VariablesWindow extends JPanel implements Observer {
    public static final String[] COLUMN_NAMES = new String[] { "Name", "Scope", "Value" };
    private static final String[] EMPTY_ROW = new String[] { "", "", "" };
    public static final Dimension TABLE_DIMENSION =
            new Dimension(Window.TABBED_INFO_WINDOW_DIMENSION.width,
                          Window.TABBED_INFO_WINDOW_DIMENSION.height - 30);
    public static final int VALUE_INDEX = 2;
    public static final int NAME_INDEX = 0;
    public static final int SCOPE_INDEX = 1;
    public static final int COL_NUM = 3;
    private static final String NEW_BUTTON = "New";
    private static final String OK_BUTTON = "Ok";

    private LogoTable myTable;
    private DefaultTableModel myModel;
    private IError myErrorNotifier;
    private VariableManager myVariableManager;
    private boolean isCellAdding = false;
    private JPanel myButtonsPanel;
    private JPanel myCardsPanel;

    public VariablesWindow () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(createTablePanel());
        add(createButtonsPanel());
        setPreferredSize(Window.TABBED_INFO_WINDOW_DIMENSION);
    }

    /**
     * @return scrollabe panel responsible for holding the variables table
     */
    private JScrollPane createTablePanel () {
        myModel = new DefaultTableModel(COLUMN_NAMES, 0);
        myTable = new LogoTable();
        myTable.setModel(myModel);
        myTable.setPreferredScrollableViewportSize(TABLE_DIMENSION);
        myTable.setFillsViewportHeight(true);

        myTable.setBackground(Window.INFO_BACKGROUND_COLOR);
        myTable.setGridColor(Color.GRAY);
        myTable.setAutoCreateRowSorter(true);
        setTableListener();

        return new JScrollPane(myTable);
    }

    /**
     * Sets the change listener for this table
     */
    private void setTableListener () {
        myModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged (TableModelEvent e) {
                if (e.getType() != TableModelEvent.UPDATE || isCellAdding) return;
                int row = e.getFirstRow();
                String varName = (String) myTable.getValueAt(row, NAME_INDEX);
                Integer newValue;
                try {
                    newValue = Integer.parseInt((String) myTable.getValueAt(row, VALUE_INDEX));
                }
                catch (Exception ex) {
                    myErrorNotifier.showError("Not a valid input!");
                    return;
                }
                myVariableManager.setVariable(varName, newValue);
            }
        });
    }

    /**
     * @return Panel containing buttons to manage variables table
     */
    private JPanel createButtonsPanel () {
        myButtonsPanel = new JPanel();
        myButtonsPanel.setLayout(new GridLayout(1, 3));
        myButtonsPanel.add(Box.createHorizontalGlue());
        JButton removeButton = new JButton("Remove");
        myButtonsPanel.add(removeButton);
        removeButton.addActionListener(createRemoveListener());

        myCardsPanel = new JPanel(new CardLayout());
        JButton newButton = new JButton(NEW_BUTTON);
        myCardsPanel.add(newButton, NEW_BUTTON);
        myCardsPanel.add(createOkButton(), OK_BUTTON);
        newButton.addActionListener(createNewListener());
        myButtonsPanel.add(myCardsPanel);

        return myButtonsPanel;
    }

    /**
     * @return ok Button with appropriate listener
     */
    private JButton createOkButton () {
        JButton okButton = new JButton(OK_BUTTON);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                String[] newData = new String[COL_NUM];
                try {
                    newData[NAME_INDEX] = (String)
                            myTable.getValueAt(myTable.getRowCount() - 1, NAME_INDEX);
                    newData[SCOPE_INDEX] = (String)
                            myTable.getComboBox().getSelectedItem();
                    newData[VALUE_INDEX] =
                            (String) myTable.getValueAt(myTable.getRowCount() - 1,
                                                        VALUE_INDEX);
                    myVariableManager.addToScope(newData[SCOPE_INDEX],
                                                 newData[NAME_INDEX],
                                                 Integer.parseInt(newData[VALUE_INDEX]));
                    myModel.addRow(newData);
                    restorePreviousState();
                }
                catch (Exception ex) {
                    myErrorNotifier.showError("Invalid Input!");
                    restorePreviousState();
                }

            }

            private void restorePreviousState () {
                isCellAdding = false;
                CardLayout cards = (CardLayout) myCardsPanel.getLayout();
                cards.show(myCardsPanel, NEW_BUTTON);
                myModel.removeRow(myTable.getRowCount() - 1);
                myTable.setLastRowEditable(false);
            }
        });
        return okButton;
    }

    /**
     * @return Appropriate listener for remove button
     */
    private ActionListener createRemoveListener () {
        return new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                int index = myTable.getSelectedRow();
                while (index >= 0) {
                    myVariableManager
                            .removeVariable((String) myTable.getValueAt(index, NAME_INDEX));
                    myModel.removeRow(index);
                    index = myTable.getSelectedRow();
                }
            }
        };
    }

    /**
     * @return Appropriate listener for New button
     */
    private ActionListener createNewListener () {
        return new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                isCellAdding = true;
                CardLayout cards = (CardLayout) myCardsPanel.getLayout();
                cards.show(myCardsPanel, OK_BUTTON);
                myModel.addRow(EMPTY_ROW);
                myTable.setLastRowEditable(true);
                JComboBox box = myTable.getComboBox();
                for (String s : myVariableManager.getScopeNames()) {
                    box.addItem(s);
                }
            }
        };
    }

    /**
     * Updates the table information according to the variables in the workspace
     */
    public void update () {
        update((Observable) myErrorNotifier, null);
    }

    @Override
    public void update (Observable work, Object arg1) {
        Workspace w = (Workspace) work;
        myErrorNotifier = w;
        myVariableManager = w.getVariables();

        while (myModel.getRowCount() > 0) {
            myModel.removeRow(0);
        }

        for (VariableScope v : myVariableManager.getScopes()) {
            String scope = v.getName();
            for (String s : v.getVariables().keySet()) {
                String[] data = new String[COL_NUM];
                data[0] = s;
                data[1] = scope;
                data[2] = Integer.toString(v.getVariables().get(s));
                myModel.addRow(data);
            }
        }
    }

}
