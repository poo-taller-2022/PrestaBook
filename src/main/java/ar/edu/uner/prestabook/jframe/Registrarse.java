package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
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

import com.github.lgooddatepicker.components.DatePicker;

import ar.edu.uner.prestabook.jframe.utils.DateSettings;
import ar.edu.uner.prestabook.model.Funcionario;
import ar.edu.uner.prestabook.model.Lector;
import ar.edu.uner.prestabook.security.PasswordEncrypter;

public class Registrarse extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Create the frame.
     */

    public Registrarse() {

        /**
         * Create components
         */

        ventana();
        JPanel contentPane = contentPane();

        JPanel panelPrestabook = panelPrestabook();
        contentPane.add(panelPrestabook);

        panelPrestabook.add(lblPrestabook());

        contentPane.add(lblRegistrarse());

        contentPane.add(lblNombre());
        JTextField textNombre = textNombre();
        contentPane.add(textNombre);

        contentPane.add(lblApellido());
        JTextField textApellido = textApellido();
        contentPane.add(textApellido);

        contentPane.add(lblTipoDeDocumento());
        JTextField textTipoDeDocumento = textTipoDeDocumento();
        contentPane.add(textTipoDeDocumento);

        contentPane.add(lblNumeroDeDocumento());
        JTextField textNumeroDeDocumento = textNumeroDeDocumento();
        contentPane.add(textNumeroDeDocumento);

        contentPane.add(lblEmail());
        JTextField textEmail = textEmail();
        contentPane.add(textEmail);

        contentPane.add(lblNumeroDeTelefono());
        JTextField textNumeroDeTelefono = textNumeroDeTelefono();
        contentPane.add(textNumeroDeTelefono);

        contentPane.add(lblFechaDeNacimiento());
        DatePicker datePickerFechaDeNacimiento = datePickerFechaDeNacimiento();
        contentPane.add(datePickerFechaDeNacimiento);

        contentPane.add(lblNacionalidad());
        JTextField textNacionalidad = textNacionalidad();
        contentPane.add(textNacionalidad);

        contentPane.add(lblDomicilio());
        JTextField textDomicilio = textDomicilio();
        contentPane.add(textDomicilio);

        contentPane.add(lblCodigoPostal());
        JTextField textCodigoPostal = textCodigoPostal();
        contentPane.add(textCodigoPostal);

        contentPane.add(lblDepartamento());
        JTextField textDepartamento = textDepartamento();
        contentPane.add(textDepartamento);

        contentPane.add(lblLocalidad());
        JTextField textLocalidad = textLocalidad();
        contentPane.add(textLocalidad);

        ButtonGroup buttonGroupTipo = new ButtonGroup();

        contentPane.add(lblRegistrarseComo());
        JPanel panelRegistrarseComo = panelRegistrarseComo();
        contentPane.add(panelRegistrarseComo);

        JRadioButton btnRadioFuncionario = btnRadioFuncionario();
        panelRegistrarseComo.add(btnRadioFuncionario);
        buttonGroupTipo.add(btnRadioFuncionario);

        JRadioButton btnRadioAlumno = btnRadioAlumno();
        panelRegistrarseComo.add(btnRadioAlumno);
        buttonGroupTipo.add(btnRadioAlumno);

        JRadioButton btnRadioDocente = btnRadioDocente();
        panelRegistrarseComo.add(btnRadioDocente);
        buttonGroupTipo.add(btnRadioDocente);

        JRadioButton btnRadioPublicoGeneral = btnRadioPublicoGeneral();
        panelRegistrarseComo.add(btnRadioPublicoGeneral);
        buttonGroupTipo.add(btnRadioPublicoGeneral);

        ButtonGroup buttonGroupSexo = new ButtonGroup();

        contentPane.add(lblSexo());

        JPanel panelSexo = panelSexo();
        contentPane.add(panelSexo);

        JRadioButton btnRadioHombre = btnRadioHombre();
        panelSexo.add(btnRadioHombre);
        buttonGroupSexo.add(btnRadioHombre);

        JRadioButton btnRadioMujer = btnRadioMujer();
        panelSexo.add(btnRadioMujer);
        buttonGroupSexo.add(btnRadioMujer);

        JRadioButton btnRadioOtro = btnRadioOtro();
        panelSexo.add(btnRadioOtro);
        buttonGroupSexo.add(btnRadioOtro);

        contentPane.add(lblContrasenia());
        JTextField textContrasenia = textContrasenia();
        contentPane.add(textContrasenia);

        JButton btnGuardar = btnGuardar();
        contentPane.add(btnGuardar);

        JButton btnRegresar = btnRegresar();
        contentPane.add(btnRegresar);

        JButton btnExit = btnExit();
        panelPrestabook.add(btnExit);

        /**
         * Method created to return to the "Login" window
         **/

        btnRegresar.addActionListener(e -> {
            IniciarSesion login = new IniciarSesion();
            login.setVisible(true);
            Registrarse.this.dispose();
        });

        /**
         * Created method to close window
         */

        btnExit.addActionListener(e -> System.exit(0));

        /**
         * Method created to instantiate a reader object with the data entered in the
         * fields and send it to the database
         **/

        btnGuardar.addActionListener(e -> {

            Boolean camposCompletos = !textNombre.getText().isBlank() && !textApellido.getText().isBlank()
                    && !textTipoDeDocumento.getText().isBlank() && !textNumeroDeDocumento.getText().isBlank()
                    && !textEmail.getText().isBlank() && !textNumeroDeTelefono.getText().isBlank()
                    && datePickerFechaDeNacimiento.isTextFieldValid() && !textNacionalidad.getText().isBlank()
                    && !textDomicilio.getText().isBlank() && !textCodigoPostal.getText().isBlank()
                    && !textDepartamento.getText().isBlank() && !textLocalidad.getText().isBlank()
                    && (btnRadioHombre.isSelected() || btnRadioMujer.isSelected() || btnRadioOtro.isSelected())
                    && (btnRadioPublicoGeneral.isSelected() || btnRadioDocente.isSelected()
                            || btnRadioAlumno.isSelected() || btnRadioFuncionario.isSelected())
                    && !(String.valueOf(((JPasswordField) textContrasenia).getPassword()).isBlank());

            if (Boolean.TRUE.equals(camposCompletos)) {
                String sexoSeleccionado = sexoSeleccionado(btnRadioHombre, btnRadioMujer, btnRadioOtro);

                String tipoSeleccionado = tipoSeleccionado(btnRadioPublicoGeneral, btnRadioDocente, btnRadioAlumno,
                        btnRadioFuncionario);

                Lector lector = crearLector(textNombre, textApellido, textTipoDeDocumento, textNumeroDeDocumento,
                        textEmail, textNumeroDeTelefono, datePickerFechaDeNacimiento, sexoSeleccionado, textNacionalidad,
                        textDomicilio, textCodigoPostal, textDepartamento, textLocalidad, textContrasenia);

                if (Objects.equals(tipoSeleccionado, btnRadioFuncionario.getText())) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.registrarse(lector);
                    JOptionPane.showInternalMessageDialog(null,
                            "Datos del " + tipoSeleccionado + " guardados correctamente");
                } else {
                    lector.registrarse(tipoSeleccionado, lector);
                    JOptionPane.showInternalMessageDialog(null,
                            "Datos del " + tipoSeleccionado + " guardados correctamente");
                }
            } else {
                JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos para poder guardar");
            }
        });
    }

    /**
     * Create components
     */

    public void ventana() {
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 648, 557);
        setLocationRelativeTo(null);
    }

    public JPanel contentPane() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        return contentPane;
    }

    public JPanel panelPrestabook() {
        JPanel panelPrestabook = new JPanel();
        panelPrestabook.setBounds(0, 0, 648, 100);
        panelPrestabook.setBackground(new Color(0, 64, 128));
        panelPrestabook.setLayout(null);

        return panelPrestabook;
    }

    public JLabel lblPrestabook() {
        JLabel lblPrestabook = new JLabel("PrestaBook");
        lblPrestabook.setBounds(214, 30, 263, 42);
        lblPrestabook.setForeground(Color.WHITE);
        lblPrestabook.setFont(new Font("Verdana", Font.BOLD, 32));
        return lblPrestabook;
    }

    public JLabel lblRegistrarse() {
        JLabel lblRegistrarse = new JLabel("Registrarse");
        lblRegistrarse.setBounds(265, 106, 180, 23);
        lblRegistrarse.setFont(new Font("Verdana", Font.BOLD, 17));
        return lblRegistrarse;
    }

    public JLabel lblNombre() {
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(24, 137, 59, 14);
        return lblNombre;
    }

    public JTextField textNombre() {
        JTextField textNombre = new JTextField();
        textNombre.setBounds(24, 154, 180, 30);
        textNombre.setForeground(Color.GRAY);
        textNombre.setColumns(10);
        textNombre.setBackground(new Color(255, 255, 255));
        return textNombre;
    }

    public JLabel lblApellido() {
        JLabel lblApellido = new JLabel("Apellido");
        lblApellido.setBounds(24, 195, 59, 14);
        return lblApellido;
    }

    public JTextField textApellido() {
        JTextField textApellido = new JTextField();
        textApellido.setBounds(24, 217, 180, 30);
        textApellido.setForeground(Color.GRAY);
        textApellido.setColumns(10);
        textApellido.setBackground(new Color(255, 255, 255));
        return textApellido;
    }

    public JLabel lblTipoDeDocumento() {
        JLabel lblTipoDeDocumento = new JLabel("Tipo de documento");
        lblTipoDeDocumento.setBounds(24, 258, 119, 14);
        return lblTipoDeDocumento;
    }

    public JTextField textTipoDeDocumento() {
        JTextField textTipoDeDocumento = new JTextField();
        textTipoDeDocumento.setBounds(24, 271, 180, 30);
        textTipoDeDocumento.setForeground(Color.GRAY);
        textTipoDeDocumento.setColumns(10);
        textTipoDeDocumento.setBackground(Color.WHITE);
        return textTipoDeDocumento;
    }

    public JLabel lblNumeroDeDocumento() {
        JLabel lblNumeroDeDocumento = new JLabel("Numero de documento");
        lblNumeroDeDocumento.setBounds(24, 312, 135, 14);
        return lblNumeroDeDocumento;
    }

    public JFormattedTextField textNumeroDeDocumento() {
        JFormattedTextField textNumeroDeDocumento = new JFormattedTextField();
        textNumeroDeDocumento.setBounds(24, 331, 180, 30);
        textNumeroDeDocumento.setForeground(Color.GRAY);
        textNumeroDeDocumento.setColumns(10);
        textNumeroDeDocumento.setBackground(Color.WHITE);
        textNumeroDeDocumento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                textNumeroDeDocumento.setEditable(!Character.isLetter(ke.getKeyChar()));
            }
        });
        return textNumeroDeDocumento;
    }

    public JTextField textEmail() {
        JTextField textEmail = new JTextField();
        textEmail.setForeground(Color.GRAY);
        textEmail.setColumns(10);
        textEmail.setBackground(Color.WHITE);
        textEmail.setBounds(241, 154, 180, 30);
        return textEmail;
    }

    public JLabel lblEmail() {
        JLabel lblEmail = new JLabel("Correo electrónico");
        lblEmail.setBounds(241, 137, 91, 14);
        return lblEmail;
    }

    public JLabel lblNumeroDeTelefono() {
        JLabel lblNumeroDeTelefono = new JLabel("Numero de telefono");
        lblNumeroDeTelefono.setBounds(241, 195, 133, 14);
        return lblNumeroDeTelefono;
    }

    public JTextField textNumeroDeTelefono() {
        JTextField textNumeroDeTelefono = new JTextField();
        textNumeroDeTelefono.setBounds(241, 217, 180, 30);
        textNumeroDeTelefono.setForeground(Color.GRAY);
        textNumeroDeTelefono.setColumns(10);
        textNumeroDeTelefono.setBackground(new Color(255, 255, 255));
        textNumeroDeTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                textNumeroDeTelefono.setEditable(!Character.isLetter(ke.getKeyChar()));
            }
        });
        return textNumeroDeTelefono;
    }

    public JLabel lblFechaDeNacimiento() {
        JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
        lblFechaDeNacimiento.setBounds(241, 258, 139, 14);
        return lblFechaDeNacimiento;
    }

    public DatePicker datePickerFechaDeNacimiento() {
        DatePicker datePickerFechaDeNacimiento = new DatePicker();
        datePickerFechaDeNacimiento.getComponentDateTextField().setSize(110, 30);
        datePickerFechaDeNacimiento.setForeground(Color.GRAY);
        datePickerFechaDeNacimiento.setBackground(Color.WHITE);
        datePickerFechaDeNacimiento.setBounds(241, 271, 180, 30);
        datePickerFechaDeNacimiento.setSettings(DateSettings.getDatePickerSettings());
        return datePickerFechaDeNacimiento;
    }

    public JLabel lblNacionalidad() {
        JLabel lblNacionalidad = new JLabel("Nacionalidad");
        lblNacionalidad.setBounds(241, 312, 108, 14);
        return lblNacionalidad;
    }

    public JTextField textNacionalidad() {
        JTextField textNacionalidad = new JTextField();
        textNacionalidad.setForeground(Color.GRAY);
        textNacionalidad.setColumns(10);
        textNacionalidad.setBackground(Color.WHITE);
        textNacionalidad.setBounds(241, 331, 180, 30);
        return textNacionalidad;
    }

    public JLabel lblDomicilio() {
        JLabel lblDomicilio = new JLabel("Domicilio");
        lblDomicilio.setBounds(458, 137, 75, 14);
        return lblDomicilio;
    }

    public JTextField textDomicilio() {
        JTextField textDomicilio = new JTextField();
        textDomicilio.setForeground(Color.GRAY);
        textDomicilio.setColumns(10);
        textDomicilio.setBackground(Color.WHITE);
        textDomicilio.setBounds(458, 154, 180, 30);
        return textDomicilio;
    }

    public JLabel lblCodigoPostal() {
        JLabel lblCodigoPostal = new JLabel("Código postal");
        lblCodigoPostal.setBounds(458, 195, 105, 14);
        return lblCodigoPostal;
    }

    public JTextField textCodigoPostal() {
        JTextField textCodigoPostal = new JTextField();
        textCodigoPostal.setForeground(Color.GRAY);
        textCodigoPostal.setColumns(10);
        textCodigoPostal.setBackground(Color.WHITE);
        textCodigoPostal.setBounds(458, 217, 180, 30);
        return textCodigoPostal;
    }

    public JLabel lblDepartamento() {
        JLabel lblDepartamento = new JLabel("Departamento");
        lblDepartamento.setBounds(458, 258, 108, 14);
        return lblDepartamento;
    }

    public JTextField textDepartamento() {
        JTextField textDepartamento = new JTextField();
        textDepartamento.setForeground(Color.GRAY);
        textDepartamento.setColumns(10);
        textDepartamento.setBackground(Color.WHITE);
        textDepartamento.setBounds(458, 271, 180, 30);
        return textDepartamento;
    }

    public JLabel lblLocalidad() {
        JLabel lblLocalidad = new JLabel("Localidad");
        lblLocalidad.setBounds(458, 312, 75, 14);
        return lblLocalidad;
    }

    public JTextField textLocalidad() {
        JTextField textLocalidad = new JTextField();
        textLocalidad.setForeground(Color.GRAY);
        textLocalidad.setColumns(10);
        textLocalidad.setBackground(Color.WHITE);
        textLocalidad.setBounds(458, 331, 180, 30);
        return textLocalidad;
    }

    public JLabel lblRegistrarseComo() {
        JLabel lblRegistrarseComo = new JLabel("Registrase como");
        lblRegistrarseComo.setBounds(46, 366, 97, 14);
        return lblRegistrarseComo;
    }

    public JPanel panelRegistrarseComo() {
        JPanel panelRegistrarseComo = new JPanel();
        panelRegistrarseComo.setBorder(new LineBorder(new Color(128, 128, 128)));
        panelRegistrarseComo.setBounds(46, 381, 560, 38);
        panelRegistrarseComo.setLayout(null);
        return panelRegistrarseComo;
    }

    public JRadioButton btnRadioFuncionario() {
        JRadioButton btnRadioFuncionario = new JRadioButton("Funcionario", false);
        btnRadioFuncionario.setBounds(19, 7, 109, 23);
        btnRadioFuncionario.setFocusPainted(false);
        return btnRadioFuncionario;
    }

    public JRadioButton btnRadioAlumno() {
        JRadioButton btnRadioAlumno = new JRadioButton("Alumno", false);
        btnRadioAlumno.setBounds(171, 7, 101, 23);
        btnRadioAlumno.setFocusPainted(false);
        return btnRadioAlumno;
    }

    public JRadioButton btnRadioDocente() {
        JRadioButton btnRadioDocente = new JRadioButton("Docente", false);
        btnRadioDocente.setBounds(308, 7, 108, 23);
        btnRadioDocente.setFocusPainted(false);
        return btnRadioDocente;
    }

    public JRadioButton btnRadioPublicoGeneral() {
        JRadioButton btnRadioPublicoGeneral = new JRadioButton("Publico general", false);
        btnRadioPublicoGeneral.setBounds(429, 7, 125, 23);
        btnRadioPublicoGeneral.setFocusPainted(false);
        return btnRadioPublicoGeneral;
    }

    public JLabel lblSexo() {
        JLabel lblSexo = new JLabel("Sexo");
        lblSexo.setBounds(79, 429, 59, 14);
        return lblSexo;
    }

    public JPanel panelSexo() {
        JPanel panelSexo = new JPanel();
        panelSexo.setBorder(new LineBorder(new Color(128, 128, 128)));
        panelSexo.setBounds(79, 447, 215, 30);
        panelSexo.setLayout(null);
        return panelSexo;
    }

    public JRadioButton btnRadioHombre() {
        JRadioButton btnRadioHombre = new JRadioButton("Hombre");
        btnRadioHombre.setBounds(6, 7, 73, 16);
        btnRadioHombre.setFocusPainted(false);
        return btnRadioHombre;
    }

    public JRadioButton btnRadioMujer() {
        JRadioButton btnRadioMujer = new JRadioButton("Mujer");
        btnRadioMujer.setBounds(81, 7, 63, 16);
        btnRadioMujer.setFocusPainted(false);
        return btnRadioMujer;
    }

    public JRadioButton btnRadioOtro() {
        JRadioButton btnRadioOtro = new JRadioButton("Otro");
        btnRadioOtro.setBounds(146, 7, 63, 16);
        btnRadioOtro.setFocusPainted(false);
        return btnRadioOtro;
    }

    public JLabel lblContrasenia() {
        JLabel lblContrasenia = new JLabel("Contraseña");
        lblContrasenia.setBounds(386, 429, 147, 14);
        return lblContrasenia;
    }

    public JPasswordField textContrasenia() {
        JPasswordField textContrasenia = new JPasswordField();
        textContrasenia.setBounds(386, 446, 180, 30);
        textContrasenia.setToolTipText("");
        textContrasenia.setBackground(Color.WHITE);
        return textContrasenia;
    }

    public JButton btnGuardar() {
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(197, 512, 119, 23);
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.setBackground(Color.WHITE);
        return btnGuardar;
    }

    public JButton btnRegresar() {
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(326, 512, 119, 23);
        btnRegresar.setForeground(Color.BLACK);
        btnRegresar.setBackground(Color.WHITE);
        return btnRegresar;
    }

    public JButton btnExit() {
        JButton btnExit = new JButton("X");
        btnExit.setBorderPainted(false);
        btnExit.setBorder(null);
        btnExit.setFocusPainted(false);
        btnExit.setBounds(601, 0, 47, 25);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Roboto Black", Font.PLAIN, 12));
        btnExit.setBackground(new Color(255, 106, 106));
        return btnExit;
    }

    /**
     * Method created to find the gender selected by the user
     */

    public String sexoSeleccionado(JRadioButton btnRadioHombre, JRadioButton btnRadioMujer, JRadioButton btnRadioOtro) {
        String sexoSeleccionado;

        if (btnRadioHombre.isSelected()) {
            sexoSeleccionado = btnRadioHombre.getText();
        } else if (btnRadioMujer.isSelected()) {
            sexoSeleccionado = btnRadioMujer.getText();
        } else {
            sexoSeleccionado = btnRadioOtro.getText();
        }
        return sexoSeleccionado;
    }

    /**
     * Method created to find the selected user type
     */

    public String tipoSeleccionado(JRadioButton btnRadioPublicoGeneral, JRadioButton btnRadioDocente,
            JRadioButton btnRadioAlumno, JRadioButton btnRadioFuncionario) {
        String tipoSeleccionado;

        if (btnRadioPublicoGeneral.isSelected()) {
            tipoSeleccionado = btnRadioPublicoGeneral.getText();
        } else if (btnRadioDocente.isSelected()) {
            tipoSeleccionado = btnRadioDocente.getText();
        } else if (btnRadioAlumno.isSelected()) {
            tipoSeleccionado = btnRadioAlumno.getText();
        } else {
            tipoSeleccionado = btnRadioFuncionario.getText();
        }
        return tipoSeleccionado;
    }

    /**
     * Method to create a reader
     */

    public Lector crearLector(JTextField textNombre, JTextField textApellido, JTextField textTipoDeDocumento,
            JTextField textNumeroDeDocumento, JTextField textEmail, JTextField textNumeroDeTelefono,
            DatePicker datePickerFechaDeNacimiento, String sexoSeleccionado, JTextField textNacionalidad,
            JTextField textDomicilio, JTextField textCodigoPostal, JTextField textDepartamento,
            JTextField textLocalidad, JTextField textContrasenia) {
        Lector lector = new Lector();
        lector.setNombre(textNombre.getText());
        lector.setApellido(textApellido.getText());
        lector.setTipoDocumento(textTipoDeDocumento.getText());
        lector.setDocumento(Long.valueOf(textNumeroDeDocumento.getText()));
        lector.setEmail(textEmail.getText());
        lector.setCelular(textNumeroDeTelefono.getText());
        lector.setFechaNacimiento(datePickerFechaDeNacimiento.getDate().toString());
        lector.setSexo(sexoSeleccionado);
        lector.setNacionalidad(textNacionalidad.getText());
        lector.setDomicilio(textDomicilio.getText());
        lector.setCodigoPostal(textCodigoPostal.getText());
        lector.setDepartamento(textDepartamento.getText());
        lector.setLocalidad(textLocalidad.getText());
        lector.setContrasenia(
                PasswordEncrypter.encrypt((String.valueOf(((JPasswordField) textContrasenia).getPassword()))));

        return lector;
    }
}
