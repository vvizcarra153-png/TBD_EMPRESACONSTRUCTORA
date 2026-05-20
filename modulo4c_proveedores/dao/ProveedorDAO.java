package modulo4c_proveedores.dao;

import modulo4c_proveedores.models.Proveedor;
import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para Proveedor - Acceso a datos
 */
public class ProveedorDAO {
    
    public void crear(Proveedor proveedor) {
        String sql = "INSERT INTO proveedores (nombre_proveedor, telefono_proveedor, direccion_proveedor, id_categoria_proveedor) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, proveedor.getNombreProveedor());
            stmt.setString(2, proveedor.getTelefonoProveedor());
            stmt.setString(3, proveedor.getDireccionProveedor());
            stmt.setInt(4, proveedor.getIdCategoriaProveedor());
            stmt.executeUpdate();
            System.out.println("✓ Proveedor registrado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Proveedor obtener(int id) {
        String sql = "SELECT * FROM proveedores WHERE id_proveedor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("id_proveedor"));
                p.setNombreProveedor(rs.getString("nombre_proveedor"));
                p.setTelefonoProveedor(rs.getString("telefono_proveedor"));
                p.setDireccionProveedor(rs.getString("direccion_proveedor"));
                p.setIdCategoriaProveedor(rs.getInt("id_categoria_proveedor"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Proveedor> obtenerTodos() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("id_proveedor"));
                p.setNombreProveedor(rs.getString("nombre_proveedor"));
                p.setTelefonoProveedor(rs.getString("telefono_proveedor"));
                p.setDireccionProveedor(rs.getString("direccion_proveedor"));
                p.setIdCategoriaProveedor(rs.getInt("id_categoria_proveedor"));
                proveedores.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }
    
    public List<Proveedor> obtenerPorCategoria(int idCategoria) {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedores WHERE id_categoria_proveedor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("id_proveedor"));
                p.setNombreProveedor(rs.getString("nombre_proveedor"));
                p.setTelefonoProveedor(rs.getString("telefono_proveedor"));
                p.setDireccionProveedor(rs.getString("direccion_proveedor"));
                p.setIdCategoriaProveedor(rs.getInt("id_categoria_proveedor"));
                proveedores.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }
    
    public void actualizar(Proveedor proveedor) {
        String sql = "UPDATE proveedores SET nombre_proveedor = ?, telefono_proveedor = ?, direccion_proveedor = ?, id_categoria_proveedor = ? WHERE id_proveedor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, proveedor.getNombreProveedor());
            stmt.setString(2, proveedor.getTelefonoProveedor());
            stmt.setString(3, proveedor.getDireccionProveedor());
            stmt.setInt(4, proveedor.getIdCategoriaProveedor());
            stmt.setInt(5, proveedor.getIdProveedor());
            stmt.executeUpdate();
            System.out.println("✓ Proveedor actualizado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar(int id) {
        String sql = "DELETE FROM proveedores WHERE id_proveedor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✓ Proveedor eliminado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
