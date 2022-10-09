package ar.edu.uner.prestabook.jframe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.io.File;
import java.sql.Connection;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class SistemaLector extends JFrame {



	private static final long serialVersionUID = 1L;
	public JLabel textUsusario;

	/**
	 * Create the frame.
	 */

	public SistemaLector(Connection conn) {

		/**
		 * Create components
		 */

		ventana();
		
		JPanel contentPane = contentPane();

		JPanel panelPrestabook = panelPrestabook();
		contentPane.add(panelPrestabook);
		
		JLabel lblIconCerrarSesion = lblIconCerrarSesion();
		panelPrestabook.add(lblIconCerrarSesion);
		JLabelImage(lblIconCerrarSesion);

		panelPrestabook.add(lblPrestabook());

		JButton btnExit = btnExit();
		panelPrestabook.add(btnExit);

		JButton btnCerrarSesion = btnCerrarSesion();
		panelPrestabook.add(btnCerrarSesion);

		JPanel panelOpciones = panelOpciones();
		contentPane.add(panelOpciones);
		
		JButton btnSolicitudes = btnSolicitudes();
		panelOpciones.add(btnSolicitudes);
		
		panelOpciones.add(lblUsuario());
		panelOpciones.add(textUsusario());
		panelOpciones.add(panelSeparador());
		panelOpciones.add(lblOpciones());

		JButton btnReservarObraParaDomicilio = btnReservarObraParaDomicilio();
		panelOpciones.add(btnReservarObraParaDomicilio);

		JButton btnReservarParaSala = btnReservarParaSala();
		panelOpciones.add(btnReservarParaSala);

		contentPane.add(lblBienvenidaParte1());
		contentPane.add(lblBienvenidaParte2());
		contentPane.add(lblIconLibreria());

		/**
		 * Method created to log out and return to the "IniciarSesion" window
		 */

		btnCerrarSesion.addActionListener(e -> {
				IniciarSesion login = new IniciarSesion(conn);
				login.setVisible(true);
				SistemaLector.this.dispose();
		});

		/**
		 * Created method to close window
		 */

		btnExit.addActionListener(e -> System.exit(0));
	}
	 
	/**
	 * Create components
	 */
	
	public void ventana() {
		setUndecorated(true); 
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Opciones");
		setBounds(100, 100, 1326, 811);
		setLocationRelativeTo(null);
	}
	
	public JPanel contentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		return contentPane;
	}
	
	public JPanel panelPrestabook () {
		JPanel panelPrestabook = new JPanel();
		panelPrestabook.setBounds(339, 0, 988, 103);
		panelPrestabook.setBorder(null);
		panelPrestabook.setBackground(new Color(0, 64, 128));
		panelPrestabook.setLayout(null);
		return panelPrestabook;
	}
	
	public JLabel lblIconCerrarSesion() {
		JLabel lblIconCerrarSesion = new JLabel("");
		lblIconCerrarSesion.setBounds(817, 5, 19, 16);
		return lblIconCerrarSesion;
	}
	
	public void JLabelImage(JLabel lblIconCerrarSesion) {
		ImageIcon image = new ImageIcon(new File("src/main/resources/Vector.png").getAbsolutePath());
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(lblIconCerrarSesion.getWidth(),
				lblIconCerrarSesion.getHeight(), Image.SCALE_DEFAULT));
		lblIconCerrarSesion.setIcon(icon);
		this.repaint();
	}
	
	public JLabel lblPrestabook() {
		JLabel lblPrestabook = new JLabel("PrestaBook");
		lblPrestabook.setBounds(399, 30, 267, 42);
		lblPrestabook.setForeground(Color.WHITE);
		lblPrestabook.setFont(new Font("Verdana", Font.BOLD, 32));
		return lblPrestabook;
	}
	
	public JButton btnExit() {
		JButton btnExit = new JButton("X");
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Verdana", Font.BOLD, 12));
		btnExit.setBackground(new Color(255, 106, 106));
		btnExit.setBounds(941, 0, 47, 25);
		return btnExit;
	}
	
	public JButton btnCerrarSesion() {
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font("Verdana", Font.BOLD, 11));
		btnCerrarSesion.setFocusPainted(false);
		btnCerrarSesion.setBorderPainted(false);
		btnCerrarSesion.setBorder(null);
		btnCerrarSesion.setBounds(841, 2, 101, 23);
		btnCerrarSesion.setForeground(new Color(255, 255, 255));
		btnCerrarSesion.setBackground(new Color(0, 64, 128));
		return btnCerrarSesion;
	}
	
	public JPanel panelOpciones() {
		JPanel panelOpciones = new JPanel();
		panelOpciones.setBounds(0, 0, 341, 811);
		panelOpciones.setBackground(new Color(0, 45, 89));
		panelOpciones.setLayout(null);
		return panelOpciones;
	}
	
	public JLabel lblUsuario() {
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(27, 0, 122, 37);
		lblUsuario.setFont(new Font("Verdana", Font.BOLD, 17));
		lblUsuario.setForeground(new Color(255, 255, 255));
		return lblUsuario;
	}
	
	public JLabel textUsusario() {
		textUsusario = new JLabel("");
		textUsusario.setBackground(new Color(0, 128, 0));
		textUsusario.setBounds(124, 0, 173, 37);
		textUsusario.setFont(new Font("Verdana", Font.BOLD, 16));
		textUsusario.setForeground(new Color(255, 255, 255));
		return textUsusario;
	}
	
	public JButton btnSolicitudes() {
		JButton btnSolicitudes = new JButton("Solicitudes");
		btnSolicitudes.setVerifyInputWhenFocusTarget(false);
		btnSolicitudes.setForeground(new Color(0, 64, 128));
		btnSolicitudes.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSolicitudes.setFocusPainted(false);
		btnSolicitudes.setBorderPainted(false);
		btnSolicitudes.setBorder(null);
		btnSolicitudes.setBackground(Color.WHITE);
		btnSolicitudes.setBounds(23, 74, 293, 31);
		return btnSolicitudes;
	}
	
	public JPanel panelSeparador() {
		JPanel panelSeparador = new JPanel();
		panelSeparador.setBounds(23, 121, 292, 3);
		return panelSeparador;
	}
	
	public JLabel lblOpciones() {
		JLabel lblOpciones = new JLabel("Opciones");
		lblOpciones.setForeground(new Color(255, 255, 255));
		lblOpciones.setFont(new Font("Verdana", Font.BOLD, 16));
		lblOpciones.setBounds(133, 135, 105, 23);
		return lblOpciones;
	}
	
	public JButton btnReservarObraParaDomicilio() {
		JButton btnReservarObraParaDomicilio = new JButton("Reservar obra para domicilio");
		btnReservarObraParaDomicilio.setFocusPainted(false);
		btnReservarObraParaDomicilio.setBackground(new Color(255, 255, 255));
		btnReservarObraParaDomicilio.setVerifyInputWhenFocusTarget(false);
		btnReservarObraParaDomicilio.setBorderPainted(false);
		btnReservarObraParaDomicilio.setBorder(null);
		btnReservarObraParaDomicilio.setFont(new Font("Verdana", Font.BOLD, 12));
		btnReservarObraParaDomicilio.setForeground(new Color(0, 64, 128));
		btnReservarObraParaDomicilio.setBounds(22, 169, 293, 31);
		return btnReservarObraParaDomicilio;
	}
	
	public JButton btnReservarParaSala() {
		JButton btnReservarParaSala = new JButton("Reservar obra para sala");
		btnReservarParaSala.setFocusPainted(false);
		btnReservarParaSala.setBackground(new Color(255, 255, 255));
		btnReservarParaSala.setForeground(new Color(0, 64, 128));
		btnReservarParaSala.setFont(new Font("Verdana", Font.BOLD, 12));
		btnReservarParaSala.setBorderPainted(false);
		btnReservarParaSala.setBounds(22, 220, 293, 31);
		return btnReservarParaSala;
	}
	
	public JLabel lblBienvenidaParte1() {
		JLabel lblBienvenidaParte1 = new JLabel("¡Bienvenido al sistema de gestión de préstamos de libros más");
		lblBienvenidaParte1.setForeground(Color.GRAY);
		lblBienvenidaParte1.setFont(new Font("Verdana", Font.BOLD, 21));
		lblBienvenidaParte1.setBounds(476, 109, 775, 136);
		return lblBienvenidaParte1;
	}
	
	public JLabel lblBienvenidaParte2() {
		JLabel lblBienvenidaParte2 = new JLabel("grande del mundo!");
		lblBienvenidaParte2.setForeground(Color.GRAY);
		lblBienvenidaParte2.setFont(new Font("Verdana", Font.BOLD, 21));
		lblBienvenidaParte2.setBounds(730, 170, 369, 136);
		return lblBienvenidaParte2;
	}
	
	public JLabel lblIconLibreria () {
		JLabel lblIconLibreria = new JLabel("");
		lblIconLibreria.setIcon(
				new ImageIcon(new File("src/main/resources/library.png").getAbsolutePath()));
		lblIconLibreria.setBounds(548, 292, 605, 493);
		return lblIconLibreria;
	}

}
