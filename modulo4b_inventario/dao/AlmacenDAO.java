package modulo4b_inventario.dao;

import modulo4b_inventario.models.Almacen;
import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para Almacén - Acceso a datos
 */
public class AlmacenDAO {
    
    public void crear(Almacen almacen) {
        String sql = "INSERT INTO almacen (nombre_almacen, ubicacion_almacen) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, almacen.getNombreAlmacen());
            stmt.setString(2, almacen.getUbicacionAlmacen());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Almacen> obtenerTodos() {
        List<Almacen> almacenes = new ArrayList<>();
        String sql = "SELECT * FROM almacen";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Almacen a = new Almacen();
                a.setIdAlmacen(rs.getInt("id_almacen"));
                a.setNombreAlmacen(rs.getString("nombre_almacen"));
                a.setUbicacionAlmacen(rs.getString("ubicacion_almacen"));
                almacenes.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return almacenes;
    }
    
    public void actualizar(Almacen almacen) {
        String sql = "UPDATE almacen SET nombre_almacen = ?, ubicacion_almacen = ? WHERE id_almacen = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, almacen.getNombreAlmacen());
            stmt.setString(2, almacen.getUbicacionAlmacen());
            stmt.setInt(3, almacen.getIdAlmacen());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
