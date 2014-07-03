/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import java.util.Calendar;
import persistencia.EstacionamentoDAOException;
import persistencia.EstacionamentoDAOJavaDb;

/**
 *
 * @author Gustavo
 */

public class CancelaSaida implements Serializable{
/**
 *  instancia da base de dados usada para criação de tickets
 */
    private final IEstacionamentoDAO baseDados;
    
    /**
 * Recupera a instancia ativa da base de dados
 * @throws negocio.EstacionamentoException

 */
    public CancelaSaida() throws EstacionamentoException{
    try {
        baseDados = EstacionamentoDAOJavaDb.getInstance();
    } catch (EstacionamentoDAOException ex) {
        throw new EstacionamentoException("Não é possivel conectar ao banco de dados");
    }
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
String resposta = "";
        Calendar calendar = Calendar.getInstance();

        int horaAtual = calendar.get(Calendar.HOUR_OF_DAY);
         if (horaAtual < 2 || horaAtual >= 8) {
        boolean testaTicketSePassouDoTempoGratuito = true;

        testaTicketSePassouDoTempoGratuito = testaTicketSePassouDoTempoGratuito(novoTicket);

        if (testaTicketSePassouDoTempoGratuito==true) {
            return "Necessário ir ao caixa";
         
        }else if (testaTicketSePassouDoTempoGratuito==false){
            return ticket.getId() + " Liberado";
        }
        else{
            ticket = getTicket(novoTicket);
            if (ticket.isLiberado()) {
                return ticket.getId() + " Liberado";
            }
            else{
              return "Necessário ir ao caixa";
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

        try {
            ticket = baseDados.getTicket(novoTicket);
            return ticket.TestaSePassouDoTempoGratuito();
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException(ex);

        }

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
        try {
            return baseDados.getTicket(ticket);
        } catch (EstacionamentoDAOException ex) {
            throw  new EstacionamentoException(ex);
        }
    
    }
}
