/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Gustavo
 */
@Stateless
public class SBOperador implements SBOperadorLocal {

    @Override
    public List<ITicket> getTickets() {
     //return em.createNamedQuery("Ticket.findAll").getResultList();   

        IOPB opb = new OperacoesNoBanco();
        List executeNativeQuery = opb.executeNativeQuery("Ticket.findAll");
        List<ITicket> listaRetorno = null;

        for (Object o : executeNativeQuery) {
            ITicket t = (ITicket) o;
            listaRetorno.add(t);

        }
        return listaRetorno;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")


    
}
