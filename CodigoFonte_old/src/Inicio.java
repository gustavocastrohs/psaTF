
import apresentacao.FachadaException;
import apresentacao.TelaPrincipal;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gustavo
 */
public class Inicio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            TelaPrincipal t;
            
            t = new TelaPrincipal();
            t.setVisible(true);
        } catch (FachadaException ex) {
            System.out.println("Não pode ser iniciado");
        }
       
        
    }
}
