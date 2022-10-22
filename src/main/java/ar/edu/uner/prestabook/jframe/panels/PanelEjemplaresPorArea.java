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

public class PanelEjemplaresPorArea extends AbstractPanel {

    @Override
    public JPanel init() {
        prepare();

        scrollPane.setViewportView(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTextField txtIngresarArea = txtIngresarArea();
        panel.add(txtIngresarArea);
        TableRowSorter<DefaultTableModel> sorted = new TableRowSorter<>(model);
        table.setRowSorter(sorted);

        txtIngresarArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrar();
            }

            private void filtrar() {
                sorted.setRowFilter(RowFilter.regexFilter(txtIngresarArea.getText().toUpperCase(), 1));
            }
        });

        txtIngresarArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	txtIngresarArea.setText("");
            }
        });

        return panel;
    }

    @Override
    public void setModelColumns() {
    	model.addColumn("");
		model.addColumn(Constants.AREA_TEMATICA);
		model.addColumn("Isbn de obra");
		model.addColumn("Titulo");
		model.addColumn("Subitulo");
		model.addColumn("1° autor");
		model.addColumn("2° autor");
		model.addColumn("3° autor");
		model.addColumn("Género");
		model.addColumn(Constants.TIPO_OBRA);
		model.addColumn("Forma de adquisición");
		model.addColumn("Fecha de adquisición");
		model.addColumn("Observaciones");
		model.addColumn("Codigo identificatorio");
        table.setAutoCreateRowSorter(true);
        Tabla.fill(model, Constants.EJEMPLARES_POR_AREA);
    }

    @Override
    public String getPanelName() {
        return "Ejemplares disponibles por area";
    }

    public JTextField txtIngresarArea() {
		JTextField txtIngresarArea = new JTextField();
		txtIngresarArea.setForeground(new Color(128, 128, 128));
		txtIngresarArea.setText("Buscar por area");
		txtIngresarArea.setBounds(10, 110, 1030, 37);
		txtIngresarArea.setColumns(10);
		return txtIngresarArea;
	}
}
