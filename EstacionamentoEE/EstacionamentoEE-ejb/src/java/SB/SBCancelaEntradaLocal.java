/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SB;

import javax.ejb.Local;
import negocio.EstacionamentoException;

/**
 *
 * @author Gustavo
 */
@Local
public interface SBCancelaEntradaLocal {

    String emitirTicket(String placa) throws EstacionamentoException;
    
}
