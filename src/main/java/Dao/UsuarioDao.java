/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.AgenciaModel;
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
public class UsuarioDao {
    
    // MySQL
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/instituicao_bancaria";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String TABLE = "usuarios";

    private static final String DELETE = "DELETE FROM " + TABLE + " WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM " + TABLE + " ORDER BY id";
    private static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " WHERE id=?";
    private static final String FIND_BY_NAME = "SELECT * FROM " + TABLE + " WHERE nome LIKE '%?%'";
    private static final String INSERT = "INSERT INTO " + TABLE + "(codigo, nome, cidade) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE " + TABLE + " SET codigo=?, nome=?, cidade=? WHERE id=?";

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

    public List<AgenciaModel> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<AgenciaModel> list = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var agencia = new AgenciaModel(rs.getInt("id"), rs.getString("codigo"), rs.getString("nome"), rs.getString("cidade"));
                
                list.add(agencia);
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

    public AgenciaModel findById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                var agencia = new AgenciaModel(rs.getInt("id"), rs.getString("codigo"), rs.getString("nome"), rs.getString("cidade"));

                return agencia;
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

    public AgenciaModel findByName(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_NAME);
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                var agencia = new AgenciaModel(rs.getInt("id"), rs.getString("codigo"), rs.getString("nome"), rs.getString("cidade"));

                return agencia;
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

    public int insert(AgenciaModel agencia) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, agencia.getCodigo());
            stmt.setString(2, agencia.getNome());
            stmt.setString(3, agencia.getCidade());

            int result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                agencia.setId(rs.getInt(1));
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

    public int update(AgenciaModel agencia) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, agencia.getCodigo());
            stmt.setString(2, agencia.getNome());
            stmt.setString(3, agencia.getCidade());

            stmt.setInt(4, agencia.getId());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
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
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
