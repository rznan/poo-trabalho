package com.rznan.persistence;

import com.rznan.entity.Trem;
import com.rznan.exceptions.TransporteException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TremDAO implements IDao<Trem> {

    private final static String DB_CLASS = "org.mariadb.jdbc.Driver";
    private final static String DB_URL = "jdbc:mariadb://localhost:3306/transportes";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "userenan";

    private final Connection con;

    public TremDAO() throws TransporteException {
        try {
            Class.forName(DB_CLASS);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new TransporteException( e );
        }
    }

    @Override
    public void insert(Trem trem) throws TransporteException {
            try {
                String sql = """
                INSERT INTO Trem (id, quantidade_max_passageiros,
                velocidade_maxima, extensao_total, empresa_dona)
                VALUES (?, ?, ?, ?, ?)
                """;
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, trem.getId());
                stm.setInt(2, trem.getQuantidadeMaxPassageiros());
                stm.setDouble(3, trem.getVelocidadeMaxima());
                stm.setDouble(4, trem.getExtensaoTotal());
                stm.setString(5, trem.getEmpresaDona());
                stm.executeUpdate();
            } catch (SQLException err) {
                throw new TransporteException(err);
        }
    }

    @Override
    public void update(Trem trem) throws TransporteException {
        try {
            String sql = """
            UPDATE Trem SET quantidade_max_passageiros = ?,
            velocidade_maxima = ?, extensao_total = ?,
            empresa_dona = ?
            WHERE id = ?
            """;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, trem.getQuantidadeMaxPassageiros());
            stm.setDouble(2, trem.getVelocidadeMaxima());
            stm.setDouble(3, trem.getExtensaoTotal());
            stm.setString(4, trem.getEmpresaDona());
            stm.setInt(5, trem.getId());
            stm.executeUpdate();
        } catch (SQLException err) {
            throw new TransporteException(err);
        }
    }

    @Override
    public void remove(Trem trem) throws TransporteException {
        try {
            String sql = """
                    DELETE FROM Trem
                    WHERE id = ?
                    """;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, trem.getId());
            stm.executeUpdate();
        } catch (SQLException err) {
            throw new TransporteException(err);
        }
    }

    @Override
    public List<Trem> search(String name) throws TransporteException {
        List<Trem> list = new ArrayList<>();
        try {
            String sql = """
                    SELECT * FROM Trem
                    WHERE empresa_dona LIKE ?
                    """;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Trem trem = new Trem();
                trem.setId(rs.getInt("id"));
                trem.setQuantidadeMaxPassageiros(rs.getInt("quantidade_max_passageiros"));
                trem.setVelocidadeMaxima(rs.getDouble("velocidade_maxima"));
                trem.setExtensaoTotal(rs.getInt("extensao_total"));
                trem.setEmpresaDona(rs.getString("empresa_dona"));
                list.add(trem);
            }
        } catch (SQLException err) {
            throw new TransporteException(err);
        }
        return list;
    }
}
