/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo3_finanzas.dao;

import config.DatabaseConnection;
import modulo3_finanzas.models.Cotizacion;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CotizacionDAO {

    public List<Cotizacion> obtenerTodos() throws SQLException {
        List<Cotizacion> lista = new ArrayList<>();
        String query = "SELECT * FROM cotizaciones";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Cotizacion(
                    rs.getInt("id_cotizacion"),
                    rs.getInt("id_cliente_cotizacion"),
                    rs.getDouble("costos_materiales_cotizacion"),
                    rs.getDouble("costo_mano_obra_cotizacion"),
                    rs.getDouble("otros_costos_cotizacion"),
                    rs.getDouble("metros_cuadrados"),
                    rs.getInt("numero_pisos"),
                    rs.getString("ubicacion_obra"),
                    rs.getInt("tiempo_estimado"),
                    rs.getDouble("margen_ganancia"),
                    rs.getDouble("precio_final"),
                    rs.getDate("fecha_cotizacion").toLocalDate()
                ));
            }
        }
        return lista;
    }

    public Cotizacion obtenerPorId(int id) throws SQLException {
        String query = "SELECT * FROM cotizaciones WHERE id_cotizacion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Cotizacion(
                    rs.getInt("id_cotizacion"),
                    rs.getInt("id_cliente_cotizacion"),
                    rs.getDouble("costos_materiales_cotizacion"),
                    rs.getDouble("costo_mano_obra_cotizacion"),
                    rs.getDouble("otros_costos_cotizacion"),
                    rs.getDouble("metros_cuadrados"),
                    rs.getInt("numero_pisos"),
                    rs.getString("ubicacion_obra"),
                    rs.getInt("tiempo_estimado"),
                    rs.getDouble("margen_ganancia"),
                    rs.getDouble("precio_final"),
                    rs.getDate("fecha_cotizacion").toLocalDate()
                );
            }
        }
        return null;
    }

    public int crear(Cotizacion c) throws SQLException {
        String query = "INSERT INTO cotizaciones (id_cliente_cotizacion, costos_materiales_cotizacion, costo_mano_obra_cotizacion, otros_costos_cotizacion, metros_cuadrados, numero_pisos, ubicacion_obra, tiempo_estimado, margen_ganancia, precio_final, fecha_cotizacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, c.getIdClienteCotizacion());
            pstmt.setDouble(2, c.getCostosMateriales());
            pstmt.setDouble(3, c.getCostoManoObra());
            pstmt.setDouble(4, c.getOtrosCostos());
            pstmt.setDouble(5, c.getMetrosCuadrados());
            pstmt.setInt(6, c.getNumeroPisos());
            pstmt.setString(7, c.getUbicacionObra());
            pstmt.setInt(8, c.getTiempoEstimado());
            pstmt.setDouble(9, c.getMargenGanancia());
            pstmt.setDouble(10, c.getPrecioFinal());
            pstmt.setDate(11, Date.valueOf(c.getFechaCotizacion()));
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public boolean actualizar(Cotizacion c) throws SQLException {
        String query = "UPDATE cotizaciones SET id_cliente_cotizacion=?, costos_materiales_cotizacion=?, costo_mano_obra_cotizacion=?, otros_costos_cotizacion=?, metros_cuadrados=?, numero_pisos=?, ubicacion_obra=?, tiempo_estimado=?, margen_ganancia=?, precio_final=?, fecha_cotizacion=? WHERE id_cotizacion=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, c.getIdClienteCotizacion());
            pstmt.setDouble(2, c.getCostosMateriales());
            pstmt.setDouble(3, c.getCostoManoObra());
            pstmt.setDouble(4, c.getOtrosCostos());
            pstmt.setDouble(5, c.getMetrosCuadrados());
            pstmt.setInt(6, c.getNumeroPisos());
            pstmt.setString(7, c.getUbicacionObra());
            pstmt.setInt(8, c.getTiempoEstimado());
            pstmt.setDouble(9, c.getMargenGanancia());
            pstmt.setDouble(10, c.getPrecioFinal());
            pstmt.setDate(11, Date.valueOf(c.getFechaCotizacion()));
            pstmt.setInt(12, c.getIdCotizacion());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String query = "DELETE FROM cotizaciones WHERE id_cotizacion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}