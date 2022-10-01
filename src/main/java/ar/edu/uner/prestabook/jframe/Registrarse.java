package ar.edu.uner.prestabook.jframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.uner.prestabook.persistence.LoginDAO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registrarse extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JPasswordField passwordField;
	private JButton btnGuardar;
	private JTextField textCorreo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrarse frame = new Registrarse();
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
	
	LoginDAO loginDao = new LoginDAO();
	private JTextField textApellido;
	
	public Registrarse() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 460);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 641, 103);
		panel.setBackground(new Color(72, 53, 247));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PrestaBook");
		lblNewLabel.setBounds(259, 28, 151, 42);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Roboto Black", Font.PLAIN, 26));
		panel.add(lblNewLabel);
		
		JButton btnExit = new JButton("X");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(601, 0, 47, 25);
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Roboto Black", Font.PLAIN, 12));
		btnExit.setBackground(new Color(255, 106, 106));
		panel.add(btnExit);
		
		JLabel lblRegistrarse = new JLabel("Registrarse");
		lblRegistrarse.setFont(new Font("Roboto Black", Font.PLAIN, 17));
		lblRegistrarse.setBounds(268, 114, 112, 30);
		contentPane.add(lblRegistrarse);
		
		textNombre = new JTextField();
		textNombre.setForeground(Color.GRAY);
		textNombre.setColumns(10);
		textNombre.setBackground(new Color(211, 211, 211));
		textNombre.setBounds(228, 155, 180, 30);
		contentPane.add(textNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(164, 213, 59, 14);
		contentPane.add(lblApellido);
		
		JLabel lblCorreo = new JLabel("Correo electronico");
		lblCorreo.setBounds(117, 267, 112, 14);
		contentPane.add(lblCorreo);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBackground(new Color(211, 211, 211));
		passwordField.setBounds(228, 313, 180, 30);
		contentPane.add(passwordField);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(147, 321, 72, 14);
		contentPane.add(lblContrasenia);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i = loginDao.guardar(textNombre.getText(), textApellido.getText(), textCorreo.getText(), passwordField.getText());
				if (i > 0) {
					JOptionPane.showInternalMessageDialog(null, "Datos guardados correctamente");
				} else {
					JOptionPane.showInternalMessageDialog(null, "No se pudo guardar los datos");
				}
			}
		});
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setBounds(182, 371, 119, 23);
		contentPane.add(btnGuardar);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				Registrarse.this.dispose();
			}
		});
		btnRegresar.setForeground(Color.BLACK);
		btnRegresar.setBackground(Color.WHITE);
		btnRegresar.setBounds(336, 371, 119, 23);
		contentPane.add(btnRegresar);
		
		textCorreo = new JTextField();
		textCorreo.setForeground(Color.GRAY);
		textCorreo.setColumns(10);
		textCorreo.setBackground(new Color(211, 211, 211));
		textCorreo.setBounds(228, 259, 180, 30);
		contentPane.add(textCorreo);
		
		textApellido = new JTextField();
		textApellido.setForeground(Color.GRAY);
		textApellido.setColumns(10);
		textApellido.setBackground(new Color(211, 211, 211));
		textApellido.setBounds(228, 205, 180, 30);
		contentPane.add(textApellido);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(164, 163, 59, 14);
		contentPane.add(lblNombre);
	}
}
