/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Gustavo
 */
public class TicketCodigoBarras extends TicketDecorator {

    /**
     *
     * @param ticket cria um novo Ticket apartir de um Iticket
     */
    public TicketCodigoBarras(ITicket ticket) {
        super(ticket);
    }

    /**
     *
     * @return um toString() que irá virar um codigo de barras
     */
    @Override
    public String toString() {
        return ""+getId();

    }

}
