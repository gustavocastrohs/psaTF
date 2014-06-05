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
public class FachadaEstacionamento {

    /**
     * instancia da base de dados usada para criação de tickets
     */
    private IEstacionamentoDAO baseEstacionamento;
    /**
     * Nova instancia da Cancela de saida
     */
    private CancelaSaida cancelaSaida = null;
    /**
     * Nova instancia da Cancela de Entrada
     */
    private CancelaEntrada cancelaEntrada = null;
    /**
     * Nova instancia do ModuloGerencial
     */
    private ModuloGerencial moduloGerencial = null;
    /**
     * Nova instancia do Operador
     */
    private Operador operador = null;

    /**
     * Busca a instacia ativa do banco de dados inicializa as instancia de :
     * CancelaSaida,,CancelaEntrada,ModuloGerencial e Operador
     *
     * @throws EstacionamentoException
     */
    public FachadaEstacionamento() throws EstacionamentoException {
        try {
            baseEstacionamento = EstacionamentoDAOJavaDb.getInstance();
            cancelaEntrada = new CancelaEntrada();
            moduloGerencial = new ModuloGerencial();
            operador = new Operador();
            cancelaSaida = new CancelaSaida();

        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException(ex);
        }
    }

    /**
     * Ação feita na CancelaEntrada
     *
     * @param tipo tipo de ticket selecionado
     * @return saida do toString do ticket criado
     * @throws EstacionamentoException
     */
    public String emisssaoDeTicketAutomatico(TipoDeTicket tipo) throws EstacionamentoException {
        return cancelaEntrada.emisssaoDeTicketAutomatico(tipo);
    }

    
    /**
     * Ação feita no Operador
     *
     * @param ticket codigo para buscar ticket no banco de dados
     * @return retorna a sitação do ticket obtida no metodo testaSeTicketEstaLiberado(int codigo)
     * @throws EstacionamentoException retorna não é possivel ler o ticket
     */
    public String validacaoDeTicketCancelaSaida(int ticket) throws EstacionamentoException {
        return cancelaSaida.validacaoDeTicket(ticket);
    }

    /**
     * Ação feita no Operador
     *
     * @param ticket do ticket a ser analisado
     * @return a validação do ticket
     * @throws EstacionamentoException caso não seja possivel ler irá indicar
     * com erro
     */
    public String validacaoDeTicketOperador(int ticket) throws EstacionamentoException {
        return operador.validacaoDeTicket(ticket);
    }

    /**
     * Ação feita no Operador
     *
     * @param codigo do ticket a ser analisado
     * @return retorna se está liberado ou o valor a ser pago
     * @throws EstacionamentoException
     */
    public String calcularOQueTemQueSerPago(int codigo) throws EstacionamentoException {
        return operador.calcularOQueTemQueSerPago(codigo);
    }

    /**
     * Ação feita no ModuloGerencial
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o valor total de estadias segundo o periodo selecionado
     * @throws EstacionamentoException
     */
    public double getValorTotalEstadia(Timestamp dia, int mes) throws EstacionamentoException {
        return moduloGerencial.getValorTotalEstadia(dia, mes);
    }

    /**
     * Ação feita no ModuloGerencial
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o numero total de tickets liberados sem pagamento segundo o
     * periodo selecionado
     * @throws negocio.EstacionamentoException
     */
    public int getNumeroDeTicketsLiberadosSemPagamento(Timestamp dia, int mes) throws EstacionamentoException {
        return moduloGerencial.getNumeroDeTicketsLiberadosSemPagamento(dia, mes);
    }

    /**
     *
     * Ação feita no ModuloGerencial
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o valor total de tickets pagos segundo o periodo selecionado
     * @throws negocio.EstacionamentoException
     */
    public int getNumeroDeTicketsPagos(Timestamp dia, int mes) throws EstacionamentoException {
        return moduloGerencial.getNumeroDeTicketsPagos(dia, mes);
    }

    /**
     *
     * Ação feita no ModuloGerencial
     *
     * @param dia dia selecionado para liberar os tickets
     * @return indica se foi bem ou mau sucedida a ação
     * @throws negocio.EstacionamentoException
     */
    public boolean liberarTicketSemPagamento(ITicket ticket) throws EstacionamentoException {
        return operador.liberarTicketSemPagamento(ticket);
    }

    /**
     * Ação feita no Operador
     *
     * @param ticket do ticket a ser analisado
     * @return retorna o ticket pago ou não
     * @throws EstacionamentoException
     */
    public String pagaTicket(int ticket) throws EstacionamentoException {
        return operador.pagaTicket(ticket);
    }
}
