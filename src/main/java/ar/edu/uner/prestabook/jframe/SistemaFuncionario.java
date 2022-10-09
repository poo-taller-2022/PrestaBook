package ar.edu.uner.prestabook.jframe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.io.File;
import java.sql.Connection;
import java.awt.Color;

public class SistemaFuncionario extends JFrame {

	private static final long serialVersionUID = 1L;
	public JLabel textUsusario;

	/**
	 * Create the frame.
	 */

	public SistemaFuncionario(Connection conn) {

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

		JButton btnPrestarADomicilio = btnPrestarADomicilio();
		panelOpciones.add(btnPrestarADomicilio);

		JButton btnPrestarEnSala = btnPrestarEnSala();
		panelOpciones.add(btnPrestarEnSala);

		JButton btnGestionarDevolucion = btnGestionarDevolucion();
		panelOpciones.add(btnGestionarDevolucion);

		JButton btnListarLectoresMorosos = btnListarLectoresMorosos();
		panelOpciones.add(btnListarLectoresMorosos);

		JButton btnMasBuscadasPorAlumnoDocente = btnMasBuscadasPorAlumnoDocente();
		panelOpciones.add(btnMasBuscadasPorAlumnoDocente);

		JButton btnMasBuscadasPorPublicoGeneral = btnMasBuscadasPorPublicoGeneral();
		panelOpciones.add(btnMasBuscadasPorPublicoGeneral);

		JButton btnListarEjemplaresDisponiblesPorArea = btnListarEjemplaresDisponiblesPorArea();
		panelOpciones.add(btnListarEjemplaresDisponiblesPorArea);

		JButton btnListarEjemplaresDisponiblesPorFecha = btnListarEjemplaresDisponiblesPorFecha();
		panelOpciones.add(btnListarEjemplaresDisponiblesPorFecha);
		
		JButton btnListarLectoresMultadosPorPeriodo = btnListarLectoresMultadosPorPeriodo();
		panelOpciones.add(btnListarLectoresMultadosPorPeriodo);

		JButton btnListarRankingDeMultados = btnListarRankingDeMultados();
		panelOpciones.add(btnListarRankingDeMultados);

		JButton btnListarObrasPorEditorial = btnListarObrasPorEditorial();
		panelOpciones.add(btnListarObrasPorEditorial);

		JButton btnAplicarMultaALector = btnAplicarMultaALector();
		panelOpciones.add(btnAplicarMultaALector);

		contentPane.add(lblBienvenidaParte1());
		contentPane.add(lblBienvenidaParte2());
		contentPane.add(lblIconLibreria());

		/**
		 * Method created to log out and return to the "IniciarSesion" window
		 */

		btnCerrarSesion.addActionListener(e -> {
				IniciarSesion login = new IniciarSesion(conn);
				login.setVisible(true);
				SistemaFuncionario.this.dispose();
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
	
	public JButton btnPrestarADomicilio() {
		JButton btnPrestarADomicilio = new JButton("Prestar obra a domicilio");
		btnPrestarADomicilio.setFocusPainted(false);
		btnPrestarADomicilio.setBackground(new Color(255, 255, 255));
		btnPrestarADomicilio.setVerifyInputWhenFocusTarget(false);
		btnPrestarADomicilio.setBorderPainted(false);
		btnPrestarADomicilio.setBorder(null);
		btnPrestarADomicilio.setFont(new Font("Verdana", Font.BOLD, 12));
		btnPrestarADomicilio.setForeground(new Color(0, 64, 128));
		btnPrestarADomicilio.setBounds(22, 169, 293, 31);
		return btnPrestarADomicilio;
	}
	
	public JButton btnPrestarEnSala() {
		JButton btnPrestarEnSala = new JButton("Prestar obra en sala");
		btnPrestarEnSala.setFocusPainted(false);
		btnPrestarEnSala.setBackground(new Color(255, 255, 255));
		btnPrestarEnSala.setForeground(new Color(0, 64, 128));
		btnPrestarEnSala.setFont(new Font("Verdana", Font.BOLD, 12));
		btnPrestarEnSala.setBorderPainted(false);
		btnPrestarEnSala.setBounds(22, 220, 293, 31);
		return btnPrestarEnSala;
	}
	
	public JButton btnGestionarDevolucion() {
		JButton btnGestionarDevolucion = new JButton("Gestionar devolución de obra");
		btnGestionarDevolucion.setFocusPainted(false);
		btnGestionarDevolucion.setBackground(new Color(255, 255, 255));
		btnGestionarDevolucion.setForeground(new Color(0, 64, 128));
		btnGestionarDevolucion.setFont(new Font("Verdana", Font.BOLD, 12));
		btnGestionarDevolucion.setBorderPainted(false);
		btnGestionarDevolucion.setBounds(22, 272, 293, 31);
		return btnGestionarDevolucion;
	}
	
	public JButton btnListarLectoresMorosos() {
		JButton btnListarLectoresMorosos = new JButton("Listar lectores morosos");
		btnListarLectoresMorosos.setFocusPainted(false);
		btnListarLectoresMorosos.setBackground(new Color(255, 255, 255));
		btnListarLectoresMorosos.setForeground(new Color(0, 64, 128));
		btnListarLectoresMorosos.setFont(new Font("Verdana", Font.BOLD, 12));
		btnListarLectoresMorosos.setBorderPainted(false);
		btnListarLectoresMorosos.setBounds(22, 325, 293, 31);
		return btnListarLectoresMorosos;
	}
	
	public JButton btnMasBuscadasPorAlumnoDocente() {
		JButton btnMasBuscadasPorAlumnoDocente = new JButton("Mas buscadas por alumnos y docentes");
		btnMasBuscadasPorAlumnoDocente.setFocusPainted(false);
		btnMasBuscadasPorAlumnoDocente.setBackground(new Color(255, 255, 255));
		btnMasBuscadasPorAlumnoDocente.setForeground(new Color(0, 64, 128));
		btnMasBuscadasPorAlumnoDocente.setFont(new Font("Verdana", Font.BOLD, 12));
		btnMasBuscadasPorAlumnoDocente.setBorderPainted(false);
		btnMasBuscadasPorAlumnoDocente.setBounds(22, 374, 293, 31);
		return btnMasBuscadasPorAlumnoDocente;
	}
	
	public JButton btnMasBuscadasPorPublicoGeneral() {
		JButton btnMasBuscadasPorPublicoGeneral = new JButton("Mas buscadas por publico en general");
		btnMasBuscadasPorPublicoGeneral.setFocusPainted(false);
		btnMasBuscadasPorPublicoGeneral.setBackground(new Color(255, 255, 255));
		btnMasBuscadasPorPublicoGeneral.setForeground(new Color(0, 64, 128));
		btnMasBuscadasPorPublicoGeneral.setFont(new Font("Verdana", Font.BOLD, 12));
		btnMasBuscadasPorPublicoGeneral.setBorderPainted(false);
		btnMasBuscadasPorPublicoGeneral.setBounds(22, 427, 293, 31);
		return btnMasBuscadasPorPublicoGeneral;
	}
	
	public JButton btnListarEjemplaresDisponiblesPorArea() {
		JButton btnListarEjemplaresDisponiblesPorArea = new JButton("Listar ejemplares disponibles por area");
		btnListarEjemplaresDisponiblesPorArea.setFocusPainted(false);
		btnListarEjemplaresDisponiblesPorArea.setBackground(new Color(255, 255, 255));
		btnListarEjemplaresDisponiblesPorArea.setForeground(new Color(0, 64, 128));
		btnListarEjemplaresDisponiblesPorArea.setFont(new Font("Verdana", Font.BOLD, 12));
		btnListarEjemplaresDisponiblesPorArea.setBorderPainted(false);
		btnListarEjemplaresDisponiblesPorArea.setBounds(22, 482, 293, 31);
		return btnListarEjemplaresDisponiblesPorArea;
	}
	
	public JButton btnListarEjemplaresDisponiblesPorFecha() {
		JButton btnListarEjemplaresDisponiblesPorFecha = new JButton("Listar ejemplares disponibles por fecha");
		btnListarEjemplaresDisponiblesPorFecha.setFocusPainted(false);
		btnListarEjemplaresDisponiblesPorFecha.setBackground(new Color(255, 255, 255));
		btnListarEjemplaresDisponiblesPorFecha.setForeground(new Color(0, 64, 128));
		btnListarEjemplaresDisponiblesPorFecha.setFont(new Font("Verdana", Font.BOLD, 12));
		btnListarEjemplaresDisponiblesPorFecha.setBorderPainted(false);
		btnListarEjemplaresDisponiblesPorFecha.setBounds(22, 535, 293, 31);
		return btnListarEjemplaresDisponiblesPorFecha;
	}
	
	public JButton btnListarLectoresMultadosPorPeriodo() {
		JButton btnListarLectoresMultadosPorPeriodo = new JButton("Listar lectores multados por periodo");
		btnListarLectoresMultadosPorPeriodo.setFocusPainted(false);
		btnListarLectoresMultadosPorPeriodo.setBackground(new Color(255, 255, 255));
		btnListarLectoresMultadosPorPeriodo.setForeground(new Color(0, 64, 128));
		btnListarLectoresMultadosPorPeriodo.setFont(new Font("Verdana", Font.BOLD, 12));
		btnListarLectoresMultadosPorPeriodo.setBorderPainted(false);
		btnListarLectoresMultadosPorPeriodo.setBounds(22, 592, 293, 31);
		return btnListarLectoresMultadosPorPeriodo;
	}
	
	public JButton btnListarRankingDeMultados() {
		JButton btnListarRankingDeMultados = new JButton("Listar ranking de multados");
		btnListarRankingDeMultados.setFocusPainted(false);
		btnListarRankingDeMultados.setBackground(new Color(255, 255, 255));
		btnListarRankingDeMultados.setForeground(new Color(0, 64, 128));
		btnListarRankingDeMultados.setFont(new Font("Verdana", Font.BOLD, 12));
		btnListarRankingDeMultados.setBorderPainted(false);
		btnListarRankingDeMultados.setBounds(22, 645, 293, 31);
		return btnListarRankingDeMultados;
	}
	
	public JButton btnListarObrasPorEditorial() {
		JButton btnListarObrasPorEditorial = new JButton("Listar obras por editorial ");
		btnListarObrasPorEditorial.setFocusPainted(false);
		btnListarObrasPorEditorial.setBackground(new Color(255, 255, 255));
		btnListarObrasPorEditorial.setForeground(new Color(0, 64, 128));
		btnListarObrasPorEditorial.setFont(new Font("Verdana", Font.BOLD, 12));
		btnListarObrasPorEditorial.setBorderPainted(false);
		btnListarObrasPorEditorial.setBounds(22, 701, 293, 31);
		return btnListarObrasPorEditorial;
	}
	
	public JButton btnAplicarMultaALector() {
		JButton btnAplicarMultaALector = new JButton("Aplicar multa a lector");
		btnAplicarMultaALector.setFocusPainted(false);
		btnAplicarMultaALector.setBackground(new Color(255, 255, 255));
		btnAplicarMultaALector.setForeground(new Color(0, 64, 128));
		btnAplicarMultaALector.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAplicarMultaALector.setBorderPainted(false);
		btnAplicarMultaALector.setBounds(22, 755, 293, 31);
		return btnAplicarMultaALector;
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
