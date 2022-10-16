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

		ventana();
		JPanel contentPane = contentPane();

		JPanel panelPrestabook = panelPrestabook();
		contentPane.add(panelPrestabook);

		panelPrestabook.add(lblPrestaBook());

		JButton btnExit = btnExit();
		panelPrestabook.add(btnExit);

		contentPane.add(lblIniciarSesion());
		contentPane.add(lblIngresarComo());

		JPanel panelIngresarComo = panelIngresarComo();
		contentPane.add(panelIngresarComo);

		ButtonGroup buttonGroupTipo = new ButtonGroup();

		JRadioButton btnRadioFuncionario = btnRadioFuncionario();
		buttonGroupTipo.add(btnRadioFuncionario);
		panelIngresarComo.add(btnRadioFuncionario);

		JRadioButton btnRadioAlumno = btnRadioAlumno();
		buttonGroupTipo.add(btnRadioAlumno);
		panelIngresarComo.add(btnRadioAlumno);

		JRadioButton btnRadioDocente = btnRadioDocente();
		buttonGroupTipo.add(btnRadioDocente);
		panelIngresarComo.add(btnRadioDocente);

		JRadioButton btnRadioPublicoGeneral = btnRadioPublicoGeneral();
		buttonGroupTipo.add(btnRadioPublicoGeneral);
		panelIngresarComo.add(btnRadioPublicoGeneral);

		contentPane.add(lblContrasenia());
		contentPane.add(lblCorreo());

		JTextField cajaCorreo = cajaCorreo();
		contentPane.add(cajaCorreo);

		JPasswordField cajaContrasenia = cajaContrasenia();
		contentPane.add(cajaContrasenia);

		JButton btnIngresar = btnIngresar();
		contentPane.add(btnIngresar);

		contentPane.add(lblNoRegistrado());

		JButton btnRegistrarse = btnRegistrarse();
		contentPane.add(btnRegistrarse);

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

		/**
		 * Method created to search for user in database
		 */

		btnIngresar.addActionListener(e -> {

			String tipoDeUsuario = encontrarTipoDeUsuario(btnRadioPublicoGeneral, btnRadioDocente, btnRadioAlumno);

			Boolean camposCompletos = (!cajaCorreo.getText().isBlank())
					&& (!(String.valueOf(cajaContrasenia.getPassword()).isBlank())
							&& (btnRadioPublicoGeneral.isSelected() || btnRadioDocente.isSelected()
									|| btnRadioAlumno.isSelected() || btnRadioFuncionario.isSelected()));

			if (Boolean.TRUE.equals(camposCompletos)) {
				IUsuarioDAO usuariosDAO = DaoFactory.getUsuarioDAO();
				String busquedaUsuario = usuariosDAO.buscarUsuarioRegistrado(tipoDeUsuario, cajaCorreo.getText(),
						String.valueOf(cajaContrasenia.getPassword()));

				if (busquedaUsuario.equals("usuario encontrado")) {
					String busquedaNombre = usuariosDAO.buscarNombre(tipoDeUsuario, cajaCorreo.getText());

					if ("Funcionarios".equals(tipoDeUsuario)) {
						SistemaFuncionario interfazSistemaFuncionario = new SistemaFuncionario();
						interfazSistemaFuncionario.setVisible(true);
						interfazSistemaFuncionario.textUsusario.setText(busquedaNombre);
					} else {
						SistemaLector interfazSistemaLector = new SistemaLector();
						interfazSistemaLector.setVisible(true);
						interfazSistemaLector.textUsuario.setText(busquedaNombre);
					}
					IniciarSesion.this.dispose();

				} else {
					JOptionPane.showInternalMessageDialog(null, "Usuario no registrado");
				}
			} else {
				JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos para poder ingresar");
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
		setBounds(100, 100, 648, 486);
		setLocationRelativeTo(null);
	}

	public JPanel contentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		return contentPane;
	}

	public JPanel panelPrestabook() {
		JPanel panelPrestabook = new JPanel();
		panelPrestabook.setBackground(new Color(0, 64, 128));
		panelPrestabook.setBounds(0, 0, 648, 100);
		panelPrestabook.setLayout(null);
		return panelPrestabook;
	}

	public JLabel lblPrestaBook() {
		JLabel lblPrestaBook = new JLabel("PrestaBook");
		lblPrestaBook.setForeground(new Color(255, 255, 255));
		lblPrestaBook.setFont(new Font("Verdana", Font.BOLD, 32));
		lblPrestaBook.setBounds(218, 34, 264, 42);
		return lblPrestaBook;
	}

	public JButton btnExit() {
		JButton btnExit = new JButton("X");
		btnExit.setBorderPainted(false);
		btnExit.setBorder(null);
		btnExit.setFocusPainted(false);
		btnExit.setFont(new Font("Roboto Black", Font.PLAIN, 12));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(255, 106, 106));
		btnExit.setBounds(601, 0, 47, 25);
		return btnExit;
	}

	public JLabel lblIniciarSesion() {
		JLabel lblIniciarSesion = new JLabel("Iniciar sesión");
		lblIniciarSesion.setFont(new Font("Verdana", Font.BOLD, 17));
		lblIniciarSesion.setBounds(258, 130, 161, 23);
		return lblIniciarSesion;
	}

	public JLabel lblIngresarComo() {
		JLabel lblIngresarComo = new JLabel("Ingresar como");
		lblIngresarComo.setBounds(39, 162, 97, 14);
		return lblIngresarComo;
	}

	public JPanel panelIngresarComo() {
		JPanel panelIngresarComo = new JPanel();
		panelIngresarComo.setLayout(null);
		panelIngresarComo.setBorder(new LineBorder(new Color(128, 128, 128)));
		panelIngresarComo.setBounds(39, 178, 560, 38);
		return panelIngresarComo;
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

	public JLabel lblContrasenia() {
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(239, 314, 66, 14);
		return lblContrasenia;
	}

	public JLabel lblCorreo() {
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(239, 238, 59, 14);
		return lblCorreo;
	}

	public JTextField cajaCorreo() {
		JTextField cajaCorreo = new JTextField();
		cajaCorreo.setBackground(new Color(255, 255, 255));
		cajaCorreo.setForeground(new Color(128, 128, 128));
		cajaCorreo.setColumns(10);
		cajaCorreo.setBounds(239, 254, 180, 30);
		return cajaCorreo;
	}

	public JPasswordField cajaContrasenia() {
		JPasswordField cajaContrasenia = new JPasswordField();
		cajaContrasenia.setToolTipText("");
		cajaContrasenia.setBackground(new Color(255, 255, 255));
		cajaContrasenia.setBounds(239, 329, 180, 30);
		return cajaContrasenia;
	}

	public JButton btnIngresar() {
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setForeground(new Color(0, 0, 0));
		btnIngresar.setBackground(new Color(255, 255, 255));
		btnIngresar.setBounds(283, 382, 89, 23);
		return btnIngresar;
	}

	public JLabel lblNoRegistrado() {
		JLabel lblNoRegistrado = new JLabel("¿No estas registrado?");
		lblNoRegistrado.setBounds(224, 432, 141, 14);
		return lblNoRegistrado;
	}

	public JButton btnRegistrarse() {
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBackground(new Color(255, 255, 255));
		btnRegistrarse.setBorder(null);
		btnRegistrarse.setBorderPainted(false);
		btnRegistrarse.setForeground(new Color(0, 64, 128));
		btnRegistrarse.setBounds(351, 428, 119, 23);
		return btnRegistrarse;
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