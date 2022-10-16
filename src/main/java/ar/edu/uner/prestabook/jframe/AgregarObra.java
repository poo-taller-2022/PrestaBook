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
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.IFormatoDAO;
import ar.edu.uner.prestabook.persistence.ITipoObraDAO;

public class AgregarObra extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public AgregarObra() {
		ventana();
		JPanel contentPane = contentPane();

		JPanel panelAgregarObra = panelAgregarObra();
		contentPane.add(panelAgregarObra);

		JLabel lblAgregarObra = lblAgregarObra();
		panelAgregarObra.add(lblAgregarObra);

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

		JLabel lblFormato = lblFormato();
		contentPane.add(lblFormato);

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

		JComboBox<Object> comboBoxFormato = comboBoxFormato();
		contentPane.add(comboBoxFormato);

		JComboBox<Object> comboBoxTipoObra = comboBoxTipoObra();
		contentPane.add(comboBoxTipoObra);

		JComboBox<Object> comboBoxAreaTematica = comboBoxAreaTematica();
		contentPane.add(comboBoxAreaTematica);

		btnAgregar.addActionListener(e -> {

			Boolean camposCompletos = !(fieldIsbn.getText().isBlank() || fieldTitulo.getText().isBlank()
					|| fieldSubtitulo.getText().isBlank() || fieldPrimerAutor.getText().isBlank()
					|| fieldSegundoAutor.getText().isBlank() || fieldTercerAutor.getText().isBlank()
					|| fieldGenero.getText().isBlank() || fieldFormaAdquisicion.getText().isBlank()
					|| fieldFechaAdquisicion.getText().isBlank() || fieldObservaciones.getText().isBlank());

			if (Boolean.TRUE.equals(camposCompletos)) {
				ModeloDeTransferencia modelo = General.modeloDeTransferencia;
				modelo.setFieldIsbn(fieldIsbn.getText());
				modelo.setFieldTitulo(fieldTitulo.getText());
				modelo.setFieldSubtitulo(fieldSubtitulo.getText());
				modelo.setFieldPrimerAutor(fieldPrimerAutor.getText());
				modelo.setFieldSegundoAutor(fieldSegundoAutor.getText());
				modelo.setFieldTercerAutor(fieldTercerAutor.getText());
				modelo.setFieldGenero(fieldGenero.getText());
				Items itemTipoObra = (Items) comboBoxTipoObra.getSelectedItem();
				modelo.setFielTipoObra(itemTipoObra.getValor());
				modelo.setIdTipoObra(itemTipoObra.getId());
				Items itemAreaTematica = (Items) comboBoxAreaTematica.getSelectedItem();
				modelo.setFielAreaTematica(itemAreaTematica.getValor());
				modelo.setIdAreaTematica(itemAreaTematica.getId());
				Items itemFormato = (Items) comboBoxFormato.getSelectedItem();
				modelo.setIdFormato(itemFormato.getId());
				modelo.setFieldFormaAdquisicion(fieldFormaAdquisicion.getText());
				modelo.setFieldFechaAdquisicion(fieldFechaAdquisicion.getText());
				modelo.setFieldObservaciones(fieldObservaciones.getText());
				modelo.setRefrescar(true);

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

	public void ventana() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 540);
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

	public JPanel panelAgregarObra() {
		JPanel panelAgregarObra = new JPanel();
		panelAgregarObra.setBackground(new Color(0, 64, 128));
		panelAgregarObra.setBounds(0, 0, 655, 98);
		panelAgregarObra.setLayout(null);
		return panelAgregarObra;
	}

	public JLabel lblAgregarObra() {
		JLabel lblAgregarObra = new JLabel("Agregar Obra");
		lblAgregarObra.setForeground(new Color(255, 255, 255));
		lblAgregarObra.setBounds(240, 31, 191, 39);
		lblAgregarObra.setFont(new Font("Verdana", Font.BOLD, 20));
		return lblAgregarObra;
	}

	public JTextField fieldIsbn() {
		JTextField fieldIsbn = new JTextField();
		fieldIsbn.setBounds(37, 134, 166, 29);
		fieldIsbn.setColumns(10);
		return fieldIsbn;
	}

	public JLabel lblIsbn() {
		JLabel lblIsbn = new JLabel("Isbn");
		lblIsbn.setBounds(37, 120, 46, 14);
		return lblIsbn;
	}

	public JTextField fieldTitulo() {
		JTextField fieldTitulo = new JTextField();
		fieldTitulo.setColumns(10);
		fieldTitulo.setBounds(235, 134, 166, 29);
		return fieldTitulo;
	}

	public JLabel lblTitulo() {
		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setBounds(237, 120, 46, 14);
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

	public JComboBox<Object> comboBoxFormato() {
		JComboBox<Object> comboBoxFormato = cargarComboBox("Formato");
		comboBoxFormato.setBounds(37, 333, 166, 29);
		return comboBoxFormato;
	}

	public JLabel lblAreaTematica() {
		JLabel lblAreaTematica = new JLabel("Área temática");
		lblAreaTematica.setBounds(437, 249, 89, 14);
		return lblAreaTematica;
	}

	public JLabel lblFormato() {
		JLabel lblFormato = new JLabel("Formato");
		lblFormato.setBounds(37, 316, 83, 14);
		return lblFormato;
	}

	public JTextField fieldFormaAdquisicion() {
		JTextField fieldFormaAdquisicion = new JTextField();
		fieldFormaAdquisicion.setColumns(10);
		fieldFormaAdquisicion.setBounds(235, 333, 166, 29);
		return fieldFormaAdquisicion;
	}

	public JTextField fieldFechaAdquisicion() {
		JTextField fieldFechaAdquisicion = new JTextField();
		fieldFechaAdquisicion.setColumns(10);
		fieldFechaAdquisicion.setBounds(437, 333, 166, 29);
		return fieldFechaAdquisicion;
	}

	public JLabel lblFormaAdquisicion() {
		JLabel lblFormaAdquisicion = new JLabel("Forma de adquisición");
		lblFormaAdquisicion.setBounds(235, 316, 127, 14);
		return lblFormaAdquisicion;
	}

	public JLabel lblFechaAdquisicion() {
		JLabel lblFechaAdquisicion = new JLabel("Fecha de adquisición");
		lblFechaAdquisicion.setBounds(437, 316, 133, 14);
		return lblFechaAdquisicion;
	}

	public JTextField fieldObservaciones() {
		JTextField fieldObservaciones = new JTextField();
		fieldObservaciones.setColumns(10);
		fieldObservaciones.setBounds(235, 397, 166, 29);
		return fieldObservaciones;
	}

	public JLabel lblObservaciones() {
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(235, 384, 133, 14);
		return lblObservaciones;
	}

	public JButton btnAgregar() {
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(194, 490, 89, 23);
		return btnAgregar;
	}

	public JButton btnCancelar() {
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(358, 490, 89, 23);
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
