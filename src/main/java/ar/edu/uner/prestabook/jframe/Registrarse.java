package ar.edu.uner.prestabook.jframe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.uner.prestabook.model.Funcionario;
import ar.edu.uner.prestabook.model.Lector;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Objects;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;

public class Registrarse extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */

	public Registrarse(Connection conn) {

		/**
		 * Create components
		 */

		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 557);
		setLocationRelativeTo(null);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelPrestabook = new JPanel();
		panelPrestabook.setBounds(0, 0, 648, 100);
		panelPrestabook.setBackground(new Color(0, 64, 128));
		contentPane.add(panelPrestabook);
		panelPrestabook.setLayout(null);

		JLabel lblPrestabook = new JLabel("PrestaBook");
		lblPrestabook.setBounds(214, 30, 263, 42);
		lblPrestabook.setForeground(Color.WHITE);
		lblPrestabook.setFont(new Font("Verdana", Font.BOLD, 32));
		panelPrestabook.add(lblPrestabook);

		JLabel lblRegistrarse = new JLabel("Registrarse");
		lblRegistrarse.setBounds(265, 106, 180, 23);
		lblRegistrarse.setFont(new Font("Verdana", Font.BOLD, 17));
		contentPane.add(lblRegistrarse);

		JTextField textNombre = new JTextField();
		textNombre.setBounds(24, 154, 180, 30);
		textNombre.setForeground(Color.GRAY);
		textNombre.setColumns(10);
		textNombre.setBackground(new Color(255, 255, 255));
		contentPane.add(textNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(24, 195, 59, 14);
		contentPane.add(lblApellido);

		JLabel lblNumeroDeTelefono = new JLabel("Numero de telefono");
		lblNumeroDeTelefono.setBounds(241, 195, 133, 14);
		contentPane.add(lblNumeroDeTelefono);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(241, 258, 139, 14);
		contentPane.add(lblFechaDeNacimiento);

		JTextField textNumeroDeTelefono = new JTextField();
		textNumeroDeTelefono.setBounds(241, 217, 180, 30);
		textNumeroDeTelefono.setForeground(Color.GRAY);
		textNumeroDeTelefono.setColumns(10);
		textNumeroDeTelefono.setBackground(new Color(255, 255, 255));
		contentPane.add(textNumeroDeTelefono);

		JTextField textApellido = new JTextField();
		textApellido.setBounds(24, 217, 180, 30);
		textApellido.setForeground(Color.GRAY);
		textApellido.setColumns(10);
		textApellido.setBackground(new Color(255, 255, 255));
		contentPane.add(textApellido);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(24, 137, 59, 14);
		contentPane.add(lblNombre);

		JTextField textTipoDeDocumento = new JTextField();
		textTipoDeDocumento.setBounds(24, 271, 180, 30);
		textTipoDeDocumento.setForeground(Color.GRAY);
		textTipoDeDocumento.setColumns(10);
		textTipoDeDocumento.setBackground(Color.WHITE);
		contentPane.add(textTipoDeDocumento);

		JLabel lblNumeroDeDocumento = new JLabel("Numero de documento");
		lblNumeroDeDocumento.setBounds(24, 312, 135, 14);
		contentPane.add(lblNumeroDeDocumento);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(79, 429, 59, 14);
		contentPane.add(lblSexo);

		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(241, 312, 108, 14);
		contentPane.add(lblNacionalidad);

		JTextField textNumeroDeDocumento = new JTextField();
		textNumeroDeDocumento.setBounds(24, 331, 180, 30);
		textNumeroDeDocumento.setForeground(Color.GRAY);
		textNumeroDeDocumento.setColumns(10);
		textNumeroDeDocumento.setBackground(Color.WHITE);
		contentPane.add(textNumeroDeDocumento);

		JLabel lblTipoDeDocumento = new JLabel("Tipo de documento");
		lblTipoDeDocumento.setBounds(24, 258, 119, 14);
		contentPane.add(lblTipoDeDocumento);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(458, 137, 75, 14);
		contentPane.add(lblDomicilio);

		JLabel lblCodigoPostal = new JLabel("Código postal");
		lblCodigoPostal.setBounds(458, 195, 105, 14);
		contentPane.add(lblCodigoPostal);

		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(458, 258, 108, 14);
		contentPane.add(lblDepartamento);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(458, 312, 75, 14);
		contentPane.add(lblLocalidad);

		JLabel lblEmail = new JLabel("Correo electrónico");
		lblEmail.setBounds(241, 137, 91, 14);
		contentPane.add(lblEmail);

		JPasswordField textContrasenia = new JPasswordField();
		textContrasenia.setBounds(386, 446, 180, 30);
		textContrasenia.setToolTipText("");
		textContrasenia.setBackground(Color.WHITE);
		contentPane.add(textContrasenia);

		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(386, 429, 147, 14);
		contentPane.add(lblContrasenia);

		JTextField textFechaDeNacimiento = new JTextField();
		textFechaDeNacimiento.setForeground(Color.GRAY);
		textFechaDeNacimiento.setColumns(10);
		textFechaDeNacimiento.setBackground(Color.WHITE);
		textFechaDeNacimiento.setBounds(241, 271, 180, 30);
		contentPane.add(textFechaDeNacimiento);

		JTextField textDomicilio = new JTextField();
		textDomicilio.setForeground(Color.GRAY);
		textDomicilio.setColumns(10);
		textDomicilio.setBackground(Color.WHITE);
		textDomicilio.setBounds(458, 154, 180, 30);
		contentPane.add(textDomicilio);

		JTextField textDepartamento = new JTextField();
		textDepartamento.setForeground(Color.GRAY);
		textDepartamento.setColumns(10);
		textDepartamento.setBackground(Color.WHITE);
		textDepartamento.setBounds(458, 271, 180, 30);
		contentPane.add(textDepartamento);

		JTextField textCodigoPostal = new JTextField();
		textCodigoPostal.setForeground(Color.GRAY);
		textCodigoPostal.setColumns(10);
		textCodigoPostal.setBackground(Color.WHITE);
		textCodigoPostal.setBounds(458, 217, 180, 30);
		contentPane.add(textCodigoPostal);

		JTextField textNacionalidad = new JTextField();
		textNacionalidad.setForeground(Color.GRAY);
		textNacionalidad.setColumns(10);
		textNacionalidad.setBackground(Color.WHITE);
		textNacionalidad.setBounds(241, 331, 180, 30);
		contentPane.add(textNacionalidad);

		JTextField textLocalidad = new JTextField();
		textLocalidad.setForeground(Color.GRAY);
		textLocalidad.setColumns(10);
		textLocalidad.setBackground(Color.WHITE);
		textLocalidad.setBounds(458, 331, 180, 30);
		contentPane.add(textLocalidad);

		JTextField textEmail = new JTextField();
		textEmail.setForeground(Color.GRAY);
		textEmail.setColumns(10);
		textEmail.setBackground(Color.WHITE);
		textEmail.setBounds(241, 154, 180, 30);
		contentPane.add(textEmail);

		ButtonGroup buttonGroupTipo = new ButtonGroup();

		JRadioButton btnRadioHombre = new JRadioButton("Hombre");
		buttonGroupTipo.add(btnRadioHombre);
		btnRadioHombre.setBounds(91, 450, 75, 23);
		contentPane.add(btnRadioHombre);

		JRadioButton btnRadioMujer = new JRadioButton("Mujer");
		buttonGroupTipo.add(btnRadioMujer);
		btnRadioMujer.setBounds(168, 450, 67, 23);
		contentPane.add(btnRadioMujer);

		JRadioButton btnRadioOtro = new JRadioButton("Otro");
		buttonGroupTipo.add(btnRadioOtro);
		btnRadioOtro.setBounds(231, 450, 59, 23);
		contentPane.add(btnRadioOtro);

		JPanel panelSexo = new JPanel();
		panelSexo.setBorder(new LineBorder(new Color(128, 128, 128)));
		panelSexo.setBounds(79, 445, 222, 31);
		contentPane.add(panelSexo);
		panelSexo.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel.setBounds(46, 381, 560, 38);
		contentPane.add(panel);
		panel.setLayout(null);

		ButtonGroup buttonGroupSexo = new ButtonGroup();

		JRadioButton btnRadioFuncionario = new JRadioButton("Funcionario", false);
		btnRadioFuncionario.setBounds(19, 7, 109, 23);
		panel.add(btnRadioFuncionario);
		buttonGroupSexo.add(btnRadioFuncionario);

		JRadioButton btnRadioAlumno = new JRadioButton("Alumno", false);
		btnRadioAlumno.setBounds(171, 7, 101, 23);
		panel.add(btnRadioAlumno);
		buttonGroupSexo.add(btnRadioAlumno);

		JRadioButton btnRadioDocente = new JRadioButton("Docente", false);
		btnRadioDocente.setBounds(308, 7, 108, 23);
		panel.add(btnRadioDocente);
		buttonGroupSexo.add(btnRadioDocente);

		JRadioButton btnRadioPublicoGeneral = new JRadioButton("Publico general", false);
		btnRadioPublicoGeneral.setBounds(429, 7, 125, 23);
		panel.add(btnRadioPublicoGeneral);
		buttonGroupSexo.add(btnRadioPublicoGeneral);

		JLabel lblNewLabel = new JLabel("Registrase como");
		lblNewLabel.setBounds(46, 366, 97, 14);
		contentPane.add(lblNewLabel);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(197, 512, 119, 23);
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setBackground(Color.WHITE);
		contentPane.add(btnGuardar);

		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(326, 512, 119, 23);
		btnRegresar.setForeground(Color.BLACK);
		btnRegresar.setBackground(Color.WHITE);
		contentPane.add(btnRegresar);

		JButton btnExit = new JButton("X");
		btnExit.setBorderPainted(false);
		btnExit.setBorder(null);
		btnExit.setFocusPainted(false);
		btnExit.setBounds(601, 0, 47, 25);
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Roboto Black", Font.PLAIN, 12));
		btnExit.setBackground(new Color(255, 106, 106));
		panelPrestabook.add(btnExit);

		/**
		 * Method created to instantiate a reader object with the data entered in the
		 * fields and send it to the database
		 **/

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Boolean camposCompletos = !textNombre.getText().isBlank() && !textApellido.getText().isBlank()
						&& !textTipoDeDocumento.getText().isBlank() && !textNumeroDeDocumento.getText().isBlank()
						&& !textEmail.getText().isBlank() && !textNumeroDeTelefono.getText().isBlank()
						&& !textFechaDeNacimiento.getText().isBlank() && !textNacionalidad.getText().isBlank()
						&& !textDomicilio.getText().isBlank() && !textCodigoPostal.getText().isBlank()
						&& !textDepartamento.getText().isBlank() && !textLocalidad.getText().isBlank()
						&& (btnRadioHombre.isSelected() || btnRadioMujer.isSelected() || btnRadioOtro.isSelected())
						&& (btnRadioPublicoGeneral.isSelected() || btnRadioDocente.isSelected()
								|| btnRadioAlumno.isSelected() || btnRadioFuncionario.isSelected())
						&& !(String.valueOf(textContrasenia.getPassword()).isBlank());

				if (Boolean.TRUE.equals(camposCompletos)) {
					String sexoSeleccionado = sexoSeleccionado(btnRadioHombre, btnRadioMujer, btnRadioOtro);

					String tipoSeleccionado = tipoSeleccionado(btnRadioPublicoGeneral, btnRadioDocente, btnRadioAlumno,
							btnRadioFuncionario);

					Lector lector = crearLector(textNombre, textApellido, textTipoDeDocumento, textNumeroDeDocumento,
							textEmail, textNumeroDeTelefono, textFechaDeNacimiento, sexoSeleccionado, textNacionalidad,
							textDomicilio, textCodigoPostal, textDepartamento, textLocalidad, textContrasenia);

					if (Objects.equals(tipoSeleccionado, btnRadioFuncionario.getText())) {
						Funcionario funcionario = new Funcionario();
						funcionario.registrarse(conn, lector);
						JOptionPane.showInternalMessageDialog(null,
								"Datos del " + tipoSeleccionado + " guardados correctamente");
					} else {
						lector.registrarse(tipoSeleccionado, conn, lector);
						JOptionPane.showInternalMessageDialog(null,
								"Datos del " + tipoSeleccionado + " guardados correctamente");
					} 
				} else {
					JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos para poder guardar");
				}
			}
		});

		/**
		 * Method created to return to the "Login" window
		 **/

		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarSesion login = new IniciarSesion(conn);
				login.setVisible(true);
				Registrarse.this.dispose();
			}
		});

		/**
		 * Created method to close window
		 */

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

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
			JTextField textFechaDeNacimiento, String sexoSeleccionado, JTextField textNacionalidad,
			JTextField textDomicilio, JTextField textCodigoPostal, JTextField textDepartamento,
			JTextField textLocalidad, JTextField textContrasenia) {
		Lector lector = new Lector();
		lector.setNombre(textNombre.getText());
		lector.setApellido(textApellido.getText());
		lector.setTipoDocumento(textTipoDeDocumento.getText());
		lector.setDocumento(textNumeroDeDocumento.getText());
		lector.setEmail(textEmail.getText());
		lector.setCelular(textNumeroDeTelefono.getText());
		lector.setFechaNacimiento(textFechaDeNacimiento.getText());
		lector.setSexo(sexoSeleccionado);
		lector.setNacionalidad(textNacionalidad.getText());
		lector.setDomicilio(textDomicilio.getText());
		lector.setCodigoPostal(textCodigoPostal.getText());
		lector.setDepartamento(textDepartamento.getText());
		lector.setLocalidad(textLocalidad.getText());
		lector.setContrasenia(String.valueOf(((JPasswordField) textContrasenia).getPassword()));

		return lector;
	}
}
