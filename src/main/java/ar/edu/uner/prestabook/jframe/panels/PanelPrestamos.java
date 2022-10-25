package ar.edu.uner.prestabook.jframe.panels;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Devoluciones;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.common.Components;

public class PanelPrestamos extends AbstractPanel {

    @Override
    public JPanel init() {
        prepare();
        JCheckBox checkBoxFiltro = checkBoxFiltro();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        panel.add(checkBoxFiltro);
        table.setAutoCreateRowSorter(true);
        table.setRowSorter(sorter);

        checkBoxFiltro.addActionListener(
                evt -> sorter.setRowFilter(checkBoxFiltro.isSelected() ? filtradorPorFueraDeTermino() : null));

        JButton btnRegistrarDevolucion = Components.btnGeneric("Registrar devolución", "Left");
        panel.add(btnRegistrarDevolucion);

        btnRegistrarDevolucion.addActionListener(ev -> {
            Devoluciones devoluciones = new Devoluciones();
            devoluciones.setVisible(true);
        });

        JButton btnRefrescar = Components.btnGeneric("Refrescar", "Right");
        panel.add(btnRefrescar);

        btnRefrescar.addActionListener(b -> {
            sorter.setRowFilter(null);
            checkBoxFiltro.setSelected(false);
            Components.clearTable(table);
            Tabla.fill(model, Constants.PRESTAMOS);
        });

        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn("Obra");
        model.addColumn("Ejemplar");
        model.addColumn("Fecha y hora de préstamo");
        model.addColumn("Válido hasta");
        model.addColumn("Fecha de devolución");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("¿Fuera de término?");
        Tabla.fill(model, Constants.PRESTAMOS);
    }

    
    
    /**
     * Creates a check box to filtering lead out of term
     */	
    private JCheckBox checkBoxFiltro() {
        JCheckBox checkBoxFiltro = new JCheckBox("Mostrar fuera de término");
        checkBoxFiltro.setBounds(10, 120, 300, 20);
        return checkBoxFiltro;
    }

    
    
    
    
    private RowFilter<TableModel, Integer> filtradorPorFueraDeTermino() {
        return new RowFilter<TableModel, Integer>() {
            @Override
            public boolean include(RowFilter.Entry<? extends TableModel, ? extends Integer> entry) {
                int modelRow = entry.getIdentifier();
                return entry.getModel().getValueAt(modelRow, 8).equals("Sí");
            }
        };
    }

    @Override
    public String getPanelName() {
        return Constants.PRESTAMOS;
    }
}
