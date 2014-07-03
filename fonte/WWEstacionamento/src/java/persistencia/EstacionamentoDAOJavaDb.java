package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import negocio.ITicket;

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
            String sql = ""+
"create table TICKET\n" +
"(\n" +
"	ID INTEGER  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
"	CODIGO INTEGER default 1 not null,\n" +
"	PLACA VARCHAR(7),\n" +
"	CHAVE VARCHAR(5),\n" +
"	LIBERADO BOOLEAN default FALSE not null,\n" +
"	PAGO BOOLEAN default FALSE not null,\n" +
"	PERNOITE BOOLEAN default FALSE not null,\n" +
"	VALOR DOUBLE default 0.0,\n" +
"        DATAIMPRESSAO TIMESTAMP default CURRENT_TIMESTAMP not null,\n" +
"	DATAPAGAMENTO TIMESTAMP,\n" +
"        CONSTRAINT primary_key PRIMARY KEY (id)\n" +
")";
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

            String sql = "SELECT ID,CODIGO,DATAIMPRESSAO,VALOR,LIBERADO,PAGO,PERNOITE,DATAPAGAMENTO,PLACA,CHAVE from TICKET where TICKET.ID = " + n;
            try {
                try (Connection con = getConnection()) {
                    Statement sta = con.createStatement();
                    ResultSet res = sta.executeQuery(sql);
                    while (res.next()) {
                        ITicket t = new Ticket(
                                res.getInt("ID"),
                                res.getInt("CODIGO"),
                                res.getTimestamp("DATAIMPRESSAO"),
                                res.getDouble("VALOR"),
                                res.getBoolean("LIBERADO"),
                                res.getBoolean("PAGO"),
                                res.getBoolean("PERNOITE"),
                                res.getTimestamp("DATAPAGAMENTO"),
                                res.getString("PLACA"),
                                res.getString("CHAVE")
                        
                        );
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
    public ITicket adicionarTicketAutomatico(String placa) throws EstacionamentoDAOException {
        
        try {
            Connection con = getConnection();
            String sql = " INSERT INTO APP.TICKET (PLACA,CHAVE)  VALUES ('"+placa+"','"+geraChave()+"') ";
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
     * @return o ultimo ticket incluso
     * @throws EstacionamentoDAOException
     */
    @Override
    public ITicket getUltimoTicketIncluso() throws EstacionamentoDAOException {

        List<ITicket> lista = new LinkedList<>();

        String sql = "SELECT ID,CODIGO,DATAIMPRESSAO,VALOR,LIBERADO,PAGO,PERNOITE,DATAPAGAMENTO,PLACA,CHAVE from TICKET where TICKET.ID = ( SELECT Count(ID) FROM TICKET)";
        try {
            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();
                ResultSet res = sta.executeQuery(sql);
                while (res.next()) {
                    ITicket t = new Ticket(
                            res.getInt("ID"),
                            res.getInt("CODIGO"),
                            res.getTimestamp("DATAIMPRESSAO"),
                            res.getDouble("VALOR"),
                            res.getBoolean("LIBERADO"),
                            res.getBoolean("PAGO"),
                            res.getBoolean("PERNOITE"),
                            res.getTimestamp("DATAPAGAMENTO"),
                            res.getString("PLACA"),
                            res.getString("CHAVE")
                    );
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
  //  @Override
    public boolean liberaTodosTickets(Timestamp dia) throws EstacionamentoDAOException {
        String sql = "UPDATE   TICKET SET   TICKET.LIBERADO = TRUE WHERE DATE(TICKET.\"DATAIMPRESSAO\") = DATE(TIMESTAMP('" + dia + "')) ";
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
            sql = "SELECT COUNT(*) FROM TICKET WHERE DATE(TICKET.\"DATAIMPRESSAO\") = DATE(TIMESTAMP('" + dia + "')) AND PAGO = FALSE AND LIBERADO = TRUE";
        } else {
            sql = "SELECT COUNT(*) FROM TICKET WHERE MONTH(TICKET.\"DATAIMPRESSAO\") = " + mes + " AND PAGO = FALSE AND LIBERADO = TRUE";
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
                    + "INSERT INTO APP.TICKET (CODIGO, DATAIMPRESSAO, VALOR,LIBERADO,PAGO,PERNOITE,DATAPAGAMENTO,PLACA,CHAVE)  VALUES"
                    + "(DEFAULT,'2013-11-20 14:13:46.844',3.5,TRUE,TRUE,DEFAULT,'2013-11-20 14:50:46.844','ASD6574','awe87'),"
                    + "(DEFAULT,'2013-11-20 19:13:46.844',10.0,TRUE,TRUE,DEFAULT,'2013-11-20 14:50:46.844','AXC3574','aw787'),"
                    + "(DEFAULT,'2013-11-20 19:13:46.844',50.0,TRUE,TRUE,TRUE,'2013-11-21 14:50:46.844','AWE2174','a8e87'),"
                    + "(DEFAULT,'2013-11-20 08:13:46.844',200.0,TRUE,TRUE,TRUE,'2013-11-24 14:50:46.844','ASD4789','a9e87')"
                    ;
            Connection con = getConnection();
            PreparedStatement sta = con.prepareStatement(sql);
            sta.executeUpdate();
            sta.close();
            con.close();
             sql = ""
                      + "INSERT INTO APP.TICKET (CODIGO, DATAIMPRESSAO, VALOR,LIBERADO,PAGO,PERNOITE,,PLACA,CHAVE)  VALUES"
                      +"(DEFAULT,'2013-11-20 20:13:46.844',DEFAULT,TRUE,DEFAULT,DEFAULT,'ASD4789','a9887'),"
                      + "(DEFAULT,'2013-11-20 19:13:46.844',DEFAULT,TRUE,DEFAULT,DEFAULT,'ASD4789','a8787')";
             con = getConnection();
             sta = con.prepareStatement(sql);
            sta.executeUpdate();
            sta.close();
            con.close();
            sql = ""
                    + "INSERT INTO APP.TICKET (DATAIMPRESSAO)  VALUES"
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
                    + "INSERT INTO APP.TICKET (CODIGO, DATAIMPRESSAO, VALOR,LIBERADO,PAGO,PERNOITE)  VALUES "
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

    @Override
    public boolean liberaTicket(ITicket ticket) throws EstacionamentoDAOException {
       
        String sql = "UPDATE   TICKET SET   TICKET.LIBERADO = TRUE WHERE DATE(TICKET.ID ="+ticket.getId();
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

    @Override
    public ITicket getTicketComPlacaEChave(String placa, String chave) throws EstacionamentoDAOException {
   
        List<ITicket> lista = new LinkedList<>();

            String sql = "SELECT ID,CODIGO,DATAIMPRESSAO,VALOR,LIBERADO,PAGO,PERNOITE,DATAPAGAMENTO,PLACA,CHAVE from TICKET where TICKET.PLACA = '" + placa+ "' and TICKET.CHAVE= '"+chave+"'";
            try {
                try (Connection con = getConnection()) {
                    Statement sta = con.createStatement();
                    ResultSet res = sta.executeQuery(sql);
                    while (res.next()) {
                        ITicket t = new Ticket(
                                res.getInt("ID"),
                                res.getInt("CODIGO"),
                                res.getTimestamp("DATAIMPRESSAO"),
                                res.getDouble("VALOR"),
                                res.getBoolean("LIBERADO"),
                                res.getBoolean("PAGO"),
                                res.getBoolean("PERNOITE"),
                                res.getTimestamp("DATAPAGAMENTO"),
                                res.getString("PLACA"),
                                res.getString("CHAVE")
                        );
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

    private String geraChave() {
            
        String chave = "";
        
        Random gerador = new Random();
        	ArrayList<String> alfabeto = new ArrayList( Arrays.asList( "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
"R", "S", "T", "U", "V", "W", "X", "Y", "Z","0","1", "2","3","4","5","6","7","8","9"));
        for (int i = 0;i<5;i++){
        int numero = gerador.nextInt(36);
        chave = chave + alfabeto.get(numero);
        
        }
        System.out.println(chave);
        return chave;
        
    }


}
