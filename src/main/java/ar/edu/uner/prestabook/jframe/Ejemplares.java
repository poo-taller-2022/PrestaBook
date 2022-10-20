package ar.edu.uner.prestabook.jframe;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.render.EjemplarRenderer;
import ar.edu.uner.prestabook.jframe.render.ObraRenderer;
import ar.edu.uner.prestabook.model.CodigoIdentificatorio;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.model.TipoObra;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * GUI Designed to display the user interface of a new book loan
 *
 */
public class Ejemplares extends JFrame {

	/** Serial number */
	private static final long serialVersionUID = 1L;

	/**
	 * Class constructor
	 */
	public Ejemplares() {
		ventana();
		JPanel contentPane = contentPane();

		JPanel panelEjemplar = panelEjemplar();
		contentPane.add(panelEjemplar);

		JLabel lblEjemplar = lblEjemplar();
		panelEjemplar.add(lblEjemplar);

		JTextField textPasillo = textPasillo();
		contentPane.add(textPasillo);

		JLabel labelPasillo = labelPasillo();
		contentPane.add(labelPasillo);

		JTextField textEstanteria = textEstanteria();
		contentPane.add(textEstanteria);

		JLabel labelEstanteria = labelEstanteria();
		contentPane.add(labelEstanteria);

		JTextField textEstante = textEstante();
		contentPane.add(textEstante);

		JLabel labelEstante = labelEstante();
		contentPane.add(labelEstante);

		DatePicker calendarFechaBaja = calendarFechaBaja();
		contentPane.add(calendarFechaBaja);

		JLabel labelFechaBaja = labelFechaBaja();
		contentPane.add(labelFechaBaja);

		JTextField textMotivoBaja = textMotivoBaja();
		contentPane.add(textMotivoBaja);

		JLabel labelMotivoBaja = labelMotivoBaja();
		contentPane.add(labelMotivoBaja);

		JTextField textObservaciones = textObservaciones();
		contentPane.add(textObservaciones);

		JLabel labelObservaciones = labelObservaciones();
		contentPane.add(labelObservaciones);
		
		JLabel lblMostrarCodigo = lblMostrarCodigo();
		contentPane.add(lblMostrarCodigo);  
	
		CodigoBarras codigoBarras = new CodigoBarras();
		
		JComboBox<Ejemplar> comboBoxEjemplar = comboBoxEjemplar();
		comboBoxEjemplar.addItemListener(e -> {
			Ejemplar ejemplar = (Ejemplar) e.getItem();
			textPasillo.setText(ejemplar.getCodigoIdentificatorio().getPasillo().toString());
			textEstanteria.setText(ejemplar.getCodigoIdentificatorio().getEstanteria().toString());
			textEstante.setText(ejemplar.getCodigoIdentificatorio().getEstante().toString());
			calendarFechaBaja.setDate(ejemplar.getFechaBaja() != null
					? LocalDate.parse(ejemplar.getFechaBaja(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
					: null);
			textMotivoBaja.setText(ejemplar.getMotivoBaja());
			textObservaciones.setText(ejemplar.getObservaciones()); 
			 
			codigoBarras.generarCodigoBarras(ejemplar.getCodigoIdentificatorio());
			ImageIcon image = codigoBarras.buscarCodigoBarras(ejemplar.getCodigoIdentificatorio().getCodigo().toString());
			Icon icon = new ImageIcon(image.getImage().getScaledInstance(lblMostrarCodigo.getWidth(),
					lblMostrarCodigo.getHeight(), Image.SCALE_DEFAULT));
			
			lblMostrarCodigo.setIcon(icon); 
			
		});
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

		JButton botonDarDeBaja = btnDarDeBaja();
		contentPane.add(botonDarDeBaja);

		JButton botonRegresar = btnRegresar();
		contentPane.add(botonRegresar);

		JButton botonEditar = btnEditar();
		contentPane.add(botonEditar);

		JButton botonConfirmar = btnConfirmar();

		JButton botonCancelar = btnCancelar();

		botonEditar.addActionListener(e -> {
			comboBoxEjemplar.setEnabled(false);
			comboBoxObra.setEnabled(false);
			textEstante.setEnabled(true);
			textEstanteria.setEnabled(true);
			textPasillo.setEnabled(true);
			contentPane.add(botonConfirmar);
			contentPane.add(botonCancelar);
			contentPane.remove(botonDarDeBaja);
			contentPane.remove(botonRegresar);
			contentPane.remove(botonEditar);
			contentPane.revalidate();
			contentPane.repaint();
		});

		botonDarDeBaja.addActionListener(e -> {
			comboBoxEjemplar.setEnabled(false);
			comboBoxObra.setEnabled(false);
			calendarFechaBaja.setEnabled(true);
			textMotivoBaja.setEnabled(true);
			textObservaciones.setEnabled(true);
			contentPane.add(botonConfirmar);
			contentPane.add(botonCancelar);
			contentPane.remove(botonDarDeBaja);
			contentPane.remove(botonRegresar);
			contentPane.remove(botonEditar);
			contentPane.revalidate();
			contentPane.repaint();
		});

		botonCancelar.addActionListener(e -> {
			comboBoxEjemplar.setEnabled(true);
			comboBoxObra.setEnabled(true);
			textEstante.setEnabled(false);
			textEstanteria.setEnabled(false);
			textPasillo.setEnabled(false);
			calendarFechaBaja.setEnabled(false);
			textMotivoBaja.setEnabled(false);
			textObservaciones.setEnabled(false);
			contentPane.add(botonDarDeBaja);
			contentPane.add(botonRegresar);
			contentPane.add(botonEditar);
			contentPane.remove(botonConfirmar);
			contentPane.remove(botonCancelar);
			contentPane.revalidate();
			contentPane.repaint();
		});

		botonConfirmar.addActionListener(e -> {
			Boolean camposCompletos = comboBoxObra.getSelectedItem() != null
					&& comboBoxEjemplar.getSelectedItem() != null;

			if (Boolean.TRUE.equals(camposCompletos)) {
				Ejemplar ejemplar = (Ejemplar) comboBoxEjemplar.getSelectedItem();
				CodigoIdentificatorio codigo = new CodigoIdentificatorio();
				codigo.setCodigo(ejemplar.getId());
				codigo.setPasillo(Integer.valueOf(textPasillo.getText()));
				codigo.setEstante(Integer.valueOf(textEstante.getText()));
				codigo.setEstanteria(Integer.valueOf(textEstanteria.getText()));
				DaoFactory.getCodigoIdentificatorioDAO().update(codigo);
				if (calendarFechaBaja.getDate() != null) {
					ejemplar.setMotivoBaja(textMotivoBaja.getText());
					ejemplar.setFechaBaja(calendarFechaBaja.getDate().toString());
					ejemplar.setObservaciones(textObservaciones.getText());
					DaoFactory.getEjemplarDAO().update(ejemplar);
				}
				JOptionPane.showInternalMessageDialog(null, "Datos guardados correctamente");
				this.setVisible(false);
			} else {
				JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos");
			}
		});

		botonRegresar.addActionListener(e -> this.setVisible(false));
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
	
	public JLabel lblMostrarCodigo() {
		JLabel lblMostrarCodigo = new JLabel("");
		lblMostrarCodigo.setBounds(314, 198, 300, 60);
		return lblMostrarCodigo;
	}

	/**
	 * Creates the pane
	 * 
	 * @return a container
	 */
	public JPanel panelEjemplar() {
		JPanel panelAgregarObra = new JPanel();
		panelAgregarObra.setBackground(new Color(0, 64, 128));
		panelAgregarObra.setBounds(0, 0, 655, 98);
		panelAgregarObra.setLayout(null);
		return panelAgregarObra;
	}

	/**
	 * Creates a label
	 * 
	 * @return a label with the New Loan text
	 */
	public JLabel lblEjemplar() {
		JLabel lblEjemplares = new JLabel("Ejemplares");
		lblEjemplares.setForeground(new Color(255, 255, 255));
		lblEjemplares.setBounds(240, 31, 191, 39);
		lblEjemplares.setFont(new Font("Verdana", Font.BOLD, 20));
		return lblEjemplares;
	}

	/**
	 * Creates a Combo Box with all the Books
	 */
	public JComboBox<Obra> comboBoxObra() {
		JComboBox<Obra> comboBoxObra = new JComboBox<>(new Vector<>(DaoFactory.getObraDAO().findAll()));
		comboBoxObra.setBounds(24, 137, 481, 29);
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
		labelObra.setBounds(26, 124, 46, 14);
		return labelObra;
	}

	/**
	 * Creates a Combo Box with all the Copies of the selected Book
	 */
	public JComboBox<Ejemplar> comboBoxEjemplar() {
		JComboBox<Ejemplar> comboBoxEjemplar = new JComboBox<>();
		comboBoxEjemplar.setBounds(528, 137, 69, 29);
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
		labelEjemplar.setBounds(528, 124, 67, 14);
		return labelEjemplar;
	}

	/**
	 * Creates a Combo Box with all the Readers
	 */
	public JTextField textPasillo() {
		JTextField textPasillo = new JTextField();
		textPasillo.setBounds(26, 215, 73, 29);
		textPasillo.setEnabled(false);
		return textPasillo;
	}

	/**
	 * Creates a label
	 * 
	 * @return a label with the Reader text
	 */
	public JLabel labelPasillo() {
		JLabel labelPasillo = new JLabel("Pasillo");
		labelPasillo.setBounds(26, 198, 73, 14);
		return labelPasillo;
	}

	/**
	 * Creates a Combo Box with all the Employees
	 */
	public JTextField textEstanteria() {
		JTextField textEstanteria = new JTextField();
		textEstanteria.setBounds(126, 215, 69, 29);
		textEstanteria.setEnabled(false);
		return textEstanteria;
	}

	/**
	 * Creates a label
	 * 
	 * @return a label with the Employee text
	 */
	public JLabel labelEstanteria() {
		JLabel labelEstanteria = new JLabel("Estantería");
		labelEstanteria.setBounds(126, 198, 83, 14);
		return labelEstanteria;
	}

	/**
	 * Creates a Combo Box with all the Employees
	 */
	public JTextField textMotivoBaja() {
		JTextField textMotivoBaja = new JTextField();
		textMotivoBaja.setBounds(314, 292, 283, 29);
		textMotivoBaja.setEnabled(false);
		return textMotivoBaja;
	}

	/**
	 * Creates a label
	 * 
	 * @return a label with the Employee text
	 */
	public JLabel labelMotivoBaja() {
		JLabel labelMotivoBaja = new JLabel("Motivo de baja");
		labelMotivoBaja.setBounds(314, 273, 81, 14);
		return labelMotivoBaja;
	}

	/**
	 * Creates a DateTimePicker for the date and time of the loan
	 */
	public JTextField textEstante() {
		JTextField textEstante = new JTextField();
		textEstante.setBounds(217, 215, 73, 29);
		textEstante.setEnabled(false);
		return textEstante;
	}

	/**
	 * Creates a label
	 * 
	 * @return a label with the date and time of loan text
	 */
	public JLabel labelEstante() {
		JLabel labelEstante = new JLabel("Estante");
		labelEstante.setBounds(217, 198, 73, 14);
		return labelEstante;
	}

	/**
	 * Creates a DateTimePicker for the date and time of the loan
	 */
	public JTextField textObservaciones() {
		JTextField textObservaciones = new JTextField();
		textObservaciones.setBounds(26, 378, 571, 98);
		textObservaciones.setEnabled(false);
		return textObservaciones;
	}

	/**
	 * Creates a label
	 * 
	 * @return a label with the date and time of loan text
	 */
	public JLabel labelObservaciones() {
		JLabel labelObservaciones = new JLabel("Observaciones");
		labelObservaciones.setBounds(26, 364, 164, 14);
		return labelObservaciones;
	}

	/**
	 * Creates a DatePicker for the date and time of the return
	 */
	public DatePicker calendarFechaBaja() {
		DatePicker calendarFechaBaja = new DatePicker();
		calendarFechaBaja.setBounds(24, 292, 271, 29);
		calendarFechaBaja.setEnabled(false);
		return calendarFechaBaja;
	}

	/**
	 * Creates a label
	 * 
	 * @return a label with the return date text
	 */
	public JLabel labelFechaBaja() {
		JLabel labelFechaBaja = new JLabel("Fecha de baja");
		labelFechaBaja.setBounds(26, 273, 166, 14);
		return labelFechaBaja;
	}

	/**
	 * Creates a confirmation button
	 */
	public JButton btnDarDeBaja() {
		JButton btnConfirmar = new JButton("Dar de baja");
		btnConfirmar.setBounds(243, 487, 120, 23);
		return btnConfirmar;
	}

	/**
	 * Creates a cancel button
	 * 
	 */
	public JButton btnEditar() {
		JButton btnCancelar = new JButton("Editar");
		btnCancelar.setBounds(480, 487, 120, 23);
		return btnCancelar;
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

	/**
	 * Creates a cancel button
	 * 
	 */
	public JButton btnRegresar() {
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(26, 487, 120, 23);
		return btnRegresar;
	}
}
