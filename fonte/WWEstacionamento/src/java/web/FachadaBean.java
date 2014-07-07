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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    private String resultado = "";
    private String placa;
    private String chave;
    private String codigoBarras;
    private String proximaPagina = "";
    private ITicket ticketCompleto = null;

    public ITicket getTicketCompleto() {
        return ticketCompleto;
    }

    public void setTicketCompleto(ITicket ticketCompleto) {
        this.ticketCompleto = ticketCompleto;
    }

    public String getProximaPagina() {
        return proximaPagina;
    }

    public void setProximaPagina(String proximaPagina) {
        this.proximaPagina = proximaPagina;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
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

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public void validacaoDeTicketCancelaSaida() {

        resultado = fachada.validacaoDeTicketCancelaSaida(Integer.parseInt(idTicket));

    }

    public void validacaoDeTicketOperador() {
        try {
            resultado = fachada.validacaoDeTicketOperador(Integer.parseInt(idTicket));
        } catch (EstacionamentoException ex) {
            resultado = ex.getMessage();
        }
    }

    public ITicket usuarioGeraCodigoDeBarras() {
        try {
           return ticketCompleto = fachada.usuarioGeraCodigoDeBarras(placa, chave);
           
            }
         catch (EstacionamentoException ex) {
            return null;
        }

    }

    public void calcularOQueTemQueSerPago() {
        try {
            resultado = fachada.calcularOQueTemQueSerPago(Integer.parseInt(idTicket));
        } catch (EstacionamentoException ex) {
            resultado = ex.getMessage();
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
        resultado = "";
        try {

            resultado = "" + fachada.getNumeroDeTicketsLiberadosSemPagamento(mes, ano);

        } catch (EstacionamentoException ex) {
            resultado = ex.getMessage();
        }
    }

    public void getNumeroDeTicketsPagos() {
        resultado = "";
        try {

            resultado = "" + fachada.getNumeroDeTicketsLiberadosSemPagamento(mes, ano);

        } catch (EstacionamentoException ex) {
            resultado = ex.getMessage();
        }
    }

    public void liberarTicketSemPagamento() {
        resultado = "";
        try {
            ITicket t = new Ticket(Integer.parseInt(idTicket));

            boolean resp = fachada.liberarTicketSemPagamento(t);
            if (resp) {
                resultado = "Ticket Liberado";
            } else {
                resultado = "Ticket não liberado";
            }
        } catch (EstacionamentoException ex) {
            resultado = "Ticket com defeito";
        }
    }

    public void getValorTotalEstadia() {
        try {
            resultado = "" + fachada.getNumeroDeTicketsLiberadosSemPagamento(mes, ano);
        } catch (EstacionamentoException ex) {
            resultado = ex.getMessage();
        }

    }

    public void pagaTicket() throws EstacionamentoException {
        resultado = fachada.pagaTicket(Integer.parseInt(idTicket));
    }

    /**
     * Forwards the {@link javax.servlet.http.HttpServletRequest} and
     * {@link javax.servlet.http.HttpServletResponse} to a servlet URL pattern
     * called {@literal /process}, and sets the
     * {@link javax.faces.context.FacesContext#responseComplete()}.
     */
    public void vaiParaProximaPaginaUser() {
        try {

            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = ctx.getExternalContext();

            HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
            HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
            request.setAttribute("placa", placa);
            request.setAttribute("chave", chave);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/UsuarioServlet");

            dispatcher.forward(request, response);
            ctx.responseComplete();
        } catch (ServletException ex) {
            Logger.getLogger(FachadaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FachadaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
