package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.persistence.IUsuarioDAO;

public class IniciarSesion extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Launch the application.
     */

    public static void main() {
        EventQueue.invokeLater(() -> {
            try {
                IniciarSesion frame = new IniciarSesion();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */

    public IniciarSesion() {

        /**
         * Create components
         */

        IUsuarioDAO usuariosDAO = DaoFactory.getUsuarioDAO();
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 648, 486);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelPrestabook = new JPanel();
        panelPrestabook.setBackground(new Color(0, 64, 128));
        panelPrestabook.setBounds(0, 0, 648, 100);
        contentPane.add(panelPrestabook);
        panelPrestabook.setLayout(null);

        JLabel lblPrestaBook = new JLabel("PrestaBook");
        lblPrestaBook.setForeground(new Color(255, 255, 255));
        lblPrestaBook.setFont(new Font("Verdana", Font.BOLD, 32));
        lblPrestaBook.setBounds(218, 34, 264, 42);
        panelPrestabook.add(lblPrestaBook);

        JButton btnExit = new JButton("X");
        btnExit.setBorderPainted(false);
        btnExit.setBorder(null);
        btnExit.setFocusPainted(false);
        btnExit.setFont(new Font("Roboto Black", Font.PLAIN, 12));
        btnExit.setForeground(new Color(255, 255, 255));
        btnExit.setBackground(new Color(255, 106, 106));
        btnExit.setBounds(601, 0, 47, 25);
        panelPrestabook.add(btnExit);

        JLabel lblContrasenia = new JLabel("Contraseña");
        lblContrasenia.setBounds(239, 314, 66, 14);
        contentPane.add(lblContrasenia);

        JLabel lblIniciarSesion = new JLabel("Iniciar sesión");
        lblIniciarSesion.setFont(new Font("Verdana", Font.BOLD, 17));
        lblIniciarSesion.setBounds(258, 130, 161, 23);
        contentPane.add(lblIniciarSesion);

        JTextField cajaCorreo = new JTextField();
        cajaCorreo.setBackground(new Color(255, 255, 255));
        cajaCorreo.setForeground(new Color(128, 128, 128));
        cajaCorreo.setColumns(10);
        cajaCorreo.setBounds(239, 254, 180, 30);
        contentPane.add(cajaCorreo);

        JPasswordField cajaContrasenia = new JPasswordField();
        cajaContrasenia.setToolTipText("");
        cajaContrasenia.setBackground(new Color(255, 255, 255));
        cajaContrasenia.setBounds(239, 329, 180, 30);
        contentPane.add(cajaContrasenia);

        JLabel lblCorreo = new JLabel("Correo");
        lblCorreo.setBounds(239, 238, 59, 14);
        contentPane.add(lblCorreo);

        JPanel panelIngresarComo = new JPanel();
        panelIngresarComo.setLayout(null);
        panelIngresarComo.setBorder(new LineBorder(new Color(128, 128, 128)));
        panelIngresarComo.setBounds(39, 178, 560, 38);
        contentPane.add(panelIngresarComo);

        ButtonGroup buttonGroupTipo = new ButtonGroup();

        JRadioButton btnRadioFuncionario = new JRadioButton("Funcionario", false);
        buttonGroupTipo.add(btnRadioFuncionario);
        btnRadioFuncionario.setBounds(19, 7, 109, 23);
        panelIngresarComo.add(btnRadioFuncionario);

        JRadioButton btnRadioAlumno = new JRadioButton("Alumno", false);
        buttonGroupTipo.add(btnRadioAlumno);
        btnRadioAlumno.setBounds(171, 7, 101, 23);
        panelIngresarComo.add(btnRadioAlumno);

        JRadioButton btnRadioDocente = new JRadioButton("Docente", false);
        buttonGroupTipo.add(btnRadioDocente);
        btnRadioDocente.setBounds(308, 7, 108, 23);
        panelIngresarComo.add(btnRadioDocente);

        JRadioButton btnRadioPublicoGeneral = new JRadioButton("Publico general", false);
        buttonGroupTipo.add(btnRadioPublicoGeneral);
        btnRadioPublicoGeneral.setBounds(429, 7, 125, 23);
        panelIngresarComo.add(btnRadioPublicoGeneral);

        JLabel lblIngresarComo = new JLabel("Ingresar como");
        lblIngresarComo.setBounds(39, 162, 97, 14);
        contentPane.add(lblIngresarComo);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setForeground(new Color(0, 0, 0));
        btnIngresar.setBackground(new Color(255, 255, 255));
        btnIngresar.setBounds(283, 382, 89, 23);
        contentPane.add(btnIngresar);

        JLabel lblNoRegistrado = new JLabel("¿No estas registrado?");
        lblNoRegistrado.setBounds(224, 432, 141, 14);
        contentPane.add(lblNoRegistrado);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBackground(new Color(255, 255, 255));
        btnRegistrarse.setBorder(null);
        btnRegistrarse.setBorderPainted(false);
        btnRegistrarse.setForeground(new Color(0, 64, 128));
        btnRegistrarse.setBounds(351, 428, 119, 23);
        contentPane.add(btnRegistrarse);

        /**
         * Method created to search for user in database
         */

        btnIngresar.addActionListener(e -> {

            String tipoDeUsuario = encontrarTipoDeUsuario(btnRadioPublicoGeneral, btnRadioDocente, btnRadioAlumno);

            Boolean camposCompletos = !cajaCorreo.getText().isBlank()
                    && !(String.valueOf(cajaContrasenia.getPassword()).isBlank());

            if (Boolean.TRUE.equals(camposCompletos)) {
                String busquedaUsuario = usuariosDAO.buscarUsuarioRegistrado(tipoDeUsuario, cajaCorreo.getText(),
                        String.valueOf(cajaContrasenia.getPassword()));

                if (busquedaUsuario.equals("usuario encontrado")) {
                    String busquedaNombre = usuariosDAO.buscarNombre(tipoDeUsuario, cajaCorreo.getText());

                    if ("Funcionarios".equals(tipoDeUsuario)) {
                        SistemaFuncionario interfazSistemaFuncionario = new SistemaFuncionario();
                        interfazSistemaFuncionario.setVisible(true);
                        interfazSistemaFuncionario.textUsuario.setText(busquedaNombre);
                    } else {
                        SistemaLector interfazSistemaLector = new SistemaLector();
                        interfazSistemaLector.setVisible(true);
                        interfazSistemaLector.textUsusario.setText(busquedaNombre);
                    }
                    IniciarSesion.this.dispose();

                } else {
                    JOptionPane.showInternalMessageDialog(null, "Usuario no registrado");
                }
            } else {
                JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos para poder ingresar");
            }
        });

        /**
         * Created method to open "Registrarse" window
         */

        btnRegistrarse.addActionListener(e -> {
            Registrarse registrarse = new Registrarse();
            registrarse.setVisible(true);
            IniciarSesion.this.dispose();
        });

        /**
         * Created method to close window
         */

        btnExit.addActionListener(e -> System.exit(0));

    }

    /**
     * Method created to find the type of user to log in and search the
     * corresponding table in the database
     */

    public String encontrarTipoDeUsuario(JRadioButton btnRadioPublicoGeneral, JRadioButton btnRadioDocente,
            JRadioButton btnRadioAlumno) {
        String tipoDeUsuario;
        if (btnRadioPublicoGeneral.isSelected()) {
            tipoDeUsuario = "Lectores";
        } else if (btnRadioDocente.isSelected()) {
            tipoDeUsuario = "Docentes";
        } else if (btnRadioAlumno.isSelected()) {
            tipoDeUsuario = "Alumnos";
        } else {
            tipoDeUsuario = "Funcionarios";
        }
        return tipoDeUsuario;
    }
}