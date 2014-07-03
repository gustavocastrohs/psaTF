/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import com.onbarcode.barcode.AbstractBarcode;
import com.onbarcode.barcode.Code39;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 09201801
 */
public class GeraCodigoDeBarras {

    Code39 barcode;

    public GeraCodigoDeBarras(String info) throws Exception {

        barcode = new Code39();
        barcode.setExtension(false);

        barcode.setAddCheckSum(true);
        // barcode image resolution in dpi
        barcode.setResolution(72);
        barcode.setData(info);

        barcode.drawBarcode("C:\\code39.gif");

    }

}
