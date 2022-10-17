package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.model.Coleccion;
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.IColeccionDAO;
import ar.edu.uner.prestabook.persistence.IFormatoDAO;

import ar.edu.uner.prestabook.persistence.ITipoObraDAO;

public class AgregarColeccion extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public AgregarColeccion() {
		ventana();
		JPanel contentPane = contentPane();

		JPanel panelAgregarColeccion = panelAgregarColeccion();
		contentPane.add(panelAgregarColeccion);

		JLabel lblAgregarColeccion = lblAgregarColeccion();
		panelAgregarColeccion.add(lblAgregarColeccion);

		JTextField fieldIsbn = fieldIsbn();
		contentPane.add(fieldIsbn);

		JLabel lblIsbn = lblIsbn();
		contentPane.add(lblIsbn);

		JTextField fieldTitulo = fieldTitulo();
		contentPane.add(fieldTitulo);

		JLabel lblTitulo = lblTitulo();
		contentPane.add(lblTitulo);

		JTextField fieldSubtitulo = fieldSubtitulo();
		contentPane.add(fieldSubtitulo);

		JLabel lblSubtitulo = lblSubtitulo();
		contentPane.add(lblSubtitulo);

		JTextField fieldPrimerAutor = fieldPrimerAutor();
		contentPane.add(fieldPrimerAutor);

		JLabel lblPrimerAutor = lblPrimerAutor();
		contentPane.add(lblPrimerAutor);

		JTextField fieldSegundoAutor = fieldSegundoAutor();
		contentPane.add(fieldSegundoAutor);

		JLabel lblSegundoAutor = lblSegundoAutor();
		contentPane.add(lblSegundoAutor);

		JTextField fieldTercerAutor = fieldTercerAutor();
		contentPane.add(fieldTercerAutor);

		JLabel lblTercerAutor = lblTercerAutor();
		contentPane.add(lblTercerAutor);

		JTextField fieldGenero = fieldGenero();
		contentPane.add(fieldGenero);

		JLabel lblGenero = lblGenero();
		contentPane.add(lblGenero);

		JLabel lblTipoObra = lblTipoObra();
		contentPane.add(lblTipoObra);

		JLabel lblAreaTematica = lblAreaTematica();
		contentPane.add(lblAreaTematica);

		JButton btnAgregar = btnAgregar();
		contentPane.add(btnAgregar);

		JButton btnCancelar = btnCancelar();
		contentPane.add(btnCancelar);

		JComboBox<Object> comboBoxTipoObra = comboBoxTipoObra();
		contentPane.add(comboBoxTipoObra);

		JComboBox<Object> comboBoxAreaTematica = comboBoxAreaTematica();
		contentPane.add(comboBoxAreaTematica);

		btnAgregar.addActionListener(e -> {

			Boolean camposCompletos = !(fieldIsbn.getText().isBlank() || fieldTitulo.getText().isBlank()
					|| fieldSubtitulo.getText().isBlank() || fieldPrimerAutor.getText().isBlank()
					|| fieldSegundoAutor.getText().isBlank() || fieldTercerAutor.getText().isBlank()
					|| fieldGenero.getText().isBlank());

			if (Boolean.TRUE.equals(camposCompletos)) {

				Items itemTipoObra = (Items) comboBoxTipoObra.getSelectedItem();

				Items itemAreaTematica = (Items) comboBoxAreaTematica.getSelectedItem();

				actualizarBaseDeDatos(fieldIsbn.getText(), fieldTitulo.getText(), fieldSubtitulo.getText(),
						fieldPrimerAutor.getText(), fieldSegundoAutor.getText(), fieldTercerAutor.getText(),
						fieldGenero.getText(), itemTipoObra.getValor(), itemTipoObra.getId(),
						itemAreaTematica.getValor(), itemAreaTematica.getId());

				JOptionPane.showInternalMessageDialog(null, "Datos guardados correctamente");
				this.setVisible(false);
			} else {
				JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos");
			}
		});

		btnCancelar.addActionListener(e -> {
			this.setVisible(false);
		});

	}

	private void actualizarBaseDeDatos(String isbn, String titulo, String subtitulo, String primerAutor,
			String segundoAutor, String tercerAutor, String genero, String tipoObra, Integer idTipoObra,
			String areaTematica, Integer idAreaTematica) {
		Coleccion coleccion = new Coleccion();
		IColeccionDAO c = DaoFactory.getColeccionDAO();

		coleccion.setGenero(genero);
		coleccion.setIsbn(isbn);
		coleccion.setTitulo(titulo);
		coleccion.setSubtitulo(subtitulo);
		coleccion.setPrimerAutor(primerAutor);
		coleccion.setSegundoAutor(segundoAutor);
		coleccion.setTercerAutor(tercerAutor);
		coleccion.setTipo(new TipoObra(idTipoObra, tipoObra));
		coleccion.setArea(new AreaTematica(idAreaTematica, areaTematica));

		c.insert(coleccion);
	}

	public void ventana() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 390);
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

	public JButton btnAñadirEdicion() {
		JButton btnAñadirEdicion = new JButton("Añadir edición");
		btnAñadirEdicion.setBounds(166, 338, 127, 23);
		return btnAñadirEdicion;
	}

	public JPanel panelAgregarColeccion() {
		JPanel panelAgregarColeccion = new JPanel();
		panelAgregarColeccion.setBackground(new Color(0, 64, 128));
		panelAgregarColeccion.setBounds(0, 0, 655, 98);
		panelAgregarColeccion.setLayout(null);
		return panelAgregarColeccion;
	}

	public JLabel lblAgregarColeccion() {
		JLabel lblAgregarColeccion = new JLabel("Agregar coleccion");
		lblAgregarColeccion.setForeground(new Color(255, 255, 255));
		lblAgregarColeccion.setBounds(227, 31, 241, 39);
		lblAgregarColeccion.setFont(new Font("Verdana", Font.BOLD, 20));
		return lblAgregarColeccion;
	}

	public JTextField fieldIsbn() {
		JTextField fieldIsbn = new JTextField();
		fieldIsbn.setBounds(37, 134, 166, 29);
		fieldIsbn.setColumns(10);
		return fieldIsbn;
	}

	public JLabel lblIsbn() {
		JLabel lblIsbn = new JLabel("Isbn de coleccion");
		lblIsbn.setBounds(37, 120, 105, 14);
		return lblIsbn;
	}

	public JTextField fieldTitulo() {
		JTextField fieldTitulo = new JTextField();
		fieldTitulo.setColumns(10);
		fieldTitulo.setBounds(235, 134, 166, 29);
		return fieldTitulo;
	}

	public JLabel lblTitulo() {
		JLabel lblTitulo = new JLabel("Título de coleccion");
		lblTitulo.setBounds(237, 120, 112, 14);
		return lblTitulo;
	}

	public JTextField fieldSubtitulo() {
		JTextField fieldSubtitulo = new JTextField();
		fieldSubtitulo.setColumns(10);
		fieldSubtitulo.setBounds(437, 134, 166, 29);
		return fieldSubtitulo;
	}

	public JLabel lblSubtitulo() {
		JLabel lblSubtitulo = new JLabel("Subtítulo");
		lblSubtitulo.setBounds(436, 120, 73, 14);
		return lblSubtitulo;
	}

	public JTextField fieldPrimerAutor() {
		JTextField fieldPrimerAutor = new JTextField();
		fieldPrimerAutor.setColumns(10);
		fieldPrimerAutor.setBounds(37, 197, 166, 29);
		return fieldPrimerAutor;
	}

	public JLabel lblPrimerAutor() {
		JLabel lblPrimerAutor = new JLabel("Primer autor");
		lblPrimerAutor.setBounds(37, 183, 83, 14);
		return lblPrimerAutor;
	}

	public JTextField fieldSegundoAutor() {
		JTextField fieldSegundoAutor = new JTextField();
		fieldSegundoAutor.setColumns(10);
		fieldSegundoAutor.setBounds(235, 197, 166, 29);
		return fieldSegundoAutor;
	}

	public JLabel lblSegundoAutor() {
		JLabel lblSegundoAutor = new JLabel("Segundo autor");
		lblSegundoAutor.setBounds(237, 183, 112, 14);
		return lblSegundoAutor;
	}

	public JTextField fieldTercerAutor() {
		JTextField fieldTercerAutor = new JTextField();
		fieldTercerAutor.setColumns(10);
		fieldTercerAutor.setBounds(437, 197, 166, 29);
		return fieldTercerAutor;
	}

	public JLabel lblTercerAutor() {
		JLabel lblTercerAutor = new JLabel("Tercer autor");
		lblTercerAutor.setBounds(436, 183, 83, 14);
		return lblTercerAutor;
	}

	public JTextField fieldGenero() {
		JTextField fieldGenero = new JTextField();
		fieldGenero.setColumns(10);
		fieldGenero.setBounds(37, 265, 166, 29);
		return fieldGenero;
	}

	public JLabel lblGenero() {
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(37, 249, 64, 14);
		return lblGenero;
	}

	public JLabel lblTipoObra() {
		JLabel lblTipoObra = new JLabel("Tipo obra");
		lblTipoObra.setBounds(235, 249, 83, 14);
		return lblTipoObra;
	}

	public JComboBox<Object> comboBoxTipoObra() {
		JComboBox<Object> comboBoxTipoObra = cargarComboBox("Tipo obra");
		comboBoxTipoObra.setBounds(235, 268, 166, 29);
		return comboBoxTipoObra;
	}

	public JComboBox<Object> comboBoxAreaTematica() {
		JComboBox<Object> comboBoxAreaTematica = cargarComboBox("Area tematica");
		comboBoxAreaTematica.setBounds(437, 268, 166, 29);
		return comboBoxAreaTematica;
	}

	public JLabel lblAreaTematica() {
		JLabel lblAreaTematica = new JLabel("Área temática");
		lblAreaTematica.setBounds(437, 249, 89, 14);
		return lblAreaTematica;
	}

	public JButton btnAgregar() {
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(196, 332, 89, 23);
		return btnAgregar;
	}

	public JButton btnCancelar() {
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(344, 332, 89, 23);
		return btnCancelar;
	}

	public JComboBox<Object> cargarComboBox(String tipoEntidad) {
		JComboBox<Object> comboBox = new JComboBox<>();
		switch (tipoEntidad) {
		case "Tipo obra":
			ITipoObraDAO tipoObraDAO = DaoFactory.getTipoObraDAO();
			java.util.List<TipoObra> tiposObra = tipoObraDAO.findAll();
			for (TipoObra tipo : tiposObra) {
				comboBox.addItem(new Items(tipo.getId(), tipo.getNombre()));
			}
			return comboBox;
		case "Area tematica":
			IAreaTematicaDAO areaTematicaDAO = DaoFactory.getAreaTematicaDAO();
			java.util.List<AreaTematica> areasTematicas = areaTematicaDAO.findAll();
			for (AreaTematica area : areasTematicas) {
				comboBox.addItem(new Items(area.getId(), area.getNombre()));
			}
			return comboBox;
		case "Formato":
			IFormatoDAO formatoDAO = DaoFactory.getFormatoDAO();
			java.util.List<Formato> formatos = formatoDAO.findAll();
			for (Formato formato : formatos) {
				comboBox.addItem(new Items(formato.getId(), formato.getNombre()));
			}
			return comboBox;
		default:
		}
		return null;
	}
}
