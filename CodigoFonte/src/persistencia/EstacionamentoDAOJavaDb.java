package persistencia;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import negocio.ITicket;
import negocio.Ticket;

/**
 *
 * @author Gustavo
 */
public class EstacionamentoDAOJavaDb implements negocio.IEstacionamentoDAO {

    /**
     * instancia da base de dados
     */
    private static EstacionamentoDAOJavaDb instancia;

    /**
     *
     * @return a intancia ativa do banco de dados se não tem uma cria.
     * @throws EstacionamentoDAOException
     */
    public static EstacionamentoDAOJavaDb getInstance() throws EstacionamentoDAOException {
        try {

            if (instancia == null) {
                instancia = new EstacionamentoDAOJavaDb();
            }
            return instancia;
        } catch (EstacionamentoDAOException ex) {

            throw new EstacionamentoDAOException("" + ex.getMessage());
        }

    }
/**
 *  Instancia a classe de conexão e verifica se existe ou não um banco criado
 * se não existir cria um;
 * @throws EstacionamentoDAOException 
 */
    private EstacionamentoDAOJavaDb() throws EstacionamentoDAOException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            throw new EstacionamentoDAOException("JdbcOdbDriver not found!!");
        }
        // Cria o banco de dados vazio
        // Retirar do comentário se necessário
        boolean verificaSeExisteATabela = verificaSeExisteATabela();
        if (!verificaSeExisteATabela) {
            createDB();
        }
    }

    /**
     * Inicia a variavel instancia caso ainda não foi feito
     *
     * @return a conexão ativa do banco de dados
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby:derbyDB");
    }

    /**
     * Inicia o banco de dados criando a esquema TICKET
     *
     * @return Se funcionou retorna True se não retorna False
     * @throws EstacionamentoDAOException
     */
    public static boolean createDB() throws EstacionamentoDAOException {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            Statement sta = con.createStatement();
// para verificar se um ticket está pago ou não basta verificar se o falor dele é ou não maior que zero.
            String sql = ""
                    + "CREATE TABLE TICKET ("
                    + "ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "CODIGO INTEGER NOT NULL WITH DEFAULT  1," // 1 - normal  0 - especial 
                    + "LIBERADO Boolean NOT NULL WITH DEFAULT  FALSE,"
                    + "PAGO Boolean NOT NULL WITH DEFAULT  FALSE,"
                    + "PERNOITE Boolean NOT NULL WITH DEFAULT  FALSE,"
                    + "DATA TIMESTAMP   NOT NULL WITH DEFAULT  CURRENT_TIMESTAMP,"
                    + "VALOR DOUBLE  WITH DEFAULT  0.0,"
                    + "DATAPAGAMENTO TIMESTAMP"
                    + ")";
            sta.executeUpdate(sql);
            sta.close();
            con.close();
            inicializaDadosNoBanco();
            return true;
        } catch (SQLException ex) {
            return false;

        }
    }

    /**
     *
     * @param n codigo do ticket a ser buscado
     * @return retorna um ticket se não encontrar retorna null
     * @throws EstacionamentoDAOException caso a busca falhe retorna um erro
     */
    @Override
    public ITicket getTicket(int n) throws EstacionamentoDAOException {
        ITicket ultimoTicketIncluso = getUltimoTicketIncluso();
        if (n <= ultimoTicketIncluso.getId()) {
            List<ITicket> lista = new LinkedList<>();

            String sql = "SELECT ID,CODIGO,DATA,VALOR,LIBERADO,PAGO,PERNOITE,DATAPAGAMENTO from TICKET where TICKET.ID = " + n;
            try {
                try (Connection con = getConnection()) {
                    Statement sta = con.createStatement();
                    ResultSet res = sta.executeQuery(sql);
                    while (res.next()) {
                        ITicket t = new Ticket(
                                res.getInt("ID"),
                                res.getInt("CODIGO"),
                                res.getTimestamp("DATA"),
                                res.getDouble("VALOR"),
                                res.getBoolean("LIBERADO"),
                                res.getBoolean("PAGO"),
                                res.getBoolean("PERNOITE"),
                                res.getTimestamp("DATAPAGAMENTO"));
                        lista.add(t);
                    }
                    res.close();
                    sta.close();
                }

            } catch (Exception ex) {
                throw new EstacionamentoDAOException("Falha na busca. " + ex.getMessage());
            }
            if (!lista.isEmpty()) {
                return (ITicket) lista.get(0);
            } else {
                return null;
            }

        }
        return null;
    }

    /**
     *
     * @return devolve um ticket criado automaticamente pelo sistema
     * @throws EstacionamentoDAOException
     */
    @Override
    public ITicket adicionarTicketAutomatico() throws EstacionamentoDAOException {

        try {
            Connection con = getConnection();
            String sql = " INSERT INTO APP.TICKET (CODIGO, DATA, VALOR,LIBERADO,PAGO,PERNOITE)  VALUES (DEFAULT, DEFAULT, DEFAULT, DEFAULT,DEFAULT,DEFAULT) ";
            PreparedStatement sta = con.prepareStatement(sql);
            sta.executeUpdate();
            sta.close();
            con.close();

            return getUltimoTicketIncluso();
        } catch (SQLException ex) {
            throw new EstacionamentoDAOException("Falha na inserção. " + ex.getMessage());
        }

    }

    /**
     *
     * @param ticket recebe um ticket
     * @return retorna se foi possivel atuliza-lo
     * @throws EstacionamentoDAOException
     */
    @Override
    public boolean alteraTicket(ITicket ticket) throws EstacionamentoDAOException {
        String sql = "UPDATE TICKET SET"
                + " TICKET.VALOR= " + ticket.getValor() + ","
                + " TICKET.LIBERADO= " + ticket.isLiberado() + ","
                + " TICKET.PAGO= " + ticket.isPago() + ","
                + " TICKET.PERNOITE= " + ticket.isPernoite() + ","
                + " TICKET.DATAPAGAMENTO = CURRENT_TIMESTAMP"
                + " WHERE TICKET.id =" + ticket.getId();
        try {
            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();
                sta.executeUpdate(sql);

                sta.close();
                return true;
            }

        } catch (Exception ex) {
            return false;
        }
    }

    /**
     *
     * @return retorna um ticket criado manualmente com valor 50.00 e codigo 0
     * @throws EstacionamentoDAOException
     */
    @Override
    public ITicket adicionarTicketManual() throws EstacionamentoDAOException {
        try {
            Connection con = getConnection();
            String sql = "INSERT INTO APP.TICKET (CODIGO, DATA, VALOR,LIBERADO,PAGO,PERNOITE)  VALUES (0, DEFAULT, 50.0, DEFAULT,DEFAULT,DEFAULT) ";
            PreparedStatement sta = con.prepareStatement(sql);
            sta.executeUpdate();
            sta.close();
            con.close();

            return getUltimoTicketIncluso();
        } catch (SQLException ex) {
            throw new EstacionamentoDAOException("Falha na inserção. " + ex.getMessage());
        }
    }

    /**
     *
     * @return o ultimo ticket incluso
     * @throws EstacionamentoDAOException
     */
    @Override
    public ITicket getUltimoTicketIncluso() throws EstacionamentoDAOException {

        List<ITicket> lista = new LinkedList<>();

        String sql = "SELECT ID,CODIGO,DATA,VALOR,LIBERADO,PAGO,PERNOITE,DATAPAGAMENTO from TICKET where TICKET.ID = ( SELECT Count(ID) FROM TICKET)";
        try {
            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();
                ResultSet res = sta.executeQuery(sql);
                while (res.next()) {
                    ITicket t = new Ticket(
                            res.getInt("ID"),
                            res.getInt("CODIGO"),
                            res.getTimestamp("DATA"),
                            res.getDouble("VALOR"),
                            res.getBoolean("LIBERADO"),
                            res.getBoolean("PAGO"),
                            res.getBoolean("PERNOITE"),
                            res.getTimestamp("DATAPAGAMENTO"));
                    lista.add(t);
                }
                res.close();
                sta.close();
            }

        } catch (SQLException ex) {
            throw new EstacionamentoDAOException("Falha na busca. " + ex.getMessage());
        }
        if (!lista.isEmpty()) {
            return (ITicket) lista.get(0);
        } else {
            return null;
        }
    }

    /**
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o valor total de estadias segundo o periodo selecionado
     * @throws EstacionamentoDAOException
     */
    @Override
    public double getValorTotalEstadia(Timestamp dia, int mes) throws EstacionamentoDAOException {
        mes++;

        String sql = "";

        if (dia != null) {
            sql = "SELECT SUM(VALOR) FROM TICKET WHERE DATE(TICKET.\"DATAPAGAMENTO\") = DATE(TIMESTAMP('" + dia + "')) AND PAGO = TRUE AND PERNOITE = TRUE";
        } else {
            sql = "SELECT SUM(VALOR) FROM TICKET WHERE MONTH(TICKET.\"DATAPAGAMENTO\") = " + mes + " AND PAGO = TRUE AND PERNOITE = TRUE";
        }
        try {
            double resultado = 0;
            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();
                ResultSet res = sta.executeQuery(sql);
                while (res.next()) {
                    resultado = res.getDouble(1);
                }
                res.close();
                sta.close();
            }
            return resultado;
        } catch (SQLException ex) {
            return 0;
        }

    }

    /**
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o valor total de tickets pagos segundo o periodo selecionado
     * @throws EstacionamentoDAOException
     */
    @Override
    public int getNumeroDeTicketsPagos(Timestamp dia, int mes) throws EstacionamentoDAOException {
        mes++;

        String sql = "";

        if (dia != null) {
            sql = "SELECT COUNT(*) FROM TICKET WHERE DATE(TICKET.\"DATAPAGAMENTO\") = DATE(TIMESTAMP('" + dia + "')) AND PAGO = TRUE";
        } else {
            sql = "SELECT COUNT(*) FROM TICKET WHERE MONTH(TICKET.\"DATAPAGAMENTO\") = " + mes + " AND PAGO = TRUE";
        }
        int resultado = 0;
        try {
            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();
                ResultSet res = sta.executeQuery(sql);
                while (res.next()) {
                    resultado = res.getInt(1);
                }
                res.close();
                sta.close();
            }
            return resultado;
        } catch (Exception ex) {
            return 0;
        }

    }

    /**
     *
     * @param dia dia selecionado para liberar os tickets
     * @return indica se foi bem ou mau sucedida a ação
     * @throws EstacionamentoDAOException
     */
    @Override
    public boolean liberaTodosTickets(Timestamp dia) throws EstacionamentoDAOException {
        String sql = "UPDATE   TICKET SET   TICKET.LIBERADO = TRUE WHERE DATE(TICKET.\"DATA\") = DATE(TIMESTAMP('" + dia + "')) ";
        try {
            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();
                int res = sta.executeUpdate(sql);

                sta.close();
                return true;
            }

        } catch (Exception ex) {
            return false;
        }

    }

    /**
     *
     * @param dia usa um Timestamp para buscar o dia sendo se este estiver null
     * será pelo mes
     * @param mes valor que escolhe o mes para a busca adiciona +1 pois vem de
     * um array que começa em zero e não são 11 mas 12 meses o ano
     * @return o numero total de tickets liberados sem pagamento segundo o
     * periodo selecionado
     * @throws EstacionamentoDAOException
     */
    @Override
    public int getNumeroDeTicketsLiberadosSemPagamento(Timestamp dia, int mes) throws EstacionamentoDAOException {
        mes++;

        String sql = "";

        if (dia != null) {
            sql = "SELECT COUNT(*) FROM TICKET WHERE DATE(TICKET.\"DATA\") = DATE(TIMESTAMP('" + dia + "')) AND PAGO = FALSE AND LIBERADO = TRUE";
        } else {
            sql = "SELECT COUNT(*) FROM TICKET WHERE MONTH(TICKET.\"DATA\") = " + mes + " AND PAGO = FALSE AND LIBERADO = TRUE";
        }
        int resultado = 0;
        try {

            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();
                ResultSet res = sta.executeQuery(sql);
                while (res.next()) {
                    resultado = res.getInt(1);
                }
                res.close();
                sta.close();
            }
            return resultado;
        } catch (Exception ex) {
            return 0;
        }

    }

    /**
     *
     * @param ticket recebe um Iticket e aplica um update nele
     * @return retorna se foi bem ou mau sucedido a atulização
     * @throws EstacionamentoDAOException
     */
    @Override
    public boolean pagaTicket(ITicket ticket) throws EstacionamentoDAOException {
        return alteraTicket(ticket);
    }

    /**
     * Antes de criar uma nova tabela o ideal é saber se ela já existe
     *
     * @return retorna se a tabela já existe = true ou não = false;
     */
    private boolean verificaSeExisteATabela() {

        String sql = "select s.schemaname || '.' || t.tablename "
                + "     from sys.systables t, sys.sysschemas s "
                + "     where t.schemaid = s.schemaid"
                + "          and t.tabletype = 'T'"
                + "     order by s.schemaname, t.tablename";

        String resultado = "";
        try {

            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();
                ResultSet res = sta.executeQuery(sql);
                while (res.next()) {
                    resultado = res.getString(1);
                }
                res.close();
                sta.close();
            }
            return !"".equals(resultado);
        } catch (SQLException ex) {
            return false;
        }

    }

    /**
     * incia os primeiros dados no banco de dados
     *
     * @throws EstacionamentoDAOException
     */
    public static void inicializaDadosNoBanco() throws EstacionamentoDAOException {
        try {
            String sql = ""
                    + "INSERT INTO APP.TICKET (CODIGO, DATA, VALOR,LIBERADO,PAGO,PERNOITE,DATAPAGAMENTO)  VALUES"
                    + "(DEFAULT,'2013-11-20 14:13:46.844',3.5,TRUE,TRUE,DEFAULT,'2013-11-20 14:50:46.844'),"
                    + "(DEFAULT,'2013-11-20 19:13:46.844',10.0,TRUE,TRUE,DEFAULT,'2013-11-20 14:50:46.844'),"
                    + "(DEFAULT,'2013-11-20 19:13:46.844',50.0,TRUE,TRUE,TRUE,'2013-11-21 14:50:46.844'),"
                    + "(DEFAULT,'2013-11-20 08:13:46.844',200.0,TRUE,TRUE,TRUE,'2013-11-24 14:50:46.844')"
                    ;
            Connection con = getConnection();
            PreparedStatement sta = con.prepareStatement(sql);
            sta.executeUpdate();
            sta.close();
            con.close();
             sql = ""
                      + "INSERT INTO APP.TICKET (CODIGO, DATA, VALOR,LIBERADO,PAGO,PERNOITE)  VALUES"
                      +"(DEFAULT,'2013-11-20 20:13:46.844',DEFAULT,TRUE,DEFAULT,DEFAULT),"
                      + "(DEFAULT,'2013-11-20 19:13:46.844',DEFAULT,TRUE,DEFAULT,DEFAULT)";
             con = getConnection();
             sta = con.prepareStatement(sql);
            sta.executeUpdate();
            sta.close();
            con.close();
            sql = ""
                    + "INSERT INTO APP.TICKET (DATA)  VALUES"
                    + " ('2013-11-20 14:13:46.844'),"
                    + "('2013-11-20 15:13:46.844'),"
                    + "('2013-11-20 16:13:46.844'),"
                    + "('2013-11-20 17:13:46.844'),"
                    + "('2013-11-20 18:13:46.844') ";
            con = getConnection();
            sta = con.prepareStatement(sql);
            sta.executeUpdate();
            sta.close();
            con.close();
            sql = ""
                    + "INSERT INTO APP.TICKET (CODIGO, DATA, VALOR,LIBERADO,PAGO,PERNOITE)  VALUES "
                    + "(0, '2013-11-20 11:13:46.844', 50.0, DEFAULT,DEFAULT,DEFAULT),"
                    + "(0, DEFAULT, 50.0, DEFAULT,DEFAULT,DEFAULT)";;
            con = getConnection();
            sta = con.prepareStatement(sql);
            sta.executeUpdate();
            sta.close();
            con.close();

        } catch (SQLException ex) {
            throw new EstacionamentoDAOException("Falha na inserção. " + ex.getMessage());
        }

    }

}
