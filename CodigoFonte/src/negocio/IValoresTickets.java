/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Gustavo
 */
public interface IValoresTickets {

    /**
     *
     * @return valor da estadia
     */
    double getValorEstadia();

    /**
     *
     * @return valor para até 3 horas no estacionamento
     */
    double getValorAte3();

    /**
     *
     * @return valores para acima de 3 horas no estacionamento
     */
    double getValorAcimaDe3();
}
