/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import negocio.EstacionamentoException;
import negocio.FachadaEstacionamento;

/**
 *
 * @author 09201801
 */

@WebService(serviceName = "WSCancelaEntrada")
public class WSCancelaEntrada {

    /**
     * This is a sample web service operation
     * @return 
     * @throws negocio.EstacionamentoException
     */
   @WebMethod(operationName = "emitirTicket")
    public String emitirTicket() throws EstacionamentoException {
        //TODO write your implementation code here:
        FachadaEstacionamento f = FachadaEstacionamento.getInstace();
        
        return f.emisssaoDeTicketAutomatico();
    }
}
