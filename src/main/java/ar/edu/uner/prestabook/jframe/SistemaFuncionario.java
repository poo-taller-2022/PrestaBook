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
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ar.edu.uner.prestabook.jframe.panels.funcionarios.PanelAreaTematica;
import ar.edu.uner.prestabook.jframe.panels.funcionarios.PanelBienvenida;
import ar.edu.uner.prestabook.jframe.panels.funcionarios.PanelColecciones;
import ar.edu.uner.prestabook.jframe.panels.funcionarios.PanelEdiciones;
import ar.edu.uner.prestabook.jframe.panels.funcionarios.PanelEjemplares;
import ar.edu.uner.prestabook.jframe.panels.funcionarios.PanelFormatos;
import ar.edu.uner.prestabook.jframe.panels.funcionarios.PanelLectores;
import ar.edu.uner.prestabook.jframe.panels.funcionarios.PanelMultas;
import ar.edu.uner.prestabook.jframe.panels.funcionarios.PanelObras;
import ar.edu.uner.prestabook.jframe.panels.funcionarios.PanelPrestamos;
import ar.edu.uner.prestabook.jframe.panels.funcionarios.PanelTiposObra;
import ar.edu.uner.prestabook.jframe.panels.funcionarios.PrestabookPanel;
import lombok.Getter;

public class SistemaFuncionario extends JFrame {

    private static final long serialVersionUID = 1L;
    @Getter
    private JLabel textUsuario;
    private transient PrestabookPanel panelBienvenida = new PanelBienvenida();
    private transient PrestabookPanel panelAreaTematica = new PanelAreaTematica();
    private transient PrestabookPanel panelColeccion = new PanelColecciones();
    private transient PrestabookPanel panelEdiciones = new PanelEdiciones();
    private transient PrestabookPanel panelEjemplares = new PanelEjemplares();
    private transient PrestabookPanel panelFormatos = new PanelFormatos();
    private transient PrestabookPanel panelLectores = new PanelLectores();
    private transient PrestabookPanel panelMultas = new PanelMultas();
    private transient PrestabookPanel panelObras = new PanelObras();
    private transient PrestabookPanel panelPrestamos = new PanelPrestamos();
    private transient PrestabookPanel panelTiposObra = new PanelTiposObra();
    private transient List<PrestabookPanel> panels = List.of(panelAreaTematica, panelBienvenida, panelColeccion,
            panelEdiciones, panelEjemplares, panelFormatos, panelLectores, panelObras, panelTiposObra, panelMultas,
            panelPrestamos);

    /**
     * Create the frame.
     */

    public SistemaFuncionario() {

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

        JButton btnListarMultas = btnListarMultas();
        panelOpciones.add(btnListarMultas);

        JButton btnListarPrestamos = btnListarPrestamos();
        panelOpciones.add(btnListarPrestamos);

        JButton btnAplicarMultaALector = btnAplicarMultaALector();
        panelOpciones.add(btnAplicarMultaALector);

        contentPane.add(panelBienvenida.init());

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

        menuItemTipoObra.addActionListener(e -> {
            ocultarPaneles();
            contentPane.add(panelTiposObra.init());

        });

        menuItemAreaTematica.addActionListener(e -> {
            ocultarPaneles();
            contentPane.add(panelAreaTematica.init());
        });

        menuItemFormato.addActionListener(e -> {
            ocultarPaneles();
            contentPane.add(panelFormatos.init());

        });

        menuItemObra.addActionListener(e -> {
            ocultarPaneles();
            contentPane.add(panelObras.init());
        });

        menuItemEjemplar.addActionListener(e -> {
            ocultarPaneles();
            contentPane.add(panelEjemplares.init());
        });

        menuItemEdicion.addActionListener(e -> {
            ocultarPaneles();
            contentPane.add(panelEdiciones.init());
        });

        menuItemColeccion.addActionListener(e -> {
            ocultarPaneles();
            contentPane.add(panelColeccion.init());

        });

        btnListarLectores.addActionListener(e -> {
            ocultarPaneles();
            contentPane.add(panelLectores.init());

        });

        btnListarMultas.addActionListener(e -> {
            ocultarPaneles();
            contentPane.add(panelMultas.init());

        });

        btnListarPrestamos.addActionListener(e -> {
            ocultarPaneles();
            contentPane.add(panelPrestamos.init());
        });

        btnCerrarSesion.addActionListener(e -> {
            IniciarSesion login = new IniciarSesion();
            login.setVisible(true);
            SistemaFuncionario.this.dispose();
        });

        btnExit.addActionListener(e -> System.exit(0));

    }

    /**
     * Creates the window
     */

    private void ventana() {
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(Constants.OPCIONES);
        setBounds(100, 100, 1390, 811);
        setLocationRelativeTo(null);
    }

    private JPanel contentPane() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        return contentPane;
    }

    private JPanel panelPrestabook() {
        JPanel panelPrestabook = new JPanel();
        panelPrestabook.setBounds(339, 0, 1061, 103);
        panelPrestabook.setBorder(null);
        panelPrestabook.setBackground(new Color(0, 64, 128));
        panelPrestabook.setLayout(null);
        return panelPrestabook;
    }

    private JLabel lblIconCerrarSesion() {
        JLabel lblIconCerrarSesion = new JLabel("");
        lblIconCerrarSesion.setBounds(817, 5, 19, 16);
        return lblIconCerrarSesion;
    }

    private void jLabelImage(JLabel lblIconCerrarSesion) {
        ImageIcon image = new ImageIcon(new File("src/main/resources/Vector.png").getAbsolutePath());
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(lblIconCerrarSesion.getWidth(),
                lblIconCerrarSesion.getHeight(), Image.SCALE_DEFAULT));
        lblIconCerrarSesion.setIcon(icon);
        this.repaint();
    }

    private JLabel lblPrestabook() {
        JLabel lblPrestabook = new JLabel("PrestaBook");
        lblPrestabook.setBounds(399, 30, 267, 42);
        lblPrestabook.setForeground(Color.WHITE);
        lblPrestabook.setFont(new Font(Constants.FONT, Font.BOLD, 32));
        return lblPrestabook;
    }

    private JButton btnExit() {
        JButton btnExit = new JButton("X");
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnExit.setBackground(new Color(255, 106, 106));
        btnExit.setBounds(1004, 1, 47, 25);
        return btnExit;
    }

    private JButton btnCerrarSesion() {
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

    private JPanel panelOpciones() {
        JPanel panelOpciones = new JPanel();
        panelOpciones.setBounds(0, 0, 341, 811);
        panelOpciones.setBackground(new Color(0, 45, 89));
        panelOpciones.setLayout(null);
        return panelOpciones;
    }

    private JLabel lblUsuario() {
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(27, 0, 122, 37);
        lblUsuario.setFont(new Font(Constants.FONT, Font.BOLD, 17));
        lblUsuario.setForeground(new Color(255, 255, 255));
        return lblUsuario;
    }

    private JLabel textUsuario() {
        textUsuario.setBackground(new Color(0, 128, 0));
        textUsuario.setBounds(118, 0, 173, 37);
        textUsuario.setFont(new Font(Constants.FONT, Font.BOLD, 16));
        textUsuario.setForeground(new Color(255, 255, 255));
        return textUsuario;
    }

    private JButton btnSolicitudes() {
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

    private JPanel panelSeparador() {
        JPanel panelSeparador = new JPanel();
        panelSeparador.setBounds(23, 121, 292, 3);
        return panelSeparador;
    }

    private JLabel lblOpciones() {
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

    private JButton btnGestionarDevolucion() {
        JButton btnGestionarDevolucion = new JButton("Gestionar devolución de obra");
        btnGestionarDevolucion.setFocusPainted(false);
        btnGestionarDevolucion.setBackground(new Color(255, 255, 255));
        btnGestionarDevolucion.setForeground(new Color(0, 64, 128));
        btnGestionarDevolucion.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnGestionarDevolucion.setBorderPainted(false);
        btnGestionarDevolucion.setBounds(23, 221, 293, 31);
        return btnGestionarDevolucion;
    }

    private JButton btnVerEjemplares() {
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

    private JButton btnMasBuscadasPorAlumnoDocente() {
        JButton btnMasBuscadasPorAlumnoDocente = new JButton("Mas buscadas por alumnos y docentes");
        btnMasBuscadasPorAlumnoDocente.setFocusPainted(false);
        btnMasBuscadasPorAlumnoDocente.setBackground(new Color(255, 255, 255));
        btnMasBuscadasPorAlumnoDocente.setForeground(new Color(0, 64, 128));
        btnMasBuscadasPorAlumnoDocente.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnMasBuscadasPorAlumnoDocente.setBorderPainted(false);
        btnMasBuscadasPorAlumnoDocente.setBounds(23, 325, 293, 31);
        return btnMasBuscadasPorAlumnoDocente;
    }

    private JButton btnMasBuscadasPorPublicoGeneral() {
        JButton btnMasBuscadasPorPublicoGeneral = new JButton("Mas buscadas por publico en general");
        btnMasBuscadasPorPublicoGeneral.setFocusPainted(false);
        btnMasBuscadasPorPublicoGeneral.setBackground(new Color(255, 255, 255));
        btnMasBuscadasPorPublicoGeneral.setForeground(new Color(0, 64, 128));
        btnMasBuscadasPorPublicoGeneral.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnMasBuscadasPorPublicoGeneral.setBorderPainted(false);
        btnMasBuscadasPorPublicoGeneral.setBounds(23, 377, 293, 31);
        return btnMasBuscadasPorPublicoGeneral;
    }

    private JButton btnListarMultas() {
        JButton btnListarMultas = new JButton("Ver Multas");
        btnListarMultas.setFocusPainted(false);
        btnListarMultas.setBackground(new Color(255, 255, 255));
        btnListarMultas.setForeground(new Color(0, 64, 128));
        btnListarMultas.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnListarMultas.setBorderPainted(false);
        btnListarMultas.setBounds(23, 585, 293, 31);
        return btnListarMultas;
    }

    private JButton btnListarEjemplaresDisponiblesPorArea() {
        JButton btnListarEjemplaresDisponiblesPorArea = new JButton("Listar ejemplares disponibles por area");
        btnListarEjemplaresDisponiblesPorArea.setFocusPainted(false);
        btnListarEjemplaresDisponiblesPorArea.setBackground(new Color(255, 255, 255));
        btnListarEjemplaresDisponiblesPorArea.setForeground(new Color(0, 64, 128));
        btnListarEjemplaresDisponiblesPorArea.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnListarEjemplaresDisponiblesPorArea.setBorderPainted(false);
        btnListarEjemplaresDisponiblesPorArea.setBounds(23, 429, 293, 31);
        return btnListarEjemplaresDisponiblesPorArea;
    }

    private JButton btnListarEjemplaresDisponiblesPorFecha() {
        JButton btnListarEjemplaresDisponiblesPorFecha = new JButton("Listar ejemplares disponibles por fecha");
        btnListarEjemplaresDisponiblesPorFecha.setFocusPainted(false);
        btnListarEjemplaresDisponiblesPorFecha.setBackground(new Color(255, 255, 255));
        btnListarEjemplaresDisponiblesPorFecha.setForeground(new Color(0, 64, 128));
        btnListarEjemplaresDisponiblesPorFecha.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnListarEjemplaresDisponiblesPorFecha.setBorderPainted(false);
        btnListarEjemplaresDisponiblesPorFecha.setBounds(23, 481, 293, 31);
        return btnListarEjemplaresDisponiblesPorFecha;
    }

    private JButton btnListarLectores() {
        JButton btnListarLectoresMultadosPorPeriodo = new JButton("Ver Lectores");
        btnListarLectoresMultadosPorPeriodo.setFocusPainted(false);
        btnListarLectoresMultadosPorPeriodo.setBackground(new Color(255, 255, 255));
        btnListarLectoresMultadosPorPeriodo.setForeground(new Color(0, 64, 128));
        btnListarLectoresMultadosPorPeriodo.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnListarLectoresMultadosPorPeriodo.setBorderPainted(false);
        btnListarLectoresMultadosPorPeriodo.setBounds(23, 533, 293, 31);
        return btnListarLectoresMultadosPorPeriodo;
    }

    private JButton btnListarPrestamos() {
        JButton btnListarObrasPorEditorial = new JButton("Ver préstamos");
        btnListarObrasPorEditorial.setFocusPainted(false);
        btnListarObrasPorEditorial.setBackground(new Color(255, 255, 255));
        btnListarObrasPorEditorial.setForeground(new Color(0, 64, 128));
        btnListarObrasPorEditorial.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnListarObrasPorEditorial.setBorderPainted(false);
        btnListarObrasPorEditorial.setBounds(23, 637, 293, 31);
        return btnListarObrasPorEditorial;
    }

    private JButton btnAplicarMultaALector() {
        JButton btnAplicarMultaALector = new JButton("Aplicar multa a lector");
        btnAplicarMultaALector.setFocusPainted(false);
        btnAplicarMultaALector.setBackground(new Color(255, 255, 255));
        btnAplicarMultaALector.setForeground(new Color(0, 64, 128));
        btnAplicarMultaALector.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnAplicarMultaALector.setBorderPainted(false);
        btnAplicarMultaALector.setBounds(23, 689, 293, 31);
        return btnAplicarMultaALector;
    }

    /**
     * Menu bar Administrar
     */

    private JMenuBar menuBarAdministrar() {
        JMenuBar menuBarAdministrar = new JMenuBar();
        menuBarAdministrar.setBounds(10, 6, 75, 22);
        menuBarAdministrar.setBackground(new Color(255, 255, 255));
        return menuBarAdministrar;
    }

    private JMenu menuAdministrar() {
        JMenu menuAdministrar = new JMenu("Administrar");
        menuAdministrar.setBackground(new Color(255, 255, 255));
        menuAdministrar.setOpaque(true);
        return menuAdministrar;
    }

    /**
     * Hides all panels
     */
    private void ocultarPaneles() {
        for (PrestabookPanel panel : panels) {
            panel.hide();
        }
    }

}
