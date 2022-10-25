package ar.edu.uner.prestabook.jframe.panels;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.common.Components;
import ar.edu.uner.prestabook.jframe.utils.DateSettings;
import ar.edu.uner.prestabook.model.Reserva;

public class PanelReservas extends AbstractPanel {

    @Override
    public JPanel init() {
        prepare();
        JLabel labelFiltro = labelFiltroFechas();
        JLabel labelFiltroInicio = labelFiltroInicio();
        DatePicker fechaInicial = datePickerInitial();
        JButton btnBorrarReserva = Components.btnGeneric("Borrar reserva", "Left");

        panel.add(btnBorrarReserva);
        panel.add(labelFiltro);
        panel.add(labelFiltroInicio);
        panel.add(fechaInicial);

        btnBorrarReserva.addActionListener(ev -> {
            if (table.getSelectedRow() != -1) {
                Integer seleccion = JOptionPane.showConfirmDialog(null, "¿Desea borrar la reserva?", "Confirmar",
                        JOptionPane.YES_NO_OPTION);
                if (seleccion == JOptionPane.YES_OPTION) {
                    Long id = (Long) table.getValueAt(table.getSelectedRow(), 0);
                    Reserva reserva = DaoFactory.getReservaDAO().findById(id);
                    reserva.setIsActive(false);
                    DaoFactory.getReservaDAO().update(reserva);
                    Components.clearTable(table);
                    Tabla.fill(model, Constants.RESERVAS);
                }
            } else {
                JOptionPane.showInternalMessageDialog(null, "Debe seleccionar una reserva");
            }
        });

        JButton btnRefrescar = Components.btnGeneric("Refrescar", "Right");
        panel.add(btnRefrescar);

        btnRefrescar.addActionListener(b -> {
            Components.clearTable(table);
            Tabla.fill(model, Constants.RESERVAS);
        });

        fechaInicial.addDateChangeListener(evt -> {
            Components.clearTable(table);
            Tabla.fill(model, Constants.RESERVAS);
            Components.filtrarFechas((DefaultTableModel) table.getModel(), 4, fechaInicial.getDate(),
                    LocalDate.MAX);
        });
        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn("Lector");
        model.addColumn("Obra");
        model.addColumn("Ejemplar");
        model.addColumn("Fecha de reserva");
        Tabla.fill(model, Constants.RESERVAS);

    }

    @Override
    public String getPanelName() {
        return Constants.RESERVAS;
    }

    
    
    /**
   	 * Creates a date picker to choose a start date
   	 * 
   	 */
    private DatePicker datePickerInitial() {
        DatePicker datePickerInitial = new DatePicker(DateSettings.getDatePickerSettings());
        datePickerInitial.setBounds(70, 70, 200, 30);
        return datePickerInitial;
    }

    
    /**
   	 * Creates a label to filter according to a start date
   	 * 
   	 */
    private JLabel labelFiltroFechas() {
        JLabel filtro = new JLabel("Filtrar a partir de");
        filtro.setBounds(10, -30, 775, 136);
        filtro.setForeground(Color.GRAY);
        filtro.setFont(new Font(Constants.FONT, Font.BOLD, 14));
        return filtro;
    }

    
    
    /**
     * Creates a label to filtering
     */
    private JLabel labelFiltroInicio() {
        JLabel filtro = new JLabel("Inicio");
        filtro.setBounds(10, 20, 775, 136);
        filtro.setForeground(Color.GRAY);
        filtro.setFont(new Font(Constants.FONT, Font.PLAIN, 12));
        return filtro;
    }

}
