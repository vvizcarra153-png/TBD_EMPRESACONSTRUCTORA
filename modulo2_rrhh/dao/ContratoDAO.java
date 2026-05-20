/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo2_rrhh.dao;

import config.DatabaseConnection;
import modulo2_rrhh.models.Contrato;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAO {
    public List<Contrato> obtenerTodos() throws SQLException {
        List<Contrato> lista = new ArrayList<>();
        String query = "SELECT * FROM contratos";
        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Contrato(
                    rs.getInt("id_contrato"),
                    rs.getInt("id_empleado"),
                    rs.getInt("id_tipo_contrato"),
                    rs.getInt("id_tipo_pago"),
                    rs.getDouble("tarifa"),
                    rs.getDate("fecha_inicio").toLocalDate(),
                    rs.getDate("fecha_fin") != null ? rs.getDate("fecha_fin").toLocalDate() : null,
                    rs.getString("estado_contrato")
                ));
            }
        }
        return lista;
    }
    // Métodos crear, actualizar, eliminar
}