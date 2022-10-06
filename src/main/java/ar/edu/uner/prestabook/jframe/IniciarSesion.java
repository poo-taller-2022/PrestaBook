package ar.edu.uner.prestabook.jframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.uner.prestabook.persistence.UsuarioDAO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;


public class IniciarSesion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(Connection conn) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciarSesion frame = new IniciarSesion(conn);
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
	
	public IniciarSesion(Connection conn) {
		
		UsuarioDAO usuariosDAO = new UsuarioDAO(conn);
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(72, 53, 247));
		panel.setBounds(0, 0, 648, 100);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPrestaBook = new JLabel("PrestaBook");
		lblPrestaBook.setForeground(new Color(255, 255, 255));
		lblPrestaBook.setFont(new Font("Roboto Black", Font.PLAIN, 26));
		lblPrestaBook.setBounds(243, 30, 151, 42);
		panel.add(lblPrestaBook);
		
		JButton btnExit = new JButton("X");
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
		lblContrasenia.setBounds(149, 236, 66, 14);
		contentPane.add(lblContrasenia);
		
		JLabel lblIniciarSesin = new JLabel("Iniciar sesión");
		lblIniciarSesin.setFont(new Font("Roboto Black", Font.PLAIN, 17));
		lblIniciarSesin.setBounds(262, 131, 119, 14);
		contentPane.add(lblIniciarSesin);
		
		JTextField cajaCorreo = new JTextField();
		cajaCorreo.setBackground(new Color(211, 211, 211));
		cajaCorreo.setForeground(new Color(128, 128, 128));
		cajaCorreo.setColumns(10);
		cajaCorreo.setBounds(234, 180, 180, 30);
		contentPane.add(cajaCorreo);
		
		JPasswordField cajaContrasenia = new JPasswordField();
		cajaContrasenia.setToolTipText("");
		cajaContrasenia.setBackground(new Color(211, 211, 211));
		cajaContrasenia.setBounds(234, 228, 180, 30);
		contentPane.add(cajaContrasenia);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(149, 188, 59, 14);
		contentPane.add(lblCorreo);
		 
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cajaCorreo.getText().isBlank() && !(String.valueOf(cajaContrasenia.getPassword()).isBlank())) {
					String busquedaUsuario = usuariosDAO.buscarUsuarioRegistrado(cajaCorreo.getText(), String.valueOf(cajaContrasenia.getPassword()));
					if (busquedaUsuario.equals("usuario encontrado")) {
						MenuOpciones interfaz = new MenuOpciones(conn);
						interfaz.setVisible(true);
						IniciarSesion.this.dispose();
						String busquedaNombre = usuariosDAO.buscarNombre(cajaCorreo.getText());
						JOptionPane.showInternalMessageDialog(null, "Bienvenido (a) \n" +  busquedaNombre);
						interfaz.textUsusario.setText(busquedaNombre);
					} else {
						JOptionPane.showInternalMessageDialog(null, "Usuario no registrado");
					}
				} else {
					JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos para poder ingresar");
				}
			}
		});
		
		btnIngresar.setForeground(new Color(0, 0, 0));
		btnIngresar.setBackground(new Color(255, 255, 255));
		btnIngresar.setBounds(268, 314, 89, 23);
		contentPane.add(btnIngresar);
		
		JLabel lblNoRegistrado = new JLabel("¿No estas registrado?");
		lblNoRegistrado.setBounds(179, 388, 141, 14);
		contentPane.add(lblNoRegistrado);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registrarse registrarse = new Registrarse(conn);
				registrarse.setVisible(true);
				IniciarSesion.this.dispose();
			}
		});
		btnRegistrarse.setBorderPainted(false);
		btnRegistrarse.setForeground(new Color(0, 0, 255));
		btnRegistrarse.setBounds(316, 384, 119, 23);
		contentPane.add(btnRegistrarse);
	}
}