package modulo4d_subcontratistas.dao;

import modulo4d_subcontratistas.models.ContratoSubcontratista;
import config.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para Contrato de Subcontratista - Acceso a datos
 */
public class ContratoSubcontratistDAO {
    
    public void crear(ContratoSubcontratista contrato) {
        String sql = "INSERT INTO contrato_subcontratista (id_subcontratista, id_proyecto, descripcion_trabajo, monto_contratado, fecha_inicio, fecha_fin, estado_contrato) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, contrato.getIdSubcontratista());
            stmt.setInt(2, contrato.getIdProyecto());
            stmt.setString(3, contrato.getDescripcionTrabajo());
            stmt.setDouble(4, contrato.getMontoContratado());
            stmt.setDate(5, Date.valueOf(contrato.getFechaInicio()));
            stmt.setDate(6, Date.valueOf(contrato.getFechaFin()));
            stmt.setString(7, contrato.getEstadoContrato());
            stmt.executeUpdate();
            System.out.println("✓ Contrato de subcontratista registrado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ContratoSubcontratista obtener(int id) {
        String sql = "SELECT * FROM contrato_subcontratista WHERE id_contrato_sub = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<ContratoSubcontratista> obtenerTodos() {
        List<ContratoSubcontratista> contratos = new ArrayList<>();
        String sql = "SELECT * FROM contrato_subcontratista";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                contratos.add(mapearResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contratos;
    }
    
    public List<ContratoSubcontratista> obtenerPorProyecto(int idProyecto) {
        List<ContratoSubcontratista> contratos = new ArrayList<>();
        String sql = "SELECT * FROM contrato_subcontratista WHERE id_proyecto = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProyecto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                contratos.add(mapearResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contratos;
    }
    
    public List<ContratoSubcontratista> obtenerPorSubcontratista(int idSubcontratista) {
        List<ContratoSubcontratista> contratos = new ArrayList<>();
        String sql = "SELECT * FROM contrato_subcontratista WHERE id_subcontratista = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idSubcontratista);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                contratos.add(mapearResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contratos;
    }
    
    public double obtenerMontoTotalPorProyecto(int idProyecto) {
        String sql = "SELECT SUM(monto_contratado) as total FROM contrato_subcontratista WHERE id_proyecto = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProyecto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    
    public void actualizar(ContratoSubcontratista contrato) {
        String sql = "UPDATE contrato_subcontratista SET id_subcontratista = ?, id_proyecto = ?, descripcion_trabajo = ?, monto_contratado = ?, fecha_inicio = ?, fecha_fin = ?, estado_contrato = ? WHERE id_contrato_sub = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, contrato.getIdSubcontratista());
            stmt.setInt(2, contrato.getIdProyecto());
            stmt.setString(3, contrato.getDescripcionTrabajo());
            stmt.setDouble(4, contrato.getMontoContratado());
            stmt.setDate(5, Date.valueOf(contrato.getFechaInicio()));
            stmt.setDate(6, Date.valueOf(contrato.getFechaFin()));
            stmt.setString(7, contrato.getEstadoContrato());
            stmt.setInt(8, contrato.getIdContratoSub());
            stmt.executeUpdate();
            System.out.println("✓ Contrato actualizado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar(int id) {
        String sql = "DELETE FROM contrato_subcontratista WHERE id_contrato_sub = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✓ Contrato eliminado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private ContratoSubcontratista mapearResultSet(ResultSet rs) throws SQLException {
        ContratoSubcontratista c = new ContratoSubcontratista();
        c.setIdContratoSub(rs.getInt("id_contrato_sub"));
        c.setIdSubcontratista(rs.getInt("id_subcontratista"));
        c.setIdProyecto(rs.getInt("id_proyecto"));
        c.setDescripcionTrabajo(rs.getString("descripcion_trabajo"));
        c.setMontoContratado(rs.getDouble("monto_contratado"));
        c.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
        c.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
        c.setEstadoContrato(rs.getString("estado_contrato"));
        return c;
    }
}
