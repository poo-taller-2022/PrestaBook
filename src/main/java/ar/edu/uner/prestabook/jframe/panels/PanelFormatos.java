package ar.edu.uner.prestabook.jframe.panels;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.common.Components;

public class PanelFormatos extends AbstractPanel {

    @Override
    public JPanel init() {
        prepare();
        JButton btnAgregarFormato = Components.btnGeneric("Agregar formato", "Left");
        JButton btnRefrescar = Components.btnGeneric("Refrescar", "Right");
        panel.add(btnAgregarFormato);
        panel.add(btnRefrescar);

        btnAgregarFormato.addActionListener(b -> {
            String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre del formato");

            if (valorAgregar != null && !valorAgregar.isBlank()) {
                Components.updateDatabase(valorAgregar, Constants.FORMATO);
                Components.clearTable(table);
                Tabla.fill(model, Constants.FORMATO);
            }
        });

        btnRefrescar.addActionListener(b -> {
            Components.clearTable(table);
            Tabla.fill(model, Constants.FORMATO);
        });

        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn(Constants.NOMBRE);
        Tabla.fill(model, Constants.FORMATO);
    }

    @Override
    public String getPanelName() {
        return Constants.FORMATOS;
    }

}
