package ar.edu.uner.prestabook.jframe.panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.AgregarEjemplar;
import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.common.Components;

public class PanelEjemplares extends AbstractPanel {

    @Override
    public JPanel init() {
        prepare();
        JButton btnRefrescar = Components.btnGeneric("Refrescar", "Right");
        JButton btnAgregarEjemplar = Components.btnGeneric("Agregar ejemplar", "Left");
        panel.add(btnAgregarEjemplar);
        panel.add(btnRefrescar);

        btnAgregarEjemplar.addActionListener(b -> {
            AgregarEjemplar agregarEjemplar = new AgregarEjemplar();
            agregarEjemplar.setVisible(true);
        });

        btnRefrescar.addActionListener(b -> {
            Components.clearTable(table);
            Tabla.fill(model, Constants.EJEMPLAR);
        });

        return panel;
    }

    @Override
    public void setModelColumns() {

        model.addColumn("");
        model.addColumn("Isbn de obra");
        model.addColumn("Forma de adquisicion");
        model.addColumn("Fecha de adquisicion");
        model.addColumn("Observaciones");
        model.addColumn("Codigo");
        model.addColumn("Pasillo");
        model.addColumn("Estantería");
        model.addColumn("Estante");

        Tabla.fill(model, Constants.EJEMPLAR);

    }

    @Override
    public String getPanelName() {
        return Constants.EJEMPLARES;
    }

}
