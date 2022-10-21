package ar.edu.uner.prestabook.jframe.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ar.edu.uner.prestabook.jframe.Constants;
import lombok.Getter;

public class Utils {

    private static final Integer BUTTON_POSITION_LEFT = 140;
    private static final Integer BUTTON_POSITION_CENTER = 440;
    private static final Integer BUTTON_POSITION_RIGHT = 740;
    @Getter
    private static JLabel textUsuario = textUsuario();

    public static JScrollPane scrollPane() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 158, 1130, 300);
        return scrollPane;
    }

    public static JButton btnGeneric(String label, String position) {
        JButton btnGeneric = new JButton(label);
        btnGeneric.setFocusPainted(false);
        btnGeneric.setBackground(new Color(0, 64, 128));
        btnGeneric.setForeground(new Color(255, 255, 255));
        btnGeneric.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnGeneric.setBorderPainted(false);
        Integer xAxis;
        switch (position) {
            case "Left":
                xAxis = BUTTON_POSITION_LEFT;
                break;
            case "Center":
                xAxis = BUTTON_POSITION_CENTER;
                break;
            case "Right":
                xAxis = BUTTON_POSITION_RIGHT;
                break;
            default:
                xAxis = BUTTON_POSITION_CENTER;
                break;
        }
        btnGeneric.setBounds(xAxis, 500, 210, 20);
        return btnGeneric;
    }

    public static JLabel lblBienvenidaParte1() {
        JLabel lblBienvenidaParte1 = new JLabel("¡Bienvenido al sistema de gestión de préstamos de libros más");
        lblBienvenidaParte1.setBounds(204, 11, 775, 136);
        lblBienvenidaParte1.setForeground(Color.GRAY);
        lblBienvenidaParte1.setFont(new Font(Constants.FONT, Font.BOLD, 21));
        return lblBienvenidaParte1;
    }

    public static JLabel lblIconLibreria() {
        JLabel lblIconLibreria = new JLabel("");
        lblIconLibreria.setBounds(194, 191, 605, 493);
        lblIconLibreria.setIcon(new ImageIcon(new File("src/main/resources/library.png").getAbsolutePath()));
        return lblIconLibreria;
    }

    public static JLabel lblBienvenidaParte2() {
        JLabel lblBienvenidaParte2 = new JLabel("grande del mundo!");
        lblBienvenidaParte2.setBounds(401, 100, 369, 136);
        lblBienvenidaParte2.setForeground(Color.GRAY);
        lblBienvenidaParte2.setFont(new Font(Constants.FONT, Font.BOLD, 21));
        return lblBienvenidaParte2;
    }

    public static JLabel textUsuario() {
        JLabel textUsuario = new JLabel("");
        textUsuario.setBackground(new Color(0, 128, 0));
        textUsuario.setBounds(124, 0, 173, 37);
        textUsuario.setFont(new Font(Constants.FONT, Font.BOLD, 16));
        textUsuario.setForeground(new Color(255, 255, 255));
        return textUsuario;
    }



    public static JPanel panelBienvenida() {
        JPanel panelBienvenida = new JPanel();
        panelBienvenida.setBounds(237, 103, 1153, 708);
        panelBienvenida.setLayout(null);
        return panelBienvenida;
    }

    public static void jLabelImage(JLabel lblIconCerrarSesion) {
        ImageIcon image = new ImageIcon(new File("src/main/resources/Vector.png").getAbsolutePath());
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(lblIconCerrarSesion.getWidth(),
                lblIconCerrarSesion.getHeight(), Image.SCALE_DEFAULT));
        lblIconCerrarSesion.setIcon(icon);
    }

    public static JPanel panelPrestabook() {
        JPanel panelPrestabook = new JPanel();
        panelPrestabook.setBounds(237, 0, 1153, 103);
        panelPrestabook.setBorder(null);
        panelPrestabook.setBackground(new Color(0, 64, 128));
        panelPrestabook.setLayout(null);
        return panelPrestabook;
    }

    public static JLabel lblIconCerrarSesion() {
        JLabel lblIconCerrarSesion = new JLabel("");
        lblIconCerrarSesion.setBounds(817, 5, 19, 16);
        return lblIconCerrarSesion;
    }

    public static JLabel lblPrestabook() {
        JLabel lblPrestabook = new JLabel("PrestaBook");
        lblPrestabook.setBounds(404, 31, 267, 42);
        lblPrestabook.setForeground(Color.WHITE);
        lblPrestabook.setFont(new Font(Constants.FONT, Font.BOLD, 32));
        return lblPrestabook;
    }

    public static JButton btnExit() {
        JButton btnExit = new JButton("X");
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnExit.setBackground(new Color(255, 106, 106));
        btnExit.setBounds(1106, 0, 47, 25);
        return btnExit;
    }

    public static JButton btnCerrarSesion() {
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font(Constants.FONT, Font.BOLD, 11));
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setBorder(null);
        btnCerrarSesion.setBounds(995, -2, 101, 23);
        btnCerrarSesion.setForeground(new Color(255, 255, 255));
        btnCerrarSesion.setBackground(new Color(0, 64, 128));
        return btnCerrarSesion;
    }

    public static JPanel panelOpciones() {
        JPanel panelOpciones = new JPanel();
        panelOpciones.setBounds(0, 0, 237, 811);
        panelOpciones.setBackground(new Color(0, 45, 89));
        panelOpciones.setLayout(null);
        return panelOpciones;
    }

    public static JLabel lblUsuario() {
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(27, 0, 122, 37);
        lblUsuario.setFont(new Font(Constants.FONT, Font.BOLD, 17));
        lblUsuario.setForeground(new Color(255, 255, 255));
        return lblUsuario;
    }

    public static JButton btnSolicitudes() {
        JButton btnSolicitudes = new JButton("Solicitudes");
        btnSolicitudes.setVerifyInputWhenFocusTarget(false);
        btnSolicitudes.setForeground(new Color(0, 64, 128));
        btnSolicitudes.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnSolicitudes.setFocusPainted(false);
        btnSolicitudes.setBorderPainted(false);
        btnSolicitudes.setBorder(null);
        btnSolicitudes.setBackground(Color.WHITE);
        btnSolicitudes.setBounds(23, 74, 188, 31);
        return btnSolicitudes;
    }

    public static JPanel panelSeparador() {
        JPanel panelSeparador = new JPanel();
        panelSeparador.setBounds(23, 121, 188, 3);
        return panelSeparador;
    }

    public static JLabel lblOpciones() {
        JLabel lblOpciones = new JLabel("Opciones");
        lblOpciones.setForeground(new Color(255, 255, 255));
        lblOpciones.setFont(new Font(Constants.FONT, Font.BOLD, 16));
        lblOpciones.setBounds(73, 135, 105, 23);
        return lblOpciones;
    }
}
