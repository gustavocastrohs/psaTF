/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import interfaces.IFachadaEstacionamento;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import negocio.EstacionamentoException;
import negocio.FachadaEstacionamento;
import negocio.TipoDeTicket;

/**
 *
 * @author Gustavo
 */
@WebService(serviceName = "WSCancelaEntrada")
public class WSCancelaEntrada {

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "emitirTicket")
    public String emitirTicket(@WebParam(name = "placa") String placa) throws EstacionamentoException {
        //TODO write your implementation code here:
        IFachadaEstacionamento f = FachadaEstacionamento.getInstace();
        
        return f.emisssaoDeTicketAutomatico(placa, TipoDeTicket.TicketSimples);
    }
}
