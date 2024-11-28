package com.rznan.persistence;

import com.rznan.entity.Onibus;
import com.rznan.exceptions.TransporteException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OnibusDAO implements IDao<Onibus> {

    private final static String DB_CLASS = "org.mariadb.jdbc.Driver";
    private final static String DB_URL = "jdbc:mariadb://localhost:3306/transportes";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "userenan";

    private final Connection con;

    public OnibusDAO() throws TransporteException {
        try {
            Class.forName(DB_CLASS);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new TransporteException( e );
        }
    }

    @Override
    public void insert(Onibus onibus) throws TransporteException {
            try {
                String sql = """
                INSERT INTO Onibus (id, quantidade_max_passageiros,
                velocidade_maxima, numero_de_pneus, marca_fabricante)
                VALUES (?, ?, ?, ?, ?)
                """;
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, onibus.getId());
                stm.setInt(2, onibus.getQuantidadeMaxPassageiros());
                stm.setDouble(3, onibus.getVelocidadeMaxima());
                stm.setInt(4, onibus.getNumeroDePneus());
                stm.setString(5, onibus.getMarcaFabricante());
                stm.executeUpdate();
            } catch (SQLException err) {
                throw new TransporteException(err);
        }
    }

    @Override
    public void update(Onibus onibus) throws TransporteException {
        try {
            String sql = """
            UPDATE Onibus SET quantidade_max_passageiros = ?,
            velocidade_maxima = ?, numero_de_pneus = ?,
            marca_fabricante = ?
            WHERE id = ?
            """;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, onibus.getQuantidadeMaxPassageiros());
            stm.setDouble(2, onibus.getVelocidadeMaxima());
            stm.setInt(3, onibus.getNumeroDePneus());
            stm.setString(4, onibus.getMarcaFabricante());
            stm.setInt(5, onibus.getId());
            stm.executeUpdate();
        } catch (SQLException err) {
            throw new TransporteException(err);
        }
    }

    @Override
    public void remove(Onibus onibus) throws TransporteException {
        try {
            String sql = """
                    DELETE FROM Onibus
                    WHERE id = ?
                    """;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, onibus.getId());
            stm.executeUpdate();
        } catch (SQLException err) {
            throw new TransporteException(err);
        }
    }

    @Override
    public List<Onibus> search(String name) throws TransporteException {
        List<Onibus> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Onibus WHERE marca_fabricante LIKE ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Onibus onibus = new Onibus();
                onibus.setId(rs.getInt("id"));
                onibus.setQuantidadeMaxPassageiros(rs.getInt("quantidade_max_passageiros"));
                onibus.setVelocidadeMaxima(rs.getDouble("velocidade_maxima"));
                onibus.setNumeroDePneus(rs.getInt("numero_de_pneus"));
                onibus.setMarcaFabricante(rs.getString("marca_fabricante"));
                list.add(onibus);
            }
        } catch (SQLException err) {
            throw new TransporteException(err);
        }
        return list;
    }
}
