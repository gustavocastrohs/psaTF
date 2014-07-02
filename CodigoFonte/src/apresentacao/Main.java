/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apresentacao;

import interfaces.IFachadaEstacionamento;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.EstacionamentoException;
import negocio.FachadaEstacionamento;
import negocio.TipoDeTicket;

/**
 *
 * @author 09201801
 */
public class Main {
 
public static void main(String[] args) {
    try {
        FachadaEstacionamento f = FachadaEstacionamento.getInstace();
     //   f.emisssaoDeTicketAutomatico("ass9781", TipoDeTicket.TicketSimples);
        System.out.println(f.usuarioGeraCodigoDeBarras("ass9781", "YLN6Q"));
    } catch (EstacionamentoException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}