/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Ticket.findByData", query = "SELECT t FROM Ticket t WHERE t.data = :data"),
    @NamedQuery(name = "Ticket.findByValor", query = "SELECT t FROM Ticket t WHERE t.valor = :valor"),
    @NamedQuery(name = "Ticket.findByDatapagamento", query = "SELECT t FROM Ticket t WHERE t.datapagamento = :datapagamento")})
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    private int codigo;
    @Size(max = 7)
    private String placa;
    @Size(max = 5)
    private String chave;
    @Basic(optional = false)
    @NotNull
    private Serializable liberado;
    @Basic(optional = false)
    @NotNull
    private Serializable pago;
    @Basic(optional = false)
    @NotNull
    private Serializable pernoite;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valor;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datapagamento;

    public Ticket() {
    }

    public Ticket(Integer id) {
        this.id = id;
    }

    public Ticket(Integer id, int codigo, Serializable liberado, Serializable pago, Serializable pernoite, Date data) {
        this.id = id;
        this.codigo = codigo;
        this.liberado = liberado;
        this.pago = pago;
        this.pernoite = pernoite;
        this.data = data;
    }

    public Integer getId() {
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

    public Serializable getLiberado() {
        return liberado;
    }

    public void setLiberado(Serializable liberado) {
        this.liberado = liberado;
    }

    public Serializable getPago() {
        return pago;
    }

    public void setPago(Serializable pago) {
        this.pago = pago;
    }

    public Serializable getPernoite() {
        return pernoite;
    }

    public void setPernoite(Serializable pernoite) {
        this.pernoite = pernoite;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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
        return "entidades.Ticket[ id=" + id + " ]";
    }
    
}
