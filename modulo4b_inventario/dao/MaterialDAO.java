package modulo4b_inventario.dao;

import modulo4b_inventario.models.Material;
import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para Material - Acceso a datos
 */
public class MaterialDAO {
    
    public void crear(Material material) {
        String sql = "INSERT INTO materiales (nombre_material, unidad_medida, id_categoria_material, id_proveedor) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getNombreMaterial());
            stmt.setString(2, material.getUnidadMedida());
            stmt.setInt(3, material.getIdCategoriaMaterial());
            stmt.setInt(4, material.getIdProveedor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Material obtener(int id) {
        String sql = "SELECT * FROM materiales WHERE id_material = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Material m = new Material();
                m.setIdMaterial(rs.getInt("id_material"));
                m.setNombreMaterial(rs.getString("nombre_material"));
                m.setUnidadMedida(rs.getString("unidad_medida"));
                m.setIdCategoriaMaterial(rs.getInt("id_categoria_material"));
                m.setIdProveedor(rs.getInt("id_proveedor"));
                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Material> obtenerTodos() {
        List<Material> materiales = new ArrayList<>();
        String sql = "SELECT * FROM materiales";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Material m = new Material();
                m.setIdMaterial(rs.getInt("id_material"));
                m.setNombreMaterial(rs.getString("nombre_material"));
                m.setUnidadMedida(rs.getString("unidad_medida"));
                m.setIdCategoriaMaterial(rs.getInt("id_categoria_material"));
                m.setIdProveedor(rs.getInt("id_proveedor"));
                materiales.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiales;
    }
    
    public void actualizar(Material material) {
        String sql = "UPDATE materiales SET nombre_material = ?, unidad_medida = ?, id_categoria_material = ?, id_proveedor = ? WHERE id_material = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getNombreMaterial());
            stmt.setString(2, material.getUnidadMedida());
            stmt.setInt(3, material.getIdCategoriaMaterial());
            stmt.setInt(4, material.getIdProveedor());
            stmt.setInt(5, material.getIdMaterial());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar(int id) {
        String sql = "DELETE FROM materiales WHERE id_material = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
