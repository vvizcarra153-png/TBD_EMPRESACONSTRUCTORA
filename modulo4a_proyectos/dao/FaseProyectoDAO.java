/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo4a_proyectos.dao;

import config.DatabaseConnection;
import modulo4a_proyectos.models.FaseProyecto;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FaseProyectoDAO {

    public List<FaseProyecto> obtenerTodos() throws SQLException {
        List<FaseProyecto> lista = new ArrayList<>();
        String query = "SELECT * FROM fases_proyecto";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new FaseProyecto(
                    rs.getInt("id_fase"),
                    rs.getInt("id_proyecto"),
                    rs.getString("nombre_fase"),
                    rs.getString("descripcion_fase"),
                    rs.getInt("orden"),
                    rs.getDate("fecha_inicio_fase") != null ? rs.getDate("fecha_inicio_fase").toLocalDate() : null,
                    rs.getDate("fecha_fin_fase") != null ? rs.getDate("fecha_fin_fase").toLocalDate() : null,
                    rs.getDouble("progreso"),
                    rs.getString("estado"),
                    rs.getDouble("porcentaje_asignado"),
                    rs.getDouble("costo_estimado"),
                    rs.getDouble("costo_real")
                ));
            }
        }
        return lista;
    }

    public FaseProyecto obtenerPorId(int id) throws SQLException {
        String query = "SELECT * FROM fases_proyecto WHERE id_fase = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new FaseProyecto(
                    rs.getInt("id_fase"),
                    rs.getInt("id_proyecto"),
                    rs.getString("nombre_fase"),
                    rs.getString("descripcion_fase"),
                    rs.getInt("orden"),
                    rs.getDate("fecha_inicio_fase") != null ? rs.getDate("fecha_inicio_fase").toLocalDate() : null,
                    rs.getDate("fecha_fin_fase") != null ? rs.getDate("fecha_fin_fase").toLocalDate() : null,
                    rs.getDouble("progreso"),
                    rs.getString("estado"),
                    rs.getDouble("porcentaje_asignado"),
                    rs.getDouble("costo_estimado"),
                    rs.getDouble("costo_real")
                );
            }
        }
        return null;
    }

    public int crear(FaseProyecto f) throws SQLException {
        String query = "INSERT INTO fases_proyecto (id_proyecto, nombre_fase, descripcion_fase, orden, fecha_inicio_fase, fecha_fin_fase, progreso, estado, porcentaje_asignado, costo_estimado, costo_real) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, f.getIdProyecto());
            pstmt.setString(2, f.getNombreFase());
            pstmt.setString(3, f.getDescripcionFase());
            pstmt.setInt(4, f.getOrden());
            if (f.getFechaInicioFase() != null)
                pstmt.setDate(5, Date.valueOf(f.getFechaInicioFase()));
            else pstmt.setNull(5, Types.DATE);
            if (f.getFechaFinFase() != null)
                pstmt.setDate(6, Date.valueOf(f.getFechaFinFase()));
            else pstmt.setNull(6, Types.DATE);
            pstmt.setDouble(7, f.getProgreso());
            pstmt.setString(8, f.getEstado());
            pstmt.setDouble(9, f.getPorcentajeAsignado());
            pstmt.setDouble(10, f.getCostoEstimado());
            pstmt.setDouble(11, f.getCostoReal());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public boolean actualizar(FaseProyecto f) throws SQLException {
        String query = "UPDATE fases_proyecto SET id_proyecto=?, nombre_fase=?, descripcion_fase=?, orden=?, fecha_inicio_fase=?, fecha_fin_fase=?, progreso=?, estado=?, porcentaje_asignado=?, costo_estimado=?, costo_real=? WHERE id_fase=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, f.getIdProyecto());
            pstmt.setString(2, f.getNombreFase());
            pstmt.setString(3, f.getDescripcionFase());
            pstmt.setInt(4, f.getOrden());
            if (f.getFechaInicioFase() != null)
                pstmt.setDate(5, Date.valueOf(f.getFechaInicioFase()));
            else pstmt.setNull(5, Types.DATE);
            if (f.getFechaFinFase() != null)
                pstmt.setDate(6, Date.valueOf(f.getFechaFinFase()));
            else pstmt.setNull(6, Types.DATE);
            pstmt.setDouble(7, f.getProgreso());
            pstmt.setString(8, f.getEstado());
            pstmt.setDouble(9, f.getPorcentajeAsignado());
            pstmt.setDouble(10, f.getCostoEstimado());
            pstmt.setDouble(11, f.getCostoReal());
            pstmt.setInt(12, f.getIdFase());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String query = "DELETE FROM fases_proyecto WHERE id_fase = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}