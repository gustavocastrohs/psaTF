/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;




/**
 *
 * @author Gustavo
 */
public class Usuario {
    private IEstacionamentoDAO baseEstacionamento;
  
    
    
    
    public Usuario() throws EstacionamentoException {

    }
    
    public String geraCodigoDeBarras(String placa,String  chave) {
        ITicket ticketComPlacaEChave = baseEstacionamento.getTicketComPlacaEChave(placa, chave);
        if (ticketComPlacaEChave== null)
            return "Ticket não encontrado";
        else
            return  ticketComPlacaEChave.toString();
    }
}
