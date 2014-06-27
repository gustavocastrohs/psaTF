/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import SB.SBCancelaEntrada;
import SB.SBCancelaEntradaLocal;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import negocio.EstacionamentoException;
import negocio.ITicket;

/**
 *
 * @author Gustavo
 */
@WebService(serviceName = "WSCancelaEntrada")
@Stateless()
public class WSCancelaEntrada {
    @EJB
    private SBCancelaEntradaLocal sBCancelaEntrada;

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "emitirTicket")
    public SBCancelaEntrada emitirTicket(@WebParam(name = "placa") String placa) throws EstacionamentoException {
        //TODO write your implementation code here:
        return null;
    }
    
}
