package ar.edu.uner.prestabook.jframe.panels.funcionarios;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.github.lgooddatepicker.components.DatePicker;

import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.panels.Utils;
import ar.edu.uner.prestabook.jframe.utils.DateSettings;

public class PanelMultas extends PrestabookPanel {

    @Override
    public JPanel init() {
        prepare();
        panel.add(Utils.lblPanelTitle(Constants.MULTAS));
        JLabel labelFiltro = labelFiltroFechas();
        JLabel labelFiltroInicio = labelFiltroInicio();
        JLabel labelFiltroFin = labelFiltroFin();
        DatePicker fechaInicial = datePickerInitial();
        DatePicker fechaFinal = datePickerFinal();
        panel.add(labelFiltro);
        panel.add(labelFiltroInicio);
        panel.add(labelFiltroFin);
        panel.add(fechaInicial);
        panel.add(fechaFinal);

        table.setAutoCreateRowSorter(true);

        table.setAutoCreateRowSorter(true);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        scrollPane.setViewportView(table);

        fechaInicial.addDateChangeListener(evt -> {
            Utils.clearTable(table);
            Tabla.fill(model, Constants.MULTAS);
            filtrarFechas((DefaultTableModel) table.getModel(), fechaInicial.getDate(),
                    fechaFinal.getDate());
        });

        fechaFinal.addDateChangeListener(evt -> {
            Utils.clearTable(table);
            Tabla.fill(model, Constants.MULTAS);
            filtrarFechas((DefaultTableModel) table.getModel(), fechaInicial.getDate(),
                    fechaFinal.getDate());
        });

        JButton btnRefrescar = Utils.btnGeneric("Refrescar", "Right");
        panel.add(btnRefrescar);

        btnRefrescar.addActionListener(b -> {
            fechaFinal.setDate(null);
            fechaInicial.setDate(null);
            Utils.clearTable(table);
            Tabla.fill(model, Constants.MULTAS);
        });

        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn("Fecha");
        model.addColumn("Plazo");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        Tabla.fill(model, Constants.MULTAS);
    }

    private DatePicker datePickerInitial() {
        DatePicker datePickerInitial = new DatePicker(DateSettings.getDatePickerSettings());
        datePickerInitial.setBounds(70, 70, 200, 30);
        return datePickerInitial;
    }

    private DatePicker datePickerFinal() {
        DatePicker datePickerFinal = new DatePicker(DateSettings.getDatePickerSettings());
        datePickerFinal.setBounds(70, 110, 200, 30);
        return datePickerFinal;
    }

    private JLabel labelFiltroFechas() {
        JLabel filtro = new JLabel("Filtrar por fecha de multas");
        filtro.setBounds(10, -30, 775, 136);
        filtro.setForeground(Color.GRAY);
        filtro.setFont(new Font(Constants.FONT, Font.BOLD, 14));
        return filtro;
    }

    private JLabel labelFiltroInicio() {
        JLabel filtro = new JLabel("Inicio");
        filtro.setBounds(10, 20, 775, 136);
        filtro.setForeground(Color.GRAY);
        filtro.setFont(new Font(Constants.FONT, Font.PLAIN, 12));
        return filtro;
    }

    private JLabel labelFiltroFin() {
        JLabel filtro = new JLabel("Fin");
        filtro.setBounds(10, 60, 775, 136);
        filtro.setForeground(Color.GRAY);
        filtro.setFont(new Font(Constants.FONT, Font.PLAIN, 12));
        return filtro;
    }

    private void filtrarFechas(DefaultTableModel modelo, LocalDate startDate,
            LocalDate endDate) {
        LocalDate newStartDate = startDate != null ? startDate : LocalDate.MIN;
        LocalDate newEndDate = endDate != null ? endDate : LocalDate.MAX;

        for (Integer i = 0; i < modelo.getDataVector().size(); i++) {
            LocalDate fecha = (LocalDate) modelo.getValueAt(i, 1);
            if (!(fecha.isBefore(newEndDate) && fecha.isAfter(newStartDate))) {
                modelo.removeRow(i--);
            }
        }
    }
}
