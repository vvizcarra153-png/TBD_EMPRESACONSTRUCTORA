package modulo1_seguridad.dao;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    /**
     * Obtiene lista de clientes
     */
    public static Object[][] obtenerClientesTabla() throws SQLException {
        String sql = "SELECT id, nombre, ci_nit, telefono, email, ciudad, estado FROM Clientes ORDER BY nombre";
        List<Object[]> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("ci_nit"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("ciudad"),
                    rs.getString("estado")
                });
            }
        }

        return lista.toArray(new Object[0][0]);
    }
}
