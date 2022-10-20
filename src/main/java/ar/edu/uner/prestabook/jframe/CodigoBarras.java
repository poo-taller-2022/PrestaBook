package ar.edu.uner.prestabook.jframe;

import java.io.File;

import javax.swing.ImageIcon;

import ar.edu.uner.prestabook.model.CodigoIdentificatorio;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

public class CodigoBarras {
	public ImageIcon buscarCodigoBarras(String id) {
		return new ImageIcon(new File("src/main/resources/codes/" + id + ".png").getAbsolutePath());
	}
	
	public void generarCodigoBarras(CodigoIdentificatorio codigo) {
		File file = new File("src/main/resources/codes/" + codigo.getCodigo() + ".png");
		try {
		    Barcode bar = BarcodeFactory.createCode128(codigo.toString());
		    BarcodeImageHandler.savePNG(bar, file); 
		    
		} catch (BarcodeException ex) {
		    System.out.println(ex);
		} catch (OutputException ex) {
			System.out.println(ex);
		}
	}
}


	
	
		

