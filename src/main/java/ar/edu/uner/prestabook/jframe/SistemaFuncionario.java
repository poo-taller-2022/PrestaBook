package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

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
import ar.edu.uner.prestabook.model.CodigoIdentificatorio;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.IEdicionDAO;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;
import ar.edu.uner.prestabook.persistence.IFormatoDAO;
import ar.edu.uner.prestabook.persistence.IObraDAO;
import ar.edu.uner.prestabook.persistence.ITipoObraDAO;

public class SistemaFuncionario extends JFrame {

    private static final String FONT = "Verdana";
    private static final String OPCIONES = "Opciones";
    private static final String FORMATO = "Formato";
    private static final String AREA_TEMATICA = "Area tematica";
    private static final String NOMBRE = "Nombre";
    private static final String TIPO_OBRA = "Tipo obra";
    private static final String OBRAS = "Obras";
    private static final String FORMATOS = "Formatos";
    private static final String AREAS_TEMATICAS = "Áreas temáticas";
    private static final String TIPOS_DE_OBRAS = "Tipos de obras";
    private static final long serialVersionUID = 1L;
    public static JLabel textUsuario;

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

        JMenuItem menuItemTipoObra = new JMenuItem(TIPOS_DE_OBRAS);
        menuAdministrar.add(menuItemTipoObra);

        JMenuItem menuItemAreaTematica = new JMenuItem(AREAS_TEMATICAS);
        menuAdministrar.add(menuItemAreaTematica);

        JMenuItem menuItemFormato = new JMenuItem(FORMATOS);
        menuAdministrar.add(menuItemFormato);

        JMenuItem menuItemObra = new JMenuItem(OBRAS);
        menuAdministrar.add(menuItemObra);

        JPanel panelTipoObra = panelEntidades();
        JPanel panelAreaTematica = panelEntidades();
        JPanel panelFormato = panelEntidades();
        JPanel panelObra = panelEntidades();

        /**
         * Crea el panel para administrar tipos de obras
         */

        menuItemTipoObra.addActionListener(e -> {
            panelBienvenida.setVisible(false);
            panelAreaTematica.setVisible(false);
            panelFormato.setVisible(false);
            panelObra.setVisible(false);
            panelTipoObra.setVisible(true);

            contentPane.add(panelTipoObra);
            String entidad = TIPO_OBRA;

            panelTipoObra.add(lblTituloEntidades(TIPOS_DE_OBRAS));

            JScrollPane scrollPane = scrollPane();
            panelTipoObra.add(scrollPane);

            JTable tableTipoDeObras = new JTable();

            DefaultTableModel model = new DefaultTableModel();
            tableTipoDeObras.setModel(model);
            model.addColumn("");
            model.addColumn(NOMBRE);

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

                String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre de tipo de obra");
                actualizarBaseDeDatos(valorAgregar, entidad);

                limpiarTabla(tableTipoDeObras);

                llenarTabla(model, entidad);
            });

        });

        /**
         * Crea el panel para administrar Areas temáticas
         */

        menuItemAreaTematica.addActionListener(e -> {
            panelBienvenida.setVisible(false);
            panelTipoObra.setVisible(false);
            panelFormato.setVisible(false);
            panelObra.setVisible(false);
            panelAreaTematica.setVisible(true);

            String entidad = AREA_TEMATICA;

            contentPane.add(panelAreaTematica);

            panelAreaTematica.add(lblTituloEntidades(AREAS_TEMATICAS));

            JScrollPane scrollPane = scrollPane();
            panelAreaTematica.add(scrollPane);

            JTable tableAreasTematicas = new JTable();

            DefaultTableModel model = new DefaultTableModel();
            tableAreasTematicas.setModel(model);
            model.addColumn("");
            model.addColumn(NOMBRE);

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

                String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre de area tematica");

                actualizarBaseDeDatos(valorAgregar, entidad);

                limpiarTabla(tableAreasTematicas);

                llenarTabla(model, entidad);
            });

        });

        /**
         * Crea el panel para administrar formatos
         */

        menuItemFormato.addActionListener(e -> {
            panelBienvenida.setVisible(false);
            panelAreaTematica.setVisible(false);
            panelTipoObra.setVisible(false);
            panelObra.setVisible(false);
            panelFormato.setVisible(true);
            contentPane.add(panelFormato);
            String entidad = FORMATO;

            panelFormato.add(lblTituloEntidades(FORMATOS));

            JScrollPane scrollPane = scrollPane();
            panelFormato.add(scrollPane);

            JTable tableFormatos = new JTable();

            DefaultTableModel model = new DefaultTableModel();
            tableFormatos.setModel(model);
            model.addColumn("");
            model.addColumn(NOMBRE);

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

                String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre del formato");
                actualizarBaseDeDatos(valorAgregar, entidad);

                limpiarTabla(tableFormatos);

                llenarTabla(model, entidad);
            });

        });

        /**
         * Crea el panel para administrar obras
         */

        menuItemObra.addActionListener(e -> {
            panelBienvenida.setVisible(false);
            panelAreaTematica.setVisible(false);
            panelFormato.setVisible(false);
            panelTipoObra.setVisible(false);
            panelObra.setVisible(true);

            contentPane.add(panelObra);
            String entidad = "Obra";

            panelObra.add(lblTituloEntidades(OBRAS));

            JScrollPane scrollPane = scrollPane();
            panelObra.add(scrollPane);

            JTable tableObras = new JTable();
            tableObras.setSize(1000, 1600);

            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tableObras.setModel(model);
            model.addColumn("Isbn");
            model.addColumn("Titulo");
            model.addColumn("Subitulo");
            model.addColumn("1° autor");
            model.addColumn("2° autor");
            model.addColumn("3° autor");
            model.addColumn("Género");
            model.addColumn(TIPO_OBRA);
            model.addColumn(AREA_TEMATICA);
            model.addColumn(FORMATO);
            
            llenarTabla(model, entidad);
            scrollPane.setViewportView(tableObras);

			tableObras.getColumnModel().getColumn(14).setMaxWidth(0);
			tableObras.getColumnModel().getColumn(14).setMinWidth(0);
			tableObras.getColumnModel().getColumn(14).setPreferredWidth(0);

			llenarTabla(model, entidad);
			scrollPane.setViewportView(tableObras);

			JButton btnAgregarObra = btnAgregarObra();
			panelObra.add(btnAgregarObra);

			JButton btnActualizarObra = btnActualizarObra();
			panelObra.add(btnActualizarObra);

			JButton btnRefrescar = btnRefrescar();
			panelObra.add(btnRefrescar);

			btnRefrescar.addActionListener(b -> {

				ModeloDeTransferencia modelo = General.modeloDeTransferencia;
				Boolean refrescar = modelo.getRefrescar();

				if (refrescar == null || !refrescar) {
					JOptionPane.showInternalMessageDialog(null, "Nada para refrescar");
				} else {

					actualizarObraEnBaseDeDatos(modelo.getFieldIsbn(), modelo.getFieldTitulo(),
							modelo.getFieldSubtitulo(), modelo.getFieldPrimerAutor(), modelo.getFieldSegundoAutor(),
							modelo.getFieldTercerAutor(), modelo.getFieldGenero(), modelo.getFielTipoObra(),
							modelo.getIdTipoObra(), modelo.getFielAreaTematica(), modelo.getIdAreaTematica(),
							modelo.getFieldeditorial(), modelo.getFieldpais(), modelo.getFieldnumero(),
							modelo.getFieldanio(), modelo.getFieldvolumenes(), modelo.getFieldpaginas(),
							modelo.getFieldidioma(), modelo.getFielformato(), modelo.getIdFormato(), modelo.getFieldFormaAdquisicion(),
							modelo.getFieldFechaAdquisicion(), modelo.getFieldObservaciones(), model);

					limpiarTabla(tableObras);

					llenarTabla(model, entidad);

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
        setTitle(OPCIONES);
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
        lblPrestabook.setFont(new Font(FONT, Font.BOLD, 32));
        return lblPrestabook;
    }

    public JButton btnExit() {
        JButton btnExit = new JButton("X");
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font(FONT, Font.BOLD, 12));
        btnExit.setBackground(new Color(255, 106, 106));
        btnExit.setBounds(1004, 1, 47, 25);
        return btnExit;
    }

    public JButton btnCerrarSesion() {
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font(FONT, Font.BOLD, 11));
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
        lblUsuario.setFont(new Font(FONT, Font.BOLD, 17));
        lblUsuario.setForeground(new Color(255, 255, 255));
        return lblUsuario;
    }

    public JLabel textUsuario() {
        textUsuario = new JLabel("");
        textUsuario.setBackground(new Color(0, 128, 0));
        textUsuario.setBounds(118, 0, 173, 37);
        textUsuario.setFont(new Font(FONT, Font.BOLD, 16));
        textUsuario.setForeground(new Color(255, 255, 255));
        return textUsuario;
    }

    public JButton btnSolicitudes() {
        JButton btnSolicitudes = new JButton("Solicitudes");
        btnSolicitudes.setVerifyInputWhenFocusTarget(false);
        btnSolicitudes.setForeground(new Color(0, 64, 128));
        btnSolicitudes.setFont(new Font(FONT, Font.BOLD, 12));
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
        JLabel lblOpciones = new JLabel(OPCIONES);
        lblOpciones.setForeground(new Color(255, 255, 255));
        lblOpciones.setFont(new Font(FONT, Font.BOLD, 16));
        lblOpciones.setBounds(133, 135, 105, 23);
        return lblOpciones;
    }

    private JButton btnGestionarPrestamo() {
        JButton btnAgregarFormato = new JButton("Gestionar Préstamo");
        btnAgregarFormato.setFocusPainted(false);
        btnAgregarFormato.setBackground(new Color(255, 255, 255));
        btnAgregarFormato.setForeground(new Color(0, 64, 128));
        btnAgregarFormato.setFont(new Font(FONT, Font.BOLD, 12));
        btnAgregarFormato.setBorderPainted(false);
        btnAgregarFormato.setBounds(23, 169, 293, 31);
        return btnAgregarFormato;
    }

    public JButton btnGestionarDevolucion() {
        JButton btnGestionarDevolucion = new JButton("Gestionar devolución de obra");
        btnGestionarDevolucion.setFocusPainted(false);
        btnGestionarDevolucion.setBackground(new Color(255, 255, 255));
        btnGestionarDevolucion.setForeground(new Color(0, 64, 128));
        btnGestionarDevolucion.setFont(new Font(FONT, Font.BOLD, 12));
        btnGestionarDevolucion.setBorderPainted(false);
        btnGestionarDevolucion.setBounds(23, 221, 293, 31);
        return btnGestionarDevolucion;
    }

    public JButton btnVerEjemplares() {
        JButton btnVerEjemplares = new JButton("Ver Ejemplares");
        btnVerEjemplares.setFocusPainted(false);
        btnVerEjemplares.setBackground(new Color(255, 255, 255));
        btnVerEjemplares.setForeground(new Color(0, 64, 128));
        btnVerEjemplares.setFont(new Font(FONT, Font.BOLD, 12));
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
        btnMasBuscadasPorAlumnoDocente.setFont(new Font(FONT, Font.BOLD, 12));
        btnMasBuscadasPorAlumnoDocente.setBorderPainted(false);
        btnMasBuscadasPorAlumnoDocente.setBounds(23, 325, 293, 31);
        return btnMasBuscadasPorAlumnoDocente;
    }

    public JButton btnMasBuscadasPorPublicoGeneral() {
        JButton btnMasBuscadasPorPublicoGeneral = new JButton("Mas buscadas por publico en general");
        btnMasBuscadasPorPublicoGeneral.setFocusPainted(false);
        btnMasBuscadasPorPublicoGeneral.setBackground(new Color(255, 255, 255));
        btnMasBuscadasPorPublicoGeneral.setForeground(new Color(0, 64, 128));
        btnMasBuscadasPorPublicoGeneral.setFont(new Font(FONT, Font.BOLD, 12));
        btnMasBuscadasPorPublicoGeneral.setBorderPainted(false);
        btnMasBuscadasPorPublicoGeneral.setBounds(23, 377, 293, 31);
        return btnMasBuscadasPorPublicoGeneral;
    }

    public JButton btnListarEjemplaresDisponiblesPorArea() {
        JButton btnListarEjemplaresDisponiblesPorArea = new JButton("Listar ejemplares disponibles por area");
        btnListarEjemplaresDisponiblesPorArea.setFocusPainted(false);
        btnListarEjemplaresDisponiblesPorArea.setBackground(new Color(255, 255, 255));
        btnListarEjemplaresDisponiblesPorArea.setForeground(new Color(0, 64, 128));
        btnListarEjemplaresDisponiblesPorArea.setFont(new Font(FONT, Font.BOLD, 12));
        btnListarEjemplaresDisponiblesPorArea.setBorderPainted(false);
        btnListarEjemplaresDisponiblesPorArea.setBounds(23, 429, 293, 31);
        return btnListarEjemplaresDisponiblesPorArea;
    }

    public JButton btnListarEjemplaresDisponiblesPorFecha() {
        JButton btnListarEjemplaresDisponiblesPorFecha = new JButton("Listar ejemplares disponibles por fecha");
        btnListarEjemplaresDisponiblesPorFecha.setFocusPainted(false);
        btnListarEjemplaresDisponiblesPorFecha.setBackground(new Color(255, 255, 255));
        btnListarEjemplaresDisponiblesPorFecha.setForeground(new Color(0, 64, 128));
        btnListarEjemplaresDisponiblesPorFecha.setFont(new Font(FONT, Font.BOLD, 12));
        btnListarEjemplaresDisponiblesPorFecha.setBorderPainted(false);
        btnListarEjemplaresDisponiblesPorFecha.setBounds(23, 481, 293, 31);
        return btnListarEjemplaresDisponiblesPorFecha;
    }

    public JButton btnListarLectoresMultadosPorPeriodo() {
        JButton btnListarLectoresMultadosPorPeriodo = new JButton("Listar lectores multados por periodo");
        btnListarLectoresMultadosPorPeriodo.setFocusPainted(false);
        btnListarLectoresMultadosPorPeriodo.setBackground(new Color(255, 255, 255));
        btnListarLectoresMultadosPorPeriodo.setForeground(new Color(0, 64, 128));
        btnListarLectoresMultadosPorPeriodo.setFont(new Font(FONT, Font.BOLD, 12));
        btnListarLectoresMultadosPorPeriodo.setBorderPainted(false);
        btnListarLectoresMultadosPorPeriodo.setBounds(23, 533, 293, 31);
        return btnListarLectoresMultadosPorPeriodo;
    }

    public JButton btnListarRankingDeMultados() {
        JButton btnListarRankingDeMultados = new JButton("Listar ranking de multados");
        btnListarRankingDeMultados.setFocusPainted(false);
        btnListarRankingDeMultados.setBackground(new Color(255, 255, 255));
        btnListarRankingDeMultados.setForeground(new Color(0, 64, 128));
        btnListarRankingDeMultados.setFont(new Font(FONT, Font.BOLD, 12));
        btnListarRankingDeMultados.setBorderPainted(false);
        btnListarRankingDeMultados.setBounds(23, 585, 293, 31);
        return btnListarRankingDeMultados;
    }

    public JButton btnListarObrasPorEditorial() {
        JButton btnListarObrasPorEditorial = new JButton("Listar obras por editorial ");
        btnListarObrasPorEditorial.setFocusPainted(false);
        btnListarObrasPorEditorial.setBackground(new Color(255, 255, 255));
        btnListarObrasPorEditorial.setForeground(new Color(0, 64, 128));
        btnListarObrasPorEditorial.setFont(new Font(FONT, Font.BOLD, 12));
        btnListarObrasPorEditorial.setBorderPainted(false);
        btnListarObrasPorEditorial.setBounds(23, 637, 293, 31);
        return btnListarObrasPorEditorial;
    }

    public JButton btnAplicarMultaALector() {
        JButton btnAplicarMultaALector = new JButton("Aplicar multa a lector");
        btnAplicarMultaALector.setFocusPainted(false);
        btnAplicarMultaALector.setBackground(new Color(255, 255, 255));
        btnAplicarMultaALector.setForeground(new Color(0, 64, 128));
        btnAplicarMultaALector.setFont(new Font(FONT, Font.BOLD, 12));
        btnAplicarMultaALector.setBorderPainted(false);
        btnAplicarMultaALector.setBounds(23, 689, 293, 31);
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
        lblBienvenidaParte1.setFont(new Font(FONT, Font.BOLD, 21));
        return lblBienvenidaParte1;
    }

    public JLabel lblTiposDeObras() {
        JLabel lblTiposDeObras = new JLabel(TIPOS_DE_OBRAS);
        lblTiposDeObras.setBounds(410, 70, 369, 136);
        lblTiposDeObras.setForeground(Color.GRAY);
        lblTiposDeObras.setFont(new Font(FONT, Font.BOLD, 19));
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
        btnAgregarTipoObra.setFont(new Font(FONT, Font.BOLD, 12));
        btnAgregarTipoObra.setBorderPainted(false);
        btnAgregarTipoObra.setBounds(210, 500, 210, 20);
        return btnAgregarTipoObra;
    }

    private JButton btnActualizarTipoObra() {
        JButton btnActualizarTipoObra = new JButton("Actualizar tipo obra");
        btnActualizarTipoObra.setFocusPainted(false);
        btnActualizarTipoObra.setBackground(new Color(0, 64, 128));
        btnActualizarTipoObra.setForeground(new Color(255, 255, 255));
        btnActualizarTipoObra.setFont(new Font(FONT, Font.BOLD, 12));
        btnActualizarTipoObra.setBorderPainted(false);
        btnActualizarTipoObra.setBounds(600, 500, 210, 20);
        return btnActualizarTipoObra;
    }

    private JButton btnAgregarAreaTematica() {
        JButton btnAgregarTipoObra = new JButton("Agregar area tematica");
        btnAgregarTipoObra.setFocusPainted(false);
        btnAgregarTipoObra.setBackground(new Color(0, 64, 128));
        btnAgregarTipoObra.setForeground(new Color(255, 255, 255));
        btnAgregarTipoObra.setFont(new Font(FONT, Font.BOLD, 12));
        btnAgregarTipoObra.setBorderPainted(false);
        btnAgregarTipoObra.setBounds(210, 500, 210, 20);
        return btnAgregarTipoObra;
    }

    private JButton btnActualizarAreaTematica() {
        JButton btnActualizarTipoObra = new JButton("Actualizar area tematica");
        btnActualizarTipoObra.setFocusPainted(false);
        btnActualizarTipoObra.setBackground(new Color(0, 64, 128));
        btnActualizarTipoObra.setForeground(new Color(255, 255, 255));
        btnActualizarTipoObra.setFont(new Font(FONT, Font.BOLD, 12));
        btnActualizarTipoObra.setBorderPainted(false);
        btnActualizarTipoObra.setBounds(600, 500, 210, 20);
        return btnActualizarTipoObra;
    }

    private JButton btnAgregarFormato() {
        JButton btnAgregarFormato = new JButton("Agregar formato");
        btnAgregarFormato.setFocusPainted(false);
        btnAgregarFormato.setBackground(new Color(0, 64, 128));
        btnAgregarFormato.setForeground(new Color(255, 255, 255));
        btnAgregarFormato.setFont(new Font(FONT, Font.BOLD, 12));
        btnAgregarFormato.setBorderPainted(false);
        btnAgregarFormato.setBounds(210, 500, 210, 20);
        return btnAgregarFormato;
    }

    private JButton btnActualizarFormato() {
        JButton btnActualizarFormato = new JButton("Actualizar formato");
        btnActualizarFormato.setFocusPainted(false);
        btnActualizarFormato.setBackground(new Color(0, 64, 128));
        btnActualizarFormato.setForeground(new Color(255, 255, 255));
        btnActualizarFormato.setFont(new Font(FONT, Font.BOLD, 12));
        btnActualizarFormato.setBorderPainted(false);
        btnActualizarFormato.setBounds(600, 500, 210, 20);
        return btnActualizarFormato;
    }

    private JButton btnAgregarObra() {
        JButton btnAgregarFormato = new JButton("Agregar obra");
        btnAgregarFormato.setFocusPainted(false);
        btnAgregarFormato.setBackground(new Color(0, 64, 128));
        btnAgregarFormato.setForeground(new Color(255, 255, 255));
        btnAgregarFormato.setFont(new Font(FONT, Font.BOLD, 12));
        btnAgregarFormato.setBorderPainted(false);
        btnAgregarFormato.setBounds(50, 500, 210, 20);
        return btnAgregarFormato;
    }

    private JButton btnActualizarObra() {
        JButton btnActualizarFormato = new JButton("Actualizar obra");
        btnActualizarFormato.setFocusPainted(false);
        btnActualizarFormato.setBackground(new Color(0, 64, 128));
        btnActualizarFormato.setForeground(new Color(255, 255, 255));
        btnActualizarFormato.setFont(new Font(FONT, Font.BOLD, 12));
        btnActualizarFormato.setBorderPainted(false);
        btnActualizarFormato.setBounds(400, 500, 210, 20);
        return btnActualizarFormato;
    }

    private JButton btnRefrescar() {
        JButton btnRefrescar = new JButton("Refrescar");
        btnRefrescar.setFocusPainted(false);
        btnRefrescar.setBackground(new Color(0, 64, 128));
        btnRefrescar.setForeground(new Color(255, 255, 255));
        btnRefrescar.setFont(new Font(FONT, Font.BOLD, 12));
        btnRefrescar.setBorderPainted(false);
        btnRefrescar.setBounds(700, 500, 210, 20);
        return btnRefrescar;
    }

    public JLabel lblBienvenidaParte1() {
        JLabel lblBienvenidaParte1 = new JLabel("¡Bienvenido al sistema de gestión de préstamos de libros más");
        lblBienvenidaParte1.setBounds(161, 0, 775, 136);
        lblBienvenidaParte1.setForeground(Color.GRAY);
        lblBienvenidaParte1.setFont(new Font(FONT, Font.BOLD, 21));
        return lblBienvenidaParte1;
    }

    public JLabel lblBienvenidaParte2() {
        JLabel lblBienvenidaParte2 = new JLabel("grande del mundo!");
        lblBienvenidaParte2.setBounds(397, 64, 369, 136);
        lblBienvenidaParte2.setForeground(Color.GRAY);
        lblBienvenidaParte2.setFont(new Font(FONT, Font.BOLD, 21));
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
        lblTituloEntidades.setFont(new Font(FONT, Font.BOLD, 19));
        return lblTituloEntidades;
    }

	/**
	 * Llena la tabla con todas las filas cargadas en la base de datos
	 */

	public void llenarTabla(DefaultTableModel model, String tipoEntidad) {
		Integer i = 0;
		switch (tipoEntidad) {
		case "Tipo obra":
			ITipoObraDAO tipoObraDAO = DaoFactory.getTipoObraDAO();
			java.util.List<TipoObra> tiposObra = tipoObraDAO.findAll();
			for (TipoObra tipo : tiposObra) {
				List<Object> fila = new LinkedList<>();
				fila.add(++i);
				fila.add(tipo.getNombre());
				model.addRow(new Vector<>(fila));
			}
			break;
		case "Area tematica":
			IAreaTematicaDAO areaTematicaDAO = DaoFactory.getAreaTematicaDAO();
			java.util.List<AreaTematica> areasTematicas = areaTematicaDAO.findAll();
			for (AreaTematica area : areasTematicas) {
				List<Object> fila = new LinkedList<>();
				fila.add(++i);
				fila.add(area.getNombre());
				model.addRow(new Vector<>(fila));
			}
			break;
		case "Formato":
			IFormatoDAO formatoDAO = DaoFactory.getFormatoDAO();
			java.util.List<Formato> formatos = formatoDAO.findAll();
			for (Formato formato : formatos) {
				List<Object> fila = new LinkedList<>();
				fila.add(++i);
				fila.add(formato.getNombre());
				model.addRow(new Vector<>(fila));
			}
			break;
		case "Obra":
			IObraDAO obraDAO = DaoFactory.getObraDAO();
			java.util.List<Obra> obras = obraDAO.findAll();
			for (Obra obra : obras) {
				List<Object> fila = new LinkedList<>();
				fila.add(obra.getIsbn());
				fila.add(obra.getTitulo());
				fila.add(obra.getSubtitulo());
				fila.add(obra.getPrimerAutor());
				fila.add(obra.getSegundoAutor());
				fila.add(obra.getTercerAutor());
				fila.add(obra.getGenero());
				fila.add(obra.getTipo().getNombre());
				fila.add(obra.getArea().getNombre());
				List<Edicion> ediciones = obra.getEdicion();
				String concatenarFormatos = "";
				for (Edicion edicion : ediciones) {
					List<Formato> formatos1 = edicion.getFormatos();
					for (Formato formato : formatos1) {
						concatenarFormatos += formato.getNombre() + ", ";
					}
				}
				fila.add(concatenarFormatos);
				fila.add(null);
				fila.add(null);
				fila.add(null);
				fila.add(null);
				fila.add(obra.getTipo().getId());
				fila.add(obra.getArea().getId());
				model.addRow(new Vector<>(fila));
			}
			break;
		default:
		}

	}

	/**
     * Actualiza en la base de datos la fila agregada en la tabla
     * 
     */

	private void actualizarBaseDeDatos(String nombre, String tipoEntidad) {

        switch (tipoEntidad) {
            case TIPO_OBRA:
                ITipoObraDAO tipoObraDAO = DaoFactory.getTipoObraDAO();
                TipoObra tipoObra = new TipoObra();
                tipoObra.setNombre(nombre);
                tipoObraDAO.insert(tipoObra);
                break;
            case AREA_TEMATICA:
                IAreaTematicaDAO areaTematicaDAO = DaoFactory.getAreaTematicaDAO();
                AreaTematica areaTematica = new AreaTematica();
                areaTematica.setNombre(nombre);
                areaTematicaDAO.insert(areaTematica);
                break;
            case FORMATO:
                IFormatoDAO formatoDAO = DaoFactory.getFormatoDAO();
                Formato formato = new Formato();
                formato.setNombre(nombre);
                formatoDAO.insert(formato);
                break;
            default:
        }

    }
	

	private void actualizarObraEnBaseDeDatos(String isbn, String titulo, String subtitulo, String primerAutor,
			String segundoAutor, String tercerAutor, String genero, String tipoObra, Integer idTipoObra,
			String areaTematica, Integer idAreaTematica, String editorial, String pais, String numero, String anio,
			String volumenes, String paginas, String idioma, String formato,Integer idFormato, String formaAdquisicion,
			String fechaAdquisicion, String observaciones, DefaultTableModel model) {

		
		Obra obra = new Obra();
		obra.setIsbn(isbn);
		obra.setTitulo(titulo);
		obra.setSubtitulo(subtitulo);
		obra.setPrimerAutor(primerAutor);
		obra.setSegundoAutor(segundoAutor);
		obra.setTercerAutor(tercerAutor);
		obra.setGenero(genero);
		obra.setTipo(new TipoObra(idTipoObra, tipoObra));
		obra.setArea(new AreaTematica(idAreaTematica, areaTematica));
		
		List<Edicion> ediciones = new LinkedList<>();
		Edicion edicion1 = new Edicion();
		edicion1.setId((long) 1);
		edicion1.setAnio(Integer.parseInt(anio));
		edicion1.setEditorial(editorial);
		edicion1.setIdioma(idioma);
		edicion1.setNumero(Integer.parseInt(numero));
		edicion1.setPaginas(Integer.parseInt(paginas));
		edicion1.setPais(pais);
		edicion1.setVolumenes(Long.parseLong(volumenes));
		
		IEdicionDAO e = DaoFactory.getEdicionDAO();
		e.insert(edicion1);
		
		edicion1.setId(edicion1.getId());
		
		List<Formato> formatos = new LinkedList<>();
		Formato formato1 = new Formato();
		formato1.setId(idFormato);
		formato1.setNombre(formato);
		formatos.add(formato1);
		edicion1.setFormatos(formatos);
		
		ediciones.add(edicion1);
		System.out.println(ediciones);
		obra.setEdicion(ediciones);
		
		List<Ejemplar> ejemplares = new LinkedList<>();
		Ejemplar ejemplar1 = new Ejemplar();
		ejemplar1.setIsbn(isbn);
		ejemplar1.setTitulo(titulo);
		ejemplar1.setSubtitulo(subtitulo);
		ejemplar1.setPrimerAutor(primerAutor);
		ejemplar1.setSegundoAutor(segundoAutor);
		ejemplar1.setTercerAutor(tercerAutor);
		ejemplar1.setGenero(genero);
		ejemplar1.setTipo(new TipoObra(idTipoObra, tipoObra));
		ejemplar1.setArea(new AreaTematica(idAreaTematica, areaTematica));
		ejemplar1.setId((long) 1);
		ejemplar1.setFormaAdquisicion(formaAdquisicion);
		ejemplar1.setFechaAdquisicion(fechaAdquisicion);
		ejemplar1.setObservaciones(observaciones);
		CodigoIdentificatorio codigoIden = new CodigoIdentificatorio();
		codigoIden.setId((long) 1);
		codigoIden.setEstante(2);
		codigoIden.setEstanteria(4);
		codigoIden.setPasillo(7);
		ejemplar1.setCodigoIdentificatorio(codigoIden);
		ejemplares.add(ejemplar1);
		
		IEjemplarDAO ej = DaoFactory.getEjemplarDAO();
		ej.insert(ejemplar1);
		
		ejemplar1.setId(ejemplar1.getId());
		
		obra.setEjemplares(ejemplares);
		
		
		IObraDAO o = DaoFactory.getObraDAO();
		o.insert(obra);
		
		
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
}
