package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.render.PersonaRenderer;
import ar.edu.uner.prestabook.jframe.render.PrestamoRenderer;
import ar.edu.uner.prestabook.jframe.utils.DateSettings;
import ar.edu.uner.prestabook.model.Lector;
import ar.edu.uner.prestabook.model.Multa;
import ar.edu.uner.prestabook.model.Prestamo;

/**
 * GUI Designed to display the user interface of a new book loan
 *
 */
public class Devoluciones extends JFrame {

    private static final String CONFIRMAR = "Confirmar";
    /** Serial number */
    private static final long serialVersionUID = 1L;

    /**
     * Class constructor
     */
    public Devoluciones() {
        ventana();
        JPanel contentPane = contentPane();

        JPanel panelAgregarObra = panelNuevoPrestamo();
        contentPane.add(panelAgregarObra);

        JLabel lblAgregarObra = labelPrestamosVigentes();
        panelAgregarObra.add(lblAgregarObra);

        JTextField textEjemplar = textEjemplar();
        contentPane.add(textEjemplar);

        JTextField textObra = textObra();
        contentPane.add(textObra);

        JLabel labelObra = labelObra();
        contentPane.add(labelObra);

        JLabel labelEjemplar = labelEjemplar();
        contentPane.add(labelEjemplar);

        JLabel labelLector = labelLector();
        contentPane.add(labelLector);

        JTextField textFuncionario = textFuncionario();
        contentPane.add(textFuncionario);

        JLabel labelFuncionario = labelFuncionario();
        contentPane.add(labelFuncionario);

        DateTimePicker calendarFechaYHoraPrestamo = calendarFechaYHoraPrestamo();
        contentPane.add(calendarFechaYHoraPrestamo);

        JLabel labelFechaYHoraPrestamo = labelFechaYHoraPrestamo();
        contentPane.add(labelFechaYHoraPrestamo);

        DatePicker calendarPactadaDevolucion = calendarFechaPactadaDevolucion();
        contentPane.add(calendarPactadaDevolucion);

        JLabel labelFechaPactadaDevolucion = labelFechaPactadaDevolucion();
        contentPane.add(labelFechaPactadaDevolucion);

        JLabel labelFechaRealDevolucion = labelFechaRealDevolucion();
        contentPane.add(labelFechaRealDevolucion);

        JLabel labelPrestamo = labePrestamo();
        contentPane.add(labelPrestamo);

        JComboBox<Prestamo> comboBoxPrestamo = comboBoxPrestamo();
        comboBoxPrestamo.addItemListener(e -> {
            Prestamo prestamo = (Prestamo) e.getItem();
            textEjemplar.setText(prestamo.getEjemplar().getId().toString());
            textObra.setText(prestamo.getEjemplar().getTitulo());
            textFuncionario.setText(prestamo.getFuncionario().getApellido());
            calendarPactadaDevolucion.setDate(prestamo.getFechaPactadaDevolucion() != null
                    ? LocalDate.parse(prestamo.getFechaPactadaDevolucion(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    : null);
            calendarFechaYHoraPrestamo.setDateTimePermissive(
                    prestamo.getFechaYHoraPrestamo() != null ? LocalDateTime.parse(prestamo.getFechaYHoraPrestamo())
                            : null);
        });
        contentPane.add(comboBoxPrestamo);

        JComboBox<Lector> comboBoxLector = comboBoxLector();
        comboBoxLector.addItemListener(e -> {
            comboBoxPrestamo.removeAllItems();
            Lector lector = (Lector) e.getItem();
            List<Prestamo> prestamos = DaoFactory.getPrestamoDAO().findAllByLectorId(lector.getDocumento());
            for (Prestamo prestamo : prestamos) {
                if (prestamo.getFechaRealDevolucion() == null && prestamo.getFuncionario() != null)
                    comboBoxPrestamo.addItem(prestamo);
            }
        });
        contentPane.add(comboBoxLector);

        JButton btnMultar = btnMultar();
        btnMultar.addActionListener(e -> {
            Integer seleccion = JOptionPane.showConfirmDialog(null, "¿Registrar devolución y aplicar multa?", CONFIRMAR,
                    JOptionPane.YES_NO_OPTION);
            if (seleccion == JOptionPane.YES_OPTION) {
                Prestamo prestamo = (Prestamo) comboBoxPrestamo.getSelectedItem();
                Integer plazoMulta = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresar días de multa",
                        "Días de multa", JOptionPane.OK_CANCEL_OPTION));
                Multa multa = new Multa();
                String fechaDevolucion = LocalDate.now().toString();
                prestamo.setFechaRealDevolucion(fechaDevolucion);
                DaoFactory.getPrestamoDAO().update(prestamo);
                multa.setFecha(fechaDevolucion);
                multa.setLector((Lector) comboBoxLector.getSelectedItem());
                multa.setPrestamo(prestamo);
                multa.setPlazo(plazoMulta);
                DaoFactory.getMultaDAO().insert(multa);
                JOptionPane.showInternalMessageDialog(null, "Datos guardados correctamente");
                this.setVisible(false);
            }
        });
        contentPane.add(btnMultar);

        DatePicker calendarFechaRealDevolucion = calendarFechaRealDevolucion();
        calendarFechaRealDevolucion.addDateChangeListener(e -> {
            LocalDate fechaReal = e.getNewDate();
            LocalDate fechaPactada = calendarPactadaDevolucion.getDate();
            btnMultar.setEnabled(fechaReal.isAfter(fechaPactada
                    .plusDays(Integer.valueOf(DaoFactory.getConfigDAO().findById("default_loan_time").getValue()))));
        });
        contentPane.add(calendarFechaRealDevolucion);

        JButton btnConfirmar = btnConfirmar();
        contentPane.add(btnConfirmar);

        JButton btnCancelar = btnCancelar();
        contentPane.add(btnCancelar);

        btnConfirmar.addActionListener(e -> {
            Boolean camposCompletos = textObra.getText() != null && textEjemplar.getText() != null
                    && comboBoxLector.getSelectedItem() != null && textFuncionario.getText() != null
                    && calendarFechaYHoraPrestamo.getDateTimePermissive() != null
                    && calendarFechaRealDevolucion.getDate() != null;

            if (Boolean.TRUE.equals(camposCompletos)) {
                Integer seleccion = JOptionPane.showConfirmDialog(null, "¿Registrar devolución?", CONFIRMAR,
                        JOptionPane.YES_NO_OPTION);
                if (seleccion == JOptionPane.YES_OPTION) {
                    Prestamo prestamo = (Prestamo) comboBoxPrestamo.getSelectedItem();
                    String fechaDevolucion = LocalDate.now().toString();
                    prestamo.setFechaRealDevolucion(fechaDevolucion);
                    DaoFactory.getPrestamoDAO().update(prestamo);
                    JOptionPane.showInternalMessageDialog(null, "Datos guardados correctamente");
                    this.setVisible(false);
                }
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
        JPanel panelAgregarObra = new JPanel();
        panelAgregarObra.setBackground(new Color(0, 64, 128));
        panelAgregarObra.setBounds(0, 0, 655, 98);
        panelAgregarObra.setLayout(null);
        return panelAgregarObra;
    }

    /**
     * Creates a label
     * 
     * @return a label with the current loans text
     */
    public JLabel labelPrestamosVigentes() {
        JLabel lblDevolucion = new JLabel("Préstamos Vigentes");
        lblDevolucion.setForeground(new Color(255, 255, 255));
        lblDevolucion.setBounds(202, 30, 251, 39);
        lblDevolucion.setFont(new Font("Verdana", Font.BOLD, 20));
        return lblDevolucion;
    }

    /**
     * Creates a label
     * 
     * @return a label with the type of Loan text
     */
    public JLabel labePrestamo() {
        JLabel lblPrestamo = new JLabel("Préstamo");
        lblPrestamo.setBounds(370, 128, 227, 14);
        return lblPrestamo;
    }

    /**
     * Creates a Combo Box with the type of loans
     * 
     */
    public JComboBox<Prestamo> comboBoxPrestamo() {
        JComboBox<Prestamo> comboBoxPrestamo = new JComboBox<>();
        comboBoxPrestamo.setBounds(370, 143, 227, 29);
        comboBoxPrestamo.setSelectedItem(null);
        comboBoxPrestamo.setRenderer(new PrestamoRenderer());
        return comboBoxPrestamo;
    }

    /**
     * Creates a Combo Box with all the Books
     */
    public JTextField textObra() {
        JTextField textObra = new JTextField();
        textObra.setBounds(24, 191, 481, 29);
        textObra.setEnabled(false);
        return textObra;
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
     * Creates a Combo Box with all the Copies of the selected Book
     */
    public JTextField textEjemplar() {
        JTextField textEjemplar = new JTextField();
        textEjemplar.setBounds(528, 191, 69, 29);
        textEjemplar.setEnabled(false);
        return textEjemplar;
    }

    /**
     * Creates a label
     * 
     * @return a label with the Copy text
     */
    public JLabel labelEjemplar() {
        JLabel labelEjemplar = new JLabel("Ejemplar");
        labelEjemplar.setBounds(530, 177, 67, 14);
        return labelEjemplar;
    }

    /**
     * Creates a Combo Box with all the Readers
     */
    public JComboBox<Lector> comboBoxLector() {
        JComboBox<Lector> comboBoxLector = new JComboBox<>(new Vector<>(DaoFactory.getLectorDAO().findAll()));
        comboBoxLector.setBounds(24, 142, 336, 29);
        comboBoxLector.setSelectedItem(null);
        comboBoxLector.setRenderer(new PersonaRenderer());
        return comboBoxLector;
    }

    /**
     * Creates a label
     * 
     * @return a label with the Reader text
     */
    public JLabel labelLector() {
        JLabel labelLector = new JLabel("Lector");
        labelLector.setBounds(24, 128, 73, 14);
        return labelLector;
    }

    /**
     * Creates a Combo Box with all the Employees
     */
    public JTextField textFuncionario() {
        JTextField textFuncionario = new JTextField();
        textFuncionario.setBounds(326, 256, 271, 29);
        textFuncionario.setEnabled(false);
        return textFuncionario;
    }

    /**
     * Creates a label
     * 
     * @return a label with the Employee text
     */
    public JLabel labelFuncionario() {
        JLabel labelFuncionario = new JLabel("Funcionario");
        labelFuncionario.setBounds(326, 242, 83, 14);
        return labelFuncionario;
    }

    /**
     * Creates a DateTimePicker for the date and time of the loan
     */
    public DateTimePicker calendarFechaYHoraPrestamo() {
        DateTimePicker calendarFechaYHoraPrestamo = new DateTimePicker();
        calendarFechaYHoraPrestamo.setBounds(26, 256, 271, 29);
        calendarFechaYHoraPrestamo.setEnabled(false);
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
        labelFechaYHoraPrestamo.setBounds(28, 239, 164, 14);
        return labelFechaYHoraPrestamo;
    }

    /**
     * Creates a DatePicker for the date and time of the return
     */
    public DatePicker calendarFechaPactadaDevolucion() {
        DatePicker calendarFechaYHoraDevolucion = new DatePicker();
        calendarFechaYHoraDevolucion.setBounds(26, 310, 271, 29);
        calendarFechaYHoraDevolucion.setEnabled(false);
        calendarFechaYHoraDevolucion.setSettings(DateSettings.getDatePickerSettings());
        return calendarFechaYHoraDevolucion;
    }

    /**
     * Creates a label
     * 
     * @return a label with the return date text
     */
    public JLabel labelFechaPactadaDevolucion() {
        JLabel labelFechaPactadaDevolucion = new JLabel("Fecha pactada de devolución");
        labelFechaPactadaDevolucion.setBounds(26, 296, 166, 14);
        return labelFechaPactadaDevolucion;
    }

    /**
     * Creates a DatePicker for the date and time of the return
     */
    public DatePicker calendarFechaRealDevolucion() {
        DatePicker calendarFechaRealDevolucion = new DatePicker();
        calendarFechaRealDevolucion.setBounds(326, 310, 271, 29);
        calendarFechaRealDevolucion.setSettings(DateSettings.getDatePickerSettings());
        return calendarFechaRealDevolucion;
    }

    /**
     * Creates a label
     * 
     * @return a label with the return date text
     */
    public JLabel labelFechaRealDevolucion() {
        JLabel labelFechaRealDevolucion = new JLabel("Fecha real de devolución");
        labelFechaRealDevolucion.setBounds(326, 296, 166, 14);
        return labelFechaRealDevolucion;
    }

    /**
     * Creates a confirmation button
     */
    public JButton btnMultar() {
        JButton btnMultar = new JButton("Aplicar multa");
        btnMultar.setBounds(261, 365, 120, 23);
        btnMultar.setEnabled(false);
        return btnMultar;
    }

    /**
     * Creates a confirmation button
     */
    public JButton btnConfirmar() {
        JButton btnConfirmar = new JButton(CONFIRMAR);
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
}
