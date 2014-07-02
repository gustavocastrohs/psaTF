/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.castrohs.sb;

import br.com.castrohs.modelo.EstacionamentoException;
import javax.ejb.Local;

/**
 *
 * @author Gustavo
 */
@Local
public interface SBCancelaEntradaLocal {

    String emisssaoDeTicketAutomatico(String placa) throws EstacionamentoException;
    
}
