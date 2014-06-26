/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public interface IOPB {

    public boolean exAtualizar(Object o);

    public boolean exCadastrar(Object o);

    public boolean exExcluir(Object o);

    public List executeQueryByParamentroString(String queryString, String paramentro, String parametroASerConsultado);

    /**
     *
     * @param placa
     * @return devolve um ticket criado automaticamente pelo sistema
     * @
     */
    public ITicket adicionarTicketAutomatico(String placa);

    /**
     *
     * @param t recebe um ticket
     * @return retorna se foi possivel atuliza-lo
     * @
     */
    boolean alteraTicket(ITicket t);

    /**
     *
     * @param n codigo do ticket a ser buscado
     * @return retorna um ticket se não encontrar retorna null
     * @ caso a busca falhe retorna um erro
     */
    ITicket getTicket(int n);

    /**
     *
     * @return o ultimo ticket incluso
     * @
     */
    ITicket getUltimoTicketIncluso();

    /**
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o valor total de estadias segundo o periodo selecionado
     * @
     */
    double getValorTotalEstadia(Timestamp dia, int mes);

    /**
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o valor total de tickets pagos segundo o periodo selecionado
     * @
     */
    int getNumeroDeTicketsPagos(Timestamp dia, int mes);

    /**
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o numero total de tickets liberados sem pagamento segundo o
     * periodo selecionado
     * @
     */
    int getNumeroDeTicketsLiberadosSemPagamento(Timestamp dia, int mes);

    /**
     *
     * @param ticket recebe um Iticket e aplica um update nele
     * @return retorna se foi bem ou mau sucedido a atulização
     * @
     */
    boolean pagaTicket(ITicket ticket);

    /**
     *
     * @return indica se foi bem ou mau sucedida a ação
     * @
     */
    //boolean liberaTodosTickets(Timestamp dia) ;

    /**
     *
     * @param ticket Libera ticket selecionado
     * @return indica se foi bem ou mau sucedida a ação
     * @
     */
    public boolean liberaTicket(ITicket ticket);

    /**
     *
     * @param placa parametro placa do carro
     * @param chave parametro chave de segurança
     * @return retorna um ticket se não encontrar retorna null
     * @ caso a busca falhe retorna um erro
     */
    ITicket getTicketComPlacaEChave(String placa, String chave);
    public List executeNativeQuery(String pesquisa) ;
    

}
