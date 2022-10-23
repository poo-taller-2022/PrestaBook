package ar.edu.uner.prestabook.jframe.panels;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.common.Components;


public class PanelNotificaciones extends AbstractPanel {

    @Override
    public JPanel init() {
        prepare();
        
        JButton btnVisto = Components.btnGeneric("Marcar como visto", "Center");
        panel.add(btnVisto);

        scrollPane.setViewportView(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableRowSorter<DefaultTableModel> sorted = new TableRowSorter<>(model);
        table.setRowSorter(sorted);
        
        btnVisto.addActionListener(b -> {
            if (table.getSelectedRow() != -1) {
                DaoFactory.getPrestamoDAO().delete((long) table.getValueAt(table.getSelectedRow(), 1));
                model.setValueAt("Si", table.getSelectedRow(), 6);
            } else {
                JOptionPane.showInternalMessageDialog(null, "Debe seleccionar una fila");
            }
        });
        
        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn("Id de préstamo");
        model.addColumn("Id ejemplar");
        model.addColumn("Fecha pedido de préstamo");
        model.addColumn("Estado");
        model.addColumn("Funcionario a cargo");
        model.addColumn("Visto");
        table.setAutoCreateRowSorter(true);
        Tabla.fill(model, Constants.NOTIFICACIONES);
    }

    @Override
    public String getPanelName() {
        return "Solicitudes de préstamos";
    }
}