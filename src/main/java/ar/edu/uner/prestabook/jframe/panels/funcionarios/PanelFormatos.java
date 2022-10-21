package ar.edu.uner.prestabook.jframe.panels.funcionarios;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.panels.Utils;

public class PanelFormatos extends PrestabookPanel {

    @Override
    public JPanel init() {
        prepare();
        JButton btnAgregarFormato = Utils.btnGeneric("Agregar formato", "Left");
        JButton btnRefrescar = Utils.btnGeneric("Refrescar", "Right");
        panel.add(Utils.lblPanelTitle(Constants.FORMATOS));
        panel.add(btnAgregarFormato);
        panel.add(btnRefrescar);

        btnAgregarFormato.addActionListener(b -> {
            String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre del formato");

            if (valorAgregar != null && !valorAgregar.isBlank()) {
                Utils.updateDatabase(valorAgregar, Constants.FORMATO);
                Utils.clearTable(table);
                Tabla.fill(model, Constants.FORMATO);
            }
        });

        btnRefrescar.addActionListener(b -> {
            Utils.clearTable(table);
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

}
