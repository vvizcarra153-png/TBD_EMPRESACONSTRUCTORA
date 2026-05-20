/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo2_rrhh.dao;

import config.DatabaseConnection;
import modulo2_rrhh.models.CategoriaEmpleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaEmpleadoDAO {
    public List<CategoriaEmpleado> obtenerTodos() throws SQLException {
        List<CategoriaEmpleado> lista = new ArrayList<>();
        String query = "SELECT * FROM categoria_empleado";
        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new CategoriaEmpleado(
                    rs.getInt("id_categoria_empleado"),
                    rs.getString("nombre_categoria_empleado"),
                    rs.getString("descripcion_categoria_empleado")
                ));
            }
        }
        return lista;
    }
    // Métodos crear, actualizar, eliminar según patrón mostrado en EmpleadoDAO
}