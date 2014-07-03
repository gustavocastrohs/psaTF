/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Gustavo
 */
public class EstacionamentoException extends Exception{

     /**
     *
     * @param cause recebe uma cause do problema
     */
    public EstacionamentoException(Throwable cause) {
        super(cause);
    }

/**
     *
     * @param cause recebe uma cause do problema
     */
    public EstacionamentoException(Exception cause) {
        super(cause);
    }

    /**
     *
     * @param message recebe a menssagem que será exposta para o usuario
     * @param cause recebe uma cause do problema
     */
    public EstacionamentoException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message recebe a menssagem que será exposta para o usuario
     */
    public EstacionamentoException(String message) {
        super(message);
    }

    
}
