/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.castrohs.modelo;

import br.com.castrohs.ejb.ITicket;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(catalog = "estacionamento", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findById", query = "SELECT t FROM Ticket t WHERE t.id = :id"),
    @NamedQuery(name = "Ticket.findByCodigo", query = "SELECT t FROM Ticket t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Ticket.findByPlaca", query = "SELECT t FROM Ticket t WHERE t.placa = :placa"),
    @NamedQuery(name = "Ticket.findByChave", query = "SELECT t FROM Ticket t WHERE t.chave = :chave"),
    @NamedQuery(name = "Ticket.findByLiberado", query = "SELECT t FROM Ticket t WHERE t.liberado = :liberado"),
    @NamedQuery(name = "Ticket.findByPago", query = "SELECT t FROM Ticket t WHERE t.pago = :pago"),
    @NamedQuery(name = "Ticket.findByPernoite", query = "SELECT t FROM Ticket t WHERE t.pernoite = :pernoite"),
    @NamedQuery(name = "Ticket.findByValor", query = "SELECT t FROM Ticket t WHERE t.valor = :valor"),
    @NamedQuery(name = "Ticket.findByDataimpressao", query = "SELECT t FROM Ticket t WHERE t.dataimpressao = :dataimpressao"),
    @NamedQuery(name = "Ticket.findByDatapagamento", query = "SELECT t FROM Ticket t WHERE t.datapagamento = :datapagamento")})
public class Ticket implements Serializable,ITicket {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int codigo;
    @Size(max = 7)
    @Column(length = 7)
    private String placa;
    @Size(max = 5)
    @Column(length = 5)
    private String chave;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean liberado;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean pago;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean pernoite;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double valor;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataimpressao;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datapagamento;

    public Ticket() {
    }

    public Ticket(Integer id) {
        this.id = id;
    }

    public Ticket(Integer id, int codigo, boolean liberado, boolean pago, boolean pernoite, Date dataimpressao, Date datapagamento) {
        this.id = id;
        this.codigo = codigo;
        this.liberado = liberado;
        this.pago = pago;
        this.pernoite = pernoite;
        this.dataimpressao = dataimpressao;
        this.datapagamento = datapagamento;
    }

    public Ticket(String placa) {
       this.placa=placa;
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
        this.dataimpressao = dataImpressao;
        this.valor = valor;
        this.liberado = liberado;
        this.pago = pago;
        this.pernoite = pernoite;
        this.datapagamento = dataPagamento;
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
        this.dataimpressao = dataImpressao;
        this.datapagamento = dataPagamento;
        this.valor = valor;
        this.liberado = liberado;
        this.pago = pago;
        this.pernoite = pernoite;
        this.placa = placa;
        this.chave = chave;
    }
    
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public boolean getLiberado() {
        return liberado;
    }

    public void setLiberado(boolean liberado) {
        this.liberado = liberado;
    }

    public boolean getPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public boolean getPernoite() {
        return pernoite;
    }

    public void setPernoite(boolean pernoite) {
        this.pernoite = pernoite;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataimpressao() {
        return dataimpressao;
    }

    public void setDataimpressao(Date dataimpressao) {
        this.dataimpressao = dataimpressao;
    }

    public Date getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(Date datapagamento) {
        this.datapagamento = datapagamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.castrohs.modelo.Ticket[ id=" + id + " ]";
    }
    
    @Override
        public boolean TestaSePassouDoTempoGratuito() {
        Calendar horarioAtual = Calendar.getInstance();
        Timestamp auxTempoAtual = new Timestamp(horarioAtual.getTimeInMillis());
        //Timestamp auxData = ;
        long m = 15 * 60 * 1000;
        Timestamp auxTempoResposta = new Timestamp(dataimpressao.getTime() + m);
        boolean resposta = auxTempoResposta.getTime() <= auxTempoAtual.getTime();
        return resposta;
    }



    @Override
    public boolean isLiberado() {
     return liberado;
    }

    @Override
    public boolean isPago() {
        return pago;
    }



    @Override
    public Timestamp getData() {
        return (Timestamp)dataimpressao;
    }

    @Override
    public Timestamp getDataPagamento() {
        return (Timestamp)datapagamento;
    }

    @Override
    public void setValor(double valor) {
        this.valor=valor;
    }

    @Override
    public void setLibera(boolean liberado) {
        this.liberado=liberado;
    }

    @Override
    public void setIsPago(boolean p) {
        this.pago=p;
    }

    @Override
    public boolean isPernoite() {
        return pernoite;
    }

  
    
}
