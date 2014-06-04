/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apresentacao;

/**
 *
 * @author Gustavo
 */
public class FachadaException extends Exception{

    /**
     *
     * @param cause recebe uma cause do problema
     */
    public FachadaException(Throwable cause) {
        super(cause);
    }

/**
     *
     * @param cause recebe uma cause do problema
     */
    public FachadaException(Exception cause) {
        super(cause);
    }

    /**
     *
     * @param message recebe a menssagem que será exposta para o usuario
     * @param cause recebe uma cause do problema
     */
    public FachadaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message recebe a menssagem que será exposta para o usuario
     */
    public FachadaException(String message) {
        super(message);
    }


}
