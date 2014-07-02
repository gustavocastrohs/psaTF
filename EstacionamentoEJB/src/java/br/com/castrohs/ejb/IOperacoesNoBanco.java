/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.castrohs.ejb;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public interface IOperacoesNoBanco {

    public List executeNativeQuery(String pesquisa);

    public List executeQueryByName(String queryString);

    public List executeNativeQuerySSI(String tabela, String condicao, int parametro);

    public List executeNativeQuerySSP(String tabela, String condicao, String parametro);

    public boolean exCadastrar(Object o);

    public boolean exAtualizar(Object o);

    public boolean exExcluir(Object o);

    public List executeQueryByParamentroString(String queryString, String paramentro, String parametroASerConsultado);

    public ITicket getTicket(int novoTicket);

    public ITicket adicionarTicketAutomatico(String placa);

    public int getNumeroDeTicketsPagos(Timestamp dia, int mes);

    public Integer getNumeroDeTicketsLiberadosSemPagamento(Timestamp dia, int mes);

    public double getValorTotalEstadia(Timestamp dia, int mes);

    public ITicket getUltimoTicketIncluso();

    public boolean pagaTicket(ITicket ticket);

    public boolean liberaTicket(ITicket ticket);

    public ITicket getTicketComPlacaEChave(String placa, String chave);
    
     
}
