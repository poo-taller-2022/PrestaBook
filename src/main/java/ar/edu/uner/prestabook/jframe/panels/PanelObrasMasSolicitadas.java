package ar.edu.uner.prestabook.jframe.panels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.common.Components;

public class PanelObrasMasSolicitadas extends AbstractPanel {

    @Override
    public JPanel init() {
        prepare();
        JButton btnRefrescar = Components.btnGeneric("Refrescar", "Right");
        panel.add(btnRefrescar);
        table.setAutoCreateRowSorter(true);
        
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(2);
        sortKeys.add(new RowSorter.SortKey(7, SortOrder.DESCENDING));
        sortKeys.add(new RowSorter.SortKey(6, SortOrder.DESCENDING));
        
        sorter.setSortKeys(sortKeys);
        btnRefrescar.addActionListener(b -> {
            Components.clearTable(table);
            Tabla.fill(model, Constants.OBRAS_MAS_SOLICITADAS);
        });

        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn("Isbn");
        model.addColumn("Titulo");
        model.addColumn("1° autor");
        model.addColumn("Género");
        model.addColumn(Constants.TIPO_OBRA);
        model.addColumn("Reservas");
        model.addColumn("Préstamos");
        Tabla.fill(model, Constants.OBRAS_MAS_SOLICITADAS);
    }

    @Override
    public String getPanelName() {
        return Constants.OBRAS_MAS_SOLICITADAS;
    }

}
