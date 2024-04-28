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
    con=null;
    ps=null;
    rs=null;
}
public void InsertarUsuario(PersonaVO persona) throws SQLException {
    String consulta="insert into `usuarios` values(`usuario`,`contraseña`)VALUES (`"+persona.getNombre()+"`,`"+persona.getContraseña()+"`)";

    con=(Connection) Conexion.getConexion();
    ps=con.prepareStatement(consulta);
    ps.executeUpdate();
    ps.close();
    Conexion.desconectar();

    }
    public boolean ConsultarUsuario(String usuario, String contraseña) throws SQLException {
        String consulta="select * from `usuarios` where `usuario`='"+usuario+"' AND `contraseña`=`"+contraseña+"`";
        con=(Connection) Conexion.getConexion();
        ps=con.prepareStatement(consulta);
        rs=ps.executeQuery();
        if(rs.next()){
            return true;
        }else
            return false;
    }
}
