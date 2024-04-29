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

    public void insertarUsuario(PersonaVO persona) throws SQLException {
        String consulta = "insert into `usuarios` values(`usuario`,`contraseña`)VALUES (`" + persona.getNombre() + "`,`" + persona.getContrasena() + "`)";

        con = (Connection) Conexion.getConexion();
        ps = con.prepareStatement(consulta);
        ps.executeUpdate();
        ps.close();
        Conexion.desconectar();

    }

    public boolean verificarUsuario(String usuario, String contrasena) throws SQLException {
        String consulta = "select * from `usuarios` where `usuario`='" + usuario + "' AND `contraseña`=`" + contrasena + "`";
        con = (Connection) Conexion.getConexion();
        ps = con.prepareStatement(consulta);
        rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        } else
            return false;
    }
}
