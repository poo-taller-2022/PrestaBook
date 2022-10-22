package ar.edu.uner.prestabook.jframe.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.IFormatoDAO;
import ar.edu.uner.prestabook.persistence.ITipoObraDAO;
import lombok.Getter;

public class Components {

    private static final Integer BUTTON_POSITION_LEFT = 140;
    private static final Integer BUTTON_POSITION_CENTER = 440;
    private static final Integer BUTTON_POSITION_RIGHT = 740;
    @Getter
    private static JLabel textUsuario = textUsuario();
    public static final Integer PANEL_WIDTH = 1000;

    private Components() {
    }

    public static JScrollPane scrollPane() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 158, 990, 300);
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
        JLabel textUsuario = new JLabel();
        textUsuario.setBackground(new Color(0, 128, 0));
        textUsuario.setBounds(118, 0, 173, 37);
        textUsuario.setFont(new Font(Constants.FONT, Font.BOLD, 16));
        textUsuario.setForeground(new Color(255, 255, 255));
        return textUsuario;
    }

    public static JPanel panelEntities() {
        JPanel panelEntities = new JPanel();
        panelEntities.setBounds(339, 104, PANEL_WIDTH, 707);
        panelEntities.setLayout(null);
        return panelEntities;
    }

    public static void jLabelImage(JLabel lblIconCerrarSesion) {
        ImageIcon image = new ImageIcon(new File("src/main/resources/Vector.png").getAbsolutePath());
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(lblIconCerrarSesion.getWidth(),
                lblIconCerrarSesion.getHeight(), Image.SCALE_DEFAULT));
        lblIconCerrarSesion.setIcon(icon);

    }

    public static JLabel lblIconCerrarSesion() {
        JLabel lblIconCerrarSesion = new JLabel("");
        lblIconCerrarSesion.setBounds(817, 5, 19, 16);
        return lblIconCerrarSesion;
    }

    public static JLabel lblPrestabook() {
        JLabel lblPrestabook = new JLabel("PrestaBook");
        lblPrestabook.setBounds(399, 30, 267, 42);
        lblPrestabook.setForeground(Color.WHITE);
        lblPrestabook.setFont(new Font(Constants.FONT, Font.BOLD, 32));
        return lblPrestabook;
    }

    public static JButton btnExit() {
        JButton btnExit = new JButton("X");
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnExit.setBackground(new Color(255, 106, 106));
        btnExit.setBounds(980, 1, 47, 25);
        return btnExit;
    }

    public static JButton btnCerrarSesion() {
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font(Constants.FONT, Font.BOLD, 11));
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setBorder(null);
        btnCerrarSesion.setBounds(870, 2, 101, 23);
        btnCerrarSesion.setForeground(new Color(255, 255, 255));
        btnCerrarSesion.setBackground(new Color(0, 64, 128));
        return btnCerrarSesion;
    }

    public static JPanel panelOpciones() {
        JPanel panelOpciones = new JPanel();
        panelOpciones.setBounds(0, 0, 341, 811);
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
        btnSolicitudes.setBounds(23, 74, 293, 31);
        return btnSolicitudes;
    }

    public static JPanel panelSeparador() {
        JPanel panelSeparador = new JPanel();
        panelSeparador.setBounds(23, 121, 292, 3);
        return panelSeparador;
    }

    public static JLabel lblOpciones() {
        JLabel lblOpciones = new JLabel(Constants.OPCIONES);
        lblOpciones.setForeground(new Color(255, 255, 255));
        lblOpciones.setFont(new Font(Constants.FONT, Font.BOLD, 16));
        lblOpciones.setBounds(133, 135, 105, 23);
        return lblOpciones;
    }

    public static JLabel lblPanelTitle(String title) {
        JLabel lblPanelTitle = new JLabel(title);
        lblPanelTitle.setBounds(440, 70, 369, 136);
        lblPanelTitle.setForeground(Color.GRAY);
        lblPanelTitle.setFont(new Font(Constants.FONT, Font.BOLD, 19));
        return lblPanelTitle;
    }

    public static void clearTable(JTable tabla) {
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

    public static void updateDatabase(String nombre, String tipoEntidad) {

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

    public static JButton btnLeftMenu(String label, Integer yAxis) {
        JButton btnAplicarMultaALector = new JButton(label);
        btnAplicarMultaALector.setFocusPainted(false);
        btnAplicarMultaALector.setBackground(new Color(255, 255, 255));
        btnAplicarMultaALector.setForeground(new Color(0, 64, 128));
        btnAplicarMultaALector.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnAplicarMultaALector.setBorderPainted(false);
        btnAplicarMultaALector.setBounds(23, yAxis, 293, 31);
        return btnAplicarMultaALector;
    }

    public static JPanel contentPane() {
        JPanel contentPane = new JPanel();
        contentPane = new JPanel();
        contentPane.setBorder(null);
        contentPane.setLayout(null);
        return contentPane;
    }

    public static JPanel panelPrestabook() {
        JPanel panelPrestabook = new JPanel();
        panelPrestabook.setBounds(339, 0, 1061, 103);
        panelPrestabook.setBorder(null);
        panelPrestabook.setBackground(new Color(0, 64, 128));
        panelPrestabook.setLayout(null);
        return panelPrestabook;
    }
}
