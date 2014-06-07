/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.Timestamp;

/**
 *
 * @author Gustavo
 */
public abstract class TicketDecorator implements ITicket {

    /**
     * cria uma instancia de um ITicket
     */
    ITicket ticketDecorado;

    /**
     *
     * @param ticket cria uma instancia de ITicket usando um ticket decorado
     */
    public TicketDecorator(ITicket ticket) {
        ticketDecorado = ticket;
    }

    /**
     *
     * @return recupera o valor do ticket decorado
     */

    @Override
    public boolean isPernoite() {
        return ticketDecorado.isPernoite();
    }

    @Override
    public void setPernoite(boolean p) {
        ticketDecorado.setPernoite(p);
    }

    /**
     *
     * @return recupera o valor do ticket decorado
     */

    @Override
    public int getCodigo() {
        return ticketDecorado.getCodigo();
    }

    /**
     *
     * @return recupera o valor do ticket decorado
     */

    @Override
    public Timestamp getData() {
        return ticketDecorado.getData();
    }

    /**
     *
     * @return recupera o valor do ticket decorado
     */

    @Override
    public int getId() {
        return ticketDecorado.getId();
    }

    /**
     *
     * @return recupera o valor do ticket decorado
     */

    @Override
    public double getValor() {
        return ticketDecorado.getValor();
    }

    @Override
    public void setCodigo(int codigo) {
        ticketDecorado.setCodigo(codigo);
    }

    @Override
    public void setValor(double valor) {
        ticketDecorado.setValor(valor);
    }

    /**
     *
     * @return recupera o valor do ticket decorado
     */

    @Override
    public boolean isLiberado() {
        return ticketDecorado.isLiberado();
    }

    @Override
    public void setLibera(boolean liberado) {
        ticketDecorado.setLibera(liberado);
    }

    @Override
    public abstract String toString();

    /**
     *
     * @return recupera o calculo do ticket decorado
     */
    @Override
    public boolean TestaSePassouDoTempoGratuito() {
        return ticketDecorado.TestaSePassouDoTempoGratuito();
    }

    /**
     *
     * @return recupera o valor do ticket decorado
     */

    @Override
    public boolean isPago() {
        return ticketDecorado.isPago();

    }

    @Override
    public void setIsPago(boolean p) {
        ticketDecorado.setIsPago(p);
    }

    /**
     *
     * @return recupera o valor do ticket decorado
     */

    @Override
    public Timestamp getDataPagamento() {
        return ticketDecorado.getDataPagamento();
    }

    /**
     * 
     * @return recupera uma placa do ticket decorado
     */
    @Override
    public String getPlaca(){    
        return ticketDecorado.getPlaca();
    }
    
    @Override
    public String getChave(){
    return ticketDecorado.getChave();
    }
    
        @Override
    public void setPlaca(String p) {
        ticketDecorado.setPlaca(p);
    }

    @Override
    public void setChave(String c) {
        ticketDecorado.setChave(c);
    }
    
}
