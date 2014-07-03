/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.Timestamp;
import persistencia.EstacionamentoDAOException;

/**
 *
 * @author Gustavo
 */
public interface IEstacionamentoDAO {

    
  /**
     *
     * @return devolve um ticket criado automaticamente pelo sistema
     * @throws EstacionamentoDAOException
     */
    ITicket adicionarTicketAutomatico(String placa) throws EstacionamentoDAOException;
    
    /**
     *
     * @param t recebe um ticket
     * @return retorna se foi possivel atuliza-lo
     * @throws EstacionamentoDAOException
     */
    boolean alteraTicket(ITicket t) throws EstacionamentoDAOException;
    /**
     *
     * @param n codigo do ticket a ser buscado
     * @return retorna um ticket se não encontrar retorna null
     * @throws EstacionamentoDAOException caso a busca falhe retorna um erro
     */
    ITicket getTicket(int n) throws EstacionamentoDAOException;
    /**
     *
     * @return o ultimo ticket incluso
     * @throws EstacionamentoDAOException
     */
    ITicket getUltimoTicketIncluso() throws EstacionamentoDAOException;
    /**
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o valor total de estadias segundo o periodo selecionado
     * @throws EstacionamentoDAOException
     */
    double getValorTotalEstadia(Timestamp dia, int mes) throws EstacionamentoDAOException;
   /**
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o valor total de tickets pagos segundo o periodo selecionado
     * @throws EstacionamentoDAOException
     */
    int getNumeroDeTicketsPagos(Timestamp dia, int mes) throws EstacionamentoDAOException;
    /**
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o numero total de tickets liberados sem pagamento segundo o
     * periodo selecionado
     * @throws EstacionamentoDAOException
     */
    int getNumeroDeTicketsLiberadosSemPagamento(Timestamp dia, int mes) throws EstacionamentoDAOException;
    /**
     *
     * @param ticket recebe um Iticket e aplica um update nele
     * @return retorna se foi bem ou mau sucedido a atulização
     * @throws EstacionamentoDAOException
     */
    boolean pagaTicket(ITicket ticket) throws EstacionamentoDAOException;
    /**
     *     
     * @return indica se foi bem ou mau sucedida a ação
     * @throws EstacionamentoDAOException
     */
    //boolean liberaTodosTickets(Timestamp dia) ;

    /**
     *
     * @param ticket Libera ticket selecionado
     * @return indica se foi bem ou mau sucedida a ação
     * @throws EstacionamentoDAOException
     */
    public boolean liberaTicket(ITicket ticket)throws EstacionamentoDAOException;

    
        /**
     *
     * @param placa parametro placa do carro
     * @param chave parametro chave de segurança
     * @return retorna um ticket se não encontrar retorna null
     * @throws EstacionamentoDAOException caso a busca falhe retorna um erro
     */
    ITicket getTicketComPlacaEChave(String placa, String chave) throws EstacionamentoDAOException;
    
 
}
