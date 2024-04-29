package Cliente.controller;

import Cliente.model.conexion.Cliente;
import Cliente.model.conexion.ConexionProperties;
import Cliente.view.Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

public class ControlCliente implements ActionListener {
    private DataInputStream entradaComunicacion;
    private DataOutputStream salida;
    private DataInputStream entradaMensaje;
    private Vista vista;
    private Cliente cliente;
    private int puertoComunicacion;
    private int puertoMensaje;
    private String ipServidor;
    private ConexionProperties conexionProperties;


    public ControlCliente() {
        vista = new Vista();
        cargarDatos();
        ipServidor = vista.pedirDatos("Direccion del servidor");
        conexion();

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
            puertoComunicacion = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("cliente.puertoComunicacion"));
            puertoMensaje = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("cliente.puertoMensaje"));
            vista.mostrarJOptionPane("Las propiedades han sido cargadas con exito");
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
            String usuario = vista.pedirDatos("Usuario a conectarse");
            String contrasena = vista.pedirDatos("Contraseña");
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


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
