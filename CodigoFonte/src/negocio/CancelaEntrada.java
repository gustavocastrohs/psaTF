/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import persistencia.EstacionamentoDAOException;
import persistencia.EstacionamentoDAOJavaDb;

/**
 *
 * @author Gustavo
 */
public class CancelaEntrada {

    /**
     * instancia da base de dados usada para criação de tickets
     */
    private final IEstacionamentoDAO baseEstacionamento;

    /**
     * Recupera a instancia ativa da base de dados
     *
     * @throws negocio.EstacionamentoException
     */
    public CancelaEntrada() throws EstacionamentoException {
        try {
            baseEstacionamento = EstacionamentoDAOJavaDb.getInstance();
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException("Não é possivel conectar ao banco de dados");
        }
    }

    /**
     *
     * @param placa recebe uma placa de um carro
     * @param tipo tipo do ticket que está sendo impresso , sem codigo de barras
     * ou com
     * @return retorna o toString() do ticket criado
     * @throws EstacionamentoException
     */
    public String emisssaoDeTicketAutomatico(String placa,TipoDeTicket tipo) throws EstacionamentoException {
        ITicket ticket = null;
        try {
            ticket = baseEstacionamento.adicionarTicketAutomatico(placa);
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException(ex);
        }
        TicketDecorator t = new TicketCodigoBarras(ticket);
        if (ticket != null) {
            if (tipo == TipoDeTicket.TicketSimples) {
                return ticket.toString();
            } else {
                return t.toString();
            }
        } else {
            return "";
        }

    }

}
