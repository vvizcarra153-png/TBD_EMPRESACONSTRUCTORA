/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo4a_proyectos.dao;

import config.DatabaseConnection;
import modulo4a_proyectos.models.TareaFase;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TareaFaseDAO {

    public List<TareaFase> obtenerTodos() throws SQLException {
        List<TareaFase> lista = new ArrayList<>();
        String query = "SELECT * FROM tareas_fase";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new TareaFase(
                    rs.getInt("id_tarea"),
                    rs.getInt("id_fase"),
                    rs.getInt("id_empleado_responsable"),
                    rs.getString("nombre_tarea"),
                    rs.getString("descripcion_tarea"),
                    rs.getDate("fecha_inicio_tarea") != null ? rs.getDate("fecha_inicio_tarea").toLocalDate() : null,
                    rs.getDate("fecha_fin_tarea") != null ? rs.getDate("fecha_fin_tarea").toLocalDate() : null,
                    rs.getString("prioridad_tarea"),
                    rs.getString("estado"),
                    rs.getDouble("porcentaje_avance")
                ));
            }
        }
        return lista;
    }

    public TareaFase obtenerPorId(int id) throws SQLException {
        String query = "SELECT * FROM tareas_fase WHERE id_tarea = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new TareaFase(
                    rs.getInt("id_tarea"),
                    rs.getInt("id_fase"),
                    rs.getInt("id_empleado_responsable"),
                    rs.getString("nombre_tarea"),
                    rs.getString("descripcion_tarea"),
                    rs.getDate("fecha_inicio_tarea") != null ? rs.getDate("fecha_inicio_tarea").toLocalDate() : null,
                    rs.getDate("fecha_fin_tarea") != null ? rs.getDate("fecha_fin_tarea").toLocalDate() : null,
                    rs.getString("prioridad_tarea"),
                    rs.getString("estado"),
                    rs.getDouble("porcentaje_avance")
                );
            }
        }
        return null;
    }

    public int crear(TareaFase t) throws SQLException {
        String query = "INSERT INTO tareas_fase (id_fase, id_empleado_responsable, nombre_tarea, descripcion_tarea, fecha_inicio_tarea, fecha_fin_tarea, prioridad_tarea, estado, porcentaje_avance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, t.getIdFase());
            pstmt.setInt(2, t.getIdEmpleadoResponsable());
            pstmt.setString(3, t.getNombreTarea());
            pstmt.setString(4, t.getDescripcionTarea());
            if (t.getFechaInicioTarea() != null) pstmt.setDate(5, Date.valueOf(t.getFechaInicioTarea())); else pstmt.setNull(5, Types.DATE);
            if (t.getFechaFinTarea() != null) pstmt.setDate(6, Date.valueOf(t.getFechaFinTarea())); else pstmt.setNull(6, Types.DATE);
            pstmt.setString(7, t.getPrioridadTarea());
            pstmt.setString(8, t.getEstado());
            pstmt.setDouble(9, t.getPorcentajeAvance());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public boolean actualizar(TareaFase t) throws SQLException {
        String query = "UPDATE tareas_fase SET id_fase=?, id_empleado_responsable=?, nombre_tarea=?, descripcion_tarea=?, fecha_inicio_tarea=?, fecha_fin_tarea=?, prioridad_tarea=?, estado=?, porcentaje_avance=? WHERE id_tarea=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, t.getIdFase());
            pstmt.setInt(2, t.getIdEmpleadoResponsable());
            pstmt.setString(3, t.getNombreTarea());
            pstmt.setString(4, t.getDescripcionTarea());
            if (t.getFechaInicioTarea() != null) pstmt.setDate(5, Date.valueOf(t.getFechaInicioTarea())); else pstmt.setNull(5, Types.DATE);
            if (t.getFechaFinTarea() != null) pstmt.setDate(6, Date.valueOf(t.getFechaFinTarea())); else pstmt.setNull(6, Types.DATE);
            pstmt.setString(7, t.getPrioridadTarea());
            pstmt.setString(8, t.getEstado());
            pstmt.setDouble(9, t.getPorcentajeAvance());
            pstmt.setInt(10, t.getIdTarea());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String query = "DELETE FROM tareas_fase WHERE id_tarea = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}