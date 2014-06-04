/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import java.sql.Timestamp;
import negocio.EstacionamentoException;
import negocio.FachadaEstacionamento;
import negocio.TipoDeTicket;

/**
 *
 * @author Gustavo
 */
public class CtrlGUI {

    private FachadaEstacionamento fachadaEstacionamento;
    /**
     * cria uma instancia da CtrlGUI
     */

    private static CtrlGUI instancia;

    /**
     * Inicializa a instancia de CtrlGUI
     *
     * @return devolve uma instancia da CtrlGUI ativa
     * @throws FachadaException
     */
    public static CtrlGUI getInstance() throws FachadaException {

        if (instancia == null) {
            instancia = new CtrlGUI();
        }
        return instancia;

    }

    /**
     * Cria uma instancia da fachada
     *
     * @throws FachadaException
     */
    private CtrlGUI() throws FachadaException {
        try {
            fachadaEstacionamento = new FachadaEstacionamento();
        } catch (EstacionamentoException ex) {
            throw new FachadaException(ex);
        }
    }

    /**
     * Local Operador
     *
     * @param tipo tipo do ticket que está sendo impresso , sem codigo de barras
     * ou com
     * @return retorna o toString() do ticket criado
     * @throws EstacionamentoException
     */
    public String emissaoDeTicketManual(TipoDeTicket tipo) throws EstacionamentoException {

        return fachadaEstacionamento.emisssaoDeTicketManual(tipo);
    }
    /**
     * Ação feita no Operador
     *
     * @param ticket do ticket a ser analisado
     * @return a validação do ticket
     */
    public String validacaoDeTicketOperador(String ticket) {

        try {
            int ticketConvertido = Integer.parseInt(ticket);
            return fachadaEstacionamento.validacaoDeTicketOperador(ticketConvertido);

        } catch (NumberFormatException | EstacionamentoException e) {

            return "Leitura incorreta";
        }

    }

    /**
     * Local Cancela Saida
     *
     * @param ticket codigo para buscar ticket no banco de dados
     * @return retorna a sitação do ticket obtida no metodo
     * testaSeTicketEstaLiberado(int codigo)
     */
    public String validacaoDeTicketCancelaSaida(String ticket) {

        try {
            int ticketConvertido = Integer.parseInt(ticket);
            return fachadaEstacionamento.validacaoDeTicketCancelaSaida(ticketConvertido);

        } catch (Exception e) {

            return "Leitura incorreta";
        }

    }

    /**
     * Local Cancela Entrada
     *
     * @param tipo tipo do ticket que está sendo impresso , sem codigo de barras
     * ou com
     * @return retorna o toString() do ticket criado
     * @throws EstacionamentoException
     */
    public String emissaoDeTicketAutomatica(TipoDeTicket tipo) throws EstacionamentoException {

        return fachadaEstacionamento.emisssaoDeTicketAutomatico(tipo);

    }

    /**
     * Local Operador
     *
     * @param ticket do ticket a ser analisado
     * @return retorna o ticket pago ou não
     * @throws EstacionamentoException
     */
    public String pagaTicket(String ticket) throws EstacionamentoException {
        try {

            int ticketConvertido = Integer.parseInt(ticket);
            return fachadaEstacionamento.pagaTicket(ticketConvertido);

        } catch (NumberFormatException | EstacionamentoException e) {

            return "Leitura incorreta";
        }
    }

    /**
     * Local Modulo Gerencial
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o valor total de estadias segundo o periodo selecionado
     * @throws EstacionamentoException
     */
    public double getValorTotalEstadia(Timestamp dia, int mes) throws EstacionamentoException {
        return fachadaEstacionamento.getValorTotalEstadia(dia, mes);
    }

    /**
     * Local Modulo Gerencial
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
        return fachadaEstacionamento.getNumeroDeTicketsLiberadosSemPagamento(dia, mes);
    }

    /**
     * Local Modulo Gerencial
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o valor total de tickets pagos segundo o periodo selecionado
     * @throws negocio.EstacionamentoException
     */
    public int getNumeroDeTicketsPagos(Timestamp dia, int mes) throws EstacionamentoException {
        return fachadaEstacionamento.getNumeroDeTicketsPagos(dia, mes);
    }

    /**
     * Local Modulo Gerencial
     *
     * @param dia dia selecionado para liberar os tickets
     * @return indica se foi bem ou mau sucedida a ação
     * @throws negocio.EstacionamentoException
     */
    public boolean liberarTicketSemPagamento(Timestamp dia) throws EstacionamentoException {
        return fachadaEstacionamento.liberarTicketSemPagamento(dia);
    }

}
