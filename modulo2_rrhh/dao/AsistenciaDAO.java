/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo2_rrhh.dao;

import config.DatabaseConnection;
import modulo2_rrhh.models.Asistencia;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaDAO {
    public List<Asistencia> obtenerTodos() throws SQLException {
        List<Asistencia> lista = new ArrayList<>();
        String query = "SELECT * FROM control_asistencia";
        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Asistencia(
                    rs.getInt("id_asistencia"),
                    rs.getInt("id_empleado"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getTime("hora_entrada") != null ? rs.getTime("hora_entrada").toLocalTime() : null,
                    rs.getTime("hora_salida") != null ? rs.getTime("hora_salida").toLocalTime() : null,
                    rs.getDouble("horas_trabajadas"),
                    rs.getDouble("horas_extra"),
                    rs.getString("estado_asistencia")
                ));
            }
        }
        return lista;
    }
    // Métodos crear, actualizar, eliminar
}