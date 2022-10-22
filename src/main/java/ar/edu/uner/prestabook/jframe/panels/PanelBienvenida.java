package ar.edu.uner.prestabook.jframe.panels;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.Constants;

public class PanelBienvenida extends AbstractPanel {

    @Override
    public JPanel init() {
        prepare();
        panel.add(lblBienvenidaParte1());
        panel.add(lblBienvenidaParte2());
        panel.add(lblIconLibreria());
        panel.remove(scrollPane);
        scrollPane.setViewportView(null);
        return panel;
    }

    @Override
    public void setModelColumns() {
        // method not implemented
    }

    private JLabel lblBienvenidaParte1() {
        JLabel lblBienvenidaParte1 = new JLabel("¡Bienvenido al sistema de gestión de préstamos de libros más");
        lblBienvenidaParte1.setBounds(161, 0, 775, 136);
        lblBienvenidaParte1.setForeground(Color.GRAY);
        lblBienvenidaParte1.setFont(new Font(Constants.FONT, Font.BOLD, 21));
        return lblBienvenidaParte1;
    }

    private JLabel lblBienvenidaParte2() {
        JLabel lblBienvenidaParte2 = new JLabel("grande del mundo!");
        lblBienvenidaParte2.setBounds(397, 64, 369, 136);
        lblBienvenidaParte2.setForeground(Color.GRAY);
        lblBienvenidaParte2.setFont(new Font(Constants.FONT, Font.BOLD, 21));
        return lblBienvenidaParte2;
    }

    private JLabel lblIconLibreria() {
        JLabel lblIconLibreria = new JLabel("");
        lblIconLibreria.setBounds(225, 169, 605, 493);
        lblIconLibreria.setIcon(new ImageIcon(new File("src/main/resources/library.png").getAbsolutePath()));
        return lblIconLibreria;
    }

    @Override
    public String getPanelName() {
        return "";
    }
}
