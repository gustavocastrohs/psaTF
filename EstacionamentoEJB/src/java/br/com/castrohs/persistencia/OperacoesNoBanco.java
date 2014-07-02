    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.castrohs.persistencia;

import br.com.castrohs.ejb.IOperacoesNoBanco;
import br.com.castrohs.ejb.ITicket;
import br.com.castrohs.modelo.Ticket;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Gustavo
 */
public class OperacoesNoBanco implements IOperacoesNoBanco,Serializable {

    // private String emfS;
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstacionamentoEJBPU");
    private final EntityManager em = emf.createEntityManager();
    private final EntityTransaction tx = em.getTransaction();
    private static IOperacoesNoBanco instancia;

    public OperacoesNoBanco() {
        //    this.emfS = "MedicinaDoTrabalhoPU";
    }
    
    public static IOperacoesNoBanco getInstance(){
    if (instancia==null){
        instancia = new OperacoesNoBanco();
    }
    return instancia;
    }

    @Override
    public List executeNativeQuery(String pesquisa) {
        String aux = pesquisa;
        List resultList = null;
        try {
            tx.begin();
            resultList = em.createNativeQuery(aux).getResultList();
            tx.commit();
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return null;

    }

    @Override
    public List executeQueryByName(String queryString) {

        try {
            tx.begin();
            javax.persistence.Query query = em.createNamedQuery(queryString);
            tx.commit();
            List resultList = query.getResultList();
            return resultList;

        } catch (Exception e) {
            e.getMessage();
        }

        return null;

    }

    @Override
    public List executeNativeQuerySSI(String tabela, String condicao, int parametro) {
        String aux = "SELECT * FROM " + tabela + " where " + condicao + " =  " + parametro;
        List resultList = null;
        try {
            tx.begin();
            resultList = em.createNativeQuery(aux).getResultList();
            tx.commit();
            //  System.out.println();
            return resultList;
        } catch (Exception e) {
            //       e.getMessage();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List executeNativeQuerySSP(String tabela, String condicao, String parametro) {
        String aux = "SELECT * FROM " + tabela + " where " + condicao + " =  " + parametro;
        List resultList = null;
        try {

            tx.begin();

            resultList = em.createNativeQuery(aux).getResultList();
            tx.commit();
            //  System.out.println();
            return resultList;
        } catch (Exception e) {
            //       e.getMessage();
            System.out.println(e.getMessage());

        }
        return null;

    }

    /**
     * cadastra um objeto
     *
     * @param o
     * @param p
     * @return
     */
    @Override
    public boolean exCadastrar(Object o) {
        try {
            tx.begin();
            em.persist(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    /**
     * atualiza um objeto
     *
     * @param p
     * @return
     */

    /**
     * atualiza um objeto
     *
     * @param o
     * @return
     */
    @Override
    public boolean exAtualizar(Object o) {
        try {
            tx.begin();
            em.merge(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public boolean exExcluir(Object o) {
        try {
            tx.begin();
            o = em.merge(o);

            em.remove(o);
            tx.commit();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    /**
     *
     * @param queryString named query
     * @param paramentro que será usado na busca
     * @param parametroASerConsultado parametro de comparação
     * @return
     */
    @Override
    public List executeQueryByParamentroString(String queryString, String paramentro, String parametroASerConsultado) {
        try {
            tx.begin();
            javax.persistence.Query query = em.createNamedQuery(queryString);
            query.setParameter(paramentro, parametroASerConsultado);
            tx.commit();
            List resultList = query.getResultList();
            return resultList;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ITicket getTicket(int novoTicket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ITicket adicionarTicketAutomatico(String placa) {
    ITicket t = new Ticket(placa);
        boolean exCadastrar = exCadastrar(t);
        if (exCadastrar)
            return t;
        else
            return null;
    }

    @Override
    public int getNumeroDeTicketsPagos(Timestamp dia, int mes) {
        return 0;
    }

    @Override
    public Integer getNumeroDeTicketsLiberadosSemPagamento(Timestamp dia, int mes) {
        return 0;
    }

    @Override
    public double getValorTotalEstadia(Timestamp dia, int mes) {
        return 0;
    }

    @Override
    public ITicket getUltimoTicketIncluso() {
        return null;
    }

    @Override
    public boolean pagaTicket(ITicket ticket) {
        return false;
    }

    @Override
    public boolean liberaTicket(ITicket ticket) {
        return false;
    }

    @Override
    public ITicket getTicketComPlacaEChave(String placa, String chave) {
        return null;
    }

}
