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
public interface ITicket {

    /**
     *
     * @return retorna o que a variavel id tem
     */
    int getId();

    /**
     *
     * @return retorna o que a variavel codigo tem
     */
    int getCodigo();

        /**
     *
     * @return retorna o que a variavel placa
     */
    String getPlaca();

        /**
     *
     * @return retorna o que a variavel chave
     */
      String getChave();

    
    /**
     *
     * @return retorna o que a variavel liberado tem
     */
    boolean isLiberado();

    /**
     *
     * @return o que tem a variavel pago
     */
    boolean isPago();

    /**
     *
     * @return retorna o que a variavel valor tem
     */
    double getValor();

    /**
     *
     * @return retorna o que a variavel dataImpressao tem
     */
    Timestamp getData();

    /**
     *
     * @return o que tem a variavel dataPagamento
     */
    Timestamp getDataPagamento();

    /**
     *
     * @param codigo seta um valor para a variavel codigo
     */
    void setCodigo(int codigo);

    /**
     *
     * @param valor seta um valor para a variavel valor
     */
    void setValor(double valor);

    /**
     *
     * @param liberado seta um valor para a variavel liberado
     */
    void setLibera(boolean liberado);

    /**
     *
     * @param p seta um valor para a variavel pago
     */
    void setIsPago(boolean p);
    
    /**
     * 
     * @param placa seta um valor para a variavel placa
     */
    void setPlaca (String placa);
    /**
     * 
     * @param chave  seta um valor para a variavel chave
     */
    
    void setChave (String chave);

    /**
     *
     * @return retorna o que a variavel pernoite tem
     */
    boolean isPernoite();

    /**
     *
     * @param pernoite seta um valor para a variavel pernoite
     */
    void setPernoite(boolean pernoite);

    /**
     *
     * @return retorna o toString() sem ou com codigo de barras deve ser
     * implementado
     */
    @Override
    String toString();

    /**
     * auxTempoResposta:Pega a hora do ticket + 15 minutos auxTempoAtual: pega o
     * do Calendar a hora atual
     *
     * @return Se auxTempoResposta maior que auxTempoAtual retorna FALSE. Se
     * auxTempoAtual for maior que auxTempoResposta retorna TRUE.
     */
    boolean TestaSePassouDoTempoGratuito();

}
