package modulo4b_inventario.dao;

import modulo4b_inventario.models.InventarioMaterial;
import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para Inventario de Material - Acceso a datos
 */
public class InventarioMaterialDAO {
    
    public void crear(InventarioMaterial inventario) {
        String sql = "INSERT INTO inventario_material (id_material, id_almacen, stock_actual_material, stock_minimo_material) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inventario.getIdMaterial());
            stmt.setInt(2, inventario.getIdAlmacen());
            stmt.setDouble(3, inventario.getStockActualMaterial());
            stmt.setDouble(4, inventario.getStockMinimoMaterial());
            stmt.executeUpdate();
            System.out.println("✓ Inventario registrado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public InventarioMaterial obtener(int id) {
        String sql = "SELECT * FROM inventario_material WHERE id_inventario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                InventarioMaterial inv = new InventarioMaterial();
                inv.setIdInventario(rs.getInt("id_inventario"));
                inv.setIdMaterial(rs.getInt("id_material"));
                inv.setIdAlmacen(rs.getInt("id_almacen"));
                inv.setStockActualMaterial(rs.getDouble("stock_actual_material"));
                inv.setStockMinimoMaterial(rs.getDouble("stock_minimo_material"));
                return inv;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<InventarioMaterial> obtenerTodos() {
        List<InventarioMaterial> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM inventario_material";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                InventarioMaterial inv = new InventarioMaterial();
                inv.setIdInventario(rs.getInt("id_inventario"));
                inv.setIdMaterial(rs.getInt("id_material"));
                inv.setIdAlmacen(rs.getInt("id_almacen"));
                inv.setStockActualMaterial(rs.getDouble("stock_actual_material"));
                inv.setStockMinimoMaterial(rs.getDouble("stock_minimo_material"));
                inventarios.add(inv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarios;
    }
    
    public List<InventarioMaterial> obtenerPorAlmacen(int idAlmacen) {
        List<InventarioMaterial> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM inventario_material WHERE id_almacen = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAlmacen);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                InventarioMaterial inv = new InventarioMaterial();
                inv.setIdInventario(rs.getInt("id_inventario"));
                inv.setIdMaterial(rs.getInt("id_material"));
                inv.setIdAlmacen(rs.getInt("id_almacen"));
                inv.setStockActualMaterial(rs.getDouble("stock_actual_material"));
                inv.setStockMinimoMaterial(rs.getDouble("stock_minimo_material"));
                inventarios.add(inv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarios;
    }
    
    public List<InventarioMaterial> obtenerAlertasStock() {
        List<InventarioMaterial> alertas = new ArrayList<>();
        String sql = "SELECT * FROM inventario_material WHERE stock_actual_material <= stock_minimo_material";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                InventarioMaterial inv = new InventarioMaterial();
                inv.setIdInventario(rs.getInt("id_inventario"));
                inv.setIdMaterial(rs.getInt("id_material"));
                inv.setIdAlmacen(rs.getInt("id_almacen"));
                inv.setStockActualMaterial(rs.getDouble("stock_actual_material"));
                inv.setStockMinimoMaterial(rs.getDouble("stock_minimo_material"));
                alertas.add(inv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alertas;
    }
    
    public void actualizar(InventarioMaterial inventario) {
        String sql = "UPDATE inventario_material SET id_material = ?, id_almacen = ?, stock_actual_material = ?, stock_minimo_material = ? WHERE id_inventario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inventario.getIdMaterial());
            stmt.setInt(2, inventario.getIdAlmacen());
            stmt.setDouble(3, inventario.getStockActualMaterial());
            stmt.setDouble(4, inventario.getStockMinimoMaterial());
            stmt.setInt(5, inventario.getIdInventario());
            stmt.executeUpdate();
            System.out.println("✓ Inventario actualizado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarStock(int idInventario, double nuevoStock) {
        String sql = "UPDATE inventario_material SET stock_actual_material = ? WHERE id_inventario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, nuevoStock);
            stmt.setInt(2, idInventario);
            stmt.executeUpdate();
            System.out.println("✓ Stock actualizado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar(int id) {
        String sql = "DELETE FROM inventario_material WHERE id_inventario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✓ Inventario eliminado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
