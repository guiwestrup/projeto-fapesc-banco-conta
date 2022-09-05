/*
 *
 */
package Dao;

import Model.ClienteModel;
import Model.ContaModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guilhermewestrup
 */
public class ContaDao {

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/instituicao_bancaria";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String TABLE = "contas";

    private static final String DELETE = "DELETE FROM " + TABLE + " WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM " + TABLE + " ORDER BY id";
    private static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " WHERE id=?";
    private static final String FIND_BY_CLIENTE_ID = "SELECT * FROM " + TABLE + " WHERE cliente_id=? LIMIT 1";
    private static final String FIND_BY_NAME = "SELECT * FROM " + TABLE + " WHERE nome LIKE '%?%'";
    private static final String INSERT = "INSERT INTO " + TABLE + "(numero_conta, saldo, cliente_id, agencia_id) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE " + TABLE + " SET numero_conta=?, saldo=?, cliente_id=?, agencia_id=? WHERE id=?";

    public int delete(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(DELETE);
            stmt.setInt(1, id);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(stmt);
        }
    }

    public List<ContaModel> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<ContaModel> list = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var conta = new ContaModel();
                conta.setId(rs.getInt("id"));
                conta.setNumeroConta(rs.getString("numero_conta"));
                conta.setSaldo(rs.getFloat("saldo"));

                var clienteDao = new ClienteDao();
                var cliente = clienteDao.findById(rs.getInt("cliente_id"));
                conta.setCliente(cliente);

                var agenciaDao = new AgenciaDao();
                var agencia = agenciaDao.findById(rs.getInt("agencia_id"));

                conta.setAgencia(agencia);

                list.add(conta);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }

        return list;
    }

    public ContaModel findById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                var conta = new ContaModel();
                conta.setId(rs.getInt("id"));
                conta.setNumeroConta(rs.getString("numero_conta"));
                conta.setSaldo(rs.getFloat("saldo"));

                var clienteDao = new ClienteDao();
                ClienteModel cliente = clienteDao.findById(rs.getInt("cliente_id"));
                conta.setCliente(cliente);

                var agenciaDao = new AgenciaDao();
                var agencia = agenciaDao.findById(rs.getInt("agencia_id"));
                conta.setAgencia(agencia);
                
                return conta;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    public ContaModel findByName(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_NAME);
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                var conta = new ContaModel();
                conta.setId(rs.getInt("id"));
                conta.setNumeroConta(rs.getString("numero_conta"));
                conta.setSaldo(rs.getFloat("saldo"));

                var clienteDao = new ClienteDao();
                var cliente = clienteDao.findById(rs.getInt("cliente_id"));
                conta.setCliente(cliente);

                var agenciaDao = new AgenciaDao();
                var agencia = agenciaDao.findById(rs.getInt("agencia_id"));

                conta.setAgencia(agencia);
                return conta;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    public int insert(ContaModel conta) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, conta.getNumeroConta());
            stmt.setFloat(2, conta.getSaldo());
            stmt.setInt(3, conta.getCliente().getId());
            stmt.setInt(4, conta.getAgencia().getId());

            int result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                conta.setId(rs.getInt(1));
            }

            return result;
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    public int update(ContaModel conta) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, conta.getNumeroConta());
            stmt.setFloat(2, conta.getSaldo());
            stmt.setInt(3, conta.getCliente().getId());
            stmt.setInt(4, conta.getAgencia().getId());

            stmt.setInt(5, conta.getId());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    public ContaModel findByClienteId(int clienteId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_CLIENTE_ID);
            stmt.setInt(1, clienteId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                var conta = new ContaModel();
                conta.setId(rs.getInt("id"));
                conta.setNumeroConta(rs.getString("numero_conta"));
                conta.setSaldo(rs.getFloat("saldo"));

                var clienteDao = new ClienteDao();
                var cliente = clienteDao.findById(rs.getInt("cliente_id"));
                conta.setCliente(cliente);

                var agenciaDao = new AgenciaDao();
                var agencia = agenciaDao.findById(rs.getInt("agencia_id"));

                conta.setAgencia(agencia);
                return conta;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    private Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
