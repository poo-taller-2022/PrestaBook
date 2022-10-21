package ar.edu.uner.prestabook.jframe.panels.funcionarios;

import javax.swing.JButton;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.AgregarEjemplar;
import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.panels.Utils;

public class PanelEjemplares extends PrestabookPanel {

    @Override
    public JPanel init() {
        prepare();
        JButton btnRefrescar = Utils.btnGeneric("Refrescar", "Right");
        JButton btnAgregarEjemplar = Utils.btnGeneric("Agregar ejemplar", "Left");
        panel.add(Utils.lblPanelTitle(Constants.EJEMPLARES));
        panel.add(btnAgregarEjemplar);
        panel.add(btnRefrescar);

        btnAgregarEjemplar.addActionListener(b -> {
            AgregarEjemplar agregarEjemplar = new AgregarEjemplar();
            agregarEjemplar.setVisible(true);
        });

        btnRefrescar.addActionListener(b -> {
            Utils.clearTable(table);
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

}
