package modulo4b_inventario.dao;

import modulo4b_inventario.models.MovimientoInventario;
import config.DatabaseConnection;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para Movimiento de Inventario - Acceso a datos
 */
public class MovimientoInventarioDAO {
    
    public void crear(MovimientoInventario movimiento) {
        String sql = "INSERT INTO movimiento_inventario (id_material, tipo_movimiento, cantidad, fecha, id_proyecto) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, movimiento.getIdMaterial());
            stmt.setString(2, movimiento.getTipoMovimiento());
            stmt.setDouble(3, movimiento.getCantidad());
            stmt.setTimestamp(4, Timestamp.valueOf(movimiento.getFecha()));
            stmt.setInt(5, movimiento.getIdProyecto());
            stmt.executeUpdate();
            System.out.println("✓ Movimiento de inventario registrado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public MovimientoInventario obtener(int id) {
        String sql = "SELECT * FROM movimiento_inventario WHERE id_movimiento = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                MovimientoInventario mov = new MovimientoInventario();
                mov.setIdMovimiento(rs.getInt("id_movimiento"));
                mov.setIdMaterial(rs.getInt("id_material"));
                mov.setTipoMovimiento(rs.getString("tipo_movimiento"));
                mov.setCantidad(rs.getDouble("cantidad"));
                mov.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                mov.setIdProyecto(rs.getInt("id_proyecto"));
                return mov;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<MovimientoInventario> obtenerTodos() {
        List<MovimientoInventario> movimientos = new ArrayList<>();
        String sql = "SELECT * FROM movimiento_inventario ORDER BY fecha DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                MovimientoInventario mov = new MovimientoInventario();
                mov.setIdMovimiento(rs.getInt("id_movimiento"));
                mov.setIdMaterial(rs.getInt("id_material"));
                mov.setTipoMovimiento(rs.getString("tipo_movimiento"));
                mov.setCantidad(rs.getDouble("cantidad"));
                mov.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                mov.setIdProyecto(rs.getInt("id_proyecto"));
                movimientos.add(mov);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }
    
    public List<MovimientoInventario> obtenerPorMaterial(int idMaterial) {
        List<MovimientoInventario> movimientos = new ArrayList<>();
        String sql = "SELECT * FROM movimiento_inventario WHERE id_material = ? ORDER BY fecha DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMaterial);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MovimientoInventario mov = new MovimientoInventario();
                mov.setIdMovimiento(rs.getInt("id_movimiento"));
                mov.setIdMaterial(rs.getInt("id_material"));
                mov.setTipoMovimiento(rs.getString("tipo_movimiento"));
                mov.setCantidad(rs.getDouble("cantidad"));
                mov.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                mov.setIdProyecto(rs.getInt("id_proyecto"));
                movimientos.add(mov);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }
    
    public List<MovimientoInventario> obtenerPorProyecto(int idProyecto) {
        List<MovimientoInventario> movimientos = new ArrayList<>();
        String sql = "SELECT * FROM movimiento_inventario WHERE id_proyecto = ? ORDER BY fecha DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProyecto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MovimientoInventario mov = new MovimientoInventario();
                mov.setIdMovimiento(rs.getInt("id_movimiento"));
                mov.setIdMaterial(rs.getInt("id_material"));
                mov.setTipoMovimiento(rs.getString("tipo_movimiento"));
                mov.setCantidad(rs.getDouble("cantidad"));
                mov.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                mov.setIdProyecto(rs.getInt("id_proyecto"));
                movimientos.add(mov);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }
    
    public List<MovimientoInventario> obtenerPorTipo(String tipo) {
        List<MovimientoInventario> movimientos = new ArrayList<>();
        String sql = "SELECT * FROM movimiento_inventario WHERE tipo_movimiento = ? ORDER BY fecha DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MovimientoInventario mov = new MovimientoInventario();
                mov.setIdMovimiento(rs.getInt("id_movimiento"));
                mov.setIdMaterial(rs.getInt("id_material"));
                mov.setTipoMovimiento(rs.getString("tipo_movimiento"));
                mov.setCantidad(rs.getDouble("cantidad"));
                mov.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                mov.setIdProyecto(rs.getInt("id_proyecto"));
                movimientos.add(mov);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }
    
    public void eliminar(int id) {
        String sql = "DELETE FROM movimiento_inventario WHERE id_movimiento = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✓ Movimiento eliminado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
