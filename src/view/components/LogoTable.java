package view.components;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import view.windows.VariablesWindow;


/**
 * Table that handles this program's specs
 * 
 * @author Henrique Moraes
 * 
 */
public class LogoTable extends JTable {
    private boolean lastRowEditable = false;
    private JComboBox myEditableComboBox;
    private TableCellEditor myComboEditor;

    public LogoTable () {
        myEditableComboBox = new JComboBox();
        myComboEditor = new DefaultCellEditor(myEditableComboBox);
    }

    @Override
    public boolean isCellEditable (int row, int col) {
        return (lastRowEditable) ? col == VariablesWindow.VALUE_INDEX ||
                                   row == getRowCount() - 1 : col == VariablesWindow.VALUE_INDEX;
    }

    /**
     * @return Combo Box associated with inclusion of new variable
     */
    public JComboBox getComboBox () {
        return myEditableComboBox;
    }

    /**
     * @param edit sets whether the last row is editable or not
     */
    public void setLastRowEditable (boolean edit) {
        if (!edit) {
            myEditableComboBox.removeAllItems();
        }

        lastRowEditable = edit;
    }

    /**
     * Takes into account an editable combo box when a variable is being added
     */
    @Override
    public TableCellEditor getCellEditor (int row, int col) {
        return (lastRowEditable && row == getRowCount() - 1 && col == VariablesWindow.SCOPE_INDEX)
                                                                                                  ? myComboEditor
                                                                                                  : super.getCellEditor(row,
                                                                                                                        col);
    }

}
