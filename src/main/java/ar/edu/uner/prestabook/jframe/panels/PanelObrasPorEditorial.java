package ar.edu.uner.prestabook.jframe.panels;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;

public class PanelObrasPorEditorial extends AbstractPanel {

    @Override
    public JPanel init() {
        prepare();

        scrollPane.setViewportView(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTextField txtIngresarEditorial = txtIngresarEditorial();
        panel.add(txtIngresarEditorial);
        TableRowSorter<DefaultTableModel> sorted = new TableRowSorter<>(model);
        table.setRowSorter(sorted);

        txtIngresarEditorial.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrar();
            }

            private void filtrar() {
                sorted.setRowFilter(RowFilter.regexFilter(txtIngresarEditorial.getText().toUpperCase(), 1));
            }
        });

        txtIngresarEditorial.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtIngresarEditorial.setText("");
            }
        });

        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn("Editorial");
        model.addColumn("Isbn de obra");
        model.addColumn("Titulo");
        model.addColumn("Subitulo");
        model.addColumn("1° autor");
        model.addColumn("2° autor");
        model.addColumn("3° autor");
        model.addColumn("Género");
        model.addColumn(Constants.TIPO_OBRA);
        model.addColumn(Constants.AREA_TEMATICA);
        table.setAutoCreateRowSorter(true);
        Tabla.fill(model, Constants.OBRAS_POR_EDITORIAL);
    }

    @Override
    public String getPanelName() {
        return "Obras por editorial";
    }

    public JTextField txtIngresarEditorial() {
        JTextField txtIngresarEditorial = new JTextField();
        txtIngresarEditorial.setForeground(new Color(128, 128, 128));
        txtIngresarEditorial.setText("Buscar por editorial");
        txtIngresarEditorial.setBounds(10, 110, 1130, 37);
        txtIngresarEditorial.setColumns(10);
        return txtIngresarEditorial;
    }
}
