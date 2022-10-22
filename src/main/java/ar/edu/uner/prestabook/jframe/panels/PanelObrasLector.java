package ar.edu.uner.prestabook.jframe.panels;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Reservas;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.VerMas;
import ar.edu.uner.prestabook.jframe.common.Components;
import ar.edu.uner.prestabook.model.Edicion;

public class PanelObrasLector extends AbstractPanel {

    public JPanel init() {
        prepare();
        JTextField txtIngresarAreaTematica = txtIngresarAreaTematica();
        JButton btnSolicitarPrestamo = Components.btnGeneric("Solicitar préstamo", "Right");
        JButton btnReservarObra = Components.btnGeneric("Reservar Obra", "Left");
        JButton btnVerMas = Components.btnGeneric("Ver más", "Center");
        panel.add(txtIngresarAreaTematica);
        panel.add(btnSolicitarPrestamo);
        panel.add(btnReservarObra);
        panel.add(btnVerMas);

        table.setAutoCreateRowSorter(true);
        TableRowSorter<DefaultTableModel> sorted = new TableRowSorter<>(model);
        table.setRowSorter(sorted);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btnReservarObra.addActionListener(b -> {
            Reservas reservas = new Reservas();
            reservas.setVisible(true);
        });

        btnVerMas.addActionListener(b -> {
            if (table.getSelectedRow() != -1) {
                DefaultTableModel modelo = (DefaultTableModel) table.getModel();

                Object idEdicion = modelo.getValueAt(table.getSelectedRow(), 8);

                Edicion edicion = DaoFactory.getEdicionDAO().findById(idEdicion);

                VerMas vermas = new VerMas(edicion);
                vermas.setVisible(true);
            } else {
                JOptionPane.showInternalMessageDialog(null, "Debe seleccionar una obra");
            }
        });

        txtIngresarAreaTematica.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtIngresarAreaTematica.setText("");
            }
        });

        txtIngresarAreaTematica.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrar();
            }

            private void filtrar() {
                sorted.setRowFilter(RowFilter.regexFilter(toMayusculas(txtIngresarAreaTematica.getText()), 1));
            }

            public String toMayusculas(String valor) {
                if (valor == null || valor.isEmpty()) {
                    return valor;
                } else {
                    return valor.toUpperCase().charAt(0) + valor.substring(1, valor.length()).toLowerCase();
                }
            }

        });

        return panel;
    }

    private JTextField txtIngresarAreaTematica() {
        JTextField txtIngresarAreaTematica = new JTextField();
        txtIngresarAreaTematica.setForeground(new Color(128, 128, 128));
        txtIngresarAreaTematica.setText("Buscar por area tematica");
        txtIngresarAreaTematica.setBounds(10, 110, 1130, 37);
        txtIngresarAreaTematica.setColumns(10);
        return txtIngresarAreaTematica;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn(Constants.AREA_TEMATICA);
        model.addColumn("Isbn");
        model.addColumn("Titulo");
        model.addColumn("Subitulo");
        model.addColumn("1° autor");
        model.addColumn("Género");
        model.addColumn(Constants.TIPO_OBRA);
        model.addColumn("Id de edicion");
        model.addColumn("N° ejemplares");
        Tabla.fill(model, Constants.OBRAS_LECTOR_VIEW);
    }

    @Override
    public String getPanelName() {
        return Constants.OBRAS;
    }

}
