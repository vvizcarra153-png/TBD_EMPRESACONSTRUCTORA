package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión a SQL Server
 */
public class DatabaseConnection {

    // ==========================================
    // CONFIGURACIÓN SQL SERVER
    // ==========================================
    private static final String SERVER = "localhost";
    private static final String PORT = "1433";
    private static final String DATABASE = "constructora";

    // Usuario SQL Server
    private static final String USERNAME = "sa";

    // CAMBIA ESTA CONTRASEÑA POR LA TUYA
    private static final String PASSWORD = "123456";

    // Driver JDBC
    private static final String DRIVER =
            "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    // Objeto de conexión
    private static Connection connection = null;

    /**
     * Obtiene conexión a SQL Server
     */
    public static Connection getConnection() throws SQLException {

        try {

            // Verificar si la conexión está cerrada
            if (connection == null || connection.isClosed()) {

                // Cargar driver
                Class.forName(DRIVER);

                // URL conexión
                String url =
                        "jdbc:sqlserver://" + SERVER + ":" + PORT +
                        ";databaseName=" + DATABASE +
                        ";encrypt=true;" +
                        "trustServerCertificate=true;";

                // Crear conexión
                connection = DriverManager.getConnection(
                        url,
                        USERNAME,
                        PASSWORD
                );

                System.out.println(
                        "✓ Conexión a SQL Server establecida correctamente"
                );
            }

        } catch (ClassNotFoundException e) {

            System.err.println(
                    "ERROR: Driver JDBC no encontrado"
            );

            System.err.println(
                    "Agrega el archivo mssql-jdbc.jar al proyecto"
            );

            throw new SQLException(
                    "Driver SQL Server no encontrado",
                    e
            );

        } catch (SQLException e) {

            System.err.println(
                    "ERROR al conectar a SQL Server"
            );

            System.err.println(
                    e.getMessage()
            );

            throw e;
        }

        return connection;
    }

    /**
     * Cierra la conexión
     */
    public static void closeConnection() {

        try {

            if (connection != null
                    && !connection.isClosed()) {

                connection.close();

                System.out.println(
                        "✓ Conexión cerrada correctamente"
                );
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error al cerrar conexión: "
                            + e.getMessage()
            );
        }
    }

    /**
     * Verifica si hay conexión activa
     */
    public static boolean isConnected() {

        try {

            return connection != null
                    && !connection.isClosed();

        } catch (SQLException e) {

            return false;
        }
    }

    /**
     * MÉTODO MAIN PARA PROBAR CONEXIÓN
     */
    public static void main(String[] args) {

        try {

            Connection conn =
                    DatabaseConnection.getConnection();

            if (conn != null) {

                System.out.println(
                        "===================================="
                );

                System.out.println(
                        " CONEXIÓN EXITOSA A SQL SERVER "
                );

                System.out.println(
                        "===================================="
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "ERROR DE CONEXIÓN"
            );

            e.printStackTrace();
        }
    }
}