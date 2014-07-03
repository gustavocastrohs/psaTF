/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import persistencia.EstacionamentoDAOException;
import persistencia.EstacionamentoDAOJavaDb;

/**
 *
 * @author Gustavo
 */
public class CancelaEntrada implements Serializable {

    /**
     * instancia da base de dados usada para criação de tickets
     */
    private final IEstacionamentoDAO baseEstacionamento;

    /**
     * Recupera a instancia ativa da base de dados
     *
     * @throws negocio.EstacionamentoException
     */
    public CancelaEntrada() throws EstacionamentoException {
        try {
            baseEstacionamento = EstacionamentoDAOJavaDb.getInstance();
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException("Não é possivel conectar ao banco de dados");
        }
    }

    /**
     *
     * @param placa recebe uma placa de um carro
     * @param tipo tipo do ticket que está sendo impresso , sem codigo de barras
     * ou com
     * @return retorna o toString() do ticket criado
     * @throws EstacionamentoException
     */
    public String emisssaoDeTicketAutomatico(String placa) throws EstacionamentoException {
        ITicket ticket = null;
        try {
            ticket = baseEstacionamento.adicionarTicketAutomatico(placa);
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException(ex);
        }
        TicketDecorator t = new TicketCodigoBarras(ticket);
        if (ticket != null) {

            return ticket.toString();

        } else {
            return "";
        }

    }

    public String emisssaoDeTicketAutomatico() throws EstacionamentoException {
        ITicket ticket = null;
        try {
            ticket = baseEstacionamento.adicionarTicketAutomatico(leitoOptico());
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException(ex);
        }
        TicketDecorator t = new TicketCodigoBarras(ticket);
        if (ticket != null) {

            return ticket.toString();

        } else {
            return "";
        }

    }

    private String leitoOptico() {

        String novaPlaca = "";

        Random gerador = new Random();
        ArrayList<String> alfabeto = new ArrayList(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        ArrayList<String> numeros = new ArrayList(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        for (int i = 0; i < 3; i++) {
            int numero = gerador.nextInt(26);
            novaPlaca = novaPlaca + alfabeto.get(numero);

        }
        for (int i = 0; i < 4; i++) {
            int numero = gerador.nextInt(10);
            novaPlaca = novaPlaca + numeros.get(numero);

        }
        // System.out.println(novaPlaca);
        return novaPlaca;

    }

}
