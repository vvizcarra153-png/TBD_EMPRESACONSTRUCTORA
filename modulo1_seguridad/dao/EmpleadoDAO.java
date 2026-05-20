package modulo1_seguridad.dao;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    /**
     * Obtiene lista de empleados activos
     */
    public static Object[][] obtenerEmpleadosTabla() throws SQLException {
        String sql = "SELECT id, nombre, cargo, departamento, telefono, salario, estado FROM Empleados WHERE estado = 'Activo' ORDER BY nombre";
        List<Object[]> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("cargo"),
                    rs.getString("departamento"),
                    rs.getString("telefono"),
                    "Bs " + String.format("%,d", rs.getLong("salario")),
                    rs.getString("estado")
                });
            }
        }

        return lista.toArray(new Object[0][0]);
    }
}
