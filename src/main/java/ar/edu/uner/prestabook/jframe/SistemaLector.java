package ar.edu.uner.prestabook.jframe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ar.edu.uner.prestabook.jframe.common.Components;
import ar.edu.uner.prestabook.jframe.panels.AbstractPanel;
import ar.edu.uner.prestabook.jframe.panels.PanelBienvenida;
import ar.edu.uner.prestabook.jframe.panels.PanelObrasLector;

public class SistemaLector extends JFrame {

    private static final long serialVersionUID = 1L;

    private transient AbstractPanel panelObra = new PanelObrasLector();
    private transient AbstractPanel panelBienvenida = new PanelBienvenida();
    private JPanel contentPane = Components.contentPane();

    private static SistemaLector instance = new SistemaLector();

    /**
     * Initializes the system interface
     * 
     * @return the instance of SistemaLector
     */
    public static SistemaLector init() {
        return instance;
    }

    private SistemaLector() {

        window();
        setContentPane(contentPane);
        JPanel panelPrestabook = Components.panelPrestabook();
        contentPane.add(panelPrestabook);

        JLabel lblIconCerrarSesion = Components.lblIconCerrarSesion();
        panelPrestabook.add(lblIconCerrarSesion);
        Components.jLabelImage(lblIconCerrarSesion);
        this.repaint();

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
        panelOpciones.add(Components.getTextUsuario());
        panelOpciones.add(Components.panelSeparador());
        panelOpciones.add(Components.lblOpciones());

        JButton btnConsultarObras = Components.btnLeftMenu("Consultar Obras", 169);
        panelOpciones.add(btnConsultarObras);

        contentPane.add(panelBienvenida.init());

        btnConsultarObras.addActionListener(e -> {
            panelBienvenida.hide();
            contentPane.add(panelObra.init());
        });

        /**
         * Method created to log out and return to the "IniciarSesion" window
         */

        btnCerrarSesion.addActionListener(e -> {
            IniciarSesion login = new IniciarSesion();
            login.setVisible(true);
            SistemaLector.this.dispose();
        });

        /**
         * Created method to close window
         */

        btnExit.addActionListener(e -> System.exit(0));
    }

    /**
     * Create components
     */

    private void window() {
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Prestabook");
        setBounds(100, 100, 1390, 811);
        setLocationRelativeTo(null);
    }

}
