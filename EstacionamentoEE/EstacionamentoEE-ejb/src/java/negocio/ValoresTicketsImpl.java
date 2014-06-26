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
     * @return valor do estacionamento
     */
    @Override
    public double getValor() {
        return 10;
    }

}
