package ar.edu.uner.prestabook.jframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.uner.prestabook.persistence.AreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.LoginDAO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.DropMode;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField cajaCorreo;
	private JPasswordField cajaContrasenia;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnExit;
	private JButton btnRegistrarse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		
		LoginDAO loginDAO = new LoginDAO();
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(72, 53, 247));
		panel.setBounds(0, 0, 648, 100);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("PrestaBook");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Roboto Black", Font.PLAIN, 26));
		lblNewLabel.setBounds(259, 28, 151, 42);
		panel.add(lblNewLabel);
		
		btnExit = new JButton("X");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnExit.setFont(new Font("Roboto Black", Font.PLAIN, 12));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(255, 106, 106));
		btnExit.setBounds(601, 0, 47, 25);
		panel.add(btnExit);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(189, 236, 66, 14);
		contentPane.add(lblContrasenia);
		
		JLabel lblIniciarSesin = new JLabel("Iniciar sesión");
		lblIniciarSesin.setFont(new Font("Roboto Black", Font.PLAIN, 17));
		lblIniciarSesin.setBounds(265, 131, 119, 14);
		contentPane.add(lblIniciarSesin);
		
		cajaCorreo = new JTextField();
		cajaCorreo.setBackground(new Color(211, 211, 211));
		cajaCorreo.setForeground(new Color(128, 128, 128));
		cajaCorreo.setColumns(10);
		cajaCorreo.setBounds(262, 180, 180, 30);
		contentPane.add(cajaCorreo);
		
		cajaContrasenia = new JPasswordField();
		cajaContrasenia.setToolTipText("");
		cajaContrasenia.setBackground(new Color(211, 211, 211));
		cajaContrasenia.setBounds(262, 228, 180, 30);
		contentPane.add(cajaContrasenia);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(189, 188, 59, 14);
		contentPane.add(lblCorreo);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz interfaz = new Interfaz();
				interfaz.setVisible(true);
				Login.this.dispose();
				
				String busquedaUsuario = loginDAO.buscarUsuarioRegistrado(cajaCorreo.getText(), cajaContrasenia.getText());
				if (cajaCorreo.getText().equals("root") && cajaContrasenia.getText().equals("root")) {
					JOptionPane.showInternalMessageDialog(null, "Bienvenido, ingresante sesión como un root (Administrador)");
					interfaz.textUsusario.setText("Administrador");
					Login.this.dispose();
				} else if (busquedaUsuario.equals("usuario encontrado")) {
					String busquedaNombre = loginDAO.buscarNombre(cajaCorreo.getText());
					JOptionPane.showInternalMessageDialog(null, "Bienvenido (a) \n" +  busquedaNombre);
					interfaz.textUsusario.setText(busquedaNombre);
					Login.this.dispose();
				} else {
					JOptionPane.showInternalMessageDialog(null, "Usuario no registrado");
				}
			}
		});
		btnIngresar.setForeground(new Color(0, 0, 0));
		btnIngresar.setBackground(new Color(255, 255, 255));
		btnIngresar.setBounds(295, 314, 89, 23);
		contentPane.add(btnIngresar);
		
		JLabel lblNewLabel_1 = new JLabel("¿No estas registrado?");
		lblNewLabel_1.setBounds(228, 388, 141, 14);
		contentPane.add(lblNewLabel_1);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registrarse registrarse = new Registrarse();
				registrarse.setVisible(true);
				Login.this.dispose();
			}
		});
		btnRegistrarse.setBorderPainted(false);
		btnRegistrarse.setForeground(new Color(0, 0, 255));
		btnRegistrarse.setBounds(372, 384, 119, 23);
		contentPane.add(btnRegistrarse);
	}
}