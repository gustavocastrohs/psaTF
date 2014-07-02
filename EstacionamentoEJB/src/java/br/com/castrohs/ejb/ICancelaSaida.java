/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.castrohs.ejb;

import br.com.castrohs.modelo.EstacionamentoException;

/**
 *
 * @author Gustavo
 */
public interface ICancelaSaida {
     public String testaSeTicketEstaLiberado(int novoTicket) throws EstacionamentoException ;
     
}
