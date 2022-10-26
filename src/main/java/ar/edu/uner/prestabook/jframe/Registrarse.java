package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.PersistenceException;
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
import com.github.lgooddatepicker.components.DatePickerSettings;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.jframe.utils.DateSettings;
import ar.edu.uner.prestabook.jframe.utils.UnderAgeVetoPolicy;
import ar.edu.uner.prestabook.jframe.validation.ValidationFields;
import ar.edu.uner.prestabook.model.Funcionario;
import ar.edu.uner.prestabook.model.Lector;
import ar.edu.uner.prestabook.security.PasswordEncrypter;

public class Registrarse extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Create the frame.
     */

    public Registrarse() {

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

        JLabel lblNroNoValido = lblNroNoValido();
        contentPane.add(lblNroNoValido);

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
        contentPane.add(lblRepetirContrasenia());
        contentPane.add(lblCondicionContrasenia());
        JTextField textContrasenia = textContrasenia();
        contentPane.add(textContrasenia);

        JTextField textRepetirContrasenia = textRepetirContrasenia();
        contentPane.add(textRepetirContrasenia);

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

        ValidationFields validationFields = new ValidationFields();

        textNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validationFields.validationGenericLetters(textNombre);
            }
        });

        textApellido.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validationFields.validationGenericLetters(textApellido);
            }
        });

        textTipoDeDocumento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validationFields.validationGenericLetters(textTipoDeDocumento);
            }
        });

        textNumeroDeDocumento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validationFields.validationDocument(textNumeroDeDocumento);
            }
        });

        textEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validationFields.validationEmail(textEmail);
            }
        });

        textNumeroDeTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validationFields.validationNumber(textNumeroDeTelefono, lblNroNoValido);
            }
        });

        textNacionalidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validationFields.validationGenericLetters(textNacionalidad);
            }
        });

        textDomicilio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validationFields.validationGenericString(textDomicilio);
            }
        });

        textCodigoPostal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validationFields.validationGenericString(textCodigoPostal);
            }
        });

        textDepartamento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validationFields.validationGenericString(textDepartamento);
            }
        });

        textLocalidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validationFields.validationGenericLetters(textLocalidad);
            }
        });

        textContrasenia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validationFields.validationPassword(textContrasenia);
            }
        });

        textRepetirContrasenia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validationFields.validationRepeatPassword(textContrasenia, textRepetirContrasenia);
            }
        });

        /**
         * Method created to instantiate a reader object with the data entered in the
         * fields and send it to the database
         **/

        btnGuardar.addActionListener(e -> {

            Boolean camposValidos = validationFields.validationPatternGeneric(textNombre.getText())
                    && validationFields.validationPatternGeneric(textApellido.getText()) &&
                    validationFields.validationPatternGeneric(textTipoDeDocumento.getText()) &&
                    validationFields.validationPatternEmail(textEmail.getText())
                    && validationFields.validationPatternNumber(textNumeroDeTelefono.getText()) &&
                    validationFields.validationPatternGeneric(textNacionalidad.getText())
                    && validationFields.validationPatternGeneric(textLocalidad.getText()) &&
                    validationFields.validationPatternPassword(textContrasenia.getText());      

            Boolean camposCompletos = !textNombre.getText().isBlank() && !textApellido.getText().isBlank()
                    && !textTipoDeDocumento.getText().isBlank() && !textNumeroDeDocumento.getText().isBlank()
                    && !textEmail.getText().isBlank() && !textNumeroDeTelefono.getText().isBlank()
                    && datePickerFechaDeNacimiento.isTextFieldValid() && !textNacionalidad.getText().isBlank()
                    && !textDomicilio.getText().isBlank() && !textCodigoPostal.getText().isBlank()
                    && !textDepartamento.getText().isBlank() && !textLocalidad.getText().isBlank()
                    && (btnRadioHombre.isSelected() || btnRadioMujer.isSelected() || btnRadioOtro.isSelected())
                    && (btnRadioPublicoGeneral.isSelected() || btnRadioDocente.isSelected()
                            || btnRadioAlumno.isSelected() || btnRadioFuncionario.isSelected())
                    && !(String.valueOf(((JPasswordField) textContrasenia).getPassword()).isBlank())
                    && !(String.valueOf(((JPasswordField) textRepetirContrasenia).getPassword()).isBlank());

            if (Boolean.TRUE.equals(camposCompletos)) {
                if (Boolean.TRUE.equals(camposValidos)) {
                    if (!textContrasenia.getText().equals(textRepetirContrasenia.getText())) {
                        JOptionPane.showInternalMessageDialog(null, "Las contraseñas no coinciden", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {

                        String sexoSeleccionado = sexoSeleccionado(btnRadioHombre, btnRadioMujer, btnRadioOtro);

                        String tipoSeleccionado = tipoSeleccionado(btnRadioPublicoGeneral, btnRadioDocente,
                                btnRadioAlumno,
                                btnRadioFuncionario);

                        Lector lector = crearLector(textNombre, textApellido, textTipoDeDocumento,
                                textNumeroDeDocumento,
                                textEmail, textNumeroDeTelefono, datePickerFechaDeNacimiento, sexoSeleccionado,
                                textNacionalidad,
                                textDomicilio, textCodigoPostal, textDepartamento, textLocalidad, textContrasenia);
                        try {
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
                            IniciarSesion login = new IniciarSesion();
                            login.setVisible(true);
                            Registrarse.this.dispose();
                        } catch (PersistenceException exception) {
                            HibernateConnection.getCurrentSession().getTransaction().rollback();
                            JOptionPane.showInternalMessageDialog(null, "Ya existe un usuario con esos datos", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showInternalMessageDialog(null, "Existen datos ingresados no válidos");
                }

            } else {
                JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos para poder guardar");
            }
        });
    }

    /**
     * Creates the window
     */
    public void ventana() {
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 648, 557);
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
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        return contentPane;
    }

    
    /**
	 * Creates a field
	 * @return password field when password is wrong
	 */
    
    private JPasswordField textRepetirContrasenia() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setToolTipText("");
        passwordField.setBackground(Color.WHITE);
        passwordField.setBounds(458, 447, 180, 30);
        return passwordField;
    }

    
    
    /**
     * Creates a panel
     * @return a container
     */
    
    public JPanel panelPrestabook() {
        JPanel panelPrestabook = new JPanel();
        panelPrestabook.setBounds(0, 0, 648, 100);
        panelPrestabook.setBackground(new Color(0, 64, 128));
        panelPrestabook.setLayout(null);

        return panelPrestabook;
    }

    
    /**
     * Creates a label
     *
     */
    
    public JLabel lblPrestabook() {
        JLabel lblPrestabook = new JLabel("PrestaBook");
        lblPrestabook.setBounds(214, 30, 263, 42);
        lblPrestabook.setForeground(Color.WHITE);
        lblPrestabook.setFont(new Font("Verdana", Font.BOLD, 32));
        return lblPrestabook;
    }

    
    
    
    /**
     * Creates a label
     *@return a label to sign up
     */
    
    public JLabel lblRegistrarse() {
        JLabel lblRegistrarse = new JLabel("Registrarse");
        lblRegistrarse.setBounds(265, 106, 180, 23);
        lblRegistrarse.setFont(new Font("Verdana", Font.BOLD, 17));
        return lblRegistrarse;
    }

    
    
    /**
     * Creates a label
     *@return a label to the name
     */
    public JLabel lblNombre() {
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(24, 137, 59, 14);
        return lblNombre;
    }

    
    /**
     * Creates a text field
     *@return a text field to insert the name 
     */
    public JTextField textNombre() {
        JTextField textNombre = new JTextField();
        textNombre.setBounds(24, 154, 180, 30);
        textNombre.setForeground(Color.GRAY);
        textNombre.setColumns(10);
        textNombre.setBackground(new Color(255, 255, 255));
        return textNombre;
    }

    
    
    
    /**
     * Creates a label
     *@return a label to the surname
     */
    public JLabel lblApellido() {
        JLabel lblApellido = new JLabel("Apellido");
        lblApellido.setBounds(24, 195, 59, 14);
        return lblApellido;
    }

    
    /**
     * Creates a text field
     *@return a text field to insert the surname 
     */
    public JTextField textApellido() {
        JTextField textApellido = new JTextField();
        textApellido.setBounds(24, 217, 180, 30);
        textApellido.setForeground(Color.GRAY);
        textApellido.setColumns(10);
        textApellido.setBackground(new Color(255, 255, 255));
        return textApellido;
    }

    
    /**
     * Creates a label
     *@return a label to the type of dni
     */
    public JLabel lblTipoDeDocumento() {
        JLabel lblTipoDeDocumento = new JLabel("Tipo de documento");
        lblTipoDeDocumento.setBounds(24, 258, 119, 14);
        return lblTipoDeDocumento;
    }

    
    /**
     * Creates a text field
     *@return a text field to insert the type of dni
     */
    public JTextField textTipoDeDocumento() {
        JTextField textTipoDeDocumento = new JTextField();
        textTipoDeDocumento.setBounds(24, 271, 180, 30);
        textTipoDeDocumento.setForeground(Color.GRAY);
        textTipoDeDocumento.setColumns(10);
        textTipoDeDocumento.setBackground(Color.WHITE);
        return textTipoDeDocumento;
    }
    
    
    /**
     * Creates a label
     *@return a label to the dni number
     */
    public JLabel lblNumeroDeDocumento() {
        JLabel lblNumeroDeDocumento = new JLabel("Numero de documento");
        lblNumeroDeDocumento.setBounds(24, 312, 135, 14);
        return lblNumeroDeDocumento;
    }

    
    
    /**
     * Creates a text field formated
     *@return dni number formated
     */
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

    
    
    
    /**
     * Creates a text field
     *@return a text field to insert the email
     */
    public JTextField textEmail() {
        JTextField textEmail = new JTextField();
        textEmail.setForeground(Color.GRAY);
        textEmail.setColumns(10);
        textEmail.setBackground(Color.WHITE);
        textEmail.setBounds(241, 154, 180, 30);
        return textEmail;
    }

    
    /**
     * Creates a label
     *@return a label to the email
     */
    public JLabel lblEmail() {
        JLabel lblEmail = new JLabel("Correo electrónico");
        lblEmail.setBounds(241, 137, 159, 14);
        return lblEmail;
    }

    
    /**
     * Creates a label
     *@return a label to the phone number
     */
    public JLabel lblNumeroDeTelefono() {
        JLabel lblNumeroDeTelefono = new JLabel("Numero de telefono");
        lblNumeroDeTelefono.setBounds(241, 195, 133, 14);
        return lblNumeroDeTelefono;
    }

    
    
    /**
     * Creates a text field
     *@return a text field to insert the phone number
     */
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

    
    /**
     * Creates a label
     *@return a label to the birth date
     */
    public JLabel lblFechaDeNacimiento() {
        JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
        lblFechaDeNacimiento.setBounds(241, 258, 139, 14);
        return lblFechaDeNacimiento;
    }

    
    /**
     * Creates a date picker
     *@return a birth date
     */
    public DatePicker datePickerFechaDeNacimiento() {
        DatePicker datePickerFechaDeNacimiento = new DatePicker();
        datePickerFechaDeNacimiento.getComponentDateTextField().setSize(110, 30);
        datePickerFechaDeNacimiento.setForeground(Color.GRAY);
        datePickerFechaDeNacimiento.setBackground(Color.WHITE);
        datePickerFechaDeNacimiento.setBounds(241, 271, 180, 30);
        datePickerFechaDeNacimiento.setSettings(DateSettings.getDatePickerSettings());
        datePickerFechaDeNacimiento.setDate(LocalDate.now().minusYears(17));
        DatePickerSettings settings = DateSettings.getDatePickerSettings();
        datePickerFechaDeNacimiento.setSettings(settings);
        settings.setVetoPolicy(new UnderAgeVetoPolicy());

        return datePickerFechaDeNacimiento;
    }

    
    /**
     * Creates a label
     *@return a label to nationality
     */
    public JLabel lblNacionalidad() {
        JLabel lblNacionalidad = new JLabel("Nacionalidad");
        lblNacionalidad.setBounds(241, 312, 108, 14);
        return lblNacionalidad;
    }

    
    /**
     * Creates a text field
     *@return a text field to insert the nationality
     */
    public JTextField textNacionalidad() {
        JTextField textNacionalidad = new JTextField();
        textNacionalidad.setForeground(Color.GRAY);
        textNacionalidad.setColumns(10);
        textNacionalidad.setBackground(Color.WHITE);
        textNacionalidad.setBounds(241, 331, 180, 30);
        return textNacionalidad;
    }

    
    /**
     * Creates a label
     *@return a label to address
     */
    public JLabel lblDomicilio() {
        JLabel lblDomicilio = new JLabel("Domicilio");
        lblDomicilio.setBounds(458, 137, 75, 14);
        return lblDomicilio;
    }

    
    /**
     * Creates a text field
     *@return a text field to insert the address
     */
    public JTextField textDomicilio() {
        JTextField textDomicilio = new JTextField();
        textDomicilio.setForeground(Color.GRAY);
        textDomicilio.setColumns(10);
        textDomicilio.setBackground(Color.WHITE);
        textDomicilio.setBounds(458, 154, 180, 30);
        return textDomicilio;
    }

    
    /**
     * Creates a label
     *@return a label to zip code
     */
    public JLabel lblCodigoPostal() {
        JLabel lblCodigoPostal = new JLabel("Código postal");
        lblCodigoPostal.setBounds(458, 195, 105, 14);
        return lblCodigoPostal;
    }

    
    /**
     * Creates a text field
     *@return a text field to insert the zip code
     *
     */ 
    public JTextField textCodigoPostal() {
        JTextField textCodigoPostal = new JTextField();
        textCodigoPostal.setForeground(Color.GRAY);
        textCodigoPostal.setColumns(10);
        textCodigoPostal.setBackground(Color.WHITE);
        textCodigoPostal.setBounds(458, 217, 180, 30);
        return textCodigoPostal;
    }
    
    
    
    /**
     * Creates a label
     *@return a label to the district
     */
    public JLabel lblDepartamento() {
        JLabel lblDepartamento = new JLabel("Departamento");
        lblDepartamento.setBounds(458, 258, 108, 14);
        return lblDepartamento;
    }

    
    
    /**
     * Creates a text field
     *@return a text field to insert the district
     *
     */ 
    public JTextField textDepartamento() {
        JTextField textDepartamento = new JTextField();
        textDepartamento.setForeground(Color.GRAY);
        textDepartamento.setColumns(10);
        textDepartamento.setBackground(Color.WHITE);
        textDepartamento.setBounds(458, 271, 180, 30);
        return textDepartamento;
    }

    
    
    /**
     * Creates a label
     *@return a label to the city
     */
    public JLabel lblLocalidad() {
        JLabel lblLocalidad = new JLabel("Localidad");
        lblLocalidad.setBounds(458, 312, 75, 14);
        return lblLocalidad;
    }

    
    /**
     * Creates a text field
     *@return a text field to insert the city
     *
     */ 
    public JTextField textLocalidad() {
        JTextField textLocalidad = new JTextField();
        textLocalidad.setForeground(Color.GRAY);
        textLocalidad.setColumns(10);
        textLocalidad.setBackground(Color.WHITE);
        textLocalidad.setBounds(458, 331, 180, 30);
        return textLocalidad;
    }

    
    /**
     * Creates a label
     *@return a label to sign up like
     */
    public JLabel lblRegistrarseComo() {
        JLabel lblRegistrarseComo = new JLabel("Registrarse como");
        lblRegistrarseComo.setBounds(24, 366, 614, 14);
        return lblRegistrarseComo;
    }

    
    /**
     * Creates a panel
     *@return a container
     */
    public JPanel panelRegistrarseComo() {
        JPanel panelRegistrarseComo = new JPanel();
        panelRegistrarseComo.setBorder(new LineBorder(new Color(128, 128, 128)));
        panelRegistrarseComo.setBounds(24, 381, 614, 38);
        panelRegistrarseComo.setLayout(null);
        return panelRegistrarseComo;
    }

    
    
    /**
     * Creates a button to sign up as funcionary
     *@return selected type
     */
    public JRadioButton btnRadioFuncionario() {
        JRadioButton btnRadioFuncionario = new JRadioButton("Funcionario", false);
        btnRadioFuncionario.setBounds(44, 7, 109, 23);
        btnRadioFuncionario.setFocusPainted(false);
        return btnRadioFuncionario;
    }

    
    /**
     * Creates a button to sign up as student
     *@return selected type
     */
    public JRadioButton btnRadioAlumno() {
        JRadioButton btnRadioAlumno = new JRadioButton("Alumno", false);
        btnRadioAlumno.setBounds(196, 7, 101, 23);
        btnRadioAlumno.setFocusPainted(false);
        return btnRadioAlumno;
    }

    /**
     * Creates a button to sign up as teacher
     *@return selected type
     */
    public JRadioButton btnRadioDocente() {
        JRadioButton btnRadioDocente = new JRadioButton("Docente", false);
        btnRadioDocente.setBounds(333, 7, 108, 23);
        btnRadioDocente.setFocusPainted(false);
        return btnRadioDocente;
    }

    
    /**
     * Creates a button to sign up as general public
     *@return selected type
     */
    public JRadioButton btnRadioPublicoGeneral() {
        JRadioButton btnRadioPublicoGeneral = new JRadioButton("Publico general", false);
        btnRadioPublicoGeneral.setBounds(454, 7, 125, 23);
        btnRadioPublicoGeneral.setFocusPainted(false);
        return btnRadioPublicoGeneral;
    }

    
    /**
     * Creates a label
     *@return a label to the sex
     */
    public JLabel lblSexo() {
        JLabel lblSexo = new JLabel("Sexo");
        lblSexo.setBounds(24, 430, 59, 14);
        return lblSexo;
    }

    /**
     * Creates a panel
     *@return a container with options of sex
     */
    public JPanel panelSexo() {
        JPanel panelSexo = new JPanel();
        panelSexo.setBorder(new LineBorder(new Color(128, 128, 128)));
        panelSexo.setBounds(24, 448, 215, 30);
        panelSexo.setLayout(null);
        return panelSexo;
    }

    
    /**
     * Creates a button to sign up as male
     *@return selected type
     */
    public JRadioButton btnRadioHombre() {
        JRadioButton btnRadioHombre = new JRadioButton("Hombre");
        btnRadioHombre.setBounds(6, 7, 73, 16);
        btnRadioHombre.setFocusPainted(false);
        return btnRadioHombre;
    }

    /**
     * Creates a button to sign up as female
     *@return selected type
     */
    public JRadioButton btnRadioMujer() {
        JRadioButton btnRadioMujer = new JRadioButton("Mujer");
        btnRadioMujer.setBounds(81, 7, 63, 16);
        btnRadioMujer.setFocusPainted(false);
        return btnRadioMujer;
    }

    /**
     * Creates a button to sign up as other
     *@return selected type
     */
    public JRadioButton btnRadioOtro() {
        JRadioButton btnRadioOtro = new JRadioButton("Otro");
        btnRadioOtro.setBounds(146, 7, 63, 16);
        btnRadioOtro.setFocusPainted(false);
        return btnRadioOtro;
    }

    /**
     * Creates a label
     *@return a label to the password
     */
    public JLabel lblContrasenia() {
        JLabel lblContrasenia = new JLabel("Contraseña");
        lblContrasenia.setBounds(265, 430, 147, 14);
        return lblContrasenia;
    }

    
    /**
     * Creates a label
     *@return a label to repeat the password
     */
    public JLabel lblRepetirContrasenia() {
        JLabel lblContrasenia = new JLabel("Repetir contraseña");
        lblContrasenia.setBounds(458, 430, 180, 14);
        return lblContrasenia;
    }

    
    /**
     * Creates a password field
     *@return a text field to insert the password
     *
     */ 
    public JPasswordField textContrasenia() {
        JPasswordField textContrasenia = new JPasswordField();
        textContrasenia.setBounds(265, 447, 180, 30);
        textContrasenia.setToolTipText("");
        textContrasenia.setBackground(Color.WHITE);
        return textContrasenia;
    }

    
    /**
     * Creates a save button
     */
    public JButton btnGuardar() {
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(197, 512, 119, 23);
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.setBackground(Color.WHITE);
        return btnGuardar;
    }

    /**
     * Creates a back button
     */
    
    public JButton btnRegresar() {
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(326, 512, 119, 23);
        btnRegresar.setForeground(Color.BLACK);
        btnRegresar.setBackground(Color.WHITE);
        return btnRegresar;
    }

    
    /**
     * Creates a exit button
     */
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
     * @return the sex selected
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
     * @return the user type
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
     * @return a reader
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

    public JLabel lblNroNoValido() {
        JLabel lblNroNoValido = new JLabel("Nro no válido en arg");
        lblNroNoValido.setFont(new Font("Tahoma", Font.PLAIN, 9));
        lblNroNoValido.setBounds(337, 246, 133, 14);
        lblNroNoValido.setForeground(new Color(170, 0, 0));
        lblNroNoValido.setVisible(false);
        return lblNroNoValido;
    }

    public JLabel lblCondicionContrasenia() {
        JLabel lblCondicionContrasenia = new JLabel(
                "Utiliza ocho caracteres como mínimo con una combinación de letras, números y símbolos");
        lblCondicionContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 9));
        lblCondicionContrasenia.setBounds(265, 477, 383, 14);
        return lblCondicionContrasenia;
    }
}
