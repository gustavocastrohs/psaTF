/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Gustavo
 */

public class CancelaSaida implements Serializable{
/**
 *  instancia da base de dados usada para criação de tickets
 */
    private IOPB opb;
    
    /**
 * Recupera a instancia ativa da base de dados
 * @throws negocio.EstacionamentoException

 */
    public CancelaSaida() throws EstacionamentoException{
    
    }
/**
 *  instancia do ticket recuperado para os testes
 */
    private ITicket ticket = null;
/**
 * 
 * @param novoTicket codigo do ticket a ser buscado no banco de dados
 * @return retorna se o ticket está liberado ou não para o uso
 * @throws EstacionamentoException 
 */
    public String testaSeTicketEstaLiberado(int novoTicket) throws EstacionamentoException {

        Calendar calendar = Calendar.getInstance();
        
        int horaAtual = calendar.get(Calendar.HOUR_OF_DAY);
           
        if (horaAtual < 2 ||horaAtual >= 8) {
            ticket = getTicket(novoTicket);
                if (ticket.isLiberado()) {
                    return ticket.getId() + " Liberado";
                }
                else{
                boolean testaTicketSePassouDoTempoGratuito = true;

                testaTicketSePassouDoTempoGratuito = testaTicketSePassouDoTempoGratuito(novoTicket);
/*  se ticket gerado manual
                if (ticket.getValor() > 0) {
                    testaTicketSePassouDoTempoGratuito = true;
                }
             */
                if (testaTicketSePassouDoTempoGratuito) {
                    return "Necessário ir ao caixa";
                }
                else
                {
                    return ticket.getId() + " Liberado";
                }
                }

                
            
        }
        return "Estacionamento Fechado";

    }
/**
 * 
 * @param novoTicket codigo para buscar ticket no banco de dados
 * @return retorna o resultado obitido no ticket.TestaSePassouDoTempoGratuito()
 * @throws EstacionamentoException 
 */
    public boolean testaTicketSePassouDoTempoGratuito(int novoTicket) throws EstacionamentoException {


            ticket = opb.getTicket(novoTicket);
            return ticket.TestaSePassouDoTempoGratuito();


    }
    /**
     * 
     * @param ticket codigo para buscar ticket no banco de dados
     * @return retorna a sitação do ticket obtida no metodo testaSeTicketEstaLiberado(int codigo)
     * @throws EstacionamentoException 
     */
    public String validacaoDeTicket(int ticket) throws EstacionamentoException {
               try {
                   return testaSeTicketEstaLiberado(ticket);

               } catch (EstacionamentoException e) {
                   return "Não é possivel ler";
               }
       }
    /**
     * 
     * @param ticket codigo para buscar ticket no banco de dados
     * @return retorna um iticket do banco de dados
     * @throws EstacionamentoException 
     */
    private ITicket getTicket(int ticket) throws EstacionamentoException{
     return opb.getTicket(ticket);
        
    
    }
}