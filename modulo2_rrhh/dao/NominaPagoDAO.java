/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo2_rrhh.dao;

import config.DatabaseConnection;
import modulo2_rrhh.models.NominaPago;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NominaPagoDAO {
    public List<NominaPago> obtenerTodos() throws SQLException {
        List<NominaPago> lista = new ArrayList<>();
        String query = "SELECT * FROM nomina_pagos";
        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new NominaPago(
                    rs.getInt("id_nomina"),
                    rs.getInt("id_empleado"),
                    rs.getInt("id_periodo_pago"),
                    rs.getDate("fecha_pago").toLocalDate(),
                    rs.getDate("periodo_inicio").toLocalDate(),
                    rs.getDate("periodo_fin").toLocalDate(),
                    rs.getInt("dias_trabajados"),
                    rs.getDouble("horas_trabajadas"),
                    rs.getDouble("horas_extra"),
                    rs.getDouble("monto_pago")
                ));
            }
        }
        return lista;
    }
    // Métodos crear, actualizar, eliminar
}