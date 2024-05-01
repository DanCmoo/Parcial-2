package Servidor.model.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection cn = null;
    private static String URLBD = "jdbc:mysql://localhost:3306/usuarios";
    private static String usuario = "root";
    private static String contrasena = "";

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return La conexión a la base de datos.
     * @throws SQLException Sí ocurre un error al intentar establecer la conexión.
     */
    public static Connection getConexion() throws SQLException {
        // Intenta establecer una conexión utilizando el controlador JDBC.
        cn = DriverManager.getConnection(URLBD, usuario, contrasena);
        // Devuelve la conexión establecida.
        return cn;
    }



    /**
     * Desconecta la conexión a la base de datos.
     */
    public static void desconectar() {
        // Establece la conexión a null para desconectarla.
        cn = null;
    }

}
