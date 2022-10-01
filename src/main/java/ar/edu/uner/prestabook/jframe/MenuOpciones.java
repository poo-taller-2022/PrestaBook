package ar.edu.uner.prestabook.jframe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import ar.edu.uner.prestabook.persistence.AreaTematicaDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MenuOpciones extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JLabel areasTematicas;
	private JButton btnPrestarADomicilio;
	private JButton btnPrestarEnSala;
	private JButton btnGestionarDevolución;
	private JButton btnListarLectoresMorosos;
	private JButton btnListarMasBuscadasPorPublicoGeneral;
	private JButton btnListarEjemplaresDisponiblesPorArea;
	private JButton btnListarEjemplaresDisponiblesPorFecha;
	private JButton btnListarLectoresMultadosPorPeriodo;
	private JButton btnListarRankingMultados;
	private JButton ListarObrasPorEditorial;
	private JButton btnExit;
	private JLabel lblNewLabel;
	public JLabel textUsusario;
	private JButton btnAplicarMultaALector;

	/**
	 * Create the frame.
	 */
	
	public MenuOpciones(Connection conn) {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		iniciarComponentes(conn);
		
		setTitle("Opciones");
		setLocationRelativeTo(null);
		
	}

	private void iniciarComponentes(Connection conn) {
		setBounds(100, 100, 598, 727);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		areasTematicas = new JLabel("Elegir una opcion");
		areasTematicas.setFont(new Font("Times New Roman", Font.BOLD, 21));
		areasTematicas.setBounds(212, 27, 156, 30);
		contentPane.add(areasTematicas);
		
		JButton btnListarAreasTematicas = new JButton("Listar areas tematicas");
		btnListarAreasTematicas.setBounds(101, 68, 382, 23);
		btnListarAreasTematicas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AreaTematicaDAO a = new AreaTematicaDAO(conn);
				JOptionPane.showInternalMessageDialog(null, a.findAll());
			}
		});
		
		
		contentPane.add(btnListarAreasTematicas);
		
		btnPrestarADomicilio = new JButton("Prestar obra a domicilio");
		btnPrestarADomicilio.setBounds(101, 106, 382, 23);
		contentPane.add(btnPrestarADomicilio);
		
		btnPrestarEnSala = new JButton("Prestar obra en sala");
		btnPrestarEnSala.setBounds(101, 148, 382, 23);
		contentPane.add(btnPrestarEnSala);
		
		btnGestionarDevolución = new JButton("Gestionar devolución de obra");
		btnGestionarDevolución.setBounds(101, 188, 382, 23);
		contentPane.add(btnGestionarDevolución);
		
		btnListarLectoresMorosos = new JButton("Listar lectores morosos");
		btnListarLectoresMorosos.setBounds(101, 225, 382, 23);
		contentPane.add(btnListarLectoresMorosos);
		
		JButton btnListarMasBuscadasPorAlumnosDocentes = new JButton("Listar obras mas buscadas por alumnos y docentes");
		btnListarMasBuscadasPorAlumnosDocentes.setBounds(101, 269, 382, 23);
		contentPane.add(btnListarMasBuscadasPorAlumnosDocentes);
		
		btnListarMasBuscadasPorPublicoGeneral = new JButton("Listar obras mas buscadas por publico en general");
		btnListarMasBuscadasPorPublicoGeneral.setBounds(101, 311, 382, 23);
		contentPane.add(btnListarMasBuscadasPorPublicoGeneral);
		
		btnListarEjemplaresDisponiblesPorArea = new JButton("Listar ejemplares disponibles por area");
		btnListarEjemplaresDisponiblesPorArea.setBounds(101, 357, 382, 23);
		contentPane.add(btnListarEjemplaresDisponiblesPorArea);
		
		btnListarEjemplaresDisponiblesPorFecha = new JButton("Listar ejemplares disponibles a partir de una fecha");
		btnListarEjemplaresDisponiblesPorFecha.setBounds(101, 407, 382, 23);
		contentPane.add(btnListarEjemplaresDisponiblesPorFecha);
		
		btnListarLectoresMultadosPorPeriodo = new JButton("Listar lectores multados por periodo");
		btnListarLectoresMultadosPorPeriodo.setBounds(101, 449, 382, 23);
		contentPane.add(btnListarLectoresMultadosPorPeriodo);
		
		btnListarRankingMultados = new JButton("Listar ranking de multados");
		btnListarRankingMultados.setBounds(101, 496, 382, 23);
		contentPane.add(btnListarRankingMultados);
		
		ListarObrasPorEditorial = new JButton("Listar obras por editorial ");
		ListarObrasPorEditorial.setBounds(101, 541, 382, 23);
		contentPane.add(ListarObrasPorEditorial);
		
		btnAplicarMultaALector = new JButton("Aplicar multa a lector");
		btnAplicarMultaALector.setBounds(101, 588, 382, 23);
		contentPane.add(btnAplicarMultaALector);
		
		btnExit = new JButton("X");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Roboto Black", Font.PLAIN, 12));
		btnExit.setBackground(new Color(255, 106, 106));
		btnExit.setBounds(551, 0, 47, 25);
		contentPane.add(btnExit);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarSesion login = new IniciarSesion(conn);
				login.setVisible(true);
				MenuOpciones.this.dispose();
			}
		});
		btnRegresar.setForeground(Color.BLACK);
		btnRegresar.setBackground(Color.WHITE);
		btnRegresar.setBounds(233, 640, 119, 23);
		contentPane.add(btnRegresar);
		
		lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(0, 6, 47, 14);
		contentPane.add(lblNewLabel);
		
		textUsusario = new JLabel("");
		textUsusario.setForeground(new Color(0, 128, 255));
		textUsusario.setBounds(57, 2, 205, 23);
		contentPane.add(textUsusario);
		
	}
}
