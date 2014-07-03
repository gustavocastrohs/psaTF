/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import negocio.EstacionamentoException;
import negocio.FachadaEstacionamento;

/**
 *
 * @author 09201801
 */
@WebService(serviceName = "WSCancelaSaida")
public class WSCancelaSaida {

    /**
     * This is a sample web service operation
     * @param placa
     * @return 
     * @throws negocio.EstacionamentoException 
     */
 @WebMethod(operationName = "validaTicket")
    public String validaTicket(@WebParam(name = "codigo") String placa) throws EstacionamentoException {
        FachadaEstacionamento  f = FachadaEstacionamento.getInstace();
        try{
           return f.validacaoDeTicketCancelaSaida(Integer.parseInt(placa));
        }catch(NumberFormatException e){
        throw  new EstacionamentoException("O valor inserido não é um numero");
        }
      
    }
}
