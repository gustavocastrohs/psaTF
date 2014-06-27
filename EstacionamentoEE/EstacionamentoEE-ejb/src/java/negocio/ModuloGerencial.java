/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 *
 * @author Gustavo
 */
public class ModuloGerencial implements Serializable{

    /**
     * instancia da base de dados usada para criação de tickets
     */
        private IOPB opb = new OperacoesNoBanco();

    /**
     * Recupera a instancia ativa da base de dados
     *
     * @throws negocio.EstacionamentoException
     */
    public ModuloGerencial() throws EstacionamentoException {
 

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
       
       return opb.getNumeroDeTicketsPagos(dia, mes);
        
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
        return opb.getNumeroDeTicketsLiberadosSemPagamento(dia, mes);
        
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
        
            return opb.getValorTotalEstadia(dia, mes);
        
    }

}
