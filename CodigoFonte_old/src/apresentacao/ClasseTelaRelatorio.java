/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JLabel;
import negocio.EstacionamentoException;

/**
 *
 * @author Gustavo
 */
public class ClasseTelaRelatorio {

    private CtrlGUI controlador;
    /**
     * cria uma instancia de ClasseTelaRelatorio
     */

    private static ClasseTelaRelatorio instancia;

    /**
     *
     * @return a intancia ativa da ClasseTelaRelatorio
     * @throws FachadaException
     */
    public static ClasseTelaRelatorio getInstance() throws FachadaException {

        if (instancia == null) {
            try {
                instancia = new ClasseTelaRelatorio();
            } catch (FachadaException ex) {
                throw new FachadaException(ex);
            }
        }
        return instancia;

    }

    /**
     * pega a instancia de CtrlGUI ativa
     *
     * @throws FachadaException
     */
    private ClasseTelaRelatorio() throws FachadaException {
        controlador = CtrlGUI.getInstance();
    }

    /**
     * Busca os dados do Controlador -> Fachada -> Base
     * ->getNumeroDeTicketsLiberadosSemPagamento
     *
     * @param label jlabel que será usado de referencia para adição de conteudo
     * @param date data selecionada para ser buscada no banco de dados este
     * campo pode ser nulo
     * @param month mes selecionado para ser buscado no banco
     * @return retorna um label
     */
    public JLabel getNumeroDeTicketsLiberadosSemPagamento(JLabel label,Date date, int month) {
        
        try {
            Timestamp time = null;
            if (date != null) {
                time = new Timestamp(date.getTime());
            }
            int numeroDeTicketsLiberadosSemPagamento = controlador.getNumeroDeTicketsLiberadosSemPagamento(time, month);
            label.setText(Integer.toString(numeroDeTicketsLiberadosSemPagamento));
            return label;
        } catch (EstacionamentoException ex) {
            return label;
        }

    }

    /**
     * Busca os dados do Controlador -> Fachada -> Base
     * ->getNumeroDeTicketsPagos
     * @param label jlabel que será usado de referencia para adição de conteudo
     * @param date data selecionada para ser buscada no banco de dados este
     * campo pode ser nulo
     * @param month mes selecionado para ser buscado no banco
     * @return retorna um label
     */
    public JLabel getNumeroDeTicketsPagos(JLabel label,Date date, int month) {
      
        try {
            Timestamp time = null;
            if (date != null) {
                time = new Timestamp(date.getTime());
            }
            int getNumeroDeTicketsPagos = controlador.getNumeroDeTicketsPagos(time, month);
            label.setText(Integer.toString(getNumeroDeTicketsPagos));
            return label;
        } catch (EstacionamentoException ex) {
            return label;
        }

    }

    /**
     * Busca os dados do Controlador -> Fachada -> Base ->getValorTotalEstadia
     *
     * @param label jlabel que será usado de referencia para adição de conteudo
     * @param date data selecionada para ser buscada no banco de dados este
     * campo pode ser nulo
     * @param month mes selecionado para ser buscado no banco
     * @return retorna um label
     */

    public JLabel getValorTotalEstadia( JLabel label,Date date, int month) {
       
        try {
            Timestamp time = null;
            if (date != null) {
                time = new Timestamp(date.getTime());
            }
            double getValorTotalEstadia = controlador.getValorTotalEstadia(time, month);
            label.setText(Double.toString(getValorTotalEstadia));
            return label;
        } catch (EstacionamentoException ex) {
            return label;
        }

    }

}
