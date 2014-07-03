/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.sql.Timestamp;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import negocio.EstacionamentoException;
import negocio.FachadaEstacionamento;
import negocio.ITicket;
import persistencia.Ticket;

/**
 *
 * @author Gustavo
 */
@Named
@RequestScoped
public class FachadaBean {

    
    private final FachadaEstacionamento fachada = FachadaEstacionamento.getInstace();
    
    private String idTicket;
    private int dia;
    private int mes;
    private int ano;
    private String resultadoValidacaoCancelaSaida;
    private String resultadoGetContadores="";
    private String placa;
    private String chave;
    private String codigoBarras;

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }
    

    public String getResultadoGetContadores() {
        return resultadoGetContadores;
    }

    public void setResultadoGetContadores(String resultadoGetContadores) {
        this.resultadoGetContadores = resultadoGetContadores;
    }
    

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getResultadoValidacaoCancelaSaida() {
        return resultadoValidacaoCancelaSaida;
    }

    public void setResultadoValidacaoCancelaSaida(String resultadoValidacaoCancelaSaida) {
        this.resultadoValidacaoCancelaSaida = resultadoValidacaoCancelaSaida;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
    
    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }


    public void validacaoDeTicketCancelaSaida() {
        
  
          resultadoValidacaoCancelaSaida=  fachada.validacaoDeTicketCancelaSaida(Integer.parseInt(idTicket));

    }

    public void validacaoDeTicketOperador() {
        try {
            resultadoValidacaoCancelaSaida= fachada.validacaoDeTicketOperador(Integer.parseInt(idTicket));
        } catch (EstacionamentoException ex) {
            resultadoValidacaoCancelaSaida= ex.getMessage();
        }
    }

    public void usuarioGeraCodigoDeBarras() {
       codigoBarras= fachada.usuarioGeraCodigoDeBarras(placa, chave);
    }

    public String calcularOQueTemQueSerPago(String ticket) {
        try {
            return fachada.calcularOQueTemQueSerPago(Integer.parseInt(ticket));
        } catch (EstacionamentoException ex) {
            return ex.getMessage();
        }
    }

    public String emisssaoDeTicketAutomatico(String placa) {
        try {
            return fachada.emisssaoDeTicketAutomatico(placa);
        } catch (EstacionamentoException ex) {
            return ex.getMessage();
        }
    }

    public String emisssaoDeTicketAutomatico() {
        try {
                return fachada.emisssaoDeTicketAutomatico();
        } catch (EstacionamentoException ex) {
            return ex.getMessage();
        }
    }

    public void getNumeroDeTicketsLiberadosSemPagamento() {
        
        try {
            Timestamp s = new Timestamp(ano,mes,dia,0,0,0,0);
            resultadoGetContadores = "" + fachada.getNumeroDeTicketsLiberadosSemPagamento(s, mes);
        } catch (EstacionamentoException ex) {
            resultadoGetContadores= ex.getMessage();
        }
    }

    public void getNumeroDeTicketsPagos() {

        try {
          Timestamp s = new Timestamp(ano,mes,dia,0,0,0,0);
            resultadoGetContadores= "" + fachada.getNumeroDeTicketsPagos(s, mes);
        } catch (EstacionamentoException ex) {
            resultadoGetContadores= ex.getMessage();
        }
    }

    public String liberarTicketSemPagamento(String ticket) {

        try {
            ITicket t = new Ticket(Integer.parseInt(ticket));

            return "" + fachada.liberarTicketSemPagamento(t);
        } catch (EstacionamentoException ex) {
            return ex.getMessage();
        }
    }

    public void geraCodigoDeBarras() throws Exception{
    fachada.geraCodigoDeBarras(idTicket);
    
    }
   
}
