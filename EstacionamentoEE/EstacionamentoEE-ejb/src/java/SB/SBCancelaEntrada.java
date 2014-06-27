/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SB;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import negocio.EstacionamentoException;
import negocio.FachadaEstacionamento;
import negocio.IOPB;
import negocio.ITicket;
import negocio.OperacoesNoBanco;
import negocio.TipoDeTicket;

/**
 *
 * @author Gustavo
 */
@Stateless
public class SBCancelaEntrada implements SBCancelaEntradaLocal,Serializable {
    
    public SBCancelaEntrada(){}

    /**
     *
     * @param placa
     * @return
     * @throws negocio.EstacionamentoException
     */
    @Override
    public String emitirTicket(String placa) throws EstacionamentoException{
         String emisssaoDeTicketAutomatico ="";
     FachadaEstacionamento f;
        try {     
            f = new FachadaEstacionamento();
      

         emisssaoDeTicketAutomatico = f.emisssaoDeTicketAutomatico(placa, TipoDeTicket.TicketSimples);
        } catch (EstacionamentoException ex) {
          throw new EstacionamentoException(ex.getMessage());
        }
        return emisssaoDeTicketAutomatico;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
