    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;


import entidades.Ticket;
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
public class OperacoesNoBanco implements IOPB{

    // private String emfS;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstacionamentoEE-ejbPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public OperacoesNoBanco() {
        //    this.emfS = "MedicinaDoTrabalhoPU";
    }


    public List executeNativeQuery(String pesquisa) {
        String aux =pesquisa;
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

    
    public List executeQueryByParamentroMedico(String queryString, String paramentro, String pesquisa) {

        try {
            
            tx.begin();
            javax.persistence.Query query = em.createNamedQuery(queryString);
            query.setParameter(paramentro,pesquisa);
            tx.commit();
            List  resultList = query.getResultList();
//            System.out.println(resultList.size());

            return resultList;

        } catch (Exception e) {
            e.getMessage();
        }

        return null;


    }
   

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
    
        public boolean exExcluir(Object o) {
        try {
            tx.begin();
            o= em.merge(o);

            em.remove(o);
            tx.commit();
            return true;
        } catch (Exception e) {
      
        }
        return false;
    }


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
    public ITicket getTicket(int n) {
        List executeQueryByParamentroString = executeQueryByParamentroString("", "", ""+n);
        if (executeQueryByParamentroString.size()>0){
            ITicket t = (ITicket)executeQueryByParamentroString.get(0);
            return t ;
        }
    return null;
    }

    @Override
    public ITicket adicionarTicketAutomatico(String placa) {
       ITicket n = new Ticket();
       n.setPlaca(placa);
       exCadastrar(n);
        List executeNativeQuery = executeNativeQuery("Ticket.findLastEnter");
         if (executeNativeQuery.size()>0){
            ITicket t = (ITicket)executeNativeQuery.get(0);
            return t ;
        }
         return null;
    }

    @Override
    public boolean alteraTicket(ITicket t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ITicket getUltimoTicketIncluso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getValorTotalEstadia(Timestamp dia, int mes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumeroDeTicketsPagos(Timestamp dia, int mes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumeroDeTicketsLiberadosSemPagamento(Timestamp dia, int mes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean pagaTicket(ITicket ticket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean liberaTicket(ITicket ticket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ITicket getTicketComPlacaEChave(String placa, String chave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      /**
       * 
       * @param queryString named query
       * @param paramentro parametro que será usado para pareamento
       * @param idPaciente um paciente de entrada
       * @return uma lista
       */
      
//    public List executeQueryByParamentroConsulta(String queryString, String paramentro, Paciente idPaciente) {
//        try {
//            tx.begin();
//            javax.persistence.Query query = em.createNamedQuery(queryString);
//            query.setParameter(paramentro,idPaciente);          
//            tx.commit();
//            List resultList = query.getResultList();
//            return resultList;
//        } catch (Exception e) {
//            e.getMessage();
//        }
//        return null;
//    }

    
    
    
    
}