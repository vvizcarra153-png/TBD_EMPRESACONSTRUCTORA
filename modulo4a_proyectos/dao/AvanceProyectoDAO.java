/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo4a_proyectos.dao;

import config.DatabaseConnection;
import modulo4a_proyectos.models.AvanceProyecto;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvanceProyectoDAO {

    public List<AvanceProyecto> obtenerTodos() throws SQLException {
        List<AvanceProyecto> lista = new ArrayList<>();
        String query = "SELECT * FROM avance_proyecto";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new AvanceProyecto(
                    rs.getInt("id_avance"),
                    rs.getInt("id_proyecto"),
                    rs.getDate("fecha") != null ? rs.getDate("fecha").toLocalDate() : null,
                    rs.getDouble("porcentaje_avance"),
                    rs.getString("observaciones")
                ));
            }
        }
        return lista;
    }

    public AvanceProyecto obtenerPorId(int id) throws SQLException {
        String query = "SELECT * FROM avance_proyecto WHERE id_avance = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new AvanceProyecto(
                    rs.getInt("id_avance"),
                    rs.getInt("id_proyecto"),
                    rs.getDate("fecha") != null ? rs.getDate("fecha").toLocalDate() : null,
                    rs.getDouble("porcentaje_avance"),
                    rs.getString("observaciones")
                );
            }
        }
        return null;
    }

    public int crear(AvanceProyecto a) throws SQLException {
        String query = "INSERT INTO avance_proyecto (id_proyecto, fecha, porcentaje_avance, observaciones) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, a.getIdProyecto());
            if (a.getFecha() != null) pstmt.setDate(2, Date.valueOf(a.getFecha())); else pstmt.setNull(2, Types.DATE);
            pstmt.setDouble(3, a.getPorcentajeAvance());
            pstmt.setString(4, a.getObservaciones());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public boolean actualizar(AvanceProyecto a) throws SQLException {
        String query = "UPDATE avance_proyecto SET id_proyecto=?, fecha=?, porcentaje_avance=?, observaciones=? WHERE id_avance=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, a.getIdProyecto());
            if (a.getFecha() != null) pstmt.setDate(2, Date.valueOf(a.getFecha())); else pstmt.setNull(2, Types.DATE);
            pstmt.setDouble(3, a.getPorcentajeAvance());
            pstmt.setString(4, a.getObservaciones());
            pstmt.setInt(5, a.getIdAvance());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String query = "DELETE FROM avance_proyecto WHERE id_avance = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}