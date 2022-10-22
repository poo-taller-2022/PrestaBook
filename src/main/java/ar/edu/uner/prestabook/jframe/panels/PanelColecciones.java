package ar.edu.uner.prestabook.jframe.panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.AgregarColeccion;
import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.common.Components;

public class PanelColecciones extends AbstractPanel {

    public JPanel init() {
        prepare();
        JButton btnAgregarColeccion = Components.btnGeneric("Agregar Colección", "Left");
        JButton btnRefrescar = Components.btnGeneric("Refrescar", "Right");
        panel.add(btnAgregarColeccion);
        panel.add(btnRefrescar);

        btnAgregarColeccion.addActionListener(b -> {
            AgregarColeccion agregarColeccion = new AgregarColeccion();
            agregarColeccion.setVisible(true);
        });

        btnRefrescar.addActionListener(b -> {
            Components.clearTable(table);
            Tabla.fill(model, Constants.COLECCION);
        });
        
        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn("Isbn");
        model.addColumn("Titulo");
        model.addColumn("Subitulo");
        model.addColumn("1° autor");
        model.addColumn("2° autor");
        model.addColumn("3° autor");
        model.addColumn("Género");
        model.addColumn(Constants.TIPO_OBRA);
        model.addColumn(Constants.AREA_TEMATICA);
        Tabla.fill(model, Constants.COLECCION);
    }

    @Override
    public String getPanelName() {
        return Constants.COLECCIONES;
    }

}
