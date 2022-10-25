package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.utils.DateSettings;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.model.Prestamo;
import ar.edu.uner.prestabook.persistence.IPrestamoDAO;
import ar.edu.uner.prestabook.persistence.IReservaDAO;

import javax.swing.JTextField;

/**
 * GUI Designed to display the user interface of a new book loan
 *
 */
public class PedirPrestamo extends JFrame {

    private static final String PRESTAMO_A_DOMICILIO = "A Domicilio";
    private static final String PRESTAMO_EN_SALA = "En Sala";
    /** Serial number */
    private static final long serialVersionUID = 1L;

    /**
     * Class constructor
     */
    public PedirPrestamo(Obra obra, Edicion edicion) {
        ventana();
        JPanel contentPane = contentPane();

        JPanel panelNuevoPrestamo = panelNuevoPrestamo();
        contentPane.add(panelNuevoPrestamo);

        JLabel labelNuevoPrestamo = labelNuevoPrestamo();
        panelNuevoPrestamo.add(labelNuevoPrestamo);

        JLabel labelObra = labelObra();
        contentPane.add(labelObra);

        JLabel labelEjemplar = labelEjemplar();
        contentPane.add(labelEjemplar);

        DateTimePicker calendarFechaYHoraPrestamo = calendarFechaYHoraPrestamo();
        contentPane.add(calendarFechaYHoraPrestamo);

        JLabel labelFechaYHoraPrestamo = labelFechaYHoraPrestamo();
        contentPane.add(labelFechaYHoraPrestamo);

        DatePicker calendarPactadaDevolucion = calendarFechaPactadaDevolucion();
        contentPane.add(calendarPactadaDevolucion);

        JLabel labelFechaDevolucion = labelFechaDevolucion();
        contentPane.add(labelFechaDevolucion);

        JLabel labelTipoPrestamo = labelTipoPrestamo();
        contentPane.add(labelTipoPrestamo);

        JTextField fieldObra = fieldObra();
        fieldObra.setText(obra.getIsbn());
        contentPane.add(fieldObra);

        JTextField fieldEdicion = fieldEdicion();
        fieldEdicion.setText(edicion.getId().toString());
        contentPane.add(fieldEdicion);

        JComboBox<String> comboBoxTipoPrestamo = comboBoxTipoPrestamo();
        comboBoxTipoPrestamo.addItemListener(e -> {
            if (e.getItem().equals(PRESTAMO_EN_SALA)) {
                labelFechaYHoraPrestamo.setVisible(false);
                labelFechaDevolucion.setVisible(false);
                calendarFechaYHoraPrestamo.setVisible(false);
                calendarPactadaDevolucion.setVisible(false);
            }
            if (e.getItem().equals(PRESTAMO_A_DOMICILIO)) {
                labelFechaYHoraPrestamo.setVisible(true);
                labelFechaDevolucion.setVisible(true);
                calendarFechaYHoraPrestamo.setVisible(true);
                calendarPactadaDevolucion.setVisible(true);
            }
        });
        contentPane.add(comboBoxTipoPrestamo);

        JButton btnConfirmar = btnConfirmar();
        contentPane.add(btnConfirmar);

        JButton btnCancelar = btnCancelar();
        contentPane.add(btnCancelar);

        btnConfirmar.addActionListener(e -> {
            Boolean camposCompletos = calendarFechaYHoraPrestamo.getDateTimePermissive() != null
                    && calendarPactadaDevolucion.getDate() != null;

            if (Boolean.TRUE.equals(camposCompletos)) {
                Prestamo prestamo = new Prestamo();
                prestamo.setEjemplar(buscarEjemplar(obra, edicion));
                prestamo.setLector(SistemaLector.getLoggedUser());
                prestamo.setFechaYHoraPrestamo(calendarFechaYHoraPrestamo.getDateTimePermissive().toString());
                prestamo.setFechaPactadaDevolucion(calendarPactadaDevolucion.getDate().toString());
                prestamo.setPlazoPrestamo(Constants.PLAZO_PRESTAMO);
                DaoFactory.getPrestamoDAO().insert(prestamo);

                JOptionPane.showInternalMessageDialog(null, "Datos guardados correctamente");
                this.setVisible(false);
            } else {
                JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos");
            }
        });

        btnCancelar.addActionListener(e -> this.setVisible(false));
    }

    /**
     * Creates the window
     */
    public void ventana() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 655, 540);
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle(Constants.PRESTABOOK);
    }

    /**
     * Creates the pane
     * 
     * @return a container
     */
    public JPanel contentPane() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new MatteBorder(3, 3, 3, 3, new Color(0, 64, 128)));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        return contentPane;
    }

    /**
     * Creates the pane
     * 
     * @return a container
     */
    public JPanel panelNuevoPrestamo() {
        JPanel panelNuevoPrestamo = new JPanel();
        panelNuevoPrestamo.setBackground(new Color(0, 64, 128));
        panelNuevoPrestamo.setBounds(0, 0, 655, 98);
        panelNuevoPrestamo.setLayout(null);
        return panelNuevoPrestamo;
    }

    /**
     * Creates a label
     * 
     * @return a label with the New Loan text
     */
    public JLabel labelNuevoPrestamo() {
        JLabel lblNuevoPrestamo = new JLabel("Nuevo préstamo");
        lblNuevoPrestamo.setForeground(new Color(255, 255, 255));
        lblNuevoPrestamo.setBounds(240, 31, 191, 39);
        lblNuevoPrestamo.setFont(new Font("Verdana", Font.BOLD, 20));
        return lblNuevoPrestamo;
    }

    /**
     * Creates a label
     * 
     * @return a label with the type of Loan text
     */
    public JLabel labelTipoPrestamo() {
        JLabel lblNuevoPrestamo = new JLabel("Tipo de préstamo");
        lblNuevoPrestamo.setBounds(24, 126, 227, 14);
        return lblNuevoPrestamo;
    }

    /**
     * Creates a Combo Box with the type of loans
     * 
     */
    public JComboBox<String> comboBoxTipoPrestamo() {
        JComboBox<String> comboBoxTipoPrestamo = new JComboBox<>(
                new Vector<>(List.of(PRESTAMO_EN_SALA, PRESTAMO_A_DOMICILIO)));
        comboBoxTipoPrestamo.setBounds(24, 137, 227, 29);
        comboBoxTipoPrestamo.setSelectedItem(PRESTAMO_A_DOMICILIO);
        return comboBoxTipoPrestamo;
    }

    /**
     * Creates a label
     * 
     * @return a label with the Book text
     */
    public JLabel labelObra() {
        JLabel labelObra = new JLabel("Obra");
        labelObra.setBounds(24, 177, 46, 14);
        return labelObra;
    }

    /**
     * Creates a label
     * 
     * @return a label with the Copy text
     */
    public JLabel labelEjemplar() {
        JLabel lblEdicion = new JLabel("Edición");
        lblEdicion.setBounds(370, 177, 67, 14);
        return lblEdicion;
    }

    /**
     * Creates a DateTimePicker for the date and time of the loan
     */
    public DateTimePicker calendarFechaYHoraPrestamo() {
        DateTimePicker calendarFechaYHoraPrestamo = new DateTimePicker();
        calendarFechaYHoraPrestamo.setBounds(24, 320, 271, 29);
        calendarFechaYHoraPrestamo.setDateTimePermissive(LocalDateTime.now());
        calendarFechaYHoraPrestamo.datePicker.setSettings(DateSettings.getDatePickerSettings());
        return calendarFechaYHoraPrestamo;
    }

    /**
     * Creates a label
     * 
     * @return a label with the date and time of loan text
     */
    public JLabel labelFechaYHoraPrestamo() {
        JLabel labelFechaYHoraPrestamo = new JLabel("Fecha y hora de préstamo");
        labelFechaYHoraPrestamo.setBounds(26, 303, 164, 14);
        return labelFechaYHoraPrestamo;
    }

    /**
     * Creates a DatePicker for the date and time of the return
     */
    public DatePicker calendarFechaPactadaDevolucion() {
        DatePicker calendarFechaYHoraDevolucion = new DatePicker();
        calendarFechaYHoraDevolucion.setBounds(326, 320, 271, 29);
        calendarFechaYHoraDevolucion.setDate(LocalDate.now().plus(Constants.PLAZO_PRESTAMO, ChronoUnit.DAYS));
        calendarFechaYHoraDevolucion.setSettings(DateSettings.getDatePickerSettings());
        return calendarFechaYHoraDevolucion;
    }

    /**
     * Creates a label
     * 
     * @return a label with the return date text
     */
    public JLabel labelFechaDevolucion() {
        JLabel labelFechaDevolucion = new JLabel("Fecha de devolución");
        labelFechaDevolucion.setBounds(326, 306, 166, 14);
        return labelFechaDevolucion;
    }

    /**
     * Creates a confirmation button
     */
    public JButton btnConfirmar() {
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(163, 490, 120, 23);
        return btnConfirmar;
    }

    /**
     * Creates a cancel button
     * 
     */
    public JButton btnCancelar() {
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(358, 490, 120, 23);
        return btnCancelar;
    }

    public JTextField fieldObra() {
        JTextField fieldObra = new JTextField();
        fieldObra.setEditable(false);
        fieldObra.setBounds(24, 191, 227, 29);
        fieldObra.setColumns(10);
        return fieldObra;
    }

    public JTextField fieldEdicion() {
        JTextField fieldEdicion = new JTextField();
        fieldEdicion.setEditable(false);
        fieldEdicion.setColumns(10);
        fieldEdicion.setBounds(370, 191, 227, 29);
        return fieldEdicion;
    }

    private Ejemplar buscarEjemplar(Obra obra, Edicion edicion) {
        IPrestamoDAO prestamoDAO = DaoFactory.getPrestamoDAO();
        IReservaDAO reservaDAO = DaoFactory.getReservaDAO();
        List<Ejemplar> ejemplares = DaoFactory.getEjemplarDAO().findAll();
        if (ejemplares != null) {
            for (Ejemplar ejemplar : ejemplares) {
                if (Objects.equals(ejemplar.getIsbnObra(), obra.getIsbn()) && ejemplar.getMotivoBaja() == null
                        && Objects.equals(ejemplar.getIdEdicion(), edicion.getId())
                        && prestamoDAO.findByIdEjemplar(ejemplar.getId()) == null
                        && reservaDAO.findByIdEjemplar(ejemplar.getId()) == null) {
                    return ejemplar;
                }
            }
        }
        return null;
    }
}
