package Cliente.controller;

import Cliente.model.conexion.Cliente;
import Cliente.model.conexion.ConexionProperties;
import Cliente.view.LoginCliente;
import Cliente.view.Vista;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

public class ControlCliente{
    private DataInputStream entradaComunicacion;
    private DataOutputStream salida;
    private DataInputStream entradaMensaje;
    private Vista vista;
    private Cliente cliente;
    private int puertoComunicacion;
    private int puertoMensaje;
    private String ipServidor;
    private ConexionProperties conexionProperties;
    private LoginCliente loginCliente;


    public ControlCliente() {
        loginCliente=new LoginCliente();
        vista = new Vista();


    }

    /**
     * Carga los datos de configuración del servidor desde un archivo de propiedades.
     * Si el archivo se encuentra y se lee correctamente, establece la dirección IP del servidor,
     * el puerto de comunicación y el puerto de mensajes para el cliente.
     * Muestra un mensaje de éxito si la carga se realiza correctamente.
     */
    public void cargarDatos() {
        try {
            conexionProperties = new ConexionProperties(vista.pedirArchivo("Archivo de propiedades del servidor", "properties"));
            conexionProperties.cargarDatosIniciales();
            ipServidor= loginCliente.getCajaIP().toString();
            puertoComunicacion = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("cliente.puertoComunicacion"));
            puertoMensaje = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("cliente.puertoMensaje"));
            try{
                conexion();
            }catch(Exception e){
                vista.mostrarJOptionPane("error con los datos ingresados");
            }
            vista.mostrarJOptionPane("Los datos han sido cargados con exito");
        } catch (FileNotFoundException e) {
            vista.mostrarJOptionPane("El archivo no se ha encontrado");
        } catch (IOException e) {
            vista.mostrarJOptionPane("El archivo no se ha leído correctamente");
        }
    }



    public void conexion() {
        try {
            cliente = new Cliente(new Socket(ipServidor, puertoComunicacion), new Socket(ipServidor, puertoMensaje));
            entradaComunicacion = new DataInputStream(cliente.getSocketComunication().getInputStream());
            salida = new DataOutputStream(cliente.getSocketComunication().getOutputStream());
            entradaMensaje = new DataInputStream(cliente.getSocketMensaje().getInputStream());
            String usuario =loginCliente.getCajaNombreDeUsuario().toString();
            String contrasena = loginCliente.getCajaContrasenia().toString();
            salida.writeUTF(usuario);
            salida.writeUTF(contrasena);
            if(entradaComunicacion.readBoolean()){
                new ControlUsuario(this,usuario,contrasena);
            }else{
                vista.mostrarJOptionPane("El cliente no está registrado");
            }


        } catch (IOException e) {
            vista.mostrarJOptionPane("\tEl servidor no está levantado");
        }
    }


    public Vista getVista() {
        return vista;
    }
}
