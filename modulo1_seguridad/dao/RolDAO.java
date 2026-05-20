/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo1_seguridad.dao;

import config.DatabaseConnection;
import modulo1_seguridad.models.Rol;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para Rol
 * Integrante: 1 - Seguridad y Usuarios
 */
public class RolDAO {
    
    /**
     * Obtener todos los roles
     */
    public List<Rol> obtenerTodos() throws SQLException {
        List<Rol> roles = new ArrayList<>();
        String query = "SELECT id_rol, nombre_rol FROM roles ORDER BY nombre_rol";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                roles.add(new Rol(rs.getInt("id_rol"), rs.getString("nombre_rol")));
            }
        }
        return roles;
    }
    
    /**
     * Obtener rol por ID
     */
    public Rol obtenerPorId(int idRol) throws SQLException {
        String query = "SELECT id_rol, nombre_rol FROM roles WHERE id_rol = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idRol);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Rol(rs.getInt("id_rol"), rs.getString("nombre_rol"));
            }
        }
        return null;
    }
    
    /**
     * Crear nuevo rol
     */
    public int crear(Rol rol) throws SQLException {
        String query = "INSERT INTO roles (nombre_rol) VALUES (?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, rol.getNombreRol());
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }
    
    /**
     * Actualizar rol
     */
    public boolean actualizar(Rol rol) throws SQLException {
        String query = "UPDATE roles SET nombre_rol = ? WHERE id_rol = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, rol.getNombreRol());
            pstmt.setInt(2, rol.getIdRol());
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    /**
     * Eliminar rol
     */
    public boolean eliminar(int idRol) throws SQLException {
        String query = "DELETE FROM roles WHERE id_rol = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idRol);
            return pstmt.executeUpdate() > 0;
        }
    }
}