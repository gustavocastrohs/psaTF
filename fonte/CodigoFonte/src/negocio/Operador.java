/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.Timestamp;
import java.util.Calendar;
import org.joda.time.DateTime;
import org.joda.time.Days;
import persistencia.EstacionamentoDAOException;
import persistencia.EstacionamentoDAOJavaDb;

/**
 *
 * @author Gustavo
 */
public class Operador {

    /**
     * instancia da base de dados usada para criação de tickets
     */
    private IEstacionamentoDAO baseEstacionamento;

    /**
     * Recupera a instancia ativa da base de dados
     *
     * @throws negocio.EstacionamentoException
     */
    public Operador() throws EstacionamentoException {
        try {
            baseEstacionamento = EstacionamentoDAOJavaDb.getInstance();
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException("Não é possivel conectar ao banco de dados");
        }
    }

   

    /**
     *
     * @param codigo do ticket a ser analisado
     * @return retorna se está liberado ou o valor a ser pago
     * @throws EstacionamentoException
     */
    public String calcularOQueTemQueSerPago(int codigo) throws EstacionamentoException {

        double calcularOQueTemQueSerPago = calcularOValorDevido(codigo);
        if (calcularOQueTemQueSerPago == 0) {
            return "Liberado";
        } else if (calcularOQueTemQueSerPago == -1) {
            return "Ticket com problema";
        } else {
            return calcularOQueTemQueSerPago + "";
        }

    }

    /**
     *
     * @param codigo do ticket a ser analisado
     * @return o valor a ser pago pelo tempo gasto no estacionamento ou retorna
     * 0 caso gratuito
     * @throws EstacionamentoException
     */
    public double calcularOValorDevido(int codigo) throws EstacionamentoException {

        ITicket ticket;
        IValoresTickets valorTicketTabela = new ValoresTicketsImpl();
        try {
            ITicket ultimoTicketIncluso = baseEstacionamento.getUltimoTicketIncluso();
            if (ultimoTicketIncluso.getId() >= codigo) {
                ticket = baseEstacionamento.getTicket(codigo);
                if (!ticket.isPago() && !ticket.isLiberado()) {
                    boolean passouDoTempoGratuito = ticket.TestaSePassouDoTempoGratuito();
                    double valorTicketArmazenado = ticket.getValor();
                    double valorCalculado = 0;
                    int numberDays = 0;
                    if (!passouDoTempoGratuito && valorTicketArmazenado == 0) {
                        return 0;
                    } else {
                        Timestamp dataDoTicket = ticket.getData();
                        //  Timestamp tempoQueEstouNoEstacionamento = ticket.tempoQueEstouNoEstacionamento();

                        Calendar calendarioTicket = Calendar.getInstance();
                        Calendar calendarioAtual = Calendar.getInstance();
                        calendarioTicket.setTimeInMillis(dataDoTicket.getTime());

                        // DEVE SER FEITO O CALCULO ( HORA ATUAL  - HORA DO TICKET )
                        if (ticket.getValor() == 0) {
                            if (calendarioTicket.get(Calendar.DATE) == calendarioAtual.get(Calendar.DATE)) {
                                int horaCalculada = calendarioAtual.get(Calendar.HOUR_OF_DAY) - calendarioTicket.get(Calendar.HOUR_OF_DAY);
                               
                                    valorCalculado = valorTicketTabela.getValor();
                                
                            }

                        }
                        try {
                            DateTime start = new DateTime(calendarioTicket.get(Calendar.YEAR), calendarioTicket.get(Calendar.MONTH), calendarioTicket.get(Calendar.DATE), 1,0, 0);
                            DateTime end = new DateTime(calendarioAtual.get(Calendar.YEAR), calendarioAtual.get(Calendar.MONTH), calendarioAtual.get(Calendar.DATE), 1, 0, 0);
                            numberDays = Days.daysBetween(start, end).getDays();

                            valorCalculado = valorCalculado + valorTicketTabela.getValorEstadia() * Math.abs(numberDays);

                            double resposta = valorTicketArmazenado + valorCalculado;
                            return resposta;
                        } catch (Exception e) {
                            throw new EstacionamentoException("Problemas de conversão do complemento do .joda.time.IllegalInstantExceptio");
                        }
                    }

                } else {
                    return 0;
                }
            }
            return -1;
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException(ex);

        }


    }

    /**
     *
     * @param codigo do ticket a ser analisado
     * @return retorna o ticket pago ou não
     * @throws EstacionamentoException
     */
    public String pagaTicket(int codigo) throws EstacionamentoException {
        Calendar calendar = Calendar.getInstance();
        int horaAtual = calendar.get(Calendar.HOUR_OF_DAY);

        if (horaAtual < 2 || horaAtual >= 8) {
            ITicket ticket;
            IValoresTickets valorTicketTabela = new ValoresTicketsImpl();
            try {
                ticket = baseEstacionamento.getTicket(codigo);
                if (!ticket.isPago() && !ticket.isLiberado()) {

                    double calcularOValorDevido = calcularOValorDevido(codigo);
                    if (calcularOValorDevido > 0) {
                        ticket.setValor(calcularOValorDevido);
                        ticket.setLibera(true);
                        ticket.setIsPago(true);
                        if (calcularOValorDevido > valorTicketTabela.getValor()) {
                            ticket.setPernoite(true);
                        }
                        boolean pagaTicket = baseEstacionamento.pagaTicket(ticket);
                        if (pagaTicket) {
                            return ticket.toString();
                        }
                    } else {
                        return "Ticket liberado";
                    }

                }

            } catch (EstacionamentoDAOException ex) {
                throw new EstacionamentoException(ex);
            }
            return "\nTICKET NÃO FOI PAGO\n";
        }
        return "\nEstacionamento Fechado";
    }

    /**
     *
     * @param ticket do ticket a ser analisado
     * @return a validação do ticket
     * @throws EstacionamentoException caso não seja possivel ler irá indicar
     * com erro
     */
    public String validacaoDeTicket(int ticket) throws EstacionamentoException {
        try {
            return calcularOQueTemQueSerPago(ticket);
        } catch (EstacionamentoException ex) {
            return "Não é possivel ler";
        }
    }


    
        /**
     *
     * @param dia dia selecionado para liberar os tickets
     * @return indica se foi bem ou mau sucedida a ação
     * @throws negocio.EstacionamentoException
     */
    public boolean  liberarTicketSemPagamento(ITicket ticket)  throws EstacionamentoException {
        try {
            return baseEstacionamento.liberaTicket(ticket);
        } catch (EstacionamentoDAOException ex) {
            throw new EstacionamentoException(ex);
        }
    }

}
