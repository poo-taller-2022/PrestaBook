package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;
import ar.edu.uner.prestabook.persistence.IObraDAO;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class VerMas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public VerMas(Edicion edicion) {
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
		
		textArea.setText(cargarObra(edicion).toString());

		btnRegresar.addActionListener(e -> this.setVisible(false));
	}

	public StringBuilder cargarObra(Edicion edicion) {
		StringBuilder informacionObra = new StringBuilder();
		
		IObraDAO obraDAO = DaoFactory.getObraDAO();
		Obra obra = obraDAO.findById(edicion.getIsbnObra());
		
		informacionObra.append(" Isbn de obra: " + obra.getIsbn() +
							   "\n Área temática: " + contatenarAreas(obra) +
							   "\n Titulo de obra: " + obra.getTitulo() +
							   "\n Subtitulo de obra: " + obra.getSubtitulo() +
							   "\n Primer autor: " + obra.getPrimerAutor() +
							   "\n Segundo autor: " + obra.getSegundoAutor() +
							   "\n Tercer autor: " + obra.getTercerAutor() +
							   "\n Género: " + obra.getGenero() +
							   "\n Tipo obra: " + obra.getTipo().getNombre() +
							   "\n Editorial: " + edicion.getEditorial() +
							   "\n Año de edicion: " + edicion.getAnio() +
							   "\n Pais de edicion: " + edicion.getPais() +
							   "\n Idioma: " + edicion.getIdioma() + 
							   "\n Numero de edicion: " + edicion.getNumero() + 
							   "\n Paginas: " + edicion.getPaginas() +
							   "\n Volumenes: " + edicion.getVolumenes() +
							   "\n Formato: " + contatenarFormatos(edicion.getFormatos()) +
							   "\n Cantidad de ejemplares disponibles: " + cantidadDeEjemplares(obra)
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
	
	public Integer cantidadDeEjemplares(Obra obra) {
		IEjemplarDAO ejemplarDAO = DaoFactory.getEjemplarDAO();
		List<Ejemplar> ejemplares = ejemplarDAO.findAll();
		Integer cantidadEjemplares = 0;
		if (ejemplares != null) {
			for (Ejemplar ejemplar : ejemplares) {

				if (Objects.equals(ejemplar.getIsbnObra(), obra.getIsbn()) && !(ejemplar.getMotivoBaja() != null)) {
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
		setBounds(100, 100, 655, 448);
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
	
	public JScrollPane scrollPane() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(67, 131, 531, 207);
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
		btnRegresar.setBounds(292, 376, 89, 23);
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
}
