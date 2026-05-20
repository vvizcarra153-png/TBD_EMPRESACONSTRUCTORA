/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo3_finanzas.dao;

import config.DatabaseConnection;
import modulo3_finanzas.models.Cliente;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public List<Cliente> obtenerTodos() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT * FROM clientes ORDER BY nombre_cliente";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getString("telefono_cliente"),
                    rs.getString("email_cliente"),
                    rs.getString("direccion_cliente"),
                    rs.getInt("id_tipo_documento"),
                    rs.getString("nro_documento_cliente"),
                    rs.getDate("fecha_registro_cliente").toLocalDate()
                ));
            }
        }
        return lista;
    }

    public Cliente obtenerPorId(int id) throws SQLException {
        String query = "SELECT * FROM clientes WHERE id_cliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getString("telefono_cliente"),
                    rs.getString("email_cliente"),
                    rs.getString("direccion_cliente"),
                    rs.getInt("id_tipo_documento"),
                    rs.getString("nro_documento_cliente"),
                    rs.getDate("fecha_registro_cliente").toLocalDate()
                );
            }
        }
        return null;
    }

    public int crear(Cliente c) throws SQLException {
        String query = "INSERT INTO clientes (nombre_cliente, telefono_cliente, email_cliente, direccion_cliente, id_tipo_documento, nro_documento_cliente, fecha_registro_cliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, c.getNombreCliente());
            pstmt.setString(2, c.getTelefonoCliente());
            pstmt.setString(3, c.getEmailCliente());
            pstmt.setString(4, c.getDireccionCliente());
            pstmt.setInt(5, c.getIdTipoDocumento());
            pstmt.setString(6, c.getNroDocumentoCliente());
            pstmt.setDate(7, Date.valueOf(c.getFechaRegistroCliente()));
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public boolean actualizar(Cliente c) throws SQLException {
        String query = "UPDATE clientes SET nombre_cliente=?, telefono_cliente=?, email_cliente=?, direccion_cliente=?, id_tipo_documento=?, nro_documento_cliente=?, fecha_registro_cliente=? WHERE id_cliente=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, c.getNombreCliente());
            pstmt.setString(2, c.getTelefonoCliente());
            pstmt.setString(3, c.getEmailCliente());
            pstmt.setString(4, c.getDireccionCliente());
            pstmt.setInt(5, c.getIdTipoDocumento());
            pstmt.setString(6, c.getNroDocumentoCliente());
            pstmt.setDate(7, Date.valueOf(c.getFechaRegistroCliente()));
            pstmt.setInt(8, c.getIdCliente());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String query = "DELETE FROM clientes WHERE id_cliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}