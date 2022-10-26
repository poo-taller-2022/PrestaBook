package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.model.Config;

/**
 * GUI Designed to display the user interface of a new book loan
 *
 */
public class Configuracion extends JFrame {

    private static final String DEFAULT_LOAN_TIME_KEY = "default_loan_time";
    /** Serial number */
    private static final long serialVersionUID = 1L;

    /**
     * Class constructor
     */
    public Configuracion() {
        ventana();
        JPanel contentPane = contentPane();

        JPanel panelNuevoPrestamo = panelNuevoPrestamo();
        contentPane.add(panelNuevoPrestamo);

        JLabel labelNuevoPrestamo = lblOpciones();
        panelNuevoPrestamo.add(labelNuevoPrestamo);

        JFormattedTextField fieldDefaultLoanDays = inputNumber();
        fieldDefaultLoanDays.setValue(DaoFactory.getConfigDAO().findById(DEFAULT_LOAN_TIME_KEY).getValue());
        contentPane.add(fieldDefaultLoanDays);

        JLabel labelPlazoPrestamo = labelPlazoPrestamo();
        contentPane.add(labelPlazoPrestamo);

        JButton btnConfirmar = btnConfirmar();
        contentPane.add(btnConfirmar);

        JButton btnCancelar = btnCancelar();
        contentPane.add(btnCancelar);

        btnConfirmar.addActionListener(e -> {
            Boolean camposCompletos = fieldDefaultLoanDays.getText() != null;

            if (Boolean.TRUE.equals(camposCompletos)) {
                Config config = new Config();
                config.setKey(DEFAULT_LOAN_TIME_KEY);
                config.setValue(fieldDefaultLoanDays.getText());
                DaoFactory.getConfigDAO().update(config);
                JOptionPane.showInternalMessageDialog(null, "Datos guardados correctamente");
                this.setVisible(false);
            } else {
                JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos");
            }
        });

        btnCancelar.addActionListener(e -> this.setVisible(false));
    }

    /**
     * Creates the window
     */
    public void ventana() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 655, 540);
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle(Constants.PRESTABOOK);
    }

    /**
     * Creates the pane
     * 
     * @return a container
     */
    public JPanel contentPane() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new MatteBorder(3, 3, 3, 3, new Color(0, 64, 128)));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        return contentPane;
    }

    /**
     * Creates the pane
     * 
     * @return a container
     */
    public JPanel panelNuevoPrestamo() {
        JPanel panelNuevoPrestamo = new JPanel();
        panelNuevoPrestamo.setBackground(new Color(0, 64, 128));
        panelNuevoPrestamo.setBounds(0, 0, 655, 98);
        panelNuevoPrestamo.setLayout(null);
        return panelNuevoPrestamo;
    }

    /**
     * Creates a label
     * 
     * @return a label with the New Loan text
     */
    public JLabel lblOpciones() {
        JLabel lblOpciones = new JLabel(Constants.OPCIONES);
        lblOpciones.setForeground(new Color(255, 255, 255));
        lblOpciones.setBounds(240, 31, 110, 39);
        lblOpciones.setFont(new Font("Verdana", Font.BOLD, 20));
        return lblOpciones;
    }

    /**
     * Creates a Combo Box with all the Books
     * @return an inputNumer
     */
    public JFormattedTextField inputNumber() {
        JFormattedTextField inputNumber = new JFormattedTextField();
        inputNumber.setBounds(24, 191, 481, 29);
        inputNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                inputNumber.setEditable(!Character.isLetter(ke.getKeyChar()));
            }
        });
        return inputNumber;
    }

    /**
     * Creates a label
     * 
     * @return a label with the Book text
     */
    public JLabel labelPlazoPrestamo() {
        JLabel labelObra = new JLabel("Plazo de préstamo por defecto");
        labelObra.setBounds(24, 177, 185, 14);
        return labelObra;
    }

    /**
     * Creates a confirmation button
     * @return a button to confirm
     */
    public JButton btnConfirmar() {
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(163, 490, 120, 23);
        return btnConfirmar;
    }

    /**
     * Creates a cancel button
     * @return a button to cancel
     * 
     */
    public JButton btnCancelar() {
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(358, 490, 120, 23);
        return btnCancelar;
    }
}
