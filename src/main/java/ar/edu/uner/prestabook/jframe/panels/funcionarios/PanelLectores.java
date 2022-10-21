package ar.edu.uner.prestabook.jframe.panels.funcionarios;

import javax.swing.JButton;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.panels.Utils;

public class PanelLectores extends PrestabookPanel {

    @Override
    public JPanel init() {
        prepare();
        panel.add(Utils.lblPanelTitle(Constants.LECTORES));

        JButton btnRefrescar = Utils.btnGeneric("Refrescar", "Right");
        panel.add(btnRefrescar);

        btnRefrescar.addActionListener(b -> {
            Utils.clearTable(table);
            Tabla.fill(model, Constants.LECTOR);
        });
        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("E-mail");
        model.addColumn("Dirección");
        model.addColumn("Fecha de nacimiento");
        model.addColumn("Multas");
        Tabla.fill(model, Constants.LECTOR);

    }

}
