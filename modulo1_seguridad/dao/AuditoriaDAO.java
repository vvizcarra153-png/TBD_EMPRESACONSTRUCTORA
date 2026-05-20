/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo1_seguridad.dao;

import config.DatabaseConnection;
import modulo1_seguridad.models.Auditoria;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para Auditoría
 * Integrante: 1 - Seguridad y Usuarios
 */
public class AuditoriaDAO {
    
    /**
     * Registrar una operación en auditoría
     */
    public void registrar(String tablaAfectada, String operacion, String usuario,
                         String datosAnteriores, String datosNuevos) throws SQLException {
        String query = "INSERT INTO auditoria (tabla_afectada, operacion, usuario, " +
                      "fecha, datos_anteriores, datos_nuevos) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, tablaAfectada);
            pstmt.setString(2, operacion);
            pstmt.setString(3, usuario);
            pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setString(5, datosAnteriores);
            pstmt.setString(6, datosNuevos);
            
            pstmt.executeUpdate();
            System.out.println("✓ Auditoría registrada: " + operacion + " en " + tablaAfectada);
        }
    }
    
    /**
     * Obtener todos los registros de auditoría
     */
    public List<Auditoria> obtenerTodos() throws SQLException {
        List<Auditoria> auditorias = new ArrayList<>();
        String query = "SELECT id_auditoria, tabla_afectada, operacion, usuario, " +
                      "fecha, datos_anteriores, datos_nuevos FROM auditoria " +
                      "ORDER BY fecha DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                auditorias.add(new Auditoria(
                    rs.getInt("id_auditoria"),
                    rs.getString("tabla_afectada"),
                    rs.getString("operacion"),
                    rs.getString("usuario"),
                    rs.getTimestamp("fecha").toLocalDateTime(),
                    rs.getString("datos_anteriores"),
                    rs.getString("datos_nuevos")
                ));
            }
        }
        return auditorias;
    }
    
    /**
     * Obtener auditoría por tabla
     */
    public List<Auditoria> obtenerPorTabla(String tabla) throws SQLException {
        List<Auditoria> auditorias = new ArrayList<>();
        String query = "SELECT id_auditoria, tabla_afectada, operacion, usuario, " +
                      "fecha, datos_anteriores, datos_nuevos FROM auditoria " +
                      "WHERE tabla_afectada = ? ORDER BY fecha DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, tabla);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                auditorias.add(new Auditoria(
                    rs.getInt("id_auditoria"),
                    rs.getString("tabla_afectada"),
                    rs.getString("operacion"),
                    rs.getString("usuario"),
                    rs.getTimestamp("fecha").toLocalDateTime(),
                    rs.getString("datos_anteriores"),
                    rs.getString("datos_nuevos")
                ));
            }
        }
        return auditorias;
    }
    
    /**
     * Obtener auditoría por usuario
     */
    public List<Auditoria> obtenerPorUsuario(String usuario) throws SQLException {
        List<Auditoria> auditorias = new ArrayList<>();
        String query = "SELECT id_auditoria, tabla_afectada, operacion, usuario, " +
                      "fecha, datos_anteriores, datos_nuevos FROM auditoria " +
                      "WHERE usuario = ? ORDER BY fecha DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, usuario);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                auditorias.add(new Auditoria(
                    rs.getInt("id_auditoria"),
                    rs.getString("tabla_afectada"),
                    rs.getString("operacion"),
                    rs.getString("usuario"),
                    rs.getTimestamp("fecha").toLocalDateTime(),
                    rs.getString("datos_anteriores"),
                    rs.getString("datos_nuevos")
                ));
            }
        }
        return auditorias;
    }
}