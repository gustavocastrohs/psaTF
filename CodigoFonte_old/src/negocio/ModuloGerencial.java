/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.Timestamp;
import persistencia.EstacionamentoDAOException;
import persistencia.EstacionamentoDAOJavaDb;

/**
 *
 * @author Gustavo
 */
public class ModuloGerencial {

    /**
     * instancia da base de dados usada para criação de tickets
     */
    private IEstacionamentoDAO baseEstacionamento = null;

    /**
     * Recupera a instancia ativa da base de dados
     *
     * @throws negocio.EstacionamentoException
     */
    public ModuloGerencial() throws EstacionamentoException {
        try {
            baseEstacionamento = EstacionamentoDAOJavaDb.getInstance();
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException("Não é possivel conectar ao banco de dados");
        }

    }

    /**
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o valor total de tickets pagos segundo o periodo selecionado
     * @throws negocio.EstacionamentoException
     */
    public int getNumeroDeTicketsPagos(Timestamp dia, int mes) throws EstacionamentoException {
        try {
            return baseEstacionamento.getNumeroDeTicketsPagos(dia, mes);
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException(ex);
        }
    }

    /**
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o numero total de tickets liberados sem pagamento segundo o
     * periodo selecionado
     * @throws negocio.EstacionamentoException
     */
    public Integer getNumeroDeTicketsLiberadosSemPagamento(Timestamp dia, int mes) throws EstacionamentoException {
        try {
            return baseEstacionamento.getNumeroDeTicketsLiberadosSemPagamento(dia, mes);
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException(ex);
        }
    }

    /**
     *
     * @param dia dia selecionado para liberar os tickets
     * @return indica se foi bem ou mau sucedida a ação
     * @throws negocio.EstacionamentoException
     */
    public boolean liberarTicketSemPagamento(Timestamp dia) throws EstacionamentoException {
        try {
            return baseEstacionamento.liberaTodosTickets(dia);
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException(ex);
        }
    }

    /**
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o valor total de estadias segundo o periodo selecionado
     * @throws EstacionamentoException
     */
    public double getValorTotalEstadia(Timestamp dia, int mes) throws EstacionamentoException {
        try {
            return baseEstacionamento.getValorTotalEstadia(dia, mes);
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException(ex);
        }
    }

}
