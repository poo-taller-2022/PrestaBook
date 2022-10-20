package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.IFormatoDAO;
import ar.edu.uner.prestabook.persistence.ITipoObraDAO;
import lombok.Getter;

public class SistemaFuncionario extends JFrame {

	private static final long serialVersionUID = 1L;
	@Getter
	private JLabel textUsuario;
	private JPanel panelAreaTematica = panelEntidades();
	private JPanel panelBienvenida = panelBienvenida();
	private JPanel panelColeccion = panelEntidades();
	private JPanel panelEdicion = panelEntidades();
	private JPanel panelEjemplar = panelEntidades();
	private JPanel panelFormato = panelEntidades();
	private JPanel panelLectores = panelEntidades();
	private JPanel panelObra = panelEntidades();
	private JPanel panelTipoObra = panelEntidades();
	private List<JPanel> paneles = List.of(panelAreaTematica, panelBienvenida, panelColeccion, panelEdicion,
			panelEjemplar, panelFormato, panelLectores, panelObra, panelTipoObra);

	/**
	 * Create the frame.
	 */

	public SistemaFuncionario() {

		/**
		 * Create components
		 */
		textUsuario = new JLabel("");
		ventana();

		JPanel contentPane = contentPane();

		JPanel panelPrestabook = panelPrestabook();
		contentPane.add(panelPrestabook);

		JLabel lblIconCerrarSesion = lblIconCerrarSesion();
		panelPrestabook.add(lblIconCerrarSesion);
		jLabelImage(lblIconCerrarSesion);

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
		panelOpciones.add(textUsuario());
		panelOpciones.add(panelSeparador());
		panelOpciones.add(lblOpciones());

		JButton btnGestionarPrestamo = btnGestionarPrestamo();
		btnGestionarPrestamo.addActionListener(e -> {
			Prestamos prestamos = new Prestamos();
			prestamos.setVisible(true);
		});
		panelOpciones.add(btnGestionarPrestamo);

		JButton btnGestionarDevolucion = btnGestionarDevolucion();
		btnGestionarDevolucion.addActionListener(e -> {
			Devoluciones devoluciones = new Devoluciones();
			devoluciones.setVisible(true);
		});
		panelOpciones.add(btnGestionarDevolucion);

		JButton btnVerEjemplares = btnVerEjemplares();
		btnVerEjemplares.addActionListener(e -> {
			Ejemplares ejemplares = new Ejemplares();
			ejemplares.setVisible(true);
		});
		panelOpciones.add(btnVerEjemplares);

		JButton btnMasBuscadasPorAlumnoDocente = btnMasBuscadasPorAlumnoDocente();
		panelOpciones.add(btnMasBuscadasPorAlumnoDocente);

		JButton btnMasBuscadasPorPublicoGeneral = btnMasBuscadasPorPublicoGeneral();
		panelOpciones.add(btnMasBuscadasPorPublicoGeneral);

		JButton btnListarEjemplaresDisponiblesPorArea = btnListarEjemplaresDisponiblesPorArea();
		panelOpciones.add(btnListarEjemplaresDisponiblesPorArea);

		JButton btnListarEjemplaresDisponiblesPorFecha = btnListarEjemplaresDisponiblesPorFecha();
		panelOpciones.add(btnListarEjemplaresDisponiblesPorFecha);

		JButton btnListarLectores = btnListarLectores();
		panelOpciones.add(btnListarLectores);

		JButton btnListarRankingDeMultados = btnListarRankingDeMultados();
		panelOpciones.add(btnListarRankingDeMultados);

		JButton btnListarObrasPorEditorial = btnListarObrasPorEditorial();
		panelOpciones.add(btnListarObrasPorEditorial);

		JButton btnAplicarMultaALector = btnAplicarMultaALector();
		panelOpciones.add(btnAplicarMultaALector);

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

		JMenuItem menuItemTipoObra = new JMenuItem(Constants.TIPOS_DE_OBRAS);
		menuAdministrar.add(menuItemTipoObra);

		JMenuItem menuItemAreaTematica = new JMenuItem(Constants.AREAS_TEMATICAS);
		menuAdministrar.add(menuItemAreaTematica);

		JMenuItem menuItemColeccion = new JMenuItem(Constants.COLECCIONES);
		menuAdministrar.add(menuItemColeccion);

		JMenuItem menuItemObra = new JMenuItem(Constants.OBRAS);
		menuAdministrar.add(menuItemObra);

		JMenuItem menuItemFormato = new JMenuItem(Constants.FORMATOS);
		menuAdministrar.add(menuItemFormato);

		JMenuItem menuItemEdicion = new JMenuItem(Constants.EDICIONES);
		menuAdministrar.add(menuItemEdicion);

		JMenuItem menuItemEjemplar = new JMenuItem(Constants.EJEMPLARES);
		menuAdministrar.add(menuItemEjemplar);

		/**
		 * Crea el panel para administrar tipos de obras
		 */

		menuItemTipoObra.addActionListener(e -> {
			ocultarPaneles();
			panelTipoObra.setVisible(true);

			contentPane.add(panelTipoObra);

			panelTipoObra.add(lblTituloEntidades(Constants.TIPOS_DE_OBRAS));

			JScrollPane scrollPane = scrollPane();
			panelTipoObra.add(scrollPane);

			JTable tableTipoDeObras = new JTable();

			DefaultTableModel model = new DefaultTableModel();
			tableTipoDeObras.setModel(model);
			model.addColumn("");
			model.addColumn(Constants.NOMBRE);

			Tabla.fill(model, Constants.TIPO_OBRA);
			scrollPane.setViewportView(tableTipoDeObras);

			JButton btnAgregarTipoObra = btnAgregarTipoObra();
			panelTipoObra.add(btnAgregarTipoObra);

			JButton btnActualizarTipoObra = btnActualizarTipoObra();
			panelTipoObra.add(btnActualizarTipoObra);

			/**
			 * Botón con evento para agregar tipo de obra
			 */

			btnAgregarTipoObra.addActionListener(b -> {

				String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre de tipo de obra");

				if (valorAgregar != null && !valorAgregar.isBlank()) {
					actualizarBaseDeDatos(valorAgregar, Constants.TIPO_OBRA);
					limpiarTabla(tableTipoDeObras);
					Tabla.fill(model, Constants.TIPO_OBRA);
				}
			});

		});

		/**
		 * Crea el panel para administrar Areas temáticas
		 */

		menuItemAreaTematica.addActionListener(e -> {
			ocultarPaneles();
			panelAreaTematica.setVisible(true);

			contentPane.add(panelAreaTematica);

			panelAreaTematica.add(lblTituloEntidades(Constants.AREAS_TEMATICAS));

			JScrollPane scrollPane = scrollPane();
			panelAreaTematica.add(scrollPane);

			JTable tableAreasTematicas = new JTable();

			DefaultTableModel model = new DefaultTableModel();
			tableAreasTematicas.setModel(model);
			model.addColumn("");
			model.addColumn(Constants.NOMBRE);

			Tabla.fill(model, Constants.AREA_TEMATICA);
			scrollPane.setViewportView(tableAreasTematicas);

			JButton btnAreaTematica = btnAgregarAreaTematica();
			panelAreaTematica.add(btnAreaTematica);

			JButton btnActualizarAreaTematica = btnActualizarAreaTematica();
			panelAreaTematica.add(btnActualizarAreaTematica);

			/**
			 * Botón con evento para agregar area tematica
			 */

			btnAreaTematica.addActionListener(b -> {
				String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre de area tematica");

				if (valorAgregar != null && !valorAgregar.isBlank()) {
					actualizarBaseDeDatos(valorAgregar, Constants.AREA_TEMATICA);
					limpiarTabla(tableAreasTematicas);
					Tabla.fill(model, Constants.AREA_TEMATICA);
				}
			});

		});

		/**
		 * Crea el panel para administrar formatos
		 */

		menuItemFormato.addActionListener(e -> {
			ocultarPaneles();
			panelFormato.setVisible(true);
			contentPane.add(panelFormato);

			panelFormato.add(lblTituloEntidades(Constants.FORMATOS));

			JScrollPane scrollPane = scrollPane();
			panelFormato.add(scrollPane);

			JTable tableFormatos = new JTable();

			DefaultTableModel model = new DefaultTableModel();
			tableFormatos.setModel(model);
			model.addColumn("");
			model.addColumn(Constants.NOMBRE);

			Tabla.fill(model, Constants.FORMATO);
			scrollPane.setViewportView(tableFormatos);

			JButton btnAgregarFormato = btnAgregarFormato();
			panelFormato.add(btnAgregarFormato);

			JButton btnActualizarFormato = btnActualizarFormato();
			panelFormato.add(btnActualizarFormato);

			/**
			 * Botón con evento para agregar formatos
			 */

			btnAgregarFormato.addActionListener(b -> {
				String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre del formato");

				if (valorAgregar != null && !valorAgregar.isBlank()) {
					actualizarBaseDeDatos(valorAgregar, Constants.FORMATO);
					limpiarTabla(tableFormatos);
					Tabla.fill(model, Constants.FORMATO);
				}
			});

		});

		/**
		 * Crea el panel para administrar obras
		 */

		menuItemObra.addActionListener(e -> {
			ocultarPaneles();
			panelObra.setVisible(true);
			contentPane.add(panelObra);

			panelObra.add(lblTituloEntidades(Constants.OBRAS));
			JScrollPane scrollPane = scrollPane();
			panelObra.add(scrollPane);

			JTable tableObras = new JTable();

			DefaultTableModel model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tableObras.setModel(model);
			model.addColumn("");
			model.addColumn("Isbn");
			model.addColumn("Titulo");
			model.addColumn("Subitulo");
			model.addColumn("1° autor");
			model.addColumn("2° autor");
			model.addColumn("3° autor");
			model.addColumn("Género");
			model.addColumn(Constants.TIPO_OBRA);
			model.addColumn(Constants.AREA_TEMATICA);

			Tabla.fill(model, Constants.OBRA);
			scrollPane.setViewportView(tableObras);

			JButton btnAgregarObra = btnAgregarObra();
			panelObra.add(btnAgregarObra);

			JButton btnRefrescar = btnRefrescar();
			panelObra.add(btnRefrescar);

			btnAgregarObra.addActionListener(b -> {
				AgregarObra agregarObra = new AgregarObra();
				agregarObra.setVisible(true);
			});
			btnRefrescar.addActionListener(b -> {
				limpiarTabla(tableObras);
				Tabla.fill(model, Constants.OBRA);
			});
		});

		/**
		 * Crea el panel para administrar ejemplares
		 */

		menuItemEjemplar.addActionListener(e -> {
			ocultarPaneles();
			panelEjemplar.setVisible(true);
			contentPane.add(panelEjemplar);

			panelEjemplar.add(lblTituloEntidades(Constants.EJEMPLARES));

			JScrollPane scrollPane = scrollPane();
			panelEjemplar.add(scrollPane);

			JTable tablaEjemplares = new JTable();
			tablaEjemplares.setSize(1000, 1600);

			DefaultTableModel model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tablaEjemplares.setModel(model);
			model.addColumn("");
			model.addColumn("Isbn de obra");
			model.addColumn("Forma de adquisicion");
			model.addColumn("Fecha de adquisicion");
			model.addColumn("Observaciones");
			model.addColumn("Codigo");
			model.addColumn("Pasillo");
			model.addColumn("Estantería");
			model.addColumn("Estante");

			Tabla.fill(model, Constants.EJEMPLAR);
			scrollPane.setViewportView(tablaEjemplares);

			JButton btnAgregarEjemplar = btnAgregarEjemplar();
			panelEjemplar.add(btnAgregarEjemplar);

			JButton btnRefrescar = btnRefrescar();
			panelEjemplar.add(btnRefrescar);

			/**
			 * Botón con evento para agregar obra
			 */

			btnAgregarEjemplar.addActionListener(b -> {
				AgregarEjemplar agregarEjemplar = new AgregarEjemplar();
				agregarEjemplar.setVisible(true);
			});

			btnRefrescar.addActionListener(b -> {
				limpiarTabla(tablaEjemplares);
				Tabla.fill(model, Constants.EJEMPLAR);
			});

		});

		/**
		 * Crea el panel para administrar ediciones
		 */

		menuItemEdicion.addActionListener(e -> {
			ocultarPaneles();
			panelEdicion.setVisible(true);
			contentPane.add(panelEdicion);

			panelEdicion.add(lblTituloEntidades(Constants.EDICIONES));

			JScrollPane scrollPane = scrollPane();
			panelEdicion.add(scrollPane);

			JTable tablaEdiciones = new JTable();
			tablaEdiciones.setSize(1000, 1600);

			DefaultTableModel model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tablaEdiciones.setModel(model);
			model.addColumn("");
			model.addColumn("Editorial");
			model.addColumn("Pais");
			model.addColumn("Numero");
			model.addColumn("Año");
			model.addColumn("Volumenes");
			model.addColumn("Paginas");
			model.addColumn("Idioma");
			model.addColumn("Formato");

			Tabla.fill(model, Constants.EDICION);
			scrollPane.setViewportView(tablaEdiciones);

			JButton btnAgregarEdicion = btnAgregarEdicion();
			panelEdicion.add(btnAgregarEdicion);

			JButton btnRefrescar = btnRefrescar();
			panelEdicion.add(btnRefrescar);

			/**
			 * Botón con evento para agregar obra
			 */

			btnAgregarEdicion.addActionListener(b -> {
				AgregarEdicion agregarEdicion = new AgregarEdicion();
				agregarEdicion.setVisible(true);
			});
			btnRefrescar.addActionListener(b -> {
				limpiarTabla(tablaEdiciones);
				Tabla.fill(model, Constants.EDICION);
			});

		});

		/**
		 * Crea el panel para administrar colecciones
		 */

		menuItemColeccion.addActionListener(e -> {
			ocultarPaneles();
			panelColeccion.setVisible(true);
			contentPane.add(panelColeccion);

			panelColeccion.add(lblTituloEntidades(Constants.COLECCIONES));

			JScrollPane scrollPane = scrollPane();
			panelColeccion.add(scrollPane);

			JTable tablaColecciones = new JTable();
			tablaColecciones.setSize(1000, 1600);

			DefaultTableModel model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tablaColecciones.setModel(model);
			model.addColumn("");
			model.addColumn("Isbn");
			model.addColumn("Titulo");
			model.addColumn("Subitulo");
			model.addColumn("1° autor");
			model.addColumn("2° autor");
			model.addColumn("3° autor");
			model.addColumn("Género");
			model.addColumn(Constants.TIPO_OBRA);
			model.addColumn(Constants.AREA_TEMATICA);

			Tabla.fill(model, Constants.COLECCION);
			scrollPane.setViewportView(tablaColecciones);

			JButton btnAgregarColeccion = btnAgregarColeccion();
			panelColeccion.add(btnAgregarColeccion);

			JButton btnRefrescar = btnRefrescar();
			panelColeccion.add(btnRefrescar);

			/**
			 * Botón con evento para agregar obra
			 */

			btnAgregarColeccion.addActionListener(b -> {
				AgregarColeccion agregarColeccion = new AgregarColeccion();
				agregarColeccion.setVisible(true);
			});

			btnRefrescar.addActionListener(b -> {
				limpiarTabla(tablaColecciones);
				Tabla.fill(model, Constants.COLECCION);
			});

		});

		btnListarLectores.addActionListener(e -> {
			ocultarPaneles();
			panelLectores.setVisible(true);
			contentPane.add(panelLectores);

			panelLectores.add(lblTituloEntidades(Constants.LECTORES));

			JScrollPane scrollPane = scrollPane();
			panelLectores.add(scrollPane);

			JTable tablaLectores = new JTable();
			tablaLectores.setSize(1000, 1600);

			DefaultTableModel model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tablaLectores.setModel(model);
			tablaLectores.setAutoCreateRowSorter(true);

			model.addColumn("");
			model.addColumn("Nombre");
			model.addColumn("Apellido");
			model.addColumn("E-mail");
			model.addColumn("Dirección");
			model.addColumn("Fecha de nacimiento");
			model.addColumn("Multas");

			Tabla.fill(model, Constants.LECTOR);

			scrollPane.setViewportView(tablaLectores);

			JButton btnRefrescar = btnRefrescar();
			panelLectores.add(btnRefrescar);

			btnRefrescar.addActionListener(b -> {
				limpiarTabla(tablaLectores);
				Tabla.fill(model, Constants.LECTOR);
			});
		});

		btnCerrarSesion.addActionListener(e -> {
			IniciarSesion login = new IniciarSesion();
			login.setVisible(true);
			SistemaFuncionario.this.dispose();
		});

		btnExit.addActionListener(e -> System.exit(0));

	}

	/**
	 * Create components
	 */

	public void ventana() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle(Constants.OPCIONES);
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

	public void jLabelImage(JLabel lblIconCerrarSesion) {
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
		lblPrestabook.setFont(new Font(Constants.FONT, Font.BOLD, 32));
		return lblPrestabook;
	}

	public JButton btnExit() {
		JButton btnExit = new JButton("X");
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnExit.setBackground(new Color(255, 106, 106));
		btnExit.setBounds(1004, 1, 47, 25);
		return btnExit;
	}

	public JButton btnCerrarSesion() {
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font(Constants.FONT, Font.BOLD, 11));
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
		lblUsuario.setFont(new Font(Constants.FONT, Font.BOLD, 17));
		lblUsuario.setForeground(new Color(255, 255, 255));
		return lblUsuario;
	}

	public JLabel textUsuario() {
		textUsuario.setBackground(new Color(0, 128, 0));
		textUsuario.setBounds(118, 0, 173, 37);
		textUsuario.setFont(new Font(Constants.FONT, Font.BOLD, 16));
		textUsuario.setForeground(new Color(255, 255, 255));
		return textUsuario;
	}

	public JButton btnSolicitudes() {
		JButton btnSolicitudes = new JButton("Solicitudes");
		btnSolicitudes.setVerifyInputWhenFocusTarget(false);
		btnSolicitudes.setForeground(new Color(0, 64, 128));
		btnSolicitudes.setFont(new Font(Constants.FONT, Font.BOLD, 12));
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
		JLabel lblOpciones = new JLabel(Constants.OPCIONES);
		lblOpciones.setForeground(new Color(255, 255, 255));
		lblOpciones.setFont(new Font(Constants.FONT, Font.BOLD, 16));
		lblOpciones.setBounds(133, 135, 105, 23);
		return lblOpciones;
	}

	private JButton btnGestionarPrestamo() {
		JButton btnAgregarFormato = new JButton("Gestionar Préstamo");
		btnAgregarFormato.setFocusPainted(false);
		btnAgregarFormato.setBackground(new Color(255, 255, 255));
		btnAgregarFormato.setForeground(new Color(0, 64, 128));
		btnAgregarFormato.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnAgregarFormato.setBorderPainted(false);
		btnAgregarFormato.setBounds(23, 169, 293, 31);
		return btnAgregarFormato;
	}

	public JButton btnGestionarDevolucion() {
		JButton btnGestionarDevolucion = new JButton("Gestionar devolución de obra");
		btnGestionarDevolucion.setFocusPainted(false);
		btnGestionarDevolucion.setBackground(new Color(255, 255, 255));
		btnGestionarDevolucion.setForeground(new Color(0, 64, 128));
		btnGestionarDevolucion.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnGestionarDevolucion.setBorderPainted(false);
		btnGestionarDevolucion.setBounds(23, 221, 293, 31);
		return btnGestionarDevolucion;
	}

	public JButton btnVerEjemplares() {
		JButton btnVerEjemplares = new JButton("Ver Ejemplares");
		btnVerEjemplares.setFocusPainted(false);
		btnVerEjemplares.setBackground(new Color(255, 255, 255));
		btnVerEjemplares.setForeground(new Color(0, 64, 128));
		btnVerEjemplares.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnVerEjemplares.setBorderPainted(false);
		btnVerEjemplares.setBounds(23, 273, 293, 31);
		btnVerEjemplares.addActionListener(null);
		return btnVerEjemplares;
	}

	public JButton btnMasBuscadasPorAlumnoDocente() {
		JButton btnMasBuscadasPorAlumnoDocente = new JButton("Mas buscadas por alumnos y docentes");
		btnMasBuscadasPorAlumnoDocente.setFocusPainted(false);
		btnMasBuscadasPorAlumnoDocente.setBackground(new Color(255, 255, 255));
		btnMasBuscadasPorAlumnoDocente.setForeground(new Color(0, 64, 128));
		btnMasBuscadasPorAlumnoDocente.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnMasBuscadasPorAlumnoDocente.setBorderPainted(false);
		btnMasBuscadasPorAlumnoDocente.setBounds(23, 325, 293, 31);
		return btnMasBuscadasPorAlumnoDocente;
	}

	public JButton btnMasBuscadasPorPublicoGeneral() {
		JButton btnMasBuscadasPorPublicoGeneral = new JButton("Mas buscadas por publico en general");
		btnMasBuscadasPorPublicoGeneral.setFocusPainted(false);
		btnMasBuscadasPorPublicoGeneral.setBackground(new Color(255, 255, 255));
		btnMasBuscadasPorPublicoGeneral.setForeground(new Color(0, 64, 128));
		btnMasBuscadasPorPublicoGeneral.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnMasBuscadasPorPublicoGeneral.setBorderPainted(false);
		btnMasBuscadasPorPublicoGeneral.setBounds(23, 377, 293, 31);
		return btnMasBuscadasPorPublicoGeneral;
	}

	public JButton btnListarEjemplaresDisponiblesPorArea() {
		JButton btnListarEjemplaresDisponiblesPorArea = new JButton("Listar ejemplares disponibles por area");
		btnListarEjemplaresDisponiblesPorArea.setFocusPainted(false);
		btnListarEjemplaresDisponiblesPorArea.setBackground(new Color(255, 255, 255));
		btnListarEjemplaresDisponiblesPorArea.setForeground(new Color(0, 64, 128));
		btnListarEjemplaresDisponiblesPorArea.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnListarEjemplaresDisponiblesPorArea.setBorderPainted(false);
		btnListarEjemplaresDisponiblesPorArea.setBounds(23, 429, 293, 31);
		return btnListarEjemplaresDisponiblesPorArea;
	}

	public JButton btnListarEjemplaresDisponiblesPorFecha() {
		JButton btnListarEjemplaresDisponiblesPorFecha = new JButton("Listar ejemplares disponibles por fecha");
		btnListarEjemplaresDisponiblesPorFecha.setFocusPainted(false);
		btnListarEjemplaresDisponiblesPorFecha.setBackground(new Color(255, 255, 255));
		btnListarEjemplaresDisponiblesPorFecha.setForeground(new Color(0, 64, 128));
		btnListarEjemplaresDisponiblesPorFecha.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnListarEjemplaresDisponiblesPorFecha.setBorderPainted(false);
		btnListarEjemplaresDisponiblesPorFecha.setBounds(23, 481, 293, 31);
		return btnListarEjemplaresDisponiblesPorFecha;
	}

	public JButton btnListarLectores() {
		JButton btnListarLectoresMultadosPorPeriodo = new JButton("Ver Lectores");
		btnListarLectoresMultadosPorPeriodo.setFocusPainted(false);
		btnListarLectoresMultadosPorPeriodo.setBackground(new Color(255, 255, 255));
		btnListarLectoresMultadosPorPeriodo.setForeground(new Color(0, 64, 128));
		btnListarLectoresMultadosPorPeriodo.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnListarLectoresMultadosPorPeriodo.setBorderPainted(false);
		btnListarLectoresMultadosPorPeriodo.setBounds(23, 533, 293, 31);
		return btnListarLectoresMultadosPorPeriodo;
	}

	public JButton btnListarRankingDeMultados() {
		JButton btnListarRankingDeMultados = new JButton("Listar ranking de multados");
		btnListarRankingDeMultados.setFocusPainted(false);
		btnListarRankingDeMultados.setBackground(new Color(255, 255, 255));
		btnListarRankingDeMultados.setForeground(new Color(0, 64, 128));
		btnListarRankingDeMultados.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnListarRankingDeMultados.setBorderPainted(false);
		btnListarRankingDeMultados.setBounds(23, 585, 293, 31);
		return btnListarRankingDeMultados;
	}

	public JButton btnListarObrasPorEditorial() {
		JButton btnListarObrasPorEditorial = new JButton("Listar obras por editorial");
		btnListarObrasPorEditorial.setFocusPainted(false);
		btnListarObrasPorEditorial.setBackground(new Color(255, 255, 255));
		btnListarObrasPorEditorial.setForeground(new Color(0, 64, 128));
		btnListarObrasPorEditorial.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnListarObrasPorEditorial.setBorderPainted(false);
		btnListarObrasPorEditorial.setBounds(23, 637, 293, 31);
		return btnListarObrasPorEditorial;
	}

	public JButton btnAplicarMultaALector() {
		JButton btnAplicarMultaALector = new JButton("Aplicar multa a lector");
		btnAplicarMultaALector.setFocusPainted(false);
		btnAplicarMultaALector.setBackground(new Color(255, 255, 255));
		btnAplicarMultaALector.setForeground(new Color(0, 64, 128));
		btnAplicarMultaALector.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnAplicarMultaALector.setBorderPainted(false);
		btnAplicarMultaALector.setBounds(23, 689, 293, 31);
		return btnAplicarMultaALector;
	}

	public JPanel panelBienvenida() {
		JPanel panel = new JPanel();
		panel.setBounds(339, 104, 1061, 707);
		panel.setLayout(null);
		return panel;
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
		lblBienvenidaParte1.setFont(new Font(Constants.FONT, Font.BOLD, 21));
		return lblBienvenidaParte1;
	}

	public JLabel lblTiposDeObras() {
		JLabel lblTiposDeObras = new JLabel(Constants.TIPOS_DE_OBRAS);
		lblTiposDeObras.setBounds(410, 70, 369, 136);
		lblTiposDeObras.setForeground(Color.GRAY);
		lblTiposDeObras.setFont(new Font(Constants.FONT, Font.BOLD, 19));
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
		btnAgregarTipoObra.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnAgregarTipoObra.setBorderPainted(false);
		btnAgregarTipoObra.setBounds(210, 500, 210, 20);
		return btnAgregarTipoObra;
	}

	private JButton btnActualizarTipoObra() {
		JButton btnActualizarTipoObra = new JButton("Actualizar tipo obra");
		btnActualizarTipoObra.setFocusPainted(false);
		btnActualizarTipoObra.setBackground(new Color(0, 64, 128));
		btnActualizarTipoObra.setForeground(new Color(255, 255, 255));
		btnActualizarTipoObra.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnActualizarTipoObra.setBorderPainted(false);
		btnActualizarTipoObra.setBounds(600, 500, 210, 20);
		return btnActualizarTipoObra;
	}

	private JButton btnAgregarColeccion() {
		JButton btnAgregarColeccion = new JButton("Agregar colección");
		btnAgregarColeccion.setFocusPainted(false);
		btnAgregarColeccion.setBackground(new Color(0, 64, 128));
		btnAgregarColeccion.setForeground(new Color(255, 255, 255));
		btnAgregarColeccion.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnAgregarColeccion.setBorderPainted(false);
		btnAgregarColeccion.setBounds(210, 500, 210, 20);
		return btnAgregarColeccion;
	}

	private JButton btnAgregarEdicion() {
		JButton btnAgregarEdicion = new JButton("Agregar edición");
		btnAgregarEdicion.setFocusPainted(false);
		btnAgregarEdicion.setBackground(new Color(0, 64, 128));
		btnAgregarEdicion.setForeground(new Color(255, 255, 255));
		btnAgregarEdicion.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnAgregarEdicion.setBorderPainted(false);
		btnAgregarEdicion.setBounds(210, 500, 210, 20);
		return btnAgregarEdicion;
	}

	private JButton btnAgregarEjemplar() {
		JButton btnAgregarEjemplar = new JButton("Agregar ejemplar");
		btnAgregarEjemplar.setFocusPainted(false);
		btnAgregarEjemplar.setBackground(new Color(0, 64, 128));
		btnAgregarEjemplar.setForeground(new Color(255, 255, 255));
		btnAgregarEjemplar.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnAgregarEjemplar.setBorderPainted(false);
		btnAgregarEjemplar.setBounds(210, 500, 210, 20);
		return btnAgregarEjemplar;
	}

	private JButton btnAgregarAreaTematica() {
		JButton btnAgregarTipoObra = new JButton("Agregar area tematica");
		btnAgregarTipoObra.setFocusPainted(false);
		btnAgregarTipoObra.setBackground(new Color(0, 64, 128));
		btnAgregarTipoObra.setForeground(new Color(255, 255, 255));
		btnAgregarTipoObra.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnAgregarTipoObra.setBorderPainted(false);
		btnAgregarTipoObra.setBounds(210, 500, 210, 20);
		return btnAgregarTipoObra;
	}

	private JButton btnActualizarAreaTematica() {
		JButton btnActualizarTipoObra = new JButton("Actualizar area tematica");
		btnActualizarTipoObra.setFocusPainted(false);
		btnActualizarTipoObra.setBackground(new Color(0, 64, 128));
		btnActualizarTipoObra.setForeground(new Color(255, 255, 255));
		btnActualizarTipoObra.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnActualizarTipoObra.setBorderPainted(false);
		btnActualizarTipoObra.setBounds(600, 500, 210, 20);
		return btnActualizarTipoObra;
	}

	private JButton btnAgregarFormato() {
		JButton btnAgregarFormato = new JButton("Agregar formato");
		btnAgregarFormato.setFocusPainted(false);
		btnAgregarFormato.setBackground(new Color(0, 64, 128));
		btnAgregarFormato.setForeground(new Color(255, 255, 255));
		btnAgregarFormato.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnAgregarFormato.setBorderPainted(false);
		btnAgregarFormato.setBounds(210, 500, 210, 20);
		return btnAgregarFormato;
	}

	private JButton btnActualizarFormato() {
		JButton btnActualizarFormato = new JButton("Actualizar formato");
		btnActualizarFormato.setFocusPainted(false);
		btnActualizarFormato.setBackground(new Color(0, 64, 128));
		btnActualizarFormato.setForeground(new Color(255, 255, 255));
		btnActualizarFormato.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnActualizarFormato.setBorderPainted(false);
		btnActualizarFormato.setBounds(600, 500, 210, 20);
		return btnActualizarFormato;
	}

	private JButton btnAgregarObra() {
		JButton btnAgregarFormato = new JButton("Agregar obra");
		btnAgregarFormato.setFocusPainted(false);
		btnAgregarFormato.setBackground(new Color(0, 64, 128));
		btnAgregarFormato.setForeground(new Color(255, 255, 255));
		btnAgregarFormato.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnAgregarFormato.setBorderPainted(false);
		btnAgregarFormato.setBounds(50, 500, 210, 20);
		return btnAgregarFormato;
	}

	private JButton btnRefrescar() {
		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setFocusPainted(false);
		btnRefrescar.setBackground(new Color(0, 64, 128));
		btnRefrescar.setForeground(new Color(255, 255, 255));
		btnRefrescar.setFont(new Font(Constants.FONT, Font.BOLD, 12));
		btnRefrescar.setBorderPainted(false);
		btnRefrescar.setBounds(700, 500, 210, 20);
		return btnRefrescar;
	}

	public JLabel lblBienvenidaParte1() {
		JLabel lblBienvenidaParte1 = new JLabel("¡Bienvenido al sistema de gestión de préstamos de libros más");
		lblBienvenidaParte1.setBounds(161, 0, 775, 136);
		lblBienvenidaParte1.setForeground(Color.GRAY);
		lblBienvenidaParte1.setFont(new Font(Constants.FONT, Font.BOLD, 21));
		return lblBienvenidaParte1;
	}

	public JLabel lblBienvenidaParte2() {
		JLabel lblBienvenidaParte2 = new JLabel("grande del mundo!");
		lblBienvenidaParte2.setBounds(397, 64, 369, 136);
		lblBienvenidaParte2.setForeground(Color.GRAY);
		lblBienvenidaParte2.setFont(new Font(Constants.FONT, Font.BOLD, 21));
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
		lblTituloEntidades.setFont(new Font(Constants.FONT, Font.BOLD, 19));
		return lblTituloEntidades;
	}

	/**
	 * Actualiza en la base de datos la fila agregada en la tabla
	 * 
	 */
	private void actualizarBaseDeDatos(String nombre, String tipoEntidad) {

		switch (tipoEntidad) {
		case Constants.TIPO_OBRA:
			ITipoObraDAO tipoObraDAO = DaoFactory.getTipoObraDAO();
			TipoObra tipoObra = new TipoObra();
			tipoObra.setNombre(nombre);
			tipoObraDAO.insert(tipoObra);
			break;
		case Constants.AREA_TEMATICA:
			IAreaTematicaDAO areaTematicaDAO = DaoFactory.getAreaTematicaDAO();
			AreaTematica areaTematica = new AreaTematica();
			areaTematica.setNombre(nombre);
			areaTematicaDAO.insert(areaTematica);
			break;
		case Constants.FORMATO:
			IFormatoDAO formatoDAO = DaoFactory.getFormatoDAO();
			Formato formato = new Formato();
			formato.setNombre(nombre);
			formatoDAO.insert(formato);
			break;
		default:
		}

	}

	public void limpiarTabla(JTable tabla) {
		try {
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			int filas = tabla.getRowCount();
			for (int i = 0; filas > i; i++) {
				modelo.removeRow(0);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
		}
	}

	private void ocultarPaneles() {
		for (JPanel panel : paneles) {
			panel.setVisible(false);
		}
	}

}
