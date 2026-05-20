package modulo1_seguridad.dao;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProyectoDAO {

    /**
     * Obtiene lista de proyectos con datos reales de la BD
     */
    public static Object[][] obtenerProyectosTabla() throws SQLException {
        String sql = "SELECT TOP 20 id, nombre, estado, centro_costo, inicio, fin, costo FROM Proyectos ORDER BY id DESC";
        List<Object[]> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("estado"),
                    rs.getInt("centro_costo"),
                    rs.getString("inicio"),
                    rs.getString("fin"),
                    "Bs " + String.format("%,d", rs.getLong("costo"))
                });
            }
        }

        return lista.toArray(new Object[0][0]);
    }

    /**
     * Obtiene estadísticas del dashboard
     */
    public static Map<String, String> obtenerEstadisticas() throws SQLException {
        Map<String, String> stats = new HashMap<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Proyectos activos
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT COUNT(*) as count FROM Proyectos WHERE estado = 'ACTIVO'")) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    stats.put("proyectos_activos", String.valueOf(rs.getInt("count")));
                }
            }

            // Total empleados
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT COUNT(*) as count FROM Empleados WHERE estado = 'Activo'")) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    stats.put("empleados_activos", String.valueOf(rs.getInt("count")));
                }
            }

            // Ingresos cobrados (últimos 30 días)
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT SUM(monto) as total FROM Pagos_Clientes WHERE DATEDIFF(DAY, fecha, GETDATE()) <= 30")) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    long ingresos = rs.getLong("total");
                    stats.put("ingresos_cobrados", "Bs " + String.format("%,d", ingresos));
                }
            }

            // Materiales bajo stock
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT COUNT(*) as count FROM Inventario WHERE stock_actual < stock_minimo")) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    stats.put("materiales_bajo_stock", String.valueOf(rs.getInt("count")));
                }
            }
        }

        // Valores por defecto si no hay BD
        stats.putIfAbsent("proyectos_activos", "12");
        stats.putIfAbsent("empleados_activos", "34");
        stats.putIfAbsent("ingresos_cobrados", "Bs 847,000");
        stats.putIfAbsent("materiales_bajo_stock", "6");

        return stats;
    }
}
