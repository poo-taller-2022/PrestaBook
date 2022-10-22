package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
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

import ar.edu.uner.prestabook.jframe.common.Components;
import ar.edu.uner.prestabook.jframe.panels.AbstractPanel;
import ar.edu.uner.prestabook.jframe.panels.PanelAreaTematica;
import ar.edu.uner.prestabook.jframe.panels.PanelBienvenida;
import ar.edu.uner.prestabook.jframe.panels.PanelColecciones;
import ar.edu.uner.prestabook.jframe.panels.PanelEdiciones;
import ar.edu.uner.prestabook.jframe.panels.PanelEjemplares;
import ar.edu.uner.prestabook.jframe.panels.PanelFormatos;
import ar.edu.uner.prestabook.jframe.panels.PanelLectores;
import ar.edu.uner.prestabook.jframe.panels.PanelMultas;
import ar.edu.uner.prestabook.jframe.panels.PanelObrasFuncionario;
import ar.edu.uner.prestabook.jframe.panels.PanelPrestamos;
import ar.edu.uner.prestabook.jframe.panels.PanelTiposObra;
import lombok.Getter;

public class SistemaFuncionario extends JFrame {

    private static final long serialVersionUID = 1L;
    @Getter
    private JLabel textUsuario;
    private transient AbstractPanel panelBienvenida = new PanelBienvenida();
    private transient AbstractPanel panelAreaTematica = new PanelAreaTematica();
    private transient AbstractPanel panelColeccion = new PanelColecciones();
    private transient AbstractPanel panelEdiciones = new PanelEdiciones();
    private transient AbstractPanel panelEjemplares = new PanelEjemplares();
    private transient AbstractPanel panelFormatos = new PanelFormatos();
    private transient AbstractPanel panelLectores = new PanelLectores();
    private transient AbstractPanel panelMultas = new PanelMultas();
    private transient AbstractPanel panelObras = new PanelObrasFuncionario();
    private transient AbstractPanel panelPrestamos = new PanelPrestamos();
    private transient AbstractPanel panelTiposObra = new PanelTiposObra();
    private transient List<AbstractPanel> panels = List.of(panelAreaTematica, panelBienvenida, panelColeccion,
            panelEdiciones, panelEjemplares, panelFormatos, panelLectores, panelObras, panelTiposObra, panelMultas,
            panelPrestamos);
    private JPanel contentPane = Components.contentPane();
    private static SistemaFuncionario instance = new SistemaFuncionario();

    /**
     * Initializes the system interface
     * 
     * @return the instance of SistemaFuncionario
     */
    public static SistemaFuncionario init() {
        return instance;
    }

    private SistemaFuncionario() {

        window();
        setContentPane(contentPane);
        textUsuario = new JLabel("");
        contentPane.add(panelBienvenida.init());

        JPanel panelPrestabook = Components.panelPrestabook();
        contentPane.add(panelPrestabook);

        JLabel lblIconCerrarSesion = Components.lblIconCerrarSesion();
        panelPrestabook.add(lblIconCerrarSesion);
        jLabelImage(lblIconCerrarSesion);

        panelPrestabook.add(Components.lblPrestabook());

        JButton btnExit = Components.btnExit();
        panelPrestabook.add(btnExit);

        JButton btnCerrarSesion = Components.btnCerrarSesion();
        panelPrestabook.add(btnCerrarSesion);

        JPanel panelOpciones = Components.panelOpciones();
        contentPane.add(panelOpciones);

        JButton btnSolicitudes = Components.btnSolicitudes();
        panelOpciones.add(btnSolicitudes);

        panelOpciones.add(Components.lblUsuario());
        panelOpciones.add(Components.textUsuario());
        panelOpciones.add(Components.panelSeparador());
        panelOpciones.add(Components.lblOpciones());

        JButton btnGestionarPrestamo = Components.btnLeftMenu("Gestionar Préstamo", 169);
        btnGestionarPrestamo.addActionListener(e -> {
            Prestamos prestamos = new Prestamos();
            prestamos.setVisible(true);
        });
        panelOpciones.add(btnGestionarPrestamo);

        JButton btnGestionarDevolucion = Components.btnLeftMenu("Gestionar devolución de obra", 221);
        btnGestionarDevolucion.addActionListener(e -> {
            Devoluciones devoluciones = new Devoluciones();
            devoluciones.setVisible(true);
        });
        panelOpciones.add(btnGestionarDevolucion);

        JButton btnVerEjemplares = Components.btnLeftMenu("Ver Ejemplares", 273);
        btnVerEjemplares.addActionListener(e -> {
            Ejemplares ejemplares = new Ejemplares();
            ejemplares.setVisible(true);
        });

        panelOpciones.add(btnVerEjemplares);

        JButton btnLeftMenuLectores = Components.btnLeftMenu("Ver Lectores", 325);
        panelOpciones.add(btnLeftMenuLectores);

        JButton btnListarMultas = Components.btnLeftMenu("Ver Multas", 377);
        panelOpciones.add(btnListarMultas);

        JButton btnListarPrestamos = Components.btnLeftMenu("Ver Préstamos", 429);
        panelOpciones.add(btnListarPrestamos);

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
            hidePanels();
            contentPane.add(panelTiposObra.init());

        });

        menuItemAreaTematica.addActionListener(e -> {
            hidePanels();
            contentPane.add(panelAreaTematica.init());
        });

        menuItemFormato.addActionListener(e -> {
            hidePanels();
            contentPane.add(panelFormatos.init());

        });

        menuItemObra.addActionListener(e -> {
            hidePanels();
            contentPane.add(panelObras.init());
        });

        menuItemEjemplar.addActionListener(e -> {
            hidePanels();
            contentPane.add(panelEjemplares.init());
        });

        menuItemEdicion.addActionListener(e -> {
            hidePanels();
            contentPane.add(panelEdiciones.init());
        });

        menuItemColeccion.addActionListener(e -> {
            hidePanels();
            contentPane.add(panelColeccion.init());

        });

        btnLeftMenuLectores.addActionListener(e -> {
            hidePanels();
            contentPane.add(panelLectores.init());

        });

        btnListarMultas.addActionListener(e -> {
            hidePanels();
            contentPane.add(panelMultas.init());

        });

        btnListarPrestamos.addActionListener(e -> {
            hidePanels();
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

    private void window() {
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(Constants.OPCIONES);
        setBounds(100, 100, 1390, 811);
        setLocationRelativeTo(null);
    }

    private void jLabelImage(JLabel lblIconCerrarSesion) {
        ImageIcon image = new ImageIcon(new File("src/main/resources/Vector.png").getAbsolutePath());
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(lblIconCerrarSesion.getWidth(),
                lblIconCerrarSesion.getHeight(), Image.SCALE_DEFAULT));
        lblIconCerrarSesion.setIcon(icon);
        this.repaint();
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
    private void hidePanels() {
        for (AbstractPanel panel : panels) {
            panel.hide();
        }
    }

}
