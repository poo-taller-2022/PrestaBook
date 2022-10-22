package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.util.List;
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

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.render.EjemplarRenderer;
import ar.edu.uner.prestabook.jframe.render.ObraRenderer;
import ar.edu.uner.prestabook.jframe.utils.DateSettings;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.model.Reserva;

/**
 * GUI Designed to display the user interface of a new book loan
 *
 */
public class Reservas extends JFrame {

    /** Serial number */
    private static final long serialVersionUID = 1L;

    /**
     * Class constructor
     */
    public Reservas() {
        ventana();
        JPanel contentPane = contentPane();

        JPanel panelAgregarObra = panelNuevoPrestamo();
        contentPane.add(panelAgregarObra);

        JLabel lblAgregarObra = labelNuevoPrestamo();
        panelAgregarObra.add(lblAgregarObra);

        JComboBox<Ejemplar> comboBoxEjemplar = comboBoxEjemplar();
        contentPane.add(comboBoxEjemplar);

        JComboBox<Obra> comboBoxObra = comboBoxObra();
        comboBoxObra.addItemListener(e -> {
            Obra obra = (Obra) e.getItem();
            comboBoxEjemplar.removeAllItems();
            List<Ejemplar> ejemplares = DaoFactory.getEjemplarDAO().findAllByObraIsbn(obra.getIsbn());
            for (Ejemplar ejemplar : ejemplares)
                comboBoxEjemplar.addItem(ejemplar);
        });
        contentPane.add(comboBoxObra);

        JLabel labelObra = labelObra();
        contentPane.add(labelObra);

        JLabel labelEjemplar = labelEjemplar();
        contentPane.add(labelEjemplar);

        DatePicker calendarFechaReserva = calendarFechaReserva();
        contentPane.add(calendarFechaReserva);

        JLabel labelFechaDevolucion = labelFechaReserva();
        contentPane.add(labelFechaDevolucion);

        JButton btnConfirmar = btnConfirmar();
        contentPane.add(btnConfirmar);

        JButton btnCancelar = btnCancelar();
        contentPane.add(btnCancelar);

        btnConfirmar.addActionListener(e -> {
            Boolean camposCompletos = comboBoxObra.getSelectedItem() != null
                    && comboBoxEjemplar.getSelectedItem() != null
                    && calendarFechaReserva.getDate() != null;

            if (Boolean.TRUE.equals(camposCompletos)) {
                Reserva reserva = new Reserva();
                reserva.setEjemplar((Ejemplar) comboBoxEjemplar.getSelectedItem());
                reserva.setFechaReserva(calendarFechaReserva.getDate().toString());
                reserva.setLector(SistemaLector.getLoggedUser());
                reserva.setIsActive(true);
                DaoFactory.getReservaDAO().insert(reserva);

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
     * @return a label with the new reservation text
     */
    public JLabel labelNuevoPrestamo() {
        JLabel lblNuevoPrestamo = new JLabel("Nueva reserva");
        lblNuevoPrestamo.setForeground(new Color(255, 255, 255));
        lblNuevoPrestamo.setBounds(240, 31, 191, 39);
        lblNuevoPrestamo.setFont(new Font("Verdana", Font.BOLD, 20));
        return lblNuevoPrestamo;
    }

    /**
     * Creates a Combo Box with all the Books
     */
    public JComboBox<Obra> comboBoxObra() {
        JComboBox<Obra> comboBoxObra = new JComboBox<>(new Vector<>(DaoFactory.getObraDAO().findAll()));
        comboBoxObra.setBounds(24, 191, 481, 29);
        comboBoxObra.setRenderer(new ObraRenderer());
        comboBoxObra.setSelectedItem(null);
        return comboBoxObra;
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
    public JComboBox<Ejemplar> comboBoxEjemplar() {
        JComboBox<Ejemplar> comboBoxEjemplar = new JComboBox<>(new Vector<>(DaoFactory.getEjemplarDAO().findAll()));
        comboBoxEjemplar.setBounds(528, 191, 69, 29);
        comboBoxEjemplar.setRenderer(new EjemplarRenderer());
        comboBoxEjemplar.setSelectedItem(null);
        return comboBoxEjemplar;
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
     * Creates a DatePicker for the date and time of the return
     */
    public DatePicker calendarFechaReserva() {
        DatePicker calendarFechaYHoraDevolucion = new DatePicker();
        calendarFechaYHoraDevolucion.setBounds(24, 245, 271, 29);
        calendarFechaYHoraDevolucion.setDate(LocalDate.now());
        calendarFechaYHoraDevolucion.setSettings(DateSettings.getDatePickerSettings());
        return calendarFechaYHoraDevolucion;
    }

    /**
     * Creates a label
     * 
     * @return a label with the return date text
     */
    public JLabel labelFechaReserva() {
        JLabel labelFechaReserva = new JLabel("Fecha de reserva");
        labelFechaReserva.setBounds(24, 231, 166, 14);
        return labelFechaReserva;
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
}
