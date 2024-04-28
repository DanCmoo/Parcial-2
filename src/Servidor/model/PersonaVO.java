package Servidor.model;

public class PersonaVO {
    private String nombre;
    private String contraseña;
    public PersonaVO(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña=contraseña;
    }
    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }
}
