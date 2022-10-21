package ar.edu.uner.prestabook.jframe.panels.funcionarios;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ar.edu.uner.prestabook.jframe.panels.Utils;
import ar.edu.uner.prestabook.jframe.tablemodel.NonEditableTableModel;

public abstract class PrestabookPanel {

    JPanel panel = Utils.panelEntities();
    DefaultTableModel model = new NonEditableTableModel();
    JTable table = new JTable();
    JScrollPane scrollPane = Utils.scrollPane();

    public void hide() {
        panel.setVisible(false);
    }

    public void prepare() {
        panel.setBounds(339, 104, 1061, 707);
        panel.setLayout(null);
        panel.setVisible(true);
        panel.add(scrollPane);
        table.setSize(1000, 1600);
        table.setModel(model);
        scrollPane.setViewportView(table);
        setModelColumns();
    }

    public abstract JPanel init();

    public abstract void setModelColumns();

}
