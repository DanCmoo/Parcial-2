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

    public PersonaVO verificarUsuario(String usuario, String contrasena) throws SQLException {
        String consulta = "SELECT * FROM `usuarios` WHERE username = '"+usuario+"' AND password = '"+contrasena+"'";
        boolean valor = false;
        PersonaVO persona = null;
        con = (Connection) Conexion.getConexion();
        ps = con.prepareStatement(consulta);
        rs = ps.executeQuery();

        while(rs.next()){
            persona = new PersonaVO();
            persona.setNombre(rs.getString("username"));
            persona.setContrasena(rs.getString("password"));
        }
        ps.close();
        Conexion.desconectar();
        return persona;
    }
}
