/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import negocio.ITicket;

/**
 *
 * @author Gustavo
 */
public class Ticket implements ITicket,Serializable {

    /**
     * id do ticket no banco de dados
     */
    private int id;
    /**
     * codigo para indicar as ações
     */
    private int codigo;
    /**
     * data de impressao do ticket
     */
    private Timestamp dataImpressao;
    /**
     * data de pagamento caso haja um
     */
    private Timestamp dataPagamento;
    /**
     * valor do ticket
     */
    private double valor;
    /**
     * indica se o ticket está ou não liberado
     */
    private boolean liberado;
    /**
     * indica se o ticket está ou não pago
     */
    private boolean pago;
    /**
     * indica se o ticket tem ou não pernoite
     */
    private boolean pernoite;
/**
 * placa do veiculo
 */
    private String placa;
    
    /**
     * chave de segurança do ticket
     */
    private String chave;
    
    /**
     *
     * @return retorna o que a variavel pernoite tem
     */
    @Override
    public boolean isPernoite() {
        return pernoite;
    }

    /**
     *
     * @param pernoite seta um valor para a variavel pernoite
     */
    @Override
    public void setPernoite(boolean pernoite) {
        this.pernoite = pernoite;
    }

    /**
     *
     * @return retorna o que a variavel codigo tem
     */
    @Override
    public int getCodigo() {
        return codigo;
    }

    /**
     *
     * @return retorna o que a variavel dataImpressao tem
     */
    @Override
    public Timestamp getData() {
        return dataImpressao;
    }

    /**
     *
     * @return retorna o que a variavel id tem
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     *
     * @return retorna o que a variavel valor tem
     */
    @Override
    public double getValor() {
        return valor;
    }

    /**
     *
     * @param codigo seta um valor para a variavel codigo
     */
    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     *
     * @param valor seta um valor para a variavel valor
     */
    @Override
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     *
     * @return retorna o que a variavel liberado tem
     */
    @Override
    public boolean isLiberado() {
        return liberado;
    }

    /**
     *
     * @param liberado seta um valor para a variavel liberado
     */
    @Override
    public void setLibera(boolean liberado) {
        this.liberado = liberado;
    }


    /**
     *
     * @param codigo codigo indicando tipo do ticket
     * @param dataImpressao data de impressao do ticket
     * @param valor valor do ticket
     * @param liberado se o ticket está liberado
     * @param pago se o ticket está pago
     * @param pernoite se o ticket pernoitou
     * @param dataPagamento a data do pagamento do ticket
     * @param placa placa do carro
     * @param chave chave de segurança
     */
    public Ticket(int codigo, Timestamp dataImpressao, double valor, boolean liberado, boolean pago, boolean pernoite, Timestamp dataPagamento,String placa, String chave) {
        this.codigo = codigo;
        this.dataImpressao = dataImpressao;
        this.valor = valor;
        this.liberado = liberado;
        this.pago = pago;
        this.pernoite = pernoite;
        this.dataPagamento = dataPagamento;
                this.placa = placa;
        this.chave = chave;
    }
    /**
     *
     * @param id id do ticket que veio do banco
     * @param codigo codigo indicando tipo do ticket
     * @param dataImpressao data de impressao do ticket
     * @param valor valor do ticket
     * @param liberado se o ticket está liberado
     * @param pago se o ticket está pago
     * @param pernoite se o ticket pernoitou
     * @param dataPagamento a data do pagamento do ticket
     * @param placa placa do carro
     * @param chave chave de segurança
     */
    public Ticket(int id, int codigo, Timestamp dataImpressao, double valor, boolean liberado, boolean pago, boolean pernoite, Timestamp dataPagamento, String placa, String chave) {
        this.id = id;
        this.codigo = codigo;
        this.dataImpressao = dataImpressao;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.liberado = liberado;
        this.pago = pago;
        this.pernoite = pernoite;
        this.placa = placa;
        this.chave = chave;
    }
public Ticket(int id){
this.id = id;
}
    
    /**
     * auxTempoResposta:Pega a hora do ticket + 15 minutos auxTempoAtual: pega o
     * do Calendar a hora atual
     *
     * @return Se auxTempoResposta maior que auxTempoAtual retorna FALSE. Se
     * auxTempoAtual for maior que auxTempoResposta retorna TRUE.
     */
    @Override
    public boolean TestaSePassouDoTempoGratuito() {
        Calendar horarioAtual = Calendar.getInstance();
        Timestamp auxTempoAtual = new Timestamp(horarioAtual.getTimeInMillis());
        //Timestamp auxData = ;
        long m = 15 * 60 * 1000;
        Timestamp auxTempoResposta = new Timestamp(dataImpressao.getTime() + m);
        boolean resposta = auxTempoResposta.getTime() <= auxTempoAtual.getTime();
        return resposta;
    }

    /**
     *
     * @return o que tem a variavel pago
     */
    @Override
    public boolean isPago() {
        return pago;
    }

    /**
     *
     * @param p seta um valor para a variavel pago
     */
    @Override
    public void setIsPago(boolean p) {
        pago = p;
    }

    /**
     *
     * @return o que tem a variavel dataPagamento
     */
    @Override
    public Timestamp getDataPagamento() {
        return dataPagamento;

    }

    /**
     *
     * @return retorna o toString() sem codigo de barras
     */
    @Override
    public String toString() {
        return "ID:" + id
                + "\nPLACA:" + placa
                + "\nCHAVE:" + chave
                + "\nDATA: " + dataImpressao
                + "\nvalor :" + valor
                + "\nliberado:" + liberado;
    }

    @Override
    public String getPlaca() {
       return placa;
    }

    @Override
    public String getChave() {
        return chave;
    }

    @Override
    public void setPlaca(String p) {
        placa = p;
    }

    @Override
    public void setChave(String c) {
        chave = c;
    }

}
