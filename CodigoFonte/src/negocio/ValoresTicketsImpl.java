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
public class ValoresTicketsImpl implements IValoresTickets {

    /**
     *
     * @return valor da estadia
     */
    @Override
    public double getValorEstadia() {
        return 50;
    }

    /**
     *
     * @return valor para até 3 horas no estacionamento
     */
    @Override
    public double getValorAte3() {
        return 3.5;
    }

    /**
     *
     * @return valores para acima de 3 horas no estacionamento
     */
    @Override
    public double getValorAcimaDe3() {
        return 10;
    }

}
