package ar.edu.uner.prestabook.jframe.panels;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.common.Components;

public class PanelTiposObra extends AbstractPanel {


    @Override
    public JPanel init() {
        prepare();

        JButton btnAgregarTipoObra = Components.btnGeneric("Agregar tipo de obra", "Left");
        panel.add(btnAgregarTipoObra);

        JButton btnRefrescar = Components.btnGeneric("Refrescar", "Right");
        panel.add(btnRefrescar);

        btnAgregarTipoObra.addActionListener(b -> {
            String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre de tipo de obra");
            if (valorAgregar != null && !valorAgregar.isBlank()) {
                Components.updateDatabase(valorAgregar, Constants.TIPO_OBRA);
                Components.clearTable(table);
                Tabla.fill(model, Constants.TIPO_OBRA);
            }
        });

        btnRefrescar.addActionListener(b -> {
            Components.clearTable(table);
            Tabla.fill(model, Constants.TIPO_OBRA);
        });
        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn(Constants.NOMBRE);
        Tabla.fill(model, Constants.TIPO_OBRA);
    }

    @Override
    public String getPanelName() {
        return Constants.TIPOS_DE_OBRAS;
    }

}
