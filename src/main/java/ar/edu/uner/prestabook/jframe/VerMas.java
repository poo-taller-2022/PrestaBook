package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.render.EdicionRenderer;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;
import ar.edu.uner.prestabook.persistence.IPrestamoDAO;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class VerMas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public VerMas(Obra obra) {
		ventana();
		JPanel contentPane = contentPane();

		JPanel panelMasInformacion = panelMasInformacion();
		contentPane.add(panelMasInformacion);

		JLabel lblMasInformacion = lblMasInformacion();
		panelMasInformacion.add(lblMasInformacion);

		JButton btnRegresar = btnRegresar();
		contentPane.add(btnRegresar);

		JScrollPane scrollPane = scrollPane();
		contentPane.add(scrollPane);

		JTextArea textArea = textArea();
		scrollPane.setViewportView(textArea);

		JLabel lblEdicion = lblEdicion();
		contentPane.add(lblEdicion);

		JTextField fieldIsbnObra = fieldIsbnObra();
		fieldIsbnObra.setText(obra.getTitulo());
		contentPane.add(fieldIsbnObra);

		JLabel lblNoDisponible = lblNoDisponible();
		contentPane.add(lblNoDisponible);

		JLabel lblTituloObra = lblTituloObra();
		contentPane.add(lblTituloObra); 

		JComboBox<Edicion> comboBoxEdiciones = comboBoxEdiciones();
		contentPane.add(comboBoxEdiciones);

		List<Edicion> ediciones = DaoFactory.getEdicionDAO().findByAllObraIsbn(obra.getIsbn());
		if (ediciones.isEmpty()) {
			comboBoxEdiciones.setEnabled(false);
			lblNoDisponible.setVisible(true);
		} else {
			for (Edicion edicion : ediciones) {
				comboBoxEdiciones.addItem(edicion);
			}
		}

		comboBoxEdiciones.addActionListener(
				e -> textArea.setText(cargarObra(obra, (Edicion) comboBoxEdiciones.getSelectedItem()).toString()));

		textArea.setText(cargarObra(obra, (Edicion) comboBoxEdiciones.getSelectedItem()).toString());

		btnRegresar.addActionListener(e -> this.setVisible(false));
	}

	public StringBuilder cargarObra(Obra obra, Edicion edicion) {
		StringBuilder informacionObra = new StringBuilder();
		informacionObra.append(" Isbn de obra: " + obra.getIsbn() +
				   "\n Área temática: " + contatenarAreas(obra) +
				   "\n Titulo de obra: " + obra.getTitulo() +
				   "\n Subtitulo de obra: " + obra.getSubtitulo() +
				   "\n Primer autor: " + obra.getPrimerAutor() +
				   "\n Segundo autor: " + obra.getSegundoAutor() +
				   "\n Tercer autor: " + obra.getTercerAutor() +
				   "\n Género: " + obra.getGenero() +
				   "\n Tipo obra: " + obra.getTipo().getNombre() +
				   "\n Editorial: " + (edicion == null ? Constants.NO_DISPONIBLE : edicion.getEditorial()) +
				   "\n Año de edicion: " + (edicion== null ? Constants.NO_DISPONIBLE : edicion.getAnio().toString()) +
				   "\n Pais de edicion: " + (edicion == null ? Constants.NO_DISPONIBLE : edicion.getPais()) +
				   "\n Idioma: " + (edicion == null ? Constants.NO_DISPONIBLE : edicion.getIdioma()) + 
				   "\n Numero de edicion: " + (edicion == null ? Constants.NO_DISPONIBLE : edicion.getNumero().toString()) + 
				   "\n Paginas: " + (edicion == null ? Constants.NO_DISPONIBLE : edicion.getPaginas().toString()) +
				   "\n Volumenes: " + (edicion == null ? Constants.NO_DISPONIBLE : edicion.getVolumenes().toString()) +
				   "\n Formato: " + (edicion == null ? Constants.NO_DISPONIBLE : contatenarFormatos(edicion.getFormatos()).toString()) +
				   "\n Pertenece a una colección: " + (obra.getIsbnColeccion() == null ? "No" : "Si") +
				   "\n Nombre de colección: " + (obra.getIsbnColeccion() == null ? "No corresponde" : DaoFactory.getColeccionDAO().findById(obra.getIsbnColeccion()).getTitulo()) +
				   "\n Cantidad de ejemplares disponibles: " + cantidadDeEjemplares(obra, edicion)
					);

		return informacionObra;
	}

	public StringBuilder contatenarAreas(Obra obra) {
		Set<AreaTematica> areas = obra.getArea();
		StringBuilder contatenarAreas = new StringBuilder();

		for (AreaTematica area : areas) {
			contatenarAreas.append(area.getNombre() + ", ");
		}
		contatenarAreas = contatenarAreas.deleteCharAt(contatenarAreas.length() - 2);
		return contatenarAreas;
	}

	public StringBuilder contatenarFormatos(Set<Formato> formatos) {
		StringBuilder contatenarFormatos = new StringBuilder();

		for (Formato formato : formatos) {
			contatenarFormatos.append(formato.getNombre() + ", ");
		}
		contatenarFormatos = contatenarFormatos.deleteCharAt(contatenarFormatos.length() - 2);
		return contatenarFormatos;
	}

	public Integer cantidadDeEjemplares(Obra obra, Edicion edicion) {
		IEjemplarDAO ejemplarDAO = DaoFactory.getEjemplarDAO();
		IPrestamoDAO prestamoDAO = DaoFactory.getPrestamoDAO();
		List<Ejemplar> ejemplares = ejemplarDAO.findAll();
		Integer cantidadEjemplares = 0;
		if (ejemplares != null) {
			for (Ejemplar ejemplar : ejemplares) {
				if (Objects.equals(ejemplar.getIsbnObra(), obra.getIsbn()) && ejemplar.getMotivoBaja() == null
						&& Objects.equals(ejemplar.getIdEdicion(), edicion.getId())
						&& prestamoDAO.findAllByIdEjemplar(ejemplar.getId()).isEmpty()) {
					cantidadEjemplares++;
				}
			}
			return cantidadEjemplares;
		} else {
			return 0;
		}
	}

	public void ventana() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 469);
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	public JPanel contentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(3, 3, 3, 3, new Color(0, 64, 128)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		return contentPane;
	}

	public JLabel lblNoDisponible() {
		JLabel lblNoDisponible = new JLabel("No disponible");
		lblNoDisponible.setEnabled(false);
		lblNoDisponible.setBounds(442, 145, 98, 14);
		lblNoDisponible.setVisible(false);
		return lblNoDisponible;
	}

	public JTextField fieldIsbnObra() {
		JTextField fieldIsbnObra = new JTextField();
		fieldIsbnObra.setEditable(false);
		fieldIsbnObra.setBounds(67, 139, 166, 29);
		fieldIsbnObra.setColumns(10);
		return fieldIsbnObra;
	}

	public JLabel lblEdicion() {
		JLabel lblEdicion = new JLabel("Edicion");
		lblEdicion.setBounds(432, 120, 46, 14);
		return lblEdicion;
	}

	public JLabel lblTituloObra() {
		JLabel lblTituloObra = new JLabel("Obra");
		lblTituloObra.setBounds(67, 120, 119, 14);
		return lblTituloObra;
	}

	public JScrollPane scrollPane() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(67, 181, 531, 207);
		return scrollPane;
	}

	public JTextArea textArea() {
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 10, 400, 100);
		return textArea;
	}

	public JButton btnRegresar() {
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(294, 414, 89, 23);
		return btnRegresar;
	}

	public JPanel panelMasInformacion() {
		JPanel panelMasInformacion = new JPanel();
		panelMasInformacion.setBackground(new Color(0, 64, 128));
		panelMasInformacion.setBounds(0, 0, 655, 98);
		panelMasInformacion.setLayout(null);
		return panelMasInformacion;
	}

	public JLabel lblMasInformacion() {
		JLabel lblMasInformacion = new JLabel("Mas información");
		lblMasInformacion.setForeground(new Color(255, 255, 255));
		lblMasInformacion.setBounds(252, 30, 276, 39);
		lblMasInformacion.setFont(new Font("Verdana", Font.BOLD, 20));
		return lblMasInformacion;
	}

	public JComboBox<Edicion> comboBoxEdiciones() {
		JComboBox<Edicion> comboBoxEdiciones = new JComboBox<>();
		comboBoxEdiciones.setBounds(432, 139, 166, 29);
		comboBoxEdiciones.setRenderer(new EdicionRenderer());
		return comboBoxEdiciones;
	}
}
