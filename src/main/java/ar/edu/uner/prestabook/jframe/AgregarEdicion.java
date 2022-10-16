package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.persistence.IFormatoDAO;
import javax.swing.JButton;


public class AgregarEdicion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public AgregarEdicion() {
		ventana();
		JPanel contentPane = contentPane();
		
		JPanel panelAgregarEdicion = panelAgregarEdicion();
		contentPane.add(panelAgregarEdicion);
		
		JLabel lblAgregarEdicion = lblAgregarEdicion();
		panelAgregarEdicion.add(lblAgregarEdicion);
		
		JTextField fieldEditorial = fieldEditorial();
		contentPane.add(fieldEditorial);

		JLabel lblEditorial = lblEditorial();
		contentPane.add(lblEditorial);

		JTextField fieldPais = fieldPais();
		contentPane.add(fieldPais);

		JLabel lblPais = lblPais();
		contentPane.add(lblPais);

		JTextField fieldNumero = fieldNumero();
		contentPane.add(fieldNumero);

		JLabel lblNumero = lblNumero();
		contentPane.add(lblNumero);

		JTextField fieldAnio = fieldAnio();
		contentPane.add(fieldAnio);

		JLabel lblAnio = lblAnio();
		contentPane.add(lblAnio);

		JTextField fieldVolumenes = fieldVolumenes();
		contentPane.add(fieldVolumenes);

		JLabel lblVolumenes = lblVolumenes();
		contentPane.add(lblVolumenes);

		JTextField fieldPaginas = fieldPaginas();
		contentPane.add(fieldPaginas);

		JLabel lblPaginas = lblPaginas();
		contentPane.add(lblPaginas);

		JTextField fieldIdioma = fieldIdioma();
		contentPane.add(fieldIdioma);

		JLabel lblIdioma = lblIdioma();
		contentPane.add(lblIdioma);

		JLabel lblFormato = lblFormato();
		contentPane.add(lblFormato);
		
		JComboBox<Object> comboBoxFormato = comboBoxFormato();
		contentPane.add(comboBoxFormato);
		
		JButton btnAgregar = btnAgregar();
		contentPane.add(btnAgregar);
		
		JButton btnCancelar = btnCancelar();
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(e -> {
			this.setVisible(false);
		});
		
		btnAgregar.addActionListener(e -> {
			Boolean camposCompletos = !(fieldEditorial.getText().isBlank() || fieldPais.getText().isBlank() || fieldNumero.getText().isBlank()
					|| fieldAnio.getText().isBlank() || fieldVolumenes.getText().isBlank() || fieldPaginas.getText().isBlank()
					|| fieldIdioma.getText().isBlank());
			
			if (Boolean.TRUE.equals(camposCompletos)) {
				ModeloDeTransferencia modelo = General.modeloDeTransferencia;
				modelo.setFieldeditorial(fieldEditorial.getText());
				modelo.setFieldpais(fieldPais.getText());
				modelo.setFieldnumero(fieldNumero.getText());
				modelo.setFieldanio(fieldAnio.getText());
				modelo.setFieldvolumenes(fieldVolumenes.getText());
				modelo.setFieldpaginas(fieldPaginas.getText());
				modelo.setFieldidioma(fieldIdioma.getText());
				Items itemFormato = (Items) comboBoxFormato.getSelectedItem();
				modelo.setFielformato(itemFormato.getValor());
				modelo.setIdFormato(itemFormato.getId());
				
				JOptionPane.showInternalMessageDialog(null, "Datos guardados correctamente");
				this.setVisible(false);
				
			} else {
				JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos");
			}
		});
	}

	private JPanel contentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 64, 128)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		return contentPane;
	}
	
	public JButton btnAgregar() {
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(94, 414, 89, 23);
		return btnAgregar;
	}
	
	public JButton btnCancelar() {
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(260, 414, 89, 23);
		return btnCancelar;
	}

	private void ventana() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 465);
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public JPanel panelAgregarEdicion() {
		JPanel panelAgregarEdicion = new JPanel();
		panelAgregarEdicion.setBackground(new Color(0, 64, 128));
		panelAgregarEdicion.setBounds(0, 0, 439, 98);
		panelAgregarEdicion.setLayout(null);
		return panelAgregarEdicion;
	}

	public JLabel lblAgregarEdicion() {
		JLabel lblAgregarEdicion = new JLabel("Agregar edición");
		lblAgregarEdicion.setForeground(new Color(255, 255, 255));
		lblAgregarEdicion.setBounds(136, 25, 191, 39);
		lblAgregarEdicion.setFont(new Font("Verdana", Font.BOLD, 20));
		return lblAgregarEdicion;
	}
	
	public JTextField fieldEditorial() {
		JTextField fieldEditorial = new JTextField();
		fieldEditorial.setBounds(37, 134, 166, 29);
		fieldEditorial.setColumns(10);
		return fieldEditorial;
	}

	public JLabel lblEditorial() {
		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setBounds(37, 120, 46, 14);
		return lblEditorial;
	}

	public JTextField fieldPais() {
		JTextField fieldPais = new JTextField();
		fieldPais.setColumns(10);
		fieldPais.setBounds(235, 134, 166, 29);
		return fieldPais;
	}

	public JLabel lblPais() {
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(237, 120, 46, 14);
		return lblPais;
	}

	public JTextField fieldNumero() {
		JTextField fieldNumero = new JTextField();
		fieldNumero.setColumns(10);
		fieldNumero.setBounds(37, 273, 166, 29);
		return fieldNumero;
	}

	public JLabel lblNumero() {
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setBounds(37, 257, 73, 14);
		return lblNumero;
	}

	public JTextField fieldAnio() {
		JTextField fieldAnio = new JTextField();
		fieldAnio.setColumns(10);
		fieldAnio.setBounds(37, 197, 166, 29);
		return fieldAnio;
	}

	public JLabel lblAnio() {
		JLabel lblAnio = new JLabel("Anio");
		lblAnio.setBounds(37, 183, 83, 14);
		return lblAnio;
	}

	public JTextField fieldVolumenes() {
		JTextField fieldVolumenes = new JTextField();
		fieldVolumenes.setColumns(10);
		fieldVolumenes.setBounds(235, 197, 166, 29);
		return fieldVolumenes;
	}

	public JLabel lblVolumenes() {
		JLabel lblVolumenes = new JLabel("Volúmenes");
		lblVolumenes.setBounds(237, 183, 112, 14);
		return lblVolumenes;
	}

	public JTextField fieldPaginas() {
		JTextField fieldPaginas = new JTextField();
		fieldPaginas.setColumns(10);
		fieldPaginas.setBounds(235, 273, 166, 29);
		return fieldPaginas;
	}

	public JLabel lblPaginas() {
		JLabel lblPaginas = new JLabel("Páginas");
		lblPaginas.setBounds(235, 257, 83, 14);
		return lblPaginas;
	}

	public JTextField fieldIdioma() {
		JTextField fieldIdioma = new JTextField();
		fieldIdioma.setColumns(10);
		fieldIdioma.setBounds(37, 353, 166, 29);
		return fieldIdioma;
	}

	public JLabel lblIdioma() {
		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setBounds(37, 328, 64, 14);
		return lblIdioma;
	}

	public JLabel lblFormato() {
		JLabel lblFormato = new JLabel("Formato");
		lblFormato.setBounds(235, 328, 83, 14);
		return lblFormato;
	}

	public JComboBox<Object> comboBoxFormato() {
		JComboBox<Object> comboBoxFormato = cargarComboBox();
		comboBoxFormato.setBounds(235, 353, 166, 29);
		return comboBoxFormato;
	}
	
	public JComboBox<Object> cargarComboBox() {
		JComboBox<Object> comboBox = new JComboBox<>();

		IFormatoDAO formatoDAO = DaoFactory.getFormatoDAO();
		java.util.List<Formato> formatos = formatoDAO.findAll();
		for (Formato formato : formatos) {
			comboBox.addItem(new Items(formato.getId(), formato.getNombre()));
		}
		return comboBox;
	}

}
