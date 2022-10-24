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

    private static final String PATH = "src/main/resources/codes/";

    public ImageIcon buscarCodigoBarras(String id) {
        return new ImageIcon(new File(PATH + id + ".png").getAbsolutePath());
    }

    public void generarCodigoBarras(CodigoIdentificatorio codigo) {
        try {
            final File tempDirectory = new File(PATH);
            File newDirectory = new File(tempDirectory.getAbsolutePath());
            if (newDirectory.mkdir() || newDirectory.isDirectory()) {
                File file = new File(PATH + codigo.getCodigo() + ".png");
                Barcode bar = BarcodeFactory.createCode128(codigo.toString());
                BarcodeImageHandler.savePNG(bar, file);
            }
        } catch (BarcodeException | OutputException ex) {
            ex.printStackTrace();
        }
    }
}
