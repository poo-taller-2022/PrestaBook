package ar.edu.uner.prestabook.jframe.tablemodel;

import javax.swing.table.DefaultTableModel;


/*
 * Create a table with non-editable rows and columns
 */

public class NonEditableTableModel extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
