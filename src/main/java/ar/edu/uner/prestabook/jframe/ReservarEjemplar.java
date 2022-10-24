package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

import com.github.lgooddatepicker.components.DatePicker;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.utils.DateSettings;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.model.Prestamo;
import ar.edu.uner.prestabook.model.Reserva;

import javax.swing.JTextField;

/**
 * GUI Designed to display the user interface of a new book loan
 *
 */
public class ReservarEjemplar extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Class constructor
     * 
     * @throws ParseException
     */
    public ReservarEjemplar(Obra obra, Edicion edicion) {
        ventana();
        JPanel contentPane = contentPane();

        JPanel panelReservarObra = panelReservarObra();
        contentPane.add(panelReservarObra);

        JLabel labelReservarObra = labelReservarObra();
        panelReservarObra.add(labelReservarObra);

        JLabel labelObra = labelObra();
        contentPane.add(labelObra);
        obra.getIsbn();

        JLabel labelFechaDisponible = labelFechaDisponible();
        contentPane.add(labelFechaDisponible);

        JButton btnConfirmar = btnConfirmar();
        contentPane.add(btnConfirmar);

        JButton btnCancelar = btnCancelar();
        contentPane.add(btnCancelar);

        JLabel lblReservarObraPara = lblReservarObraPara();
        contentPane.add(lblReservarObraPara);

        JTextField fieldTitulo = fieldTitulo();
        fieldTitulo.setText(obra.getTitulo());
        contentPane.add(fieldTitulo);

        JLabel lblTitulo = lblTitulo();
        contentPane.add(lblTitulo);

        JTextField fieldIsbnObra = fieldIsbnObra();
        fieldIsbnObra.setText(obra.getIsbn());
        contentPane.add(fieldIsbnObra);

        JTextField fieldFechaDisponible = fieldFechaDisponible();
        fieldFechaDisponible.setText(buscarFechaDisponible(obra, edicion).get(1).toString());
        contentPane.add(fieldFechaDisponible);

        DatePicker calendarFechaReserva = calendarFechaReserva();
        contentPane.add(calendarFechaReserva);

        JLabel lblEdicion = lblEdicion();
        contentPane.add(lblEdicion);

        JTextField fieldEdicion = fieldEdicion();
        fieldEdicion.setText(edicion.getId().toString());
        contentPane.add(fieldEdicion);

        btnConfirmar.addActionListener(e -> {

            if (calendarFechaReserva.getDate() != null) {
                Reserva reserva = new Reserva();

                reserva.setEjemplar(DaoFactory.getEjemplarDAO()
                        .findById(buscarFechaDisponible(obra, edicion).get(0)));

                reserva.setFechaReserva(calendarFechaReserva.getText());
                reserva.setLector(SistemaLector.getLoggedUser());
                reserva.setIsActive(true);
                DaoFactory.getReservaDAO().insert(reserva);

                JOptionPane.showInternalMessageDialog(null, "Datos guardados correctamente");
                this.setVisible(false);
            } else {
                JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos");
            }

        });

        btnCancelar.addActionListener(e -> {
            this.setVisible(false);
            AdministrarObra verMas = new AdministrarObra(obra);
            verMas.setVisible(true);
        });
    }

    /**
     * Creates the window
     */
    public void ventana() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 655, 440);
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
    public JPanel panelReservarObra() {
        JPanel panelReservarObra = new JPanel();
        panelReservarObra.setBackground(new Color(0, 64, 128));
        panelReservarObra.setBounds(0, 0, 655, 98);
        panelReservarObra.setLayout(null);
        return panelReservarObra;
    }

    public JTextField fieldTitulo() {
        JTextField fieldTitulo = new JTextField();
        fieldTitulo.setEditable(false);
        fieldTitulo.setColumns(10);
        fieldTitulo.setBounds(358, 140, 227, 29);
        return fieldTitulo;
    }

    public JTextField fieldIsbnObra() {
        JTextField fieldIsbnObra = new JTextField();
        fieldIsbnObra.setEditable(false);
        fieldIsbnObra.setBounds(56, 140, 227, 29);
        fieldIsbnObra.setColumns(10);
        return fieldIsbnObra;
    }

    public JLabel labelReservarObra() {
        JLabel labelReservarObra = new JLabel("Reservar obra");
        labelReservarObra.setForeground(new Color(255, 255, 255));
        labelReservarObra.setBounds(240, 31, 191, 39);
        labelReservarObra.setFont(new Font("Verdana", Font.BOLD, 20));
        return labelReservarObra;
    }

    public JLabel lblReservarObraPara() {
        JLabel lblReservarObraPara = new JLabel("Reservar obra para el dia");
        lblReservarObraPara.setBounds(358, 256, 191, 14);
        return lblReservarObraPara;
    }

    public JLabel labelObra() {
        JLabel lblIsbnObra = new JLabel("Isbn de obra");
        lblIsbnObra.setBounds(56, 123, 106, 14);
        return lblIsbnObra;
    }

    /**
     * Creates a label
     * 
     * @return a label with the Copy text
     */
    public JLabel lblTitulo() {
        JLabel lblTitulo = new JLabel("Título");
        lblTitulo.setBounds(358, 123, 106, 14);
        return lblTitulo;
    }

    /**
     * Creates a DatePicker for the date and time of the return
     */
    public JTextField fieldFechaDisponible() {
        JTextField fieldFechaDisponible = new JTextField();
        fieldFechaDisponible.setText((String) null);
        fieldFechaDisponible.setEditable(false);
        fieldFechaDisponible.setColumns(10);
        fieldFechaDisponible.setBounds(56, 281, 227, 29);
        return fieldFechaDisponible;
    }

    /**
     * Creates a label
     * 
     * @return a label with the return date text
     */
    public JLabel labelFechaDisponible() {
        JLabel labelFechaDisponible = new JLabel("Ejemplar disponible para el dia");
        labelFechaDisponible.setBounds(56, 256, 191, 14);
        return labelFechaDisponible;
    }

    /**
     * Creates a confirmation button
     */
    public JButton btnConfirmar() {
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(174, 392, 120, 23);
        return btnConfirmar;
    }

    /**
     * Creates a cancel button
     * 
     */
    public JButton btnCancelar() {
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(343, 392, 120, 23);
        return btnCancelar;
    }

    public DatePicker calendarFechaReserva() {
        DatePicker calendarFechaReserva = new DatePicker();
        calendarFechaReserva.setBounds(360, 281, 230, 29);
        calendarFechaReserva.setSettings(DateSettings.getDatePickerSettings());
        return calendarFechaReserva;
    }

    public List<Object> buscarFechaDisponible(Obra obra, Edicion edicion) {
        List<Reserva> reservas = DaoFactory.getReservaDAO().findAll();
        List<Object> fechaIdEjempalar = new LinkedList<>();
        if (!reservas.isEmpty()) {
            for (Reserva reserva : reservas) {
                if (Objects.equals(reserva.getEjemplar().getIsbnObra(), obra.getIsbn())
                        && Objects.equals(reserva.getEjemplar().getIdEdicion(), edicion.getId())) {
                    LocalDate fechaReserva = LocalDate
                            .parse(DaoFactory.getReservaDAO().findByIdEjemplar(reserva.getEjemplar().getId())
                                    .getFechaReserva(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            .plus(Constants.PLAZO_PRESTAMO, ChronoUnit.DAYS);
                    fechaIdEjempalar.add(reserva.getEjemplar().getId());
                    fechaIdEjempalar.add(fechaReserva.toString());
                }
            }
        }

        if (fechaIdEjempalar.isEmpty()) {
            List<Prestamo> prestamos = DaoFactory.getPrestamoDAO().findAll();
            if (!prestamos.isEmpty()) {
                for (Prestamo prestamo : prestamos) {
                    if (Objects.equals(prestamo.getEjemplar().getIsbnObra(), obra.getIsbn())
                            && Objects.equals(prestamo.getEjemplar().getIdEdicion(), edicion.getId())) {
                        fechaIdEjempalar.add(prestamo.getEjemplar().getId());
                        fechaIdEjempalar.add(prestamo.getFechaPactadaDevolucion());
                    }
                }
            }
        }
        
        return fechaIdEjempalar;
    }

    public JLabel lblEdicion() {
        JLabel lblEdicion = new JLabel("Edicion");
        lblEdicion.setBounds(56, 196, 106, 14);
        return lblEdicion;
    }

    public JTextField fieldEdicion() {
        JTextField fieldEdicion = new JTextField();
        fieldEdicion.setText((String) null);
        fieldEdicion.setEditable(false);
        fieldEdicion.setColumns(10);
        fieldEdicion.setBounds(56, 208, 227, 29);
        return fieldEdicion;
    }
}
