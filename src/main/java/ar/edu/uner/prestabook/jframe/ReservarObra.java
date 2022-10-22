package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

import com.github.lgooddatepicker.components.DatePicker;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.utils.DateSettings;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.model.Prestamo;
import ar.edu.uner.prestabook.persistence.IPrestamoDAO;

import javax.swing.JTextField;

/**
 * GUI Designed to display the user interface of a new book loan
 *
 */
public class ReservarObra extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Class constructor
	 */
	public ReservarObra(Obra obra) {
		ventana();
		JPanel contentPane = contentPane();

		JPanel panelReservarObra = panelReservarObra();
		contentPane.add(panelReservarObra);

		JLabel labelReservarObra = labelReservarObra();
		panelReservarObra.add(labelReservarObra);

		JLabel labelObra = labelObra();
		contentPane.add(labelObra);
		obra.getIsbn();

		JLabel labelFechaDevolucion = labelFechaDevolucion();
		contentPane.add(labelFechaDevolucion);

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
		fieldFechaDisponible.setText(buscarFechaDisponible(obra));
		contentPane.add(fieldFechaDisponible);
		
		DatePicker calendarPactadaDevolucion = calendarFechaPactadaDevolucion();
		contentPane.add(calendarPactadaDevolucion);

		btnConfirmar.addActionListener(e -> {});

		btnCancelar.addActionListener(e -> this.setVisible(false));
	}

	/**
	 * Creates the window
	 */
	public void ventana() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 380);
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
		lblReservarObraPara.setBounds(360, 203, 191, 14);
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
		fieldFechaDisponible.setBounds(56, 224, 227, 29);
		return fieldFechaDisponible;
	}

	/**
	 * Creates a label
	 * 
	 * @return a label with the return date text
	 */
	public JLabel labelFechaDevolucion() {
		JLabel labelFechaDevolucion = new JLabel("Ejemplar disponible para el dia");
		labelFechaDevolucion.setBounds(56, 203, 191, 14);
		return labelFechaDevolucion;
	}

	/**
	 * Creates a confirmation button
	 */
	public JButton btnConfirmar() {
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(173, 312, 120, 23);
		return btnConfirmar;
	}

	/**
	 * Creates a cancel button
	 * 
	 */
	public JButton btnCancelar() {
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(344, 312, 120, 23);
		return btnCancelar;
	}
	
	public DatePicker calendarFechaPactadaDevolucion() {
		DatePicker calendarFechaYHoraDevolucion = new DatePicker();
		calendarFechaYHoraDevolucion.setBounds(360, 224, 230, 29);
		calendarFechaYHoraDevolucion.setSettings(DateSettings.getDatePickerSettings());
		return calendarFechaYHoraDevolucion;
	}
	
	public String buscarFechaDisponible(Obra obra) {
		IPrestamoDAO prestamoDAO = DaoFactory.getPrestamoDAO();
		List<Prestamo> prestamos = prestamoDAO.findAll();
		List<Object> fechas = new LinkedList<>();
		for (Prestamo prestamo : prestamos) {
			Ejemplar ejemplar = prestamo.getEjemplar();
			if (Objects.equals(ejemplar.getIsbnObra(), obra.getIsbn())) {
				fechas.add(prestamo.getFechaPactadaDevolucion());
			}
		}
		fechas.stream().sorted().collect(Collectors.toList());
		return fechas.get(0).toString();
	}
}
