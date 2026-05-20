/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo4a_proyectos.dao;

import config.DatabaseConnection;
import modulo4a_proyectos.models.Proyecto;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProyectoDAO {

    public List<Proyecto> obtenerTodos() throws SQLException {
        List<Proyecto> proyectos = new ArrayList<>();
        String query = "SELECT * FROM proyectos ORDER BY nombre_proyecto";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                proyectos.add(new Proyecto(
                    rs.getInt("id_proyecto"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_estado_proyecto"),
                    rs.getInt("id_centro_costo"),
                    rs.getObject("id_cotizacion") != null ? rs.getInt("id_cotizacion") : null,
                    rs.getString("nombre_proyecto"),
                    rs.getString("ubicacion_proyecto"),
                    rs.getDate("fecha_inicio_proyecto").toLocalDate(),
                    rs.getDate("fecha_fin_proyecto") != null ? rs.getDate("fecha_fin_proyecto").toLocalDate() : null,
                    rs.getDouble("costo_real_proyecto"),
                    rs.getString("prioridad_proyecto")
                ));
            }
        }
        return proyectos;
    }

    public Proyecto obtenerPorId(int id) throws SQLException {
        String query = "SELECT * FROM proyectos WHERE id_proyecto = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Proyecto(
                    rs.getInt("id_proyecto"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_estado_proyecto"),
                    rs.getInt("id_centro_costo"),
                    rs.getObject("id_cotizacion") != null ? rs.getInt("id_cotizacion") : null,
                    rs.getString("nombre_proyecto"),
                    rs.getString("ubicacion_proyecto"),
                    rs.getDate("fecha_inicio_proyecto").toLocalDate(),
                    rs.getDate("fecha_fin_proyecto") != null ? rs.getDate("fecha_fin_proyecto").toLocalDate() : null,
                    rs.getDouble("costo_real_proyecto"),
                    rs.getString("prioridad_proyecto")
                );
            }
        }
        return null;
    }

    public int crear(Proyecto p) throws SQLException {
        String query = "INSERT INTO proyectos (id_cliente, id_estado_proyecto, id_centro_costo, id_cotizacion, nombre_proyecto, ubicacion_proyecto, fecha_inicio_proyecto, fecha_fin_proyecto, costo_real_proyecto, prioridad_proyecto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, p.getIdCliente());
            pstmt.setInt(2, p.getIdEstadoProyecto());
            pstmt.setInt(3, p.getIdCentroCosto());
            if (p.getIdCotizacion() != null) {
                pstmt.setInt(4, p.getIdCotizacion());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            pstmt.setString(5, p.getNombreProyecto());
            pstmt.setString(6, p.getUbicacionProyecto());
            pstmt.setDate(7, Date.valueOf(p.getFechaInicioProyecto()));
            if (p.getFechaFinProyecto() != null)
                pstmt.setDate(8, Date.valueOf(p.getFechaFinProyecto()));
            else
                pstmt.setNull(8, Types.DATE);
            pstmt.setDouble(9, p.getCostoRealProyecto());
            pstmt.setString(10, p.getPrioridadProyecto());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public boolean actualizar(Proyecto p) throws SQLException {
        String query = "UPDATE proyectos SET id_cliente=?, id_estado_proyecto=?, id_centro_costo=?, id_cotizacion=?, nombre_proyecto=?, ubicacion_proyecto=?, fecha_inicio_proyecto=?, fecha_fin_proyecto=?, costo_real_proyecto=?, prioridad_proyecto=? WHERE id_proyecto=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, p.getIdCliente());
            pstmt.setInt(2, p.getIdEstadoProyecto());
            pstmt.setInt(3, p.getIdCentroCosto());
            if (p.getIdCotizacion() != null) {
                pstmt.setInt(4, p.getIdCotizacion());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            pstmt.setString(5, p.getNombreProyecto());
            pstmt.setString(6, p.getUbicacionProyecto());
            pstmt.setDate(7, Date.valueOf(p.getFechaInicioProyecto()));
            if (p.getFechaFinProyecto() != null)
                pstmt.setDate(8, Date.valueOf(p.getFechaFinProyecto()));
            else
                pstmt.setNull(8, Types.DATE);
            pstmt.setDouble(9, p.getCostoRealProyecto());
            pstmt.setString(10, p.getPrioridadProyecto());
            pstmt.setInt(11, p.getIdProyecto());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String query = "DELETE FROM proyectos WHERE id_proyecto = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}
