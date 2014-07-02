/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.castrohs.sb;

import br.com.castrohs.ejb.ICancelaEntrada;
import br.com.castrohs.ejb.TipoDeTicket;
import br.com.castrohs.modelo.CancelaEntrada;
import br.com.castrohs.modelo.EstacionamentoException;
import javax.ejb.Stateless;

/**
 *
 * @author Gustavo
 */
@Stateless
public class SBCancelaEntrada implements SBCancelaEntradaLocal {

    @Override
    public String emisssaoDeTicketAutomatico(String placa) throws EstacionamentoException {
        ICancelaEntrada c = new CancelaEntrada();
        
        return c.emisssaoDeTicketAutomatico(placa, TipoDeTicket.TicketSimples);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
