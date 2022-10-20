package ar.edu.uner.prestabook.jframe;

import java.awt.Color;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.border.LineBorder;




public class CodigoBarras extends JFrame {
	
	String id;
    JButton mostrar;
    JLabel jlCod;
    
    public CodigoBarras(String id) {
    	  this.id = id;
          setSize(500, 200);
          setLocationRelativeTo(null);
          setDefaultCloseOperation(EXIT_ON_CLOSE);
          setLayout(null);
          ventana(this.id);
          
          setVisible(true);
    }
    private void ventana(String id) {
	  
	    
	    jlCod = new JLabel();
	    jlCod.setBounds(50, 70, 350, 50);
	    jlCod.setBorder(new LineBorder(Color.BLACK));
	    add(jlCod);
	    buscarCodigoBarras(id);
    }
    
    
	public void buscarCodigoBarras(String id) {
		
		File file = new File("src/main/resources/codes/" + id + ".png");
		ImageIcon icono = new ImageIcon(file.getPath());
		jlCod.setIcon(icono);
	}
	
	
}
	
	
		

