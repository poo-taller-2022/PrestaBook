package ar.edu.uner.prestabook.jframe.panels;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.SistemaFuncionario;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.common.Components;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.Lector;
import ar.edu.uner.prestabook.model.Prestamo;

public class PanelSolicitudes extends AbstractPanel {

    @Override
    public JPanel init() {
        prepare();
        JButton btnConfirmarPrestamo = Components.btnGeneric("Confirmar préstamo", "Left");
        JButton btnRachazarPrestamo = Components.btnGeneric("Rechazar préstamo", "Right");
        panel.add(btnConfirmarPrestamo);
        panel.add(btnRachazarPrestamo);
        scrollPane.setViewportView(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableRowSorter<DefaultTableModel> sorted = new TableRowSorter<>(model);
        table.setRowSorter(sorted);

        btnConfirmarPrestamo.addActionListener(b -> {
            if (table.getSelectedRow() != -1) {
                if (table.getValueAt(table.getSelectedRow(), 4) == "No") {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setId((long) table.getValueAt(table.getSelectedRow(), 1));

                    Ejemplar ejemplar = new Ejemplar();
                    ejemplar.setId((long) table.getValueAt(table.getSelectedRow(), 5));
                    prestamo.setEjemplar(ejemplar);

                    prestamo.setFechaYHoraPrestamo(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(new Date()));
                    prestamo.setFechaPactadaDevolucion(table.getValueAt(table.getSelectedRow(), 7).toString());
                    prestamo.setPlazoPrestamo(Constants.PLAZO_PRESTAMO);

                    Lector lector = new Lector();
                    lector.setDocumento((long) table.getValueAt(table.getSelectedRow(), 2));
                    prestamo.setLector(lector);

                    prestamo.setFuncionario(SistemaFuncionario.getLoggedUser());

                    DaoFactory.getPrestamoDAO().update(prestamo);

                    JOptionPane.showInternalMessageDialog(null, "Préstamo realizado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No se pudo realizar el préstamo porque el lector posee multas activas", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showInternalMessageDialog(null, "Debe seleccionar un pedido de prestamo");
            }
        });

        btnRachazarPrestamo.addActionListener(b -> {
            if (table.getSelectedRow() != -1) {
                Prestamo prestamo = new Prestamo();
                prestamo.setId((long) table.getValueAt(table.getSelectedRow(), 1));

                Ejemplar ejemplar = new Ejemplar();
                ejemplar.setId((long) table.getValueAt(table.getSelectedRow(), 5));
                prestamo.setEjemplar(ejemplar);

                prestamo.setFechaYHoraPrestamo(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(new Date()));
                prestamo.setFechaPactadaDevolucion(null);
                prestamo.setPlazoPrestamo(null);

                Lector lector = new Lector();
                lector.setDocumento((long) table.getValueAt(table.getSelectedRow(), 2));
                prestamo.setLector(lector);

                prestamo.setFuncionario(SistemaFuncionario.getLoggedUser());

                DaoFactory.getPrestamoDAO().update(prestamo);

                JOptionPane.showInternalMessageDialog(null, "Préstamo rechazado correctamente");
                Tabla.fill(model, Constants.SOLICITUDES);
            } else {
                JOptionPane.showInternalMessageDialog(null, "Debe seleccionar un pedido de prestamo");
            }
        });

        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn("Id de préstamo");
        model.addColumn("Documento de lector");
        model.addColumn("Cantidad de multas");
        model.addColumn("Multa activa");
        model.addColumn("Id ejemplar");
        model.addColumn("Fecha pedido de préstamo");
        model.addColumn("Fecha pactada de devolución");
        table.setAutoCreateRowSorter(true);
        Tabla.fill(model, Constants.SOLICITUDES);
    }

    @Override
    public String getPanelName() {
        return "Solicitudes de préstamos";
    }
}
