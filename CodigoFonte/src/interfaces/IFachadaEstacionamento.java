/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.Timestamp;
import negocio.EstacionamentoException;
import negocio.TipoDeTicket;
import persistencia.EstacionamentoDAOException;

/**
 *
 * @author Gustavo
 */

public interface IFachadaEstacionamento {

    public String calcularOQueTemQueSerPago(int codigo) throws EstacionamentoException;

    public String emisssaoDeTicketAutomatico(String placa, TipoDeTicket tipo) throws EstacionamentoException;

    public int getNumeroDeTicketsLiberadosSemPagamento(Timestamp dia, int mes) throws EstacionamentoException;

    public int getNumeroDeTicketsPagos(Timestamp dia, int mes) throws EstacionamentoException;

    public boolean liberarTicketSemPagamento(negocio.ITicket ticket) throws EstacionamentoException;

    public String pagaTicket(int ticket) throws EstacionamentoException;

    public String usuarioGeraCodigoDeBarras(String placa, String chave) throws EstacionamentoException;

    public String validacaoDeTicketCancelaSaida(int ticket) throws EstacionamentoException;

    public String validacaoDeTicketOperador(int ticket) throws EstacionamentoException;

}
