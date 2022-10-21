package ar.edu.uner.prestabook.jframe.panels.lectores;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.VerMas;
import ar.edu.uner.prestabook.jframe.panels.Utils;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.persistence.IEdicionDAO;

public class PanelObra extends JPanel {
    


    public JPanel init() {
        JPanel panelEntidades = new JPanel();
        panelEntidades.setBounds(237, 103, 1153, 708);
        panelEntidades.setLayout(null);

        panelEntidades.setVisible(true);

        panelEntidades.add(lblConsultarObras());

        JScrollPane scrollPane = Utils.scrollPane();
        panelEntidades.add(scrollPane);

        JTextField txtIngresarAreaTematica = txtIngresarAreaTematica();
        panelEntidades.add(txtIngresarAreaTematica);

        JButton btnSolicitarPrestamo = Utils.btnGeneric("Solicitar préstamo", "Right");
        panelEntidades.add(btnSolicitarPrestamo);

        JTable tableObras = new JTable();

        DefaultTableModel model = new DefaultTableModel();
        tableObras.setModel(model);
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

        tableObras.setAutoCreateRowSorter(true);
        TableRowSorter<DefaultTableModel> sorted = new TableRowSorter<>(model);
        tableObras.setRowSorter(sorted);

        Tabla.fill(model, Constants.OBRA);
        scrollPane.setViewportView(tableObras);

        tableObras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton btnReservarObra = Utils.btnGeneric("Reservar Obra", "Left");
        panelEntidades.add(btnReservarObra);

        JButton btnVerMas = Utils.btnGeneric("Ver más", "Center");
        panelEntidades.add(btnVerMas);

        /**
         * Botón con evento para reservar una obra
         */

        btnReservarObra.addActionListener(b -> {

        });

        /**
         * Botón con evento para ver mas informacion de una obra seleccionada en la
         * tabla
         */

        btnVerMas.addActionListener(b -> {
            if (tableObras.getSelectedRow() != -1) {
                DefaultTableModel modelo = (DefaultTableModel) tableObras.getModel();

                Object idEdicion = modelo.getValueAt(tableObras.getSelectedRow(), 8);

                IEdicionDAO o = DaoFactory.getEdicionDAO();
                Edicion edicion = o.findById(idEdicion);

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

        return panelEntidades;
    }

    public JTextField txtIngresarAreaTematica() {
        JTextField txtIngresarAreaTematica = new JTextField();
        txtIngresarAreaTematica.setForeground(new Color(128, 128, 128));
        txtIngresarAreaTematica.setText("Buscar por area tematica");
        txtIngresarAreaTematica.setBounds(10, 110, 1130, 37);
        txtIngresarAreaTematica.setColumns(10);
        return txtIngresarAreaTematica;
    }

    public JLabel lblConsultarObras() {
        JLabel lblConsultarObras = new JLabel("Consultar obras");
        lblConsultarObras.setBounds(440, 10, 369, 136);
        lblConsultarObras.setForeground(Color.GRAY);
        lblConsultarObras.setFont(new Font(Constants.FONT, Font.BOLD, 19));
        return lblConsultarObras;
    }

}
