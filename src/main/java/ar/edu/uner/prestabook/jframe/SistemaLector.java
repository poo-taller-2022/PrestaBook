package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SistemaLector extends JFrame {

	private static final long serialVersionUID = 1L;
	public JLabel textUsusario;

	/**
	 * Create the frame.
	 */

	public SistemaLector() {

		/**
		 * Create components
		 */

		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Opciones");
		setBounds(100, 100, 1326, 811);
		setLocationRelativeTo(null);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelPrestabook = new JPanel();
		panelPrestabook.setBounds(339, 0, 988, 103);
		panelPrestabook.setBorder(null);
		panelPrestabook.setBackground(new Color(0, 64, 128));
		contentPane.add(panelPrestabook);
		panelPrestabook.setLayout(null);

		JLabel lblPrestabook = new JLabel("PrestaBook");
		lblPrestabook.setBounds(403, 27, 243, 42);
		lblPrestabook.setForeground(Color.WHITE);
		lblPrestabook.setFont(new Font("Verdana", Font.BOLD, 32));
		panelPrestabook.add(lblPrestabook);

		JButton btnExit = new JButton("X");
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Verdana", Font.BOLD, 12));
		btnExit.setBackground(new Color(255, 106, 106));
		btnExit.setBounds(941, 0, 47, 25);
		panelPrestabook.add(btnExit);

		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font("Verdana", Font.BOLD, 11));
		btnCerrarSesion.setFocusPainted(false);
		btnCerrarSesion.setBorderPainted(false);
		btnCerrarSesion.setBorder(null);
		btnCerrarSesion.setBounds(839, 1, 102, 23);
		panelPrestabook.add(btnCerrarSesion);
		btnCerrarSesion.setForeground(new Color(255, 255, 255));
		btnCerrarSesion.setBackground(new Color(0, 64, 128));

		JLabel lblIconCerrarSesion = new JLabel("");
		lblIconCerrarSesion.setBounds(817, 5, 19, 16);
		panelPrestabook.add(lblIconCerrarSesion);

		ImageIcon image = new ImageIcon(
				SistemaLector.class.getResource("/ar/edu/uner/prestabook/jframe/images/Vector.png"));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(lblIconCerrarSesion.getWidth(),
				lblIconCerrarSesion.getHeight(), Image.SCALE_DEFAULT));
		lblIconCerrarSesion.setIcon(icon);
		this.repaint();

		JPanel panelOpciones = new JPanel();
		panelOpciones.setBounds(0, 0, 341, 811);
		panelOpciones.setBackground(new Color(0, 45, 89));
		contentPane.add(panelOpciones);
		panelOpciones.setLayout(null);

		JPanel panelSeparador = new JPanel();
		panelSeparador.setBounds(23, 121, 292, 3);
		panelOpciones.add(panelSeparador);

		JLabel lblOpciones = new JLabel("Opciones");
		lblOpciones.setForeground(new Color(255, 255, 255));
		lblOpciones.setFont(new Font("Verdana", Font.BOLD, 16));
		lblOpciones.setBounds(133, 135, 105, 23);
		panelOpciones.add(lblOpciones);

		JButton btnReservarObraParaDomicilio = new JButton("Reservar obra para domicilio");
		btnReservarObraParaDomicilio.setFocusPainted(false);
		btnReservarObraParaDomicilio.setBackground(new Color(255, 255, 255));
		btnReservarObraParaDomicilio.setVerifyInputWhenFocusTarget(false);
		btnReservarObraParaDomicilio.setBorderPainted(false);
		btnReservarObraParaDomicilio.setBorder(null);
		btnReservarObraParaDomicilio.setFont(new Font("Verdana", Font.BOLD, 12));
		btnReservarObraParaDomicilio.setForeground(new Color(0, 64, 128));
		btnReservarObraParaDomicilio.setBounds(22, 169, 293, 31);
		panelOpciones.add(btnReservarObraParaDomicilio);

		JButton btnReservarParaSala = new JButton("Reservar obra para sala");
		btnReservarParaSala.setFocusPainted(false);
		btnReservarParaSala.setBackground(new Color(255, 255, 255));
		btnReservarParaSala.setForeground(new Color(0, 64, 128));
		btnReservarParaSala.setFont(new Font("Verdana", Font.BOLD, 12));
		btnReservarParaSala.setBorderPainted(false);
		btnReservarParaSala.setBounds(22, 220, 293, 31);
		panelOpciones.add(btnReservarParaSala);

		JButton btnSolicitudes = new JButton("Solicitudes");
		btnSolicitudes.setVerifyInputWhenFocusTarget(false);
		btnSolicitudes.setForeground(new Color(0, 64, 128));
		btnSolicitudes.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSolicitudes.setFocusPainted(false);
		btnSolicitudes.setBorderPainted(false);
		btnSolicitudes.setBorder(null);
		btnSolicitudes.setBackground(Color.WHITE);
		btnSolicitudes.setBounds(23, 74, 293, 31);
		panelOpciones.add(btnSolicitudes);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(27, 0, 122, 37);
		panelOpciones.add(lblUsuario);
		lblUsuario.setFont(new Font("Verdana", Font.BOLD, 17));
		lblUsuario.setForeground(new Color(255, 255, 255));

		textUsusario = new JLabel("");
		textUsusario.setBackground(new Color(0, 128, 0));
		textUsusario.setBounds(126, 0, 173, 37);
		panelOpciones.add(textUsusario);
		textUsusario.setFont(new Font("Verdana", Font.BOLD, 16));
		textUsusario.setForeground(new Color(255, 255, 255));

		JPanel panel = new JPanel();
		panel.setBounds(339, 103, 988, 708);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblBienvenidaParte1 = new JLabel("¡Bienvenido al sistema de gestión de préstamos de libros más");
		lblBienvenidaParte1.setForeground(new Color(128, 128, 128));
		lblBienvenidaParte1.setFont(new Font("Verdana", Font.BOLD, 21));
		lblBienvenidaParte1.setBounds(127, 40, 775, 136);
		panel.add(lblBienvenidaParte1);

		JLabel lblBienvenidaParte2 = new JLabel("grande del mundo!");
		lblBienvenidaParte2.setForeground(new Color(128, 128, 128));
		lblBienvenidaParte2.setFont(new Font("Verdana", Font.BOLD, 21));
		lblBienvenidaParte2.setBounds(366, 80, 369, 136);
		panel.add(lblBienvenidaParte2);

		JLabel lblIconLibreria = new JLabel("");
		lblIconLibreria.setIcon(
				new ImageIcon(SistemaLector.class.getResource("/ar/edu/uner/prestabook/jframe/images/library.png")));
		lblIconLibreria.setBounds(236, 186, 605, 493);
		panel.add(lblIconLibreria);

		/**
		 * Method created to log out and return to the "IniciarSesion" window
		 */

		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarSesion login = new IniciarSesion();
				login.setVisible(true);
				SistemaLector.this.dispose();
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

}
