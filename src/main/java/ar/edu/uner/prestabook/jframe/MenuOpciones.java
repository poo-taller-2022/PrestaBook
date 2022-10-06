package ar.edu.uner.prestabook.jframe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
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
	private static final long serialVersionUID = 1L;
	JLabel textUsusario;

	/**
	 * Create the frame.
	 */
	
	public MenuOpciones(Connection conn) {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		iniciarComponentes(conn);
		
		setTitle("Opciones");
		setLocationRelativeTo(null);
	}

	private void iniciarComponentes(Connection conn) {
		setBounds(100, 100, 598, 727);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel areasTematicas = new JLabel("Elegir una opcion");
		areasTematicas.setFont(new Font("Times New Roman", Font.BOLD, 21));
		areasTematicas.setBounds(212, 27, 156, 30);
		contentPane.add(areasTematicas);
		
		JButton btnListarAreasTematicas = new JButton("Listar areas tematicas   ");
		btnListarAreasTematicas.setBounds(101, 68, 382, 23);
		btnListarAreasTematicas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AreaTematicaDAO a = new AreaTematicaDAO(conn);
				JOptionPane.showInternalMessageDialog(null, a.findAll());
			}
		});
		
		contentPane.add(btnListarAreasTematicas);
		
		JButton btnPrestarADomicilio = new JButton("Prestar obra a domicilio");
		btnPrestarADomicilio.setBounds(101, 106, 382, 23);
		contentPane.add(btnPrestarADomicilio);
		
		JButton btnPrestarEnSala = new JButton("Prestar obra en sala");
		btnPrestarEnSala.setBounds(101, 148, 382, 23);
		contentPane.add(btnPrestarEnSala);
		
		JButton btnGestionarDevolucion = new JButton("Gestionar devolución de obra");
		btnGestionarDevolucion.setBounds(101, 188, 382, 23);
		contentPane.add(btnGestionarDevolucion);
		
		JButton btnListarLectoresMorosos = new JButton("Listar lectores morosos");
		btnListarLectoresMorosos.setBounds(101, 225, 382, 23);
		contentPane.add(btnListarLectoresMorosos);
		
		JButton btnListarMasBuscadasPorAlumnosDocentes = new JButton("Listar obras mas buscadas por alumnos y docentes");
		btnListarMasBuscadasPorAlumnosDocentes.setBounds(101, 269, 382, 23);
		contentPane.add(btnListarMasBuscadasPorAlumnosDocentes);
		
		JButton btnListarMasBuscadasPorPublicoGeneral = new JButton("Listar obras mas buscadas por publico en general");
		btnListarMasBuscadasPorPublicoGeneral.setBounds(101, 311, 382, 23);
		contentPane.add(btnListarMasBuscadasPorPublicoGeneral);
		
		JButton btnListarEjemplaresDisponiblesPorArea = new JButton("Listar ejemplares disponibles por area");
		btnListarEjemplaresDisponiblesPorArea.setBounds(101, 357, 382, 23);
		contentPane.add(btnListarEjemplaresDisponiblesPorArea);
		
		JButton btnListarEjemplaresDisponiblesPorFecha = new JButton("Listar ejemplares disponibles a partir de una fecha");
		btnListarEjemplaresDisponiblesPorFecha.setBounds(101, 407, 382, 23);
		contentPane.add(btnListarEjemplaresDisponiblesPorFecha);
		
		JButton btnListarLectoresMultadosPorPeriodo = new JButton("Listar lectores multados por periodo");
		btnListarLectoresMultadosPorPeriodo.setBounds(101, 449, 382, 23);
		contentPane.add(btnListarLectoresMultadosPorPeriodo);
		
		JButton btnListarRankingMultados = new JButton("Listar ranking de multados");
		btnListarRankingMultados.setBounds(101, 496, 382, 23);
		contentPane.add(btnListarRankingMultados);
		
		JButton ListarObrasPorEditorial = new JButton("Listar obras por editorial ");
		ListarObrasPorEditorial.setBounds(101, 541, 382, 23);
		contentPane.add(ListarObrasPorEditorial);
		
		JButton btnAplicarMultaALector = new JButton("Aplicar multa a lector");
		btnAplicarMultaALector.setBounds(101, 588, 382, 23);
		contentPane.add(btnAplicarMultaALector);
		
		JButton btnExit = new JButton("X");
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
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(0, 6, 47, 14);
		contentPane.add(lblNewLabel);
		
		textUsusario = new JLabel("");
		textUsusario.setForeground(new Color(0, 128, 255));
		textUsusario.setBounds(57, 2, 205, 23);
		contentPane.add(textUsusario);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 27, 104, 23);
		contentPane.add(panel);
	}
}
