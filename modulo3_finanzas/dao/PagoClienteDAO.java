/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo3_finanzas.dao;

import config.DatabaseConnection;
import modulo3_finanzas.models.PagoCliente;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PagoClienteDAO {

    public List<PagoCliente> obtenerTodos() throws SQLException {
        List<PagoCliente> lista = new ArrayList<>();
        String query = "SELECT * FROM pagos_cliente";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new PagoCliente(
                    rs.getInt("id_pago_cliente"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_proyecto"),
                    rs.getInt("id_estado_pago"),
                    rs.getDouble("monto"),
                    rs.getDate("fecha_pago").toLocalDate(),
                    rs.getString("metodo_pago")
                ));
            }
        }
        return lista;
    }

    public PagoCliente obtenerPorId(int id) throws SQLException {
        String query = "SELECT * FROM pagos_cliente WHERE id_pago_cliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new PagoCliente(
                    rs.getInt("id_pago_cliente"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_proyecto"),
                    rs.getInt("id_estado_pago"),
                    rs.getDouble("monto"),
                    rs.getDate("fecha_pago").toLocalDate(),
                    rs.getString("metodo_pago")
                );
            }
        }
        return null;
    }

    public int crear(PagoCliente p) throws SQLException {
        String query = "INSERT INTO pagos_cliente (id_cliente, id_proyecto, id_estado_pago, monto, fecha_pago, metodo_pago) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, p.getIdCliente());
            pstmt.setInt(2, p.getIdProyecto());
            pstmt.setInt(3, p.getIdEstadoPago());
            pstmt.setDouble(4, p.getMonto());
            pstmt.setDate(5, Date.valueOf(p.getFechaPago()));
            pstmt.setString(6, p.getMetodoPago());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public boolean actualizar(PagoCliente p) throws SQLException {
        String query = "UPDATE pagos_cliente SET id_cliente=?, id_proyecto=?, id_estado_pago=?, monto=?, fecha_pago=?, metodo_pago=? WHERE id_pago_cliente=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, p.getIdCliente());
            pstmt.setInt(2, p.getIdProyecto());
            pstmt.setInt(3, p.getIdEstadoPago());
            pstmt.setDouble(4, p.getMonto());
            pstmt.setDate(5, Date.valueOf(p.getFechaPago()));
            pstmt.setString(6, p.getMetodoPago());
            pstmt.setInt(7, p.getIdPagoCliente());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String query = "DELETE FROM pagos_cliente WHERE id_pago_cliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}