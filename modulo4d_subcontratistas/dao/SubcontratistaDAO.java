package modulo4d_subcontratistas.dao;

import modulo4d_subcontratistas.models.Subcontratista;
import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para Subcontratista - Acceso a datos
 */
public class SubcontratistaDAO {
    
    public void crear(Subcontratista subcontratista) {
        String sql = "INSERT INTO subcontratistas (nombre_subcontratista, representante, ci_subcontratista, telefono_subcontratista, email_subcontratista, direccion_subcontratista, especialidad) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, subcontratista.getNombreSubcontratista());
            stmt.setString(2, subcontratista.getRepresentante());
            stmt.setString(3, subcontratista.getCiSubcontratista());
            stmt.setString(4, subcontratista.getTelefonoSubcontratista());
            stmt.setString(5, subcontratista.getEmailSubcontratista());
            stmt.setString(6, subcontratista.getDireccionSubcontratista());
            stmt.setString(7, subcontratista.getEspecialidad());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Subcontratista> obtenerTodos() {
        List<Subcontratista> subcontratistas = new ArrayList<>();
        String sql = "SELECT * FROM subcontratistas";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Subcontratista s = new Subcontratista();
                s.setIdSubcontratista(rs.getInt("id_subcontratista"));
                s.setNombreSubcontratista(rs.getString("nombre_subcontratista"));
                s.setRepresentante(rs.getString("representante"));
                s.setCiSubcontratista(rs.getString("ci_subcontratista"));
                s.setTelefonoSubcontratista(rs.getString("telefono_subcontratista"));
                s.setEmailSubcontratista(rs.getString("email_subcontratista"));
                s.setDireccionSubcontratista(rs.getString("direccion_subcontratista"));
                s.setEspecialidad(rs.getString("especialidad"));
                subcontratistas.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subcontratistas;
    }
    
    public void actualizar(Subcontratista subcontratista) {
        String sql = "UPDATE subcontratistas SET nombre_subcontratista = ?, representante = ?, ci_subcontratista = ?, telefono_subcontratista = ?, email_subcontratista = ?, direccion_subcontratista = ?, especialidad = ? WHERE id_subcontratista = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, subcontratista.getNombreSubcontratista());
            stmt.setString(2, subcontratista.getRepresentante());
            stmt.setString(3, subcontratista.getCiSubcontratista());
            stmt.setString(4, subcontratista.getTelefonoSubcontratista());
            stmt.setString(5, subcontratista.getEmailSubcontratista());
            stmt.setString(6, subcontratista.getDireccionSubcontratista());
            stmt.setString(7, subcontratista.getEspecialidad());
            stmt.setInt(8, subcontratista.getIdSubcontratista());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar(int id) {
        String sql = "DELETE FROM subcontratistas WHERE id_subcontratista = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
