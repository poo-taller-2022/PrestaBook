package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;

public class AñadirEjemplar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public AñadirEjemplar() {
		ventana();
		JPanel contentPane = contentPane();
		
		JPanel panelAgregarEjemplar = panelAgregarEjemplar();
		contentPane.add(panelAgregarEjemplar);
		
		JLabel lblAgregarEjemplar = lblAgregarEjemplar();
		panelAgregarEjemplar.add(lblAgregarEjemplar);
		
		JTextField fieldFormaAdquisicion = fieldFormaAdquisicion();
		contentPane.add(fieldFormaAdquisicion);

		JTextField fieldFechaAdquisicion = fieldFechaAdquisicion();
		contentPane.add(fieldFechaAdquisicion);

		JLabel lblFormaAdquisicion = lblFormaAdquisicion();
		contentPane.add(lblFormaAdquisicion);

		JLabel lblFechaAdquisicion = lblFechaAdquisicion();
		contentPane.add(lblFechaAdquisicion);

		JTextField fieldObservaciones = fieldObservaciones();
		contentPane.add(fieldObservaciones);

		JLabel lblObservaciones = lblObservaciones();
		contentPane.add(lblObservaciones);
		
		JButton btnAgregar = btnAgregar();
		contentPane.add(btnAgregar);
		
		JButton btnCancelar = btnCancelar();
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(e -> {
			this.setVisible(false);
		});
		
		btnAgregar.addActionListener(e -> {
			Boolean camposCompletos = !(fieldFormaAdquisicion.getText().isBlank() || fieldFechaAdquisicion.getText().isBlank()
					|| fieldObservaciones.getText().isBlank());
			
			if(Boolean.TRUE.equals(camposCompletos)) {
				ModeloDeTransferencia modelo = General.modeloDeTransferencia;
				modelo.setFieldFormaAdquisicion(fieldFormaAdquisicion.getText());
				modelo.setFieldFechaAdquisicion(fieldFechaAdquisicion.getText());
				modelo.setFieldObservaciones(fieldObservaciones.getText());
				
				JOptionPane.showInternalMessageDialog(null, "Datos guardados correctamente");
				this.setVisible(false);
			} else {
				JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos");
			}
		});
	}
	
	public void ventana() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 493);
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	public JPanel contentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 64, 128)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		return contentPane;
	}
	
	public JButton btnAgregar() {
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(123, 409, 89, 23);
		return btnAgregar;
	}
	
	public JButton btnCancelar() {
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(271, 409, 89, 23);
		return btnCancelar;
	}
	
	public JPanel panelAgregarEjemplar() {
		JPanel panelAgregarEjemplar = new JPanel();
		panelAgregarEjemplar.setBackground(new Color(0, 64, 128));
		panelAgregarEjemplar.setBounds(0, 0, 492, 98);
		panelAgregarEjemplar.setLayout(null);
		return panelAgregarEjemplar;
	}

	public JLabel lblAgregarEjemplar() {
		JLabel lblAgregarEjemplar = new JLabel("Agregar Ejemplar");
		lblAgregarEjemplar.setForeground(new Color(255, 255, 255));
		lblAgregarEjemplar.setBounds(158, 30, 191, 39);
		lblAgregarEjemplar.setFont(new Font("Verdana", Font.BOLD, 20));
		return lblAgregarEjemplar;
	}
	
	public JTextField fieldFormaAdquisicion() {
		JTextField fieldFormaAdquisicion = new JTextField();
		fieldFormaAdquisicion.setColumns(10);
		fieldFormaAdquisicion.setBounds(166, 177, 166, 29);
		return fieldFormaAdquisicion;
	}

	public JTextField fieldFechaAdquisicion() {
		JTextField fieldFechaAdquisicion = new JTextField();
		fieldFechaAdquisicion.setColumns(10);
		fieldFechaAdquisicion.setBounds(166, 262, 166, 29);
		return fieldFechaAdquisicion;
	}

	public JLabel lblFormaAdquisicion() {
		JLabel lblFormaAdquisicion = new JLabel("Forma de adquisición");
		lblFormaAdquisicion.setBounds(166, 160, 127, 14);
		return lblFormaAdquisicion;
	}

	public JLabel lblFechaAdquisicion() {
		JLabel lblFechaAdquisicion = new JLabel("Fecha de adquisición");
		lblFechaAdquisicion.setBounds(166, 248, 133, 14);
		return lblFechaAdquisicion;
	}

	public JTextField fieldObservaciones() {
		JTextField fieldObservaciones = new JTextField();
		fieldObservaciones.setColumns(10);
		fieldObservaciones.setBounds(166, 341, 166, 29);
		return fieldObservaciones;
	}

	public JLabel lblObservaciones() {
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(166, 327, 133, 14);
		return lblObservaciones;
	}

}
