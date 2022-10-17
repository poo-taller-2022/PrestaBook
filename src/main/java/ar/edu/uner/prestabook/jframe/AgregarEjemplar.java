package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.model.CodigoIdentificatorio;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.ICodigoIdentificatorioDAO;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;
import ar.edu.uner.prestabook.persistence.IObraDAO;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class AgregarEjemplar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public AgregarEjemplar() {
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
		
		JTextField fieldCodigo = fieldCodigo();
		contentPane.add(fieldCodigo);
		
		JLabel lblCodigo = lblCodigo();
		contentPane.add(lblCodigo);
		
		JTextField fieldPasillo = fieldPasillo();
		contentPane.add(fieldPasillo);
		
		JTextField fieldEstanteria = fieldEstanteria();
		contentPane.add(fieldEstanteria);
		
		JTextField fieldEstante = fieldEstante();
		contentPane.add(fieldEstante);
		
		JLabel lblPasillo = lblPasillo();
		contentPane.add(lblPasillo);
		
		JLabel lblEstanteria = lblEstanteria();
		contentPane.add(lblEstanteria);
		
		JLabel lblEstante = lblEstante();
		contentPane.add(lblEstante);
		
		JLabel lblObra = lblObra();
		contentPane.add(lblObra);
		
		JComboBox<Object> comboBoxObras = comboBoxObras();
		contentPane.add(comboBoxObras);
		
		btnCancelar.addActionListener(e -> {
			this.setVisible(false);
		});
		
		btnAgregar.addActionListener(e -> {
			Boolean camposCompletos = !(fieldFormaAdquisicion.getText().isBlank() || fieldFechaAdquisicion.getText().isBlank()
					|| fieldObservaciones.getText().isBlank() || fieldCodigo.getText().isBlank() || fieldPasillo.getText().isBlank()
					|| fieldEstanteria.getText().isBlank() || fieldEstante.getText().isBlank());
			
			if(Boolean.TRUE.equals(camposCompletos)) {
				ModeloDeTransferencia modelo = General.modeloDeTransferencia;
				
				Items itemObra = (Items) comboBoxObras.getSelectedItem();
				
				actualizarBaseDeDatos(fieldFormaAdquisicion.getText(), fieldFechaAdquisicion.getText(),
						fieldObservaciones.getText(), fieldCodigo.getText(), fieldPasillo.getText(),
						fieldEstanteria.getText(), fieldEstante.getText(), itemObra.getId());
				
				JOptionPane.showInternalMessageDialog(null, "Datos guardados correctamente");
				this.setVisible(false);
			} else {
				JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos");
			}
		});
	}
	
	private void actualizarBaseDeDatos(String formaAdquisicion,
			String fechaAdquisicion, String observaciones, String codigo, String pasillo,
			String estanteria, String estante, Integer idObra) {
		IObraDAO o = DaoFactory.getObraDAO();
		Obra obra = o.findById((long) idObra);
		
		Ejemplar ejemplar = new Ejemplar();
		ejemplar.setIsbn(obra.getIsbn());
		ejemplar.setTitulo(obra.getTitulo());
		ejemplar.setSubtitulo(obra.getSubtitulo());
		ejemplar.setPrimerAutor(obra.getPrimerAutor());
		ejemplar.setSegundoAutor(obra.getSegundoAutor());
		ejemplar.setTercerAutor(obra.getTercerAutor());
		ejemplar.setGenero(obra.getGenero());
		ejemplar.setTipo(new TipoObra(obra.getTipo().getId(), obra.getTipo().getNombre()));
		ejemplar.setArea(
				new AreaTematica(obra.getArea().getId(), obra.getArea().getNombre()));
		ejemplar.setId((long) 1);
		ejemplar.setFormaAdquisicion(formaAdquisicion);
		ejemplar.setFechaAdquisicion(fechaAdquisicion);
		ejemplar.setObservaciones(observaciones);

		CodigoIdentificatorio codigoIden = new CodigoIdentificatorio();
		codigoIden.setId((long) 1);
		codigoIden.setCodigo(Integer.parseInt(codigo));
		codigoIden.setEstante(Integer.parseInt(estante));
		codigoIden.setEstanteria(Integer.parseInt(estanteria));
		codigoIden.setPasillo(Integer.parseInt(pasillo));

		ICodigoIdentificatorioDAO co = DaoFactory.getCodigoIdentificatorioDAO();
		co.insert(codigoIden);
		codigoIden.setId(codigoIden.getId());

		ejemplar.setCodigoIdentificatorio(codigoIden);

		IEjemplarDAO ej = DaoFactory.getEjemplarDAO();
		ej.insert(ejemplar);
		
		ejemplar.setId(ejemplar.getId());
		
		List<Ejemplar> ejemplares = new LinkedList<>();
		ejemplares.add(ejemplar);
		obra.setEjemplares(ejemplares);
		o.insert(obra);
		
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
		contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 64, 128)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		return contentPane;
	}
	
	public JTextField fieldCodigo() {
		JTextField fieldCodigo = new JTextField();
		fieldCodigo.setColumns(10);
		fieldCodigo.setBounds(241, 233, 166, 29);
		return fieldCodigo;
	}
	
	public JLabel lblCodigo() {
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(241, 220, 133, 14);
		return lblCodigo;
	}
	
	public JTextField fieldPasillo() {
		JTextField fieldPasillo = new JTextField();
		fieldPasillo.setColumns(10);
		fieldPasillo.setBounds(446, 233, 166, 29);
		return fieldPasillo;
	}
	
	public JTextField fieldEstanteria() {
		JTextField fieldEstanteria = new JTextField();
		fieldEstanteria.setColumns(10);
		fieldEstanteria.setBounds(154, 297, 166, 29);
		return fieldEstanteria;
	}
	
	public JTextField fieldEstante() {
		JTextField fieldEstante = new JTextField();
		fieldEstante.setColumns(10);
		fieldEstante.setBounds(363, 297, 166, 29);
		return fieldEstante;
	}
	
	public JLabel lblPasillo() {
		JLabel lblPasillo = new JLabel("Pasillo");
		lblPasillo.setBounds(446, 220, 133, 14);
		return lblPasillo;
	}
	
	public JLabel lblEstanteria() {
		JLabel lblEstanteria = new JLabel("Estantería");
		lblEstanteria.setBounds(154, 284, 133, 14);
		return lblEstanteria;
	}
	
	public JLabel lblEstante() {
		JLabel lblEstante = new JLabel("Estante");
		lblEstante.setBounds(363, 284, 133, 14);
		return lblEstante;
	}
	
	public JButton btnAgregar() {
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(198, 376, 89, 23);
		return btnAgregar;
	}
	
	public JButton btnCancelar() {
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(390, 376, 89, 23);
		return btnCancelar;
	}
	
	public JPanel panelAgregarEjemplar() {
		JPanel panelAgregarEjemplar = new JPanel();
		panelAgregarEjemplar.setBackground(new Color(0, 64, 128));
		panelAgregarEjemplar.setBounds(0, 0, 655, 98);
		panelAgregarEjemplar.setLayout(null);
		return panelAgregarEjemplar;
	}

	public JLabel lblAgregarEjemplar() {
		JLabel lblAgregarEjemplar = new JLabel("Agregar Ejemplar");
		lblAgregarEjemplar.setForeground(new Color(255, 255, 255));
		lblAgregarEjemplar.setBounds(252, 30, 276, 39);
		lblAgregarEjemplar.setFont(new Font("Verdana", Font.BOLD, 20));
		return lblAgregarEjemplar;
	}
	
	public JTextField fieldFormaAdquisicion() {
		JTextField fieldFormaAdquisicion = new JTextField();
		fieldFormaAdquisicion.setColumns(10);
		fieldFormaAdquisicion.setBounds(241, 163, 166, 29);
		return fieldFormaAdquisicion;
	}

	public JTextField fieldFechaAdquisicion() {
		JTextField fieldFechaAdquisicion = new JTextField();
		fieldFechaAdquisicion.setColumns(10);
		fieldFechaAdquisicion.setBounds(446, 163, 166, 29);
		return fieldFechaAdquisicion;
	}

	public JLabel lblFormaAdquisicion() {
		JLabel lblFormaAdquisicion = new JLabel("Forma de adquisición");
		lblFormaAdquisicion.setBounds(241, 149, 127, 14);
		return lblFormaAdquisicion;
	}

	public JLabel lblFechaAdquisicion() {
		JLabel lblFechaAdquisicion = new JLabel("Fecha de adquisición");
		lblFechaAdquisicion.setBounds(446, 149, 133, 14);
		return lblFechaAdquisicion;
	}

	public JTextField fieldObservaciones() {
		JTextField fieldObservaciones = new JTextField();
		fieldObservaciones.setColumns(10);
		fieldObservaciones.setBounds(41, 233, 166, 29);
		return fieldObservaciones;
	}

	public JLabel lblObservaciones() {
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(41, 220, 133, 14);
		return lblObservaciones;
	}
	
	public JLabel lblObra() {
		JLabel lblObra = new JLabel("Obra");
		lblObra.setBounds(41, 149, 46, 14);
		return lblObra;
	}
	
	public JComboBox<Object> comboBoxObras() {
		JComboBox<Object> comboBoxObras = cargarComboBoxObra();
		comboBoxObras.setBounds(41, 163, 166, 29);
		return comboBoxObras;
	}
	
	public JComboBox<Object> cargarComboBoxObra() {
		JComboBox<Object> comboBox = new JComboBox<>();

		IObraDAO obraDAO = DaoFactory.getObraDAO();
		java.util.List<Obra> obras = obraDAO.findAll();
		for (Obra obra : obras) {
			comboBox.addItem(new Items(obra.getId().intValue(), obra.getIsbn()));
		}
		return comboBox;
	}
}