package ar.edu.uner.prestabook.jframe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.IFormatoDAO;
import ar.edu.uner.prestabook.persistence.IObraDAO;
import ar.edu.uner.prestabook.persistence.ITipoObraDAO;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.io.File;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class SistemaFuncionario extends JFrame {

	private static final long serialVersionUID = 1L;
	public JLabel textUsusario;

	/**
	 * Create the frame.
	 */

	public SistemaFuncionario() {

		/**
		 * Create components
		 */

		ventana();

		JPanel contentPane = contentPane();

		JPanel panelPrestabook = panelPrestabook();
		contentPane.add(panelPrestabook);

		JLabel lblIconCerrarSesion = lblIconCerrarSesion();
		panelPrestabook.add(lblIconCerrarSesion);
		JLabelImage(lblIconCerrarSesion);

		panelPrestabook.add(lblPrestabook());

		JButton btnExit = btnExit();
		panelPrestabook.add(btnExit);

		JButton btnCerrarSesion = btnCerrarSesion();
		panelPrestabook.add(btnCerrarSesion);

		JPanel panelOpciones = panelOpciones();
		contentPane.add(panelOpciones);

		JButton btnSolicitudes = btnSolicitudes();
		panelOpciones.add(btnSolicitudes);

		panelOpciones.add(lblUsuario());
		panelOpciones.add(textUsusario());
		panelOpciones.add(panelSeparador());
		panelOpciones.add(lblOpciones());

		JButton btnGestionarDevolucion = btnGestionarDevolucion();
		panelOpciones.add(btnGestionarDevolucion);

		JButton btnListarLectoresMorosos = btnListarLectoresMorosos();
		panelOpciones.add(btnListarLectoresMorosos);

		JButton btnMasBuscadasPorAlumnoDocente = btnMasBuscadasPorAlumnoDocente();
		panelOpciones.add(btnMasBuscadasPorAlumnoDocente);

		JButton btnMasBuscadasPorPublicoGeneral = btnMasBuscadasPorPublicoGeneral();
		panelOpciones.add(btnMasBuscadasPorPublicoGeneral);

		JButton btnListarEjemplaresDisponiblesPorArea = btnListarEjemplaresDisponiblesPorArea();
		panelOpciones.add(btnListarEjemplaresDisponiblesPorArea);

		JButton btnListarEjemplaresDisponiblesPorFecha = btnListarEjemplaresDisponiblesPorFecha();
		panelOpciones.add(btnListarEjemplaresDisponiblesPorFecha);

		JButton btnListarLectoresMultadosPorPeriodo = btnListarLectoresMultadosPorPeriodo();
		panelOpciones.add(btnListarLectoresMultadosPorPeriodo);

		JButton btnListarRankingDeMultados = btnListarRankingDeMultados();
		panelOpciones.add(btnListarRankingDeMultados);

		JButton btnListarObrasPorEditorial = btnListarObrasPorEditorial();
		panelOpciones.add(btnListarObrasPorEditorial);

		JButton btnAplicarMultaALector = btnAplicarMultaALector();
		panelOpciones.add(btnAplicarMultaALector);

		JPanel panelBienvenida = panelBienvenida();
		contentPane.add(panelBienvenida);

		panelBienvenida.add(lblBienvenidaParte1());
		panelBienvenida.add(lblBienvenidaParte2());
		panelBienvenida.add(lblIconLibreria());

		/**
		 * Menu bar Administrar
		 */

		JMenuBar menuBarAdministrar = menuBarAdministrar();
		panelPrestabook.add(menuBarAdministrar);

		JMenu menuAdministrar = menuAdministrar();
		menuBarAdministrar.add(menuAdministrar);

		JMenuItem MenuItemTipoObra = new JMenuItem("Tipos de obras");
		menuAdministrar.add(MenuItemTipoObra);

		JMenuItem MenuItemAreaTematica = new JMenuItem("Áreas temáticas");
		menuAdministrar.add(MenuItemAreaTematica);

		JMenuItem MenuItemFormato = new JMenuItem("Formatos");
		menuAdministrar.add(MenuItemFormato);

		JMenuItem MenuItemObra = new JMenuItem("Obras");
		menuAdministrar.add(MenuItemObra);

		JPanel panelTipoObra = panelEntidades();
		JPanel panelAreaTematica = panelEntidades();
		JPanel panelFormato = panelEntidades();
		JPanel panelObra = panelEntidades();

		/**
		 * Crea el panel para administrar tipos de obras
		 */

		MenuItemTipoObra.addActionListener(e -> {
			panelBienvenida.setVisible(false);
			panelAreaTematica.setVisible(false);
			panelFormato.setVisible(false);
			panelObra.setVisible(false);
			panelTipoObra.setVisible(true);

			contentPane.add(panelTipoObra);
			String entidad = "Tipo obra";

			panelTipoObra.add(lblTituloEntidades("Tipos de obras"));

			JScrollPane scrollPane = scrollPane();
			panelTipoObra.add(scrollPane);

			JTable tableTipoDeObras = new JTable();

			DefaultTableModel model = new DefaultTableModel();
			tableTipoDeObras.setModel(model);
			model.addColumn("");
			model.addColumn("Nombre");
 
			llenarTabla(model, entidad);
			scrollPane.setViewportView(tableTipoDeObras);

			JButton btnAgregarTipoObra = btnAgregarTipoObra();
			panelTipoObra.add(btnAgregarTipoObra);

			JButton btnActualizarTipoObra = btnActualizarTipoObra();
			panelTipoObra.add(btnActualizarTipoObra);

			/**
			 * Botón con evento para agregar tipo de obra
			 */

			btnAgregarTipoObra.addActionListener(b -> {
				Long enumeracion = ultimaEnumeracion(model);

				String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre de tipo de obra");
				agregarEntidadATabla(valorAgregar, entidad, model, enumeracion);
			});

		});

		/**
		 * Crea el panel para administrar Areas temáticas
		 */

		MenuItemAreaTematica.addActionListener(e -> {
			panelBienvenida.setVisible(false);
			panelTipoObra.setVisible(false);
			panelFormato.setVisible(false);
			panelObra.setVisible(false);
			panelAreaTematica.setVisible(true);

			String entidad = "Area tematica";

			contentPane.add(panelAreaTematica);

			panelAreaTematica.add(lblTituloEntidades("Áreas temáticas"));

			JScrollPane scrollPane = scrollPane();
			panelAreaTematica.add(scrollPane);

			JTable tableAreasTematicas = new JTable();

			DefaultTableModel model = new DefaultTableModel();
			tableAreasTematicas.setModel(model);
			model.addColumn("");
			model.addColumn("Nombre");

			llenarTabla(model, entidad);
			scrollPane.setViewportView(tableAreasTematicas);

			JButton btnAreaTematica = btnAgregarAreaTematica();
			panelAreaTematica.add(btnAreaTematica);

			JButton btnActualizarAreaTematica = btnActualizarAreaTematica();
			panelAreaTematica.add(btnActualizarAreaTematica);

			/**
			 * Botón con evento para agregar area tematica
			 */

			btnAreaTematica.addActionListener(b -> {
				Long enumeracion = ultimaEnumeracion(model);

				String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre de area tematica");
				agregarEntidadATabla(valorAgregar, entidad, model, enumeracion);
			});

		});

		/**
		 * Crea el panel para administrar formatos
		 */

		MenuItemFormato.addActionListener(e -> {
			panelBienvenida.setVisible(false);
			panelAreaTematica.setVisible(false);
			panelTipoObra.setVisible(false);
			panelObra.setVisible(false);
			panelFormato.setVisible(true);
			contentPane.add(panelFormato);
			String entidad = "Formato";

			panelFormato.add(lblTituloEntidades("Formatos"));

			JScrollPane scrollPane = scrollPane();
			panelFormato.add(scrollPane);

			JTable tableFormatos = new JTable();

			DefaultTableModel model = new DefaultTableModel();
			tableFormatos.setModel(model);
			model.addColumn("");
			model.addColumn("Nombre");

			llenarTabla(model, entidad);
			scrollPane.setViewportView(tableFormatos);

			JButton btnAgregarFormato = btnAgregarFormato();
			panelFormato.add(btnAgregarFormato);

			JButton btnActualizarFormato = btnActualizarFormato();
			panelFormato.add(btnActualizarFormato);

			/**
			 * Botón con evento para agregar formatos
			 */

			btnAgregarFormato.addActionListener(b -> {
				Long enumeracion = ultimaEnumeracion(model);

				String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre del formato");
				agregarEntidadATabla(valorAgregar, entidad, model, enumeracion);
			});

		});

		/**
		 * Crea el panel para administrar obras
		 */

		MenuItemObra.addActionListener(e -> {
			panelBienvenida.setVisible(false);
			panelAreaTematica.setVisible(false);
			panelFormato.setVisible(false);
			panelTipoObra.setVisible(false);
			panelObra.setVisible(true);

			contentPane.add(panelObra);
			String entidad = "Obra";

			panelObra.add(lblTituloEntidades("Obras"));

			JScrollPane scrollPane = scrollPane();
			panelObra.add(scrollPane);

			JTable tableObras = new JTable();
			tableObras.setSize(1000, 1600);

			DefaultTableModel model = new DefaultTableModel();
			tableObras.setModel(model);
			model.addColumn("Isbn");
			model.addColumn("Titulo");
			model.addColumn("Subitulo");
			model.addColumn("1° autor");
			model.addColumn("2° autor");
			model.addColumn("3° autor");
			model.addColumn("Género");
			model.addColumn("Tipo obra");
			model.addColumn("Area tematica");
			model.addColumn("Formato");
			model.addColumn("Forma adquisicion");
			model.addColumn("Fecha adquisicion");
			model.addColumn("Observaciones");

			llenarTabla(model, entidad);
			scrollPane.setViewportView(tableObras);

			JButton btnAgregarObra = btnAgregarObra();
			panelObra.add(btnAgregarObra);

			JButton btnActualizarObra = btnActualizarObra();
			panelObra.add(btnActualizarObra);

			JButton btnRefrescar = btnRefrescar();
			panelObra.add(btnRefrescar);

			btnRefrescar.addActionListener(b -> {
				Long enumeracion = ultimaEnumeracion(model);

				ModeloDeTransferencia modelo = General.modeloDeTransferencia;

				String isbn = modelo.getFieldIsbn();
				String titulo = modelo.getFieldTitulo(); 
				String subtitulo = modelo.getFieldSubtitulo();
				String primerAutor = modelo.getFieldPrimerAutor();
				String segundoAutor = modelo.getFieldSegundoAutor();
				String tercerAutor = modelo.getFieldTercerAutor();
				String genero = modelo.getFieldGenero();
				String tipoObra = modelo.getComboBoxTipoObra();
				String areaTematica = modelo.getComboBoxAreaTematica();
				String formato = modelo.getComboBoxFormato();
				String formaAdquisicion = modelo.getFieldFormaAdquisicion();
				String fechaAdquisicion = modelo.getFieldFechaAdquisicion();
				String observaciones = modelo.getFieldObservaciones();
				Boolean refrescar = modelo.getRefrescar();
				
				if (refrescar == null || !refrescar) {
					JOptionPane.showInternalMessageDialog(null, "Nada para refrescar");
				} else {
					agregarEntidadObraATabla(isbn, titulo, subtitulo, primerAutor, segundoAutor, tercerAutor, genero, tipoObra,
							areaTematica, formato, formaAdquisicion, fechaAdquisicion, observaciones, entidad, model,
							enumeracion);
					modelo.setRefrescar(false);
				}
				
			});

			/**
			 * Botón con evento para agregar obra
			 */

			btnAgregarObra.addActionListener(b -> {
				AgregarObra agregarObra = new AgregarObra();
				agregarObra.setVisible(true);
			});

		});

		/**
		 * Method created to log out and return to the "IniciarSesion" window
		 */

		btnCerrarSesion.addActionListener(e -> {
			IniciarSesion login = new IniciarSesion();
			login.setVisible(true);
			SistemaFuncionario.this.dispose();
		});

		/**
		 * Created method to close window
		 */

		btnExit.addActionListener(e -> System.exit(0));

	}

	/**
	 * Create components
	 */

	public void ventana() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Opciones");
		setBounds(100, 100, 1390, 811);
		setLocationRelativeTo(null);
	}

	public JPanel contentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		return contentPane;
	}

	public JPanel panelPrestabook() {
		JPanel panelPrestabook = new JPanel();
		panelPrestabook.setBounds(339, 0, 1061, 103);
		panelPrestabook.setBorder(null);
		panelPrestabook.setBackground(new Color(0, 64, 128));
		panelPrestabook.setLayout(null);
		return panelPrestabook;
	}

	public JLabel lblIconCerrarSesion() {
		JLabel lblIconCerrarSesion = new JLabel("");
		lblIconCerrarSesion.setBounds(817, 5, 19, 16);
		return lblIconCerrarSesion;
	}

	public void JLabelImage(JLabel lblIconCerrarSesion) {
		ImageIcon image = new ImageIcon(new File("src/main/resources/Vector.png").getAbsolutePath());
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(lblIconCerrarSesion.getWidth(),
				lblIconCerrarSesion.getHeight(), Image.SCALE_DEFAULT));
		lblIconCerrarSesion.setIcon(icon);
		this.repaint();
	}

	public JLabel lblPrestabook() {
		JLabel lblPrestabook = new JLabel("PrestaBook");
		lblPrestabook.setBounds(399, 30, 267, 42);
		lblPrestabook.setForeground(Color.WHITE);
		lblPrestabook.setFont(new Font("Verdana", Font.BOLD, 32));
		return lblPrestabook;
	}

	public JButton btnExit() {
		JButton btnExit = new JButton("X");
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Verdana", Font.BOLD, 12));
		btnExit.setBackground(new Color(255, 106, 106));
		btnExit.setBounds(1004, 1, 47, 25);
		return btnExit;
	}

	public JButton btnCerrarSesion() {
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font("Verdana", Font.BOLD, 11));
		btnCerrarSesion.setFocusPainted(false);
		btnCerrarSesion.setBorderPainted(false);
		btnCerrarSesion.setBorder(null);
		btnCerrarSesion.setBounds(893, 2, 101, 23);
		btnCerrarSesion.setForeground(new Color(255, 255, 255));
		btnCerrarSesion.setBackground(new Color(0, 64, 128));
		return btnCerrarSesion;
	}

	public JPanel panelOpciones() {
		JPanel panelOpciones = new JPanel();
		panelOpciones.setBounds(0, 0, 341, 811);
		panelOpciones.setBackground(new Color(0, 45, 89));
		panelOpciones.setLayout(null);
		return panelOpciones;
	}

	public JLabel lblUsuario() {
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(27, 0, 122, 37);
		lblUsuario.setFont(new Font("Verdana", Font.BOLD, 17));
		lblUsuario.setForeground(new Color(255, 255, 255));
		return lblUsuario;
	}

	public JLabel textUsusario() {
		textUsusario = new JLabel("");
		textUsusario.setBackground(new Color(0, 128, 0));
		textUsusario.setBounds(118, 0, 173, 37);
		textUsusario.setFont(new Font("Verdana", Font.BOLD, 16));
		textUsusario.setForeground(new Color(255, 255, 255));
		return textUsusario;
	}

	public JButton btnSolicitudes() {
		JButton btnSolicitudes = new JButton("Solicitudes");
		btnSolicitudes.setVerifyInputWhenFocusTarget(false);
		btnSolicitudes.setForeground(new Color(0, 64, 128));
		btnSolicitudes.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSolicitudes.setFocusPainted(false);
		btnSolicitudes.setBorderPainted(false);
		btnSolicitudes.setBorder(null);
		btnSolicitudes.setBackground(Color.WHITE);
		btnSolicitudes.setBounds(23, 74, 293, 31);
		return btnSolicitudes;
	}

	public JPanel panelSeparador() {
		JPanel panelSeparador = new JPanel();
		panelSeparador.setBounds(23, 121, 292, 3);
		return panelSeparador;
	}

	public JLabel lblOpciones() {
		JLabel lblOpciones = new JLabel("Opciones");
		lblOpciones.setForeground(new Color(255, 255, 255));
		lblOpciones.setFont(new Font("Verdana", Font.BOLD, 16));
		lblOpciones.setBounds(133, 135, 105, 23);
		return lblOpciones;
	}

	public JButton btnGestionarDevolucion() {
		JButton btnGestionarDevolucion = new JButton("Gestionar devolución de obra");
		btnGestionarDevolucion.setFocusPainted(false);
		btnGestionarDevolucion.setBackground(new Color(255, 255, 255));
		btnGestionarDevolucion.setForeground(new Color(0, 64, 128));
		btnGestionarDevolucion.setFont(new Font("Verdana", Font.BOLD, 12));
		btnGestionarDevolucion.setBorderPainted(false);
		btnGestionarDevolucion.setBounds(23, 169, 293, 31);
		return btnGestionarDevolucion;
	}

	public JButton btnListarLectoresMorosos() {
		JButton btnListarLectoresMorosos = new JButton("Listar lectores morosos");
		btnListarLectoresMorosos.setFocusPainted(false);
		btnListarLectoresMorosos.setBackground(new Color(255, 255, 255));
		btnListarLectoresMorosos.setForeground(new Color(0, 64, 128));
		btnListarLectoresMorosos.setFont(new Font("Verdana", Font.BOLD, 12));
		btnListarLectoresMorosos.setBorderPainted(false);
		btnListarLectoresMorosos.setBounds(23, 222, 293, 31);
		return btnListarLectoresMorosos;
	}

	public JButton btnMasBuscadasPorAlumnoDocente() {
		JButton btnMasBuscadasPorAlumnoDocente = new JButton("Mas buscadas por alumnos y docentes");
		btnMasBuscadasPorAlumnoDocente.setFocusPainted(false);
		btnMasBuscadasPorAlumnoDocente.setBackground(new Color(255, 255, 255));
		btnMasBuscadasPorAlumnoDocente.setForeground(new Color(0, 64, 128));
		btnMasBuscadasPorAlumnoDocente.setFont(new Font("Verdana", Font.BOLD, 12));
		btnMasBuscadasPorAlumnoDocente.setBorderPainted(false);
		btnMasBuscadasPorAlumnoDocente.setBounds(23, 271, 293, 31);
		return btnMasBuscadasPorAlumnoDocente;
	}

	public JButton btnMasBuscadasPorPublicoGeneral() {
		JButton btnMasBuscadasPorPublicoGeneral = new JButton("Mas buscadas por publico en general");
		btnMasBuscadasPorPublicoGeneral.setFocusPainted(false);
		btnMasBuscadasPorPublicoGeneral.setBackground(new Color(255, 255, 255));
		btnMasBuscadasPorPublicoGeneral.setForeground(new Color(0, 64, 128));
		btnMasBuscadasPorPublicoGeneral.setFont(new Font("Verdana", Font.BOLD, 12));
		btnMasBuscadasPorPublicoGeneral.setBorderPainted(false);
		btnMasBuscadasPorPublicoGeneral.setBounds(23, 324, 293, 31);
		return btnMasBuscadasPorPublicoGeneral;
	}

	public JButton btnListarEjemplaresDisponiblesPorArea() {
		JButton btnListarEjemplaresDisponiblesPorArea = new JButton("Listar ejemplares disponibles por area");
		btnListarEjemplaresDisponiblesPorArea.setFocusPainted(false);
		btnListarEjemplaresDisponiblesPorArea.setBackground(new Color(255, 255, 255));
		btnListarEjemplaresDisponiblesPorArea.setForeground(new Color(0, 64, 128));
		btnListarEjemplaresDisponiblesPorArea.setFont(new Font("Verdana", Font.BOLD, 12));
		btnListarEjemplaresDisponiblesPorArea.setBorderPainted(false);
		btnListarEjemplaresDisponiblesPorArea.setBounds(23, 379, 293, 31);
		return btnListarEjemplaresDisponiblesPorArea;
	}

	public JButton btnListarEjemplaresDisponiblesPorFecha() {
		JButton btnListarEjemplaresDisponiblesPorFecha = new JButton("Listar ejemplares disponibles por fecha");
		btnListarEjemplaresDisponiblesPorFecha.setFocusPainted(false);
		btnListarEjemplaresDisponiblesPorFecha.setBackground(new Color(255, 255, 255));
		btnListarEjemplaresDisponiblesPorFecha.setForeground(new Color(0, 64, 128));
		btnListarEjemplaresDisponiblesPorFecha.setFont(new Font("Verdana", Font.BOLD, 12));
		btnListarEjemplaresDisponiblesPorFecha.setBorderPainted(false);
		btnListarEjemplaresDisponiblesPorFecha.setBounds(23, 432, 293, 31);
		return btnListarEjemplaresDisponiblesPorFecha;
	}

	public JButton btnListarLectoresMultadosPorPeriodo() {
		JButton btnListarLectoresMultadosPorPeriodo = new JButton("Listar lectores multados por periodo");
		btnListarLectoresMultadosPorPeriodo.setFocusPainted(false);
		btnListarLectoresMultadosPorPeriodo.setBackground(new Color(255, 255, 255));
		btnListarLectoresMultadosPorPeriodo.setForeground(new Color(0, 64, 128));
		btnListarLectoresMultadosPorPeriodo.setFont(new Font("Verdana", Font.BOLD, 12));
		btnListarLectoresMultadosPorPeriodo.setBorderPainted(false);
		btnListarLectoresMultadosPorPeriodo.setBounds(23, 489, 293, 31);
		return btnListarLectoresMultadosPorPeriodo;
	}

	public JButton btnListarRankingDeMultados() {
		JButton btnListarRankingDeMultados = new JButton("Listar ranking de multados");
		btnListarRankingDeMultados.setFocusPainted(false);
		btnListarRankingDeMultados.setBackground(new Color(255, 255, 255));
		btnListarRankingDeMultados.setForeground(new Color(0, 64, 128));
		btnListarRankingDeMultados.setFont(new Font("Verdana", Font.BOLD, 12));
		btnListarRankingDeMultados.setBorderPainted(false);
		btnListarRankingDeMultados.setBounds(23, 542, 293, 31);
		return btnListarRankingDeMultados;
	}

	public JButton btnListarObrasPorEditorial() {
		JButton btnListarObrasPorEditorial = new JButton("Listar obras por editorial ");
		btnListarObrasPorEditorial.setFocusPainted(false);
		btnListarObrasPorEditorial.setBackground(new Color(255, 255, 255));
		btnListarObrasPorEditorial.setForeground(new Color(0, 64, 128));
		btnListarObrasPorEditorial.setFont(new Font("Verdana", Font.BOLD, 12));
		btnListarObrasPorEditorial.setBorderPainted(false);
		btnListarObrasPorEditorial.setBounds(23, 598, 293, 31);
		return btnListarObrasPorEditorial;
	}

	public JButton btnAplicarMultaALector() {
		JButton btnAplicarMultaALector = new JButton("Aplicar multa a lector");
		btnAplicarMultaALector.setFocusPainted(false);
		btnAplicarMultaALector.setBackground(new Color(255, 255, 255));
		btnAplicarMultaALector.setForeground(new Color(0, 64, 128));
		btnAplicarMultaALector.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAplicarMultaALector.setBorderPainted(false);
		btnAplicarMultaALector.setBounds(23, 652, 293, 31);
		return btnAplicarMultaALector;
	}

	public JPanel panelBienvenida() {
		JPanel panelBienvenida = new JPanel();
		panelBienvenida.setBounds(339, 104, 1061, 707);
		panelBienvenida.setLayout(null);
		return panelBienvenida;
	}

	public JPanel panelAdministrarEntidades() {
		JPanel panelAdministrarEntidades = new JPanel();
		panelAdministrarEntidades.setBounds(339, 104, 987, 707);
		panelAdministrarEntidades.setLayout(null);
		return panelAdministrarEntidades;
	}

	public JLabel lblAdministrarEntidades() {
		JLabel lblBienvenidaParte1 = new JLabel("Administrador de entidades");
		lblBienvenidaParte1.setBounds(330, 0, 369, 136);
		lblBienvenidaParte1.setForeground(Color.GRAY);
		lblBienvenidaParte1.setFont(new Font("Verdana", Font.BOLD, 21));
		return lblBienvenidaParte1;
	}

	public JLabel lblTiposDeObras() {
		JLabel lblTiposDeObras = new JLabel("Tipos de obras");
		lblTiposDeObras.setBounds(410, 70, 369, 136);
		lblTiposDeObras.setForeground(Color.GRAY);
		lblTiposDeObras.setFont(new Font("Verdana", Font.BOLD, 19));
		return lblTiposDeObras;
	}

	public JScrollPane scrollPane() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 158, 1030, 300);
		return scrollPane;
	}

	private JButton btnAgregarTipoObra() {
		JButton btnAgregarTipoObra = new JButton("Agregar tipo obra");
		btnAgregarTipoObra.setFocusPainted(false);
		btnAgregarTipoObra.setBackground(new Color(0, 64, 128));
		btnAgregarTipoObra.setForeground(new Color(255, 255, 255));
		btnAgregarTipoObra.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAgregarTipoObra.setBorderPainted(false);
		btnAgregarTipoObra.setBounds(210, 500, 210, 20);
		return btnAgregarTipoObra;
	}

	private JButton btnActualizarTipoObra() {
		JButton btnActualizarTipoObra = new JButton("Actualizar tipo obra");
		btnActualizarTipoObra.setFocusPainted(false);
		btnActualizarTipoObra.setBackground(new Color(0, 64, 128));
		btnActualizarTipoObra.setForeground(new Color(255, 255, 255));
		btnActualizarTipoObra.setFont(new Font("Verdana", Font.BOLD, 12));
		btnActualizarTipoObra.setBorderPainted(false);
		btnActualizarTipoObra.setBounds(600, 500, 210, 20);
		return btnActualizarTipoObra;
	}

	private JButton btnAgregarAreaTematica() {
		JButton btnAgregarTipoObra = new JButton("Agregar area tematica");
		btnAgregarTipoObra.setFocusPainted(false);
		btnAgregarTipoObra.setBackground(new Color(0, 64, 128));
		btnAgregarTipoObra.setForeground(new Color(255, 255, 255));
		btnAgregarTipoObra.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAgregarTipoObra.setBorderPainted(false);
		btnAgregarTipoObra.setBounds(210, 500, 210, 20);
		return btnAgregarTipoObra;
	}

	private JButton btnActualizarAreaTematica() {
		JButton btnActualizarTipoObra = new JButton("Actualizar area tematica");
		btnActualizarTipoObra.setFocusPainted(false);
		btnActualizarTipoObra.setBackground(new Color(0, 64, 128));
		btnActualizarTipoObra.setForeground(new Color(255, 255, 255));
		btnActualizarTipoObra.setFont(new Font("Verdana", Font.BOLD, 12));
		btnActualizarTipoObra.setBorderPainted(false);
		btnActualizarTipoObra.setBounds(600, 500, 210, 20);
		return btnActualizarTipoObra;
	}

	private JButton btnAgregarFormato() {
		JButton btnAgregarFormato = new JButton("Agregar formato");
		btnAgregarFormato.setFocusPainted(false);
		btnAgregarFormato.setBackground(new Color(0, 64, 128));
		btnAgregarFormato.setForeground(new Color(255, 255, 255));
		btnAgregarFormato.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAgregarFormato.setBorderPainted(false);
		btnAgregarFormato.setBounds(210, 500, 210, 20);
		return btnAgregarFormato;
	}

	private JButton btnActualizarFormato() {
		JButton btnActualizarFormato = new JButton("Actualizar formato");
		btnActualizarFormato.setFocusPainted(false);
		btnActualizarFormato.setBackground(new Color(0, 64, 128));
		btnActualizarFormato.setForeground(new Color(255, 255, 255));
		btnActualizarFormato.setFont(new Font("Verdana", Font.BOLD, 12));
		btnActualizarFormato.setBorderPainted(false);
		btnActualizarFormato.setBounds(600, 500, 210, 20);
		return btnActualizarFormato;
	}

	private JButton btnAgregarObra() {
		JButton btnAgregarFormato = new JButton("Agregar obra");
		btnAgregarFormato.setFocusPainted(false);
		btnAgregarFormato.setBackground(new Color(0, 64, 128));
		btnAgregarFormato.setForeground(new Color(255, 255, 255));
		btnAgregarFormato.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAgregarFormato.setBorderPainted(false);
		btnAgregarFormato.setBounds(50, 500, 210, 20);
		return btnAgregarFormato;
	}

	private JButton btnActualizarObra() {
		JButton btnActualizarFormato = new JButton("Actualizar obra");
		btnActualizarFormato.setFocusPainted(false);
		btnActualizarFormato.setBackground(new Color(0, 64, 128));
		btnActualizarFormato.setForeground(new Color(255, 255, 255));
		btnActualizarFormato.setFont(new Font("Verdana", Font.BOLD, 12));
		btnActualizarFormato.setBorderPainted(false);
		btnActualizarFormato.setBounds(400, 500, 210, 20);
		return btnActualizarFormato;
	}

	private JButton btnRefrescar() {
		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setFocusPainted(false);
		btnRefrescar.setBackground(new Color(0, 64, 128));
		btnRefrescar.setForeground(new Color(255, 255, 255));
		btnRefrescar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnRefrescar.setBorderPainted(false);
		btnRefrescar.setBounds(700, 500, 210, 20);
		return btnRefrescar;
	}

	public JLabel lblBienvenidaParte1() {
		JLabel lblBienvenidaParte1 = new JLabel("¡Bienvenido al sistema de gestión de préstamos de libros más");
		lblBienvenidaParte1.setBounds(161, 0, 775, 136);
		lblBienvenidaParte1.setForeground(Color.GRAY);
		lblBienvenidaParte1.setFont(new Font("Verdana", Font.BOLD, 21));
		return lblBienvenidaParte1;
	}

	public JLabel lblBienvenidaParte2() {
		JLabel lblBienvenidaParte2 = new JLabel("grande del mundo!");
		lblBienvenidaParte2.setBounds(397, 64, 369, 136);
		lblBienvenidaParte2.setForeground(Color.GRAY);
		lblBienvenidaParte2.setFont(new Font("Verdana", Font.BOLD, 21));
		return lblBienvenidaParte2;
	}

	public JLabel lblIconLibreria() {
		JLabel lblIconLibreria = new JLabel("");
		lblIconLibreria.setBounds(225, 169, 605, 493);
		lblIconLibreria.setIcon(new ImageIcon(new File("src/main/resources/library.png").getAbsolutePath()));
		return lblIconLibreria;
	}

	/**
	 * Menu bar Administrar
	 */

	public JMenuBar menuBarAdministrar() {
		JMenuBar menuBarAdministrar = new JMenuBar();
		menuBarAdministrar.setBounds(10, 6, 75, 22);
		menuBarAdministrar.setBackground(new Color(255, 255, 255));
		return menuBarAdministrar;
	}

	public JMenu menuAdministrar() {
		JMenu menuAdministrar = new JMenu("Administrar");
		menuAdministrar.setBackground(new Color(255, 255, 255));
		menuAdministrar.setOpaque(true);
		return menuAdministrar;
	}

	/**
	 * Paneles de entidades
	 * 
	 * @return
	 */

	public JPanel panelEntidades() {
		JPanel panelEntidades = new JPanel();
		panelEntidades.setBounds(339, 104, 1061, 707);
		panelEntidades.setLayout(null);
		return panelEntidades;
	}

	public JLabel lblTituloEntidades(String text) {
		JLabel lblTituloEntidades = new JLabel(text);
		lblTituloEntidades.setBounds(440, 70, 369, 136);
		lblTituloEntidades.setForeground(Color.GRAY);
		lblTituloEntidades.setFont(new Font("Verdana", Font.BOLD, 19));
		return lblTituloEntidades;
	}

	/**
	 * Llena la tabla con todas las filas cargadas en la base de dato
	 */

	public void llenarTabla(DefaultTableModel model, String tipoEntidad) {
		Integer i = 0;
		switch (tipoEntidad) {
		case "Tipo obra":
			ITipoObraDAO tipoObraDAO = DaoFactory.getTipoObraDAO();
			java.util.List<TipoObra> tiposObra = tipoObraDAO.findAll();
			for (TipoObra tipo : tiposObra) {
				Object[] fila = new Object[2];
				fila[0] = ++i;
				fila[1] = tipo.getNombre();
				model.addRow(fila);
			}
			break;
		case "Area tematica":
			IAreaTematicaDAO areaTematicaDAO = DaoFactory.getAreaTematicaDAO();
			java.util.List<AreaTematica> areasTematicas = areaTematicaDAO.findAll();
			for (AreaTematica area : areasTematicas) {
				Object[] fila = new Object[2];
				fila[0] = ++i;
				fila[1] = area.getNombre();
				model.addRow(fila);
			}
			break;
		case "Formato":
			IFormatoDAO formatoDAO = DaoFactory.getFormatoDAO();
			java.util.List<Formato> formatos = formatoDAO.findAll();
			for (Formato formato : formatos) {
				Object[] fila = new Object[2];
				fila[0] = ++i;
				fila[1] = formato.getNombre();
				model.addRow(fila);
			}
			break;
		case "Obra":
			IObraDAO obraDAO = DaoFactory.getObraDAO();
			java.util.List<Obra> obras = obraDAO.findAll();
			for (Obra obra : obras) {
				Object[] fila = new Object[2];
				fila[0] = ++i;
				fila[1] = obra.getIsbn();
				fila[2] = obra.getTitulo();
				fila[3] = obra.getSubtitulo();
				fila[4] = obra.getPrimerAutor();
				fila[5] = obra.getSegundoAutor();
				fila[6] = obra.getTercerAutor();
				fila[7] = obra.getGenero();
				fila[8] = obra.getTipo().getNombre();
				fila[9] = obra.getArea().getNombre();
				fila[10] = null;
				fila[11] = null;
				fila[12] = null;
				fila[13] = null;
				model.addRow(fila);
			}
			break;
		default:
		}

	}

	/**
	 * Busca la ultima enumeracion de la lista
	 */

	public Long ultimaEnumeracion(DefaultTableModel model) {
		Integer cantidad = model.getRowCount();
		if (cantidad != 0) {
			return (long) cantidad;
		} else {
			return (long) 0;
		}
	}

	/**
	 * Agrega una fila a la tabla y llama al metodo (actualizarBaseDeDatos) para
	 * actualizar la misma fila en la base de datos. Metodo funcional solo para obra
	 */

	public void agregarEntidadObraATabla(String isbn, String titulo, String subtitulo, String primerAutor, String segundoAutor,
			String tercerAutor, String genero, String tipoObra, String areaTematica, String formato,
			String formaAdquisicion, String fechaAdquisicion, String observaciones, String entidad,
			DefaultTableModel model, Long enumeracion) {
		Object[] fila = new Object[14];

		fila[0] = isbn;
		fila[1] = titulo;
		fila[2] = subtitulo;
		fila[3] = primerAutor;
		fila[4] = segundoAutor;
		fila[5] = tercerAutor;
		fila[6] = genero;
		fila[7] = tipoObra;
		fila[8] = areaTematica;
		fila[9] = formato;
		fila[10] = formaAdquisicion;
		fila[11] = fechaAdquisicion;
		fila[12] = observaciones;
		model.addRow(fila);

		actualizarBaseDeDatos(model, entidad);
	}

	/**
	 * Agrega una fila a la tabla y llama al metodo (actualizarBaseDeDatos) para
	 * actualizar la misma fila en la base de datos. Metodo funcional para: Tipo obra, area tematica y formato
	 */

	public void agregarEntidadATabla(String valorAgregar, String entidad, DefaultTableModel model, Long enumeracion) {
		Object[] fila = new Object[2];
		if (valorAgregar != null) {
			if (!valorAgregar.isBlank()) {
				fila[0] = enumeracion + 1;
				fila[1] = valorAgregar;
				model.addRow(fila);
				actualizarBaseDeDatos(model, entidad);
			} else {
				JOptionPane.showInternalMessageDialog(null, "Debe completar el campo para poder agregar tipo de obra");
			}
		} 
	}
	
	/**
	 * Actualiza en la base de datos la fila agregada en la tabla
	 * 
	 */

	private void actualizarBaseDeDatos(DefaultTableModel model, String tipoEntidad) {

		Integer cantidad = model.getRowCount();
		String nombre = model.getValueAt(cantidad - 1, 1).toString();

		switch (tipoEntidad) {
		case "Tipo obra":
			ITipoObraDAO tipoObraDAO = DaoFactory.getTipoObraDAO();
			TipoObra tipoObra = new TipoObra();
			tipoObra.setNombre(nombre);
			tipoObraDAO.insert(tipoObra);
			break;
		case "Area tematica":
			IAreaTematicaDAO areaTematicaDAO = DaoFactory.getAreaTematicaDAO();
			AreaTematica areaTematica = new AreaTematica();
			areaTematica.setNombre(nombre);
			areaTematicaDAO.insert(areaTematica);
			break;
		case "Formato":
			IFormatoDAO formatoDAO = DaoFactory.getFormatoDAO();
			Formato formato = new Formato();
			formato.setNombre(nombre);
			formatoDAO.insert(formato);
			break;
		case "Obra":
			IObraDAO obraDAO = DaoFactory.getObraDAO();
			Obra obra = new Obra();
			obra.setIsbn(model.getValueAt(cantidad - 1, 0).toString());
			obra.setTitulo(model.getValueAt(cantidad - 1, 1).toString());
			obra.setSubtitulo(model.getValueAt(cantidad - 1, 2).toString());
			obra.setPrimerAutor(model.getValueAt(cantidad - 1, 3).toString());
			obra.setSegundoAutor(model.getValueAt(cantidad - 1, 4).toString());
			obra.setTercerAutor(model.getValueAt(cantidad - 1, 5).toString());
			obra.setGenero(model.getValueAt(cantidad - 1, 6).toString());
			obra.setTipo(new TipoObra(null, model.getValueAt(cantidad - 1, 7).toString()));
			obra.setArea(new AreaTematica(null, model.getValueAt(cantidad - 1, 8).toString()));

			obraDAO.insert(obra); 
			break;
		default:
		}

	}

}
