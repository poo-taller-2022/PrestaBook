package ar.edu.uner.prestabook.jframe.panels.funcionarios;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.panels.Utils;

public class PanelAreaTematica extends PrestabookPanel {

    public JPanel init() {
        prepare();
        panel.add(Utils.lblPanelTitle(Constants.AREAS_TEMATICAS));
        JButton btnAreaTematica = Utils.btnGeneric("Agregar Area Tematica", "Left");
        JButton btnActualizarAreaTematica = Utils.btnGeneric("Actualizar Area Tematica", "Right");

        panel.add(btnAreaTematica);
        panel.add(btnActualizarAreaTematica);

        btnAreaTematica.addActionListener(b -> {
            String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre de area tematica");
            if (valorAgregar != null && !valorAgregar.isBlank()) {
                Utils.updateDatabase(valorAgregar, Constants.AREA_TEMATICA);
                Utils.clearTable(table);
                Tabla.fill(model, Constants.AREA_TEMATICA);
            }
        });
        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn(Constants.NOMBRE);
        Tabla.fill(model, Constants.AREA_TEMATICA);
    }

}
