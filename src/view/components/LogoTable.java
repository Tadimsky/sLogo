package view.components;

import javax.swing.JTable;

/**
 * Table that handles this program's specs
 * @author Henrique Moraes
 *
 */
public class LogoTable extends JTable{
    
    @Override 
    public boolean isCellEditable(int row, int col) {
        return col == 2;
    }

}
