/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import interfaces.IFachadaEstacionamento;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import negocio.EstacionamentoException;
import negocio.ITicket;
import negocio.TipoDeTicket;
import persistencia.Ticket;

/**
 *
 * @author Gustavo
 */
@Named
@RequestScoped
public class FachadaBean {

    @EJB(name = "fachadaEstacionamento")
    private IFachadaEstacionamento fachada;

    public String validacaoDeTicketCancelaSaida(String ticket) {
        try {
            return fachada.validacaoDeTicketCancelaSaida(Integer.parseInt(ticket));
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
        try {
            return fachada.usuarioGeraCodigoDeBarras(ticket, chave);
        } catch (EstacionamentoException ex) {
            return ex.getMessage();
        }
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
            return fachada.emisssaoDeTicketAutomatico(placa, TipoDeTicket.TicketSimples);
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

    /**

     * public boolean liberarTicketSemPagamento(negocio.ITicket ticket) throws
     * EstacionamentoException;
     *
     * public String pagaTicket(int ticket) throws EstacionamentoException;
     *
     *
     */
}
