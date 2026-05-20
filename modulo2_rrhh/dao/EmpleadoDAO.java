/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo2_rrhh.dao;

import config.DatabaseConnection;
import modulo2_rrhh.models.Empleado;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    public List<Empleado> obtenerTodos() throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        String query = "SELECT * FROM empleados ORDER BY nombre_empleado";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getString("nombre_empleado"),
                    rs.getString("apellido_empleado"),
                    rs.getString("sexo"),
                    rs.getString("telefono_empleado"),
                    rs.getString("email_empleado"),
                    rs.getString("ci_empleado"),
                    rs.getString("direccion_empleado"),
                    rs.getDate("fecha_nacimiento_empleado").toLocalDate(),
                    rs.getInt("id_categoria_empleado"),
                    rs.getDate("fecha_ingreso_empleado").toLocalDate()
                ));
            }
        }
        return lista;
    }

    public Empleado obtenerPorId(int id) throws SQLException {
        String query = "SELECT * FROM empleados WHERE id_empleado = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getString("nombre_empleado"),
                    rs.getString("apellido_empleado"),
                    rs.getString("sexo"),
                    rs.getString("telefono_empleado"),
                    rs.getString("email_empleado"),
                    rs.getString("ci_empleado"),
                    rs.getString("direccion_empleado"),
                    rs.getDate("fecha_nacimiento_empleado").toLocalDate(),
                    rs.getInt("id_categoria_empleado"),
                    rs.getDate("fecha_ingreso_empleado").toLocalDate()
                );
            }
        }
        return null;
    }

    public int crear(Empleado e) throws SQLException {
        String query = "INSERT INTO empleados (nombre_empleado, apellido_empleado, sexo, telefono_empleado, email_empleado, ci_empleado, direccion_empleado, fecha_nacimiento_empleado, id_categoria_empleado, fecha_ingreso_empleado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, e.getNombreEmpleado());
            pstmt.setString(2, e.getApellidoEmpleado());
            pstmt.setString(3, e.getSexo());
            pstmt.setString(4, e.getTelefonoEmpleado());
            pstmt.setString(5, e.getEmailEmpleado());
            pstmt.setString(6, e.getCiEmpleado());
            pstmt.setString(7, e.getDireccionEmpleado());
            pstmt.setDate(8, Date.valueOf(e.getFechaNacimientoEmpleado()));
            pstmt.setInt(9, e.getIdCategoriaEmpleado());
            pstmt.setDate(10, Date.valueOf(e.getFechaIngresoEmpleado()));
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public boolean actualizar(Empleado e) throws SQLException {
        String query = "UPDATE empleados SET nombre_empleado=?, apellido_empleado=?, sexo=?, telefono_empleado=?, email_empleado=?, ci_empleado=?, direccion_empleado=?, fecha_nacimiento_empleado=?, id_categoria_empleado=?, fecha_ingreso_empleado=? WHERE id_empleado=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, e.getNombreEmpleado());
            pstmt.setString(2, e.getApellidoEmpleado());
            pstmt.setString(3, e.getSexo());
            pstmt.setString(4, e.getTelefonoEmpleado());
            pstmt.setString(5, e.getEmailEmpleado());
            pstmt.setString(6, e.getCiEmpleado());
            pstmt.setString(7, e.getDireccionEmpleado());
            pstmt.setDate(8, Date.valueOf(e.getFechaNacimientoEmpleado()));
            pstmt.setInt(9, e.getIdCategoriaEmpleado());
            pstmt.setDate(10, Date.valueOf(e.getFechaIngresoEmpleado()));
            pstmt.setInt(11, e.getIdEmpleado());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String query = "DELETE FROM empleados WHERE id_empleado = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}