package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ar.edu.uner.prestabook.jframe.panels.Utils;
import ar.edu.uner.prestabook.jframe.panels.lectores.PanelObra;

public class SistemaLector extends JFrame {

    private static final long serialVersionUID = 1L;


    /**
     * Create the frame.
     */

    public SistemaLector() {

        /**
         * Create components
         */

        ventana();

        JPanel contentPane = contentPane();

        JPanel panelPrestabook = Utils.panelPrestabook();
        contentPane.add(panelPrestabook);

        JLabel lblIconCerrarSesion = Utils.lblIconCerrarSesion();
        panelPrestabook.add(lblIconCerrarSesion);
        Utils.jLabelImage(lblIconCerrarSesion);
        this.repaint();

        panelPrestabook.add(Utils.lblPrestabook());

        JButton btnExit = Utils.btnExit();
        panelPrestabook.add(btnExit);

        JButton btnCerrarSesion = Utils.btnCerrarSesion();
        panelPrestabook.add(btnCerrarSesion);

        JPanel panelOpciones = Utils.panelOpciones();
        contentPane.add(panelOpciones);

        JButton btnSolicitudes = Utils.btnSolicitudes();
        panelOpciones.add(btnSolicitudes);

        panelOpciones.add(Utils.lblUsuario());
        panelOpciones.add(Utils.getTextUsuario());
        panelOpciones.add(Utils.panelSeparador());
        panelOpciones.add(Utils.lblOpciones());

        JButton btnConsultarObras = btnConsultarObras();
        panelOpciones.add(btnConsultarObras);

        JPanel panelBienvenida = Utils.panelBienvenida();
        contentPane.add(panelBienvenida);

        JLabel lblBienvenidaParte1 = Utils.lblBienvenidaParte1();
        panelBienvenida.add(lblBienvenidaParte1);

        JLabel lblIconLibreria = Utils.lblIconLibreria();
        panelBienvenida.add(lblIconLibreria);

        JLabel lblBienvenidaParte2 = Utils.lblBienvenidaParte2();
        panelBienvenida.add(lblBienvenidaParte2);

        btnConsultarObras.addActionListener(e -> {
            panelBienvenida.setVisible(false);
            PanelObra panelObra = new PanelObra();
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
    
    private void ventana() {
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Prestabook");
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

    private JButton btnConsultarObras() {
        JButton btnConsultarObras = new JButton("Consultar obras");
        btnConsultarObras.setFocusPainted(false);
        btnConsultarObras.setBackground(new Color(255, 255, 255));
        btnConsultarObras.setVerifyInputWhenFocusTarget(false);
        btnConsultarObras.setBorderPainted(false);
        btnConsultarObras.setBorder(null);
        btnConsultarObras.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnConsultarObras.setForeground(new Color(0, 64, 128));
        btnConsultarObras.setBounds(22, 169, 189, 31);
        return btnConsultarObras;
    }
}
