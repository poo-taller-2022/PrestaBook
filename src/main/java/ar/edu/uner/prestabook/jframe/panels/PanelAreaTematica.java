package ar.edu.uner.prestabook.jframe.panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;

public class PanelAreaTematica extends PanelEntidades {
    public PanelAreaTematica(JPanel contentPane) {
        JPanel panel = build(Constants.AREAS_TEMATICAS);
        panel.setVisible(true);
        panel.add(lblTituloEntidades());

        JScrollPane scrollPane = scrollPane();
        panel.add(scrollPane);

        JTable tableAreasTematicas = new JTable();

        DefaultTableModel model = new DefaultTableModel();
        tableAreasTematicas.setModel(model);
        model.addColumn("");
        model.addColumn(Constants.NOMBRE);

        Tabla.fill(model, Constants.AREA_TEMATICA);
        scrollPane.setViewportView(tableAreasTematicas);

        JButton btnAreaTematica = btnAgregarAreaTematica();
        panel.add(btnAreaTematica);

        JButton btnActualizarAreaTematica = btnActualizarAreaTematica();
        panel.add(btnActualizarAreaTematica);

        btnAreaTematica.addActionListener(b -> {
            String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre de area tematica");

            if (valorAgregar != null && !valorAgregar.isBlank()) {
                actualizarBaseDeDatos(valorAgregar, Constants.AREA_TEMATICA);
                limpiarTabla(tableAreasTematicas);
                Tabla.fill(model, Constants.AREA_TEMATICA);
            }
        });
        contentPane.add(panel);
    }

    public void init() {
        this.setVisible(true);
    }

    private static JButton btnAgregarAreaTematica() {
        JButton btnAgregarTipoObra = new JButton("Agregar area tematica");
        btnAgregarTipoObra.setFocusPainted(false);
        btnAgregarTipoObra.setBackground(new Color(0, 64, 128));
        btnAgregarTipoObra.setForeground(new Color(255, 255, 255));
        btnAgregarTipoObra.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnAgregarTipoObra.setBorderPainted(false);
        btnAgregarTipoObra.setBounds(210, 500, 210, 20);
        return btnAgregarTipoObra;
    }

    private static JButton btnActualizarAreaTematica() {
        JButton btnActualizarTipoObra = new JButton("Actualizar area tematica");
        btnActualizarTipoObra.setFocusPainted(false);
        btnActualizarTipoObra.setBackground(new Color(0, 64, 128));
        btnActualizarTipoObra.setForeground(new Color(255, 255, 255));
        btnActualizarTipoObra.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnActualizarTipoObra.setBorderPainted(false);
        btnActualizarTipoObra.setBounds(600, 500, 210, 20);
        return btnActualizarTipoObra;
    }
}
