package Servidor.controller.DAO;

import Servidor.model.PersonaVO;
import Servidor.model.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public PersonaDAO() {
        con = null;
        ps = null;
        rs = null;
    }

    /**
     * Verifica las credenciales de un usuario en la base de datos.
     *
     * @param usuario    El nombre de usuario.
     * @param contrasena La contraseña del usuario.
     * @return Un objeto PersonaVO si se encuentra un usuario con las credenciales especificadas, o null si no se encuentra.
     * @throws SQLException Si ocurre un error durante la ejecución de la consulta SQL.
     */
    public PersonaVO verificarUsuario(String usuario, String contrasena) throws SQLException {
        // Construye la consulta SQL para verificar las credenciales del usuario.
        String consulta = "SELECT * FROM `usuarios` WHERE username = '" + usuario + "' AND password = '" + contrasena + "'";
        // Inicializa una variable para determinar si se encontró un usuario válido.
        boolean valor = false;
        // Inicializa un objeto PersonaVO para almacenar la información del usuario.
        PersonaVO persona = null;

        // Establece la conexión a la base de datos.
        con = (Connection) Conexion.getConexion();
        ps = con.prepareStatement(consulta);
        rs = ps.executeQuery();

        // Recorre el resultado de la consulta y asigna los datos del usuario al objeto PersonaVO.
        while (rs.next()) {
            persona = new PersonaVO();
            persona.setNombre(rs.getString("username"));
            persona.setContrasena(rs.getString("password"));
        }

        // Cierra los recursos y desconecta de la base de datos.
        ps.close();
        Conexion.desconectar();

        // Devuelve el objeto PersonaVO (puede ser null si no se encontró un usuario válido).
        return persona;
    }

}
