package ar.edu.uner.prestabook.jframe.panels.funcionarios;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.panels.Utils;

public class PanelTiposObra extends PrestabookPanel {

    @Override
    public JPanel init() {
        prepare();
        panel.add(Utils.lblPanelTitle(Constants.TIPOS_DE_OBRAS));

        JButton btnAgregarTipoObra = Utils.btnGeneric("Agregar tipo de obra", "Left");
        panel.add(btnAgregarTipoObra);

        JButton btnRefrescar = Utils.btnGeneric("Refrescar", "Right");
        panel.add(btnRefrescar);

        btnAgregarTipoObra.addActionListener(b -> {
            String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre de tipo de obra");
            if (valorAgregar != null && !valorAgregar.isBlank()) {
                Utils.updateDatabase(valorAgregar, Constants.TIPO_OBRA);
                Utils.clearTable(table);
                Tabla.fill(model, Constants.TIPO_OBRA);
            }
        });

        btnRefrescar.addActionListener(b -> {
            Utils.clearTable(table);
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

}
