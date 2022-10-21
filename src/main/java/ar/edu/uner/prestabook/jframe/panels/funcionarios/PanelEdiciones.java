package ar.edu.uner.prestabook.jframe.panels.funcionarios;

import javax.swing.JButton;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.AgregarEdicion;
import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.panels.Utils;

public class PanelEdiciones extends PrestabookPanel {

    @Override
    public JPanel init() {

        prepare();
        JButton btnAgregarEdicion = Utils.btnGeneric("Agregar edición", "Left");
        JButton btnRefrescar = Utils.btnGeneric("Refrescar", "Right");
        panel.add(Utils.lblPanelTitle(Constants.EDICIONES));
        panel.add(btnAgregarEdicion);
        panel.add(btnRefrescar);

        btnAgregarEdicion.addActionListener(b -> {
            AgregarEdicion agregarEdicion = new AgregarEdicion();
            agregarEdicion.setVisible(true);
        });

        btnRefrescar.addActionListener(b -> {
            Utils.clearTable(table);
            Tabla.fill(model, Constants.EDICION);
        });

        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn("Editorial");
        model.addColumn("Pais");
        model.addColumn("Numero");
        model.addColumn("Año");
        model.addColumn("Volumenes");
        model.addColumn("Paginas");
        model.addColumn("Idioma");
        model.addColumn("Formato");
        Tabla.fill(model, Constants.EDICION);
    }

}
