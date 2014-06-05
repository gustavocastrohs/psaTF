/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.SQLException;

/**
 *
 * @author Gustavo
 */
public class EstacionamentoDAOException extends Exception {

    /**
     *
     * @param message recebe a menssagem que será exposta para o usuario
     */
    public EstacionamentoDAOException(String message) {
        super(message);
    }
/**
 * 
 * @param message recebe a menssagem que será exposta para o usuario
 * @param ex  recebe a causa do problema
 */

    public EstacionamentoDAOException(String message, SQLException ex) {
        super(message);
    }
/**
 * 
 * @param ex recebe a causa do problema
 */
    public EstacionamentoDAOException(SQLException ex) {
        super(ex);
    }

}
