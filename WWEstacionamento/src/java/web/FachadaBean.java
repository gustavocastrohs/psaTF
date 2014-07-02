/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.sql.Timestamp;
import javax.ejb.EJB;
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

    @EJB(name = "fachada")
    private final FachadaEstacionamento fachada = FachadaEstacionamento.getInstace();
    
    private String idTicket;
    private int dia;
    private int mes;

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


    public String validacaoDeTicketCancelaSaida() {
        
        try {
            return fachada.validacaoDeTicketCancelaSaida(Integer.parseInt(idTicket));
        } catch (EstacionamentoException ex) {
            return ex.getMessage();
        }
    }

    public String validacaoDeTicketOperador(String ticket) {
        try {
            return fachada.validacaoDeTicketOperador(Integer.parseInt(ticket));
        } catch (EstacionamentoException ex) {
            return ex.getMessage();
        }
    }

    public String usuarioGeraCodigoDeBarras(String ticket, String chave) {
        return fachada.usuarioGeraCodigoDeBarras(ticket, chave);
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

    public String getNumeroDeTicketsLiberadosSemPagamento(Timestamp dia, int mes) {

        try {
            return "" + fachada.getNumeroDeTicketsLiberadosSemPagamento(dia, mes);
        } catch (EstacionamentoException ex) {
            return ex.getMessage();
        }
    }

    public String getNumeroDeTicketsPagos(Timestamp dia, int mes) {

        try {
            return "" + fachada.getNumeroDeTicketsPagos(dia, mes);
        } catch (EstacionamentoException ex) {
            return ex.getMessage();
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

   
}
