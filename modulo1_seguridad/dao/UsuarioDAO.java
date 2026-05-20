package modulo1_seguridad.dao;

import config.DatabaseConnection;
import modulo1_seguridad.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    /**
     * Autentica un usuario contra la BD
     */
    public static Usuario autenticar(String usuario, String password) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE usuario = ? AND password = ? AND estado = 'Activo'";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("usuario"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("rol"),
                    rs.getString("estado")
                );
            }
        }
        return null;
    }

    /**
     * Registra un nuevo usuario
     */
    public static boolean registrar(String usuario, String nombre, String email, String password, String rol) throws SQLException {
        if (usuarioExiste(usuario)) {
            return false;
        }
        if (emailExiste(email)) {
            return false;
        }
        
        String sql = "INSERT INTO Usuarios (usuario, nombre, email, password, rol, estado) VALUES (?, ?, ?, ?, ?, 'Activo')";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario);
            stmt.setString(2, nombre);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setString(5, rol);
            
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Verifica si el usuario existe
     */
    public static boolean usuarioExiste(String usuario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Usuarios WHERE usuario = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    /**
     * Verifica si el email existe
     */
    public static boolean emailExiste(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Usuarios WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    /**
     * Obtiene un usuario por ID
     */
    public static Usuario obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("usuario"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("rol"),
                    rs.getString("estado")
                );
            }
        }
        return null;
    }
}
