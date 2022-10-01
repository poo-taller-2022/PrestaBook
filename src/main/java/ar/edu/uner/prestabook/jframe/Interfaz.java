package ar.edu.uner.prestabook.jframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import ar.edu.uner.prestabook.persistence.AreaTematicaDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextField;

public class Interfaz extends JFrame {

	private JPanel contentPane;
	private JLabel areasTematicas;
	private JButton btnPrestarADomicilio;
	private JButton btnListarAreasTematicas_2;
	private JButton btnListarAreasTematicas_3;
	private JButton btnListarAreasTematicas_4;
	private JButton btnListarAreasTematicas_1;
	private JButton btnListarAreasTematicas_5;
	private JButton btnListarAreasTematicas_6;
	private JButton btnListarAreasTematicas_7;
	private JButton btnListarAreasTematicas_8;
	private JButton btnListarAreasTematicas_9;
	private JButton btnListarAreasTematicas_10;
	private JButton btnListarAreasTematicas_11;
	private JButton btnExit;
	private JLabel lblNewLabel;
	public JLabel textUsusario;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
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
	public Interfaz() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		iniciarComponentes();
		
		setTitle("Opciones");
		setLocationRelativeTo(null);
		
	}

	private void iniciarComponentes() {
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
				AreaTematicaDAO a = new AreaTematicaDAO();
				JOptionPane.showInternalMessageDialog(null, a.findAll());
			}
		});
		
		
		contentPane.add(btnListarAreasTematicas);
		
		btnPrestarADomicilio = new JButton("Prestar obra a domicilio");
		btnPrestarADomicilio.setBounds(101, 106, 382, 23);
		contentPane.add(btnPrestarADomicilio);
		
		btnListarAreasTematicas_2 = new JButton("Prestar obra en sala");
		btnListarAreasTematicas_2.setBounds(101, 148, 382, 23);
		contentPane.add(btnListarAreasTematicas_2);
		
		btnListarAreasTematicas_3 = new JButton("Gestionar devolución de obra");
		btnListarAreasTematicas_3.setBounds(101, 188, 382, 23);
		contentPane.add(btnListarAreasTematicas_3);
		
		btnListarAreasTematicas_4 = new JButton("Listar lectores morosos");
		btnListarAreasTematicas_4.setBounds(101, 225, 382, 23);
		contentPane.add(btnListarAreasTematicas_4);
		
		btnListarAreasTematicas_1 = new JButton("Listar obras mas buscadas por alumnos y docentes");
		btnListarAreasTematicas_1.setBounds(101, 269, 382, 23);
		contentPane.add(btnListarAreasTematicas_1);
		
		btnListarAreasTematicas_5 = new JButton("Listar obras mas buscadas por publico en general");
		btnListarAreasTematicas_5.setBounds(101, 311, 382, 23);
		contentPane.add(btnListarAreasTematicas_5);
		
		btnListarAreasTematicas_6 = new JButton("Listar ejemplares disponibles por area");
		btnListarAreasTematicas_6.setBounds(101, 357, 382, 23);
		contentPane.add(btnListarAreasTematicas_6);
		
		btnListarAreasTematicas_7 = new JButton("Listar ejemplares disponibles a partir de una fecha");
		btnListarAreasTematicas_7.setBounds(101, 407, 382, 23);
		contentPane.add(btnListarAreasTematicas_7);
		
		btnListarAreasTematicas_8 = new JButton("Listar lectores multados por periodo");
		btnListarAreasTematicas_8.setBounds(101, 449, 382, 23);
		contentPane.add(btnListarAreasTematicas_8);
		
		btnListarAreasTematicas_9 = new JButton("Listar ranking de multados");
		btnListarAreasTematicas_9.setBounds(101, 496, 382, 23);
		contentPane.add(btnListarAreasTematicas_9);
		
		btnListarAreasTematicas_10 = new JButton("Listar obras por editorial ");
		btnListarAreasTematicas_10.setBounds(101, 541, 382, 23);
		contentPane.add(btnListarAreasTematicas_10);
		
		btnListarAreasTematicas_11 = new JButton("Aplicar multa a lector");
		btnListarAreasTematicas_11.setBounds(101, 588, 382, 23);
		contentPane.add(btnListarAreasTematicas_11);
		
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
				Login login = new Login();
				login.setVisible(true);
				Interfaz.this.dispose();
			}
		});
		btnRegresar.setForeground(Color.BLACK);
		btnRegresar.setBackground(Color.WHITE);
		btnRegresar.setBounds(233, 640, 119, 23);
		contentPane.add(btnRegresar);
		
		lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(0, 6, 62, 14);
		contentPane.add(lblNewLabel);
		
		textUsusario = new JLabel("");
		textUsusario.setBounds(52, 0, 205, 30);
		contentPane.add(textUsusario);
		
	}

}
