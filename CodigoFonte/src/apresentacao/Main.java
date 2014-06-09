/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apresentacao;

import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.EstacionamentoException;
import negocio.FachadaEstacionamento;

/**
 *
 * @author 09201801
 */
public class Main {
 
public static void main(String[] args) {
    try {
        FachadaEstacionamento f = new FachadaEstacionamento();
        
    } catch (EstacionamentoException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}