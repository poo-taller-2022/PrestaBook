package ar.edu.uner.prestabook.jframe.panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.AgregarObra;
import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.common.Components;

public class PanelObrasFuncionario extends AbstractPanel {

    @Override
    public JPanel init() {
        prepare();

        scrollPane.setViewportView(table);

        JButton btnAgregarObra = Components.btnGeneric("Agregar obra", "Left");
        panel.add(btnAgregarObra);

        JButton btnRefrescar = Components.btnGeneric("Refrescar", "Right");
        panel.add(btnRefrescar);

        btnAgregarObra.addActionListener(b -> {
            AgregarObra agregarObra = new AgregarObra();
            agregarObra.setVisible(true);
        });
        
        btnRefrescar.addActionListener(b -> {
            Components.clearTable(table);
            Tabla.fill(model, Constants.OBRA);
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

        Tabla.fill(model, Constants.OBRA);

    }

    @Override
    public String getPanelName() {
        return Constants.OBRAS;
    }

}
