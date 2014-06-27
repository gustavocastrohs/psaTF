package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-27T10:38:16")
@StaticMetamodel(Ticket.class)
public class Ticket_ { 

    public static volatile SingularAttribute<Ticket, Integer> id;
    public static volatile SingularAttribute<Ticket, Integer> codigo;
    public static volatile SingularAttribute<Ticket, Serializable> pago;
    public static volatile SingularAttribute<Ticket, Double> valor;
    public static volatile SingularAttribute<Ticket, Date> datapagamento;
    public static volatile SingularAttribute<Ticket, Serializable> liberado;
    public static volatile SingularAttribute<Ticket, String> placa;
    public static volatile SingularAttribute<Ticket, Date> data;
    public static volatile SingularAttribute<Ticket, Serializable> pernoite;
    public static volatile SingularAttribute<Ticket, String> chave;

}