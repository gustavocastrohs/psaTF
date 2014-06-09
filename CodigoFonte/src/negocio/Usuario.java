/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

import persistencia.EstacionamentoDAOException;
import persistencia.EstacionamentoDAOJavaDb;

/**
 *
 * @author Gustavo
 */
public class Usuario {
    private IEstacionamentoDAO baseEstacionamento;
  
    
    
    
    public Usuario() throws EstacionamentoException {
        try {
            baseEstacionamento = EstacionamentoDAOJavaDb.getInstance();
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException("Não é possivel conectar ao banco de dados");
        }
    }
    
    public String geraCodigoDeBarras(String placa,String  chave) throws EstacionamentoDAOException{
        ITicket ticketComPlacaEChave = baseEstacionamento.getTicketComPlacaEChave(placa, chave);
        if (ticketComPlacaEChave== null)
            return "Ticket não encontrado";
        else
            return  ticketComPlacaEChave.toString();
    }
}
