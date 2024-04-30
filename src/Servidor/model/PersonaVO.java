package Servidor.model;

public class PersonaVO {
    private String nombre;
    private String contrasena;
    public PersonaVO(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena=contrasena;
    }

    public PersonaVO() {

    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }
}
