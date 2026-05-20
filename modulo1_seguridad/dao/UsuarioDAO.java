/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo1_seguridad.dao;

import config.DatabaseConnection;
import modulo1_seguridad.models.Usuario;
import modulo1_seguridad.models.Rol;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para Usuario
 * Integrante: 1 - Seguridad y Usuarios
 */
public class UsuarioDAO {
    
    /**
     * Obtener usuario por username y password (para login)
     */
    public Usuario autenticar(String username, String password) throws SQLException {
        String query = "SELECT id_usuario, username, password_hash, estado, id_empleado " +
                      "FROM usuarios WHERE username = ? AND estado = 'activo'";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Verificar contraseña (se puede usar BCrypt para más seguridad)
                if (verificarPassword(password, rs.getString("password_hash"))) {
                    Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("estado"),
                        rs.getInt("id_empleado")
                    );
                    
                    // Cargar roles del usuario
                    usuario.setRoles(obtenerRolesUsuario(usuario.getIdUsuario()));
                    return usuario;
                }
            }
        }
        return null;
    }
    
    /**
     * Obtener todos los usuarios
     */
    public List<Usuario> obtenerTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT id_usuario, username, password_hash, estado, id_empleado " +
                      "FROM usuarios ORDER BY username";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("username"),
                    rs.getString("password_hash"),
                    rs.getString("estado"),
                    rs.getInt("id_empleado")
                );
                usuario.setRoles(obtenerRolesUsuario(usuario.getIdUsuario()));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
    
    /**
     * Obtener usuario por ID
     */
    public Usuario obtenerPorId(int idUsuario) throws SQLException {
        String query = "SELECT id_usuario, username, password_hash, estado, id_empleado " +
                      "FROM usuarios WHERE id_usuario = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("username"),
                    rs.getString("password_hash"),
                    rs.getString("estado"),
                    rs.getInt("id_empleado")
                );
                usuario.setRoles(obtenerRolesUsuario(idUsuario));
                return usuario;
            }
        }
        return null;
    }
    
    /**
     * Crear nuevo usuario
     */
    public int crear(Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuarios (username, password_hash, estado, id_empleado) " +
                      "VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getPasswordHash());
            pstmt.setString(3, usuario.getEstado());
            pstmt.setInt(4, usuario.getIdEmpleado());
            
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }
    
    /**
     * Actualizar usuario
     */
    public boolean actualizar(Usuario usuario) throws SQLException {
        String query = "UPDATE usuarios SET username = ?, password_hash = ?, " +
                      "estado = ?, id_empleado = ? WHERE id_usuario = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getPasswordHash());
            pstmt.setString(3, usuario.getEstado());
            pstmt.setInt(4, usuario.getIdEmpleado());
            pstmt.setInt(5, usuario.getIdUsuario());
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    /**
     * Eliminar usuario
     */
    public boolean eliminar(int idUsuario) throws SQLException {
        String query = "DELETE FROM usuarios WHERE id_usuario = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idUsuario);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    /**
     * Obtener roles del usuario
     */
    public List<Rol> obtenerRolesUsuario(int idUsuario) throws SQLException {
        List<Rol> roles = new ArrayList<>();
        String query = "SELECT r.id_rol, r.nombre_rol FROM roles r " +
                      "INNER JOIN usuario_rol ur ON r.id_rol = ur.id_rol " +
                      "WHERE ur.id_usuario = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                roles.add(new Rol(rs.getInt("id_rol"), rs.getString("nombre_rol")));
            }
        }
        return roles;
    }
    
    /**
     * Asignar rol a usuario
     */
    public boolean asignarRol(int idUsuario, int idRol) throws SQLException {
        String query = "INSERT INTO usuario_rol (id_usuario, id_rol) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idUsuario);
            pstmt.setInt(2, idRol);
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    /**
     * Remover rol de usuario
     */
    public boolean removerRol(int idUsuario, int idRol) throws SQLException {
        String query = "DELETE FROM usuario_rol WHERE id_usuario = ? AND id_rol = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idUsuario);
            pstmt.setInt(2, idRol);
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    /**
     * Verificar contraseña (implementación básica)
     * TODO: Usar BCrypt en producción
     */
    private boolean verificarPassword(String password, String hash) {
        // Implementación simple - en producción usar BCrypt o similar
        // Ejemplo: return BCrypt.checkpw(password, hash);
        return hash.equals(password); // ¡NUNCA usar en producción!
    }
}