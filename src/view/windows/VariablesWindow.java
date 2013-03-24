package view.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import parser.VariableManager;
import parser.VariableScope;
import controller.Workspace;
import controller.support.IError;
import view.Window;
import view.components.LogoTable;

public class VariablesWindow extends JPanel implements Observer{
    public static final String[] COLUMN_NAMES = new String[] {"Name", "Scope", "Value"};
    public static final Dimension TABLE_DIMENSION = 
            new Dimension(Window.TABBED_INFO_WINDOW_DIMENSION.width,
                          Window.TABBED_INFO_WINDOW_DIMENSION.height-30);
    
    private JTable myTable;
    private DefaultTableModel myModel;
    private IError myErrorNotifier;
    private VariableManager myVariableManager;
    private JButton myNewButton;
    private JButton myRemoveButton;
        
    public VariablesWindow() { 
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(createTablePanel());
        add(createButtonsPanel()); 
        setPreferredSize(Window.TABBED_INFO_WINDOW_DIMENSION);
    }
    
    /**
     * @return scrollabe panel responsible for holding the variables table
     */
    private JScrollPane createTablePanel() {
        myModel = new DefaultTableModel(COLUMN_NAMES,0);
        myTable = new LogoTable();
        myTable.setModel(myModel);
        myTable.setPreferredScrollableViewportSize(TABLE_DIMENSION);
        myTable.setFillsViewportHeight(true);
        //TODO fix constant
        myTable.setBackground(InformationView.BACKGROUND_COLOR);
        myTable.setGridColor(Color.GRAY);
        setTableListener();
        
        return new JScrollPane(myTable);
    }
    
    /**
     * Sets the change listener for this table
     */
    private void setTableListener(){
        myModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                switch (e.getType()) {
                    case TableModelEvent.UPDATE:
                        int row = e.getFirstRow();
                        String varName = (String) myTable.getValueAt(row, 0);
                        Integer newValue = Integer.parseInt((String) myTable.getValueAt(row, 2));
                        myVariableManager.setVariable(varName, newValue);
                }
            }
        });
    }
    
    /**
     * @return Panel containing buttons to manage variables table
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,3));
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(myRemoveButton = new JButton("Remove"));
        buttonsPanel.add(myNewButton = new JButton("New"));
        myRemoveButton.addActionListener(createRemoveListener());
        
        return buttonsPanel;       
    }

    /**
     * @return Appropriate listener for remove button
     */
    private ActionListener createRemoveListener() {
        return new ActionListener() {       
            @Override
            public void actionPerformed (ActionEvent arg0) {
                int index = myTable.getSelectedRow();
                while(index >= 0) {
                    myVariableManager.removeVariable((String) myTable.getValueAt(index, 0));                       
                    myModel.removeRow(index);
                    index = myTable.getSelectedRow();
                }
            }
        };
    }
    
    /**
     * Updates the table information according to the variables in the workspace
     */
    public void update() {
        update((Observable) myErrorNotifier, null);
    }

    @Override
    public void update (Observable work, Object arg1) {
        Workspace w = (Workspace) work;
        myErrorNotifier = (IError) w;
        myVariableManager = w.getVariables();
        for (int i = 0; i < myModel.getRowCount(); i++) {
            myModel.removeRow(i);
        }

        for (VariableScope v : myVariableManager.getScopes()) {
            String scope = v.getName();;
            for (String s : v.getVariables().keySet()) {
                String[] data = new String[3];
                data[0] = s;
                data[1] = scope;
                data[2] = Integer.toString(v.getVariables().get(s));
                myModel.addRow(data);
            }   
        }       
    }
}
