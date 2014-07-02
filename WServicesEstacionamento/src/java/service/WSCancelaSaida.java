/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import negocio.EstacionamentoException;
import negocio.FachadaEstacionamento;
import interfaces.IFachadaEstacionamento;

/**
 *
 * @author Gustavo
 */
@WebService(serviceName = "WSCancelaSaida")
public class WSCancelaSaida {

    /**
     * Operação de Web service
     * @param codigo
     * @return 
     * @throws negocio.EstacionamentoException
     */
    @WebMethod(operationName = "validaTicket")
    public String validaTicket(@WebParam(name = "codigo") String codigo) throws EstacionamentoException {
        IFachadaEstacionamento  f = FachadaEstacionamento.getInstace();
        try{
        f.validacaoDeTicketCancelaSaida(Integer.parseInt(codigo));
        }catch(NumberFormatException e){
        throw  new EstacionamentoException("O valor inserido não é um numero");
        }
        return "";
    }
}
