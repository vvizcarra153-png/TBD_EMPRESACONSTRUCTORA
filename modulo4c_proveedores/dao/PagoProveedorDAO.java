package modulo4c_proveedores.dao;

import modulo4c_proveedores.models.PagoProveedor;
import config.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para Pago a Proveedor - Acceso a datos
 */
public class PagoProveedorDAO {
    
    public void crear(PagoProveedor pago) {
        String sql = "INSERT INTO pagos_proveedor (id_proveedor, id_proyecto, id_estado_pago, monto, fecha_pago) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pago.getIdProveedor());
            stmt.setInt(2, pago.getIdProyecto());
            stmt.setInt(3, pago.getIdEstadoPago());
            stmt.setDouble(4, pago.getMonto());
            stmt.setDate(5, Date.valueOf(pago.getFechaPago()));
            stmt.executeUpdate();
            System.out.println("✓ Pago a proveedor registrado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public PagoProveedor obtener(int id) {
        String sql = "SELECT * FROM pagos_proveedor WHERE id_pago_proveedor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                PagoProveedor pp = new PagoProveedor();
                pp.setIdPagoProveedor(rs.getInt("id_pago_proveedor"));
                pp.setIdProveedor(rs.getInt("id_proveedor"));
                pp.setIdProyecto(rs.getInt("id_proyecto"));
                pp.setIdEstadoPago(rs.getInt("id_estado_pago"));
                pp.setMonto(rs.getDouble("monto"));
                pp.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
                return pp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<PagoProveedor> obtenerTodos() {
        List<PagoProveedor> pagos = new ArrayList<>();
        String sql = "SELECT * FROM pagos_proveedor";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                PagoProveedor pp = new PagoProveedor();
                pp.setIdPagoProveedor(rs.getInt("id_pago_proveedor"));
                pp.setIdProveedor(rs.getInt("id_proveedor"));
                pp.setIdProyecto(rs.getInt("id_proyecto"));
                pp.setIdEstadoPago(rs.getInt("id_estado_pago"));
                pp.setMonto(rs.getDouble("monto"));
                pp.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
                pagos.add(pp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagos;
    }
    
    public List<PagoProveedor> obtenerPorProveedor(int idProveedor) {
        List<PagoProveedor> pagos = new ArrayList<>();
        String sql = "SELECT * FROM pagos_proveedor WHERE id_proveedor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProveedor);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PagoProveedor pp = new PagoProveedor();
                pp.setIdPagoProveedor(rs.getInt("id_pago_proveedor"));
                pp.setIdProveedor(rs.getInt("id_proveedor"));
                pp.setIdProyecto(rs.getInt("id_proyecto"));
                pp.setIdEstadoPago(rs.getInt("id_estado_pago"));
                pp.setMonto(rs.getDouble("monto"));
                pp.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
                pagos.add(pp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagos;
    }
    
    public double obtenerTotalPagosProveedor(int idProveedor) {
        String sql = "SELECT SUM(monto) as total FROM pagos_proveedor WHERE id_proveedor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProveedor);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    
    public void actualizar(PagoProveedor pago) {
        String sql = "UPDATE pagos_proveedor SET id_proveedor = ?, id_proyecto = ?, id_estado_pago = ?, monto = ?, fecha_pago = ? WHERE id_pago_proveedor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pago.getIdProveedor());
            stmt.setInt(2, pago.getIdProyecto());
            stmt.setInt(3, pago.getIdEstadoPago());
            stmt.setDouble(4, pago.getMonto());
            stmt.setDate(5, Date.valueOf(pago.getFechaPago()));
            stmt.setInt(6, pago.getIdPagoProveedor());
            stmt.executeUpdate();
            System.out.println("✓ Pago actualizado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar(int id) {
        String sql = "DELETE FROM pagos_proveedor WHERE id_pago_proveedor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✓ Pago eliminado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
