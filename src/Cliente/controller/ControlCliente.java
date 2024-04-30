package Cliente.controller;

import Cliente.model.conexion.Cliente;
import Cliente.model.conexion.ConexionProperties;
import Cliente.view.LoginCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class ControlCliente implements ActionListener {
    private DataInputStream entradaComunicacion;
    private DataOutputStream salida;
    private DataInputStream entradaMensaje;
    private Cliente cliente;
    private int puertoComunicacion;
    private int puertoMensaje;
    private String ipServidor;
    private ConexionProperties conexionProperties;
    private LoginCliente loginCliente;

    public ControlCliente() {
        loginCliente = new LoginCliente();
        loginCliente.getBotonIngresarDatos().addActionListener(this);
        cargarDatos();

    }

    /**
     * Carga los datos de configuración del servidor desde un archivo de propiedades.
     * Si el archivo se encuentra y se lee correctamente, establece la dirección IP del servidor,
     * el puerto de comunicación y el puerto de mensajes para el cliente.
     * Muestra un mensaje de éxito si la carga se realiza correctamente.
     */
    public void cargarDatos() {
        try {
            conexionProperties = new ConexionProperties(loginCliente.pedirArchivo("Archivo de propiedades del servidor", "properties"));
            conexionProperties.cargarDatosIniciales();
            puertoComunicacion = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("cliente.puertoComunicacion"));
            puertoMensaje = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("cliente.puertoMensaje"));
        } catch (FileNotFoundException e) {
            loginCliente.mostrarJOptionPane("El archivo no se ha encontrado");
        } catch (IOException e) {
            loginCliente.mostrarJOptionPane("El archivo no se ha leído correctamente");
        }
    }


    public void conexion(String nombre,String contrasena) {
        try {
            cliente = new Cliente(new Socket(ipServidor, puertoComunicacion), new Socket(ipServidor, puertoMensaje));
            entradaComunicacion = new DataInputStream(cliente.getSocketComunication().getInputStream());
            salida = new DataOutputStream(cliente.getSocketComunication().getOutputStream());
            entradaMensaje = new DataInputStream(cliente.getSocketMensaje().getInputStream());
            salida.writeUTF(nombre);
            salida.writeUTF(contrasena);
            if (entradaComunicacion.readBoolean()) {
                ControlUsuario controlUsuario = new ControlUsuario(salida,nombre,this);
                controlUsuario.start();
            } else {
                loginCliente.mostrarJOptionPane("El cliente no está registrado");
                System.exit(0);

            }

        } catch (IOException e) {
            loginCliente.mostrarJOptionPane("\tEl servidor no está levantado");
        }
    }

    public void escribirServidor(String texto) throws IOException {
        salida.writeUTF(texto);

    }

    public LoginCliente getLoginCliente() {
        return loginCliente;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("INGRESAR_DATOS")){
            String ip = loginCliente.getCajaIP().getText();
            String nombre = loginCliente.getCajaNombreDeUsuario().getText();
            String contrasena = loginCliente.getCajaPassword().getText();
            if(!ip.isEmpty() && !nombre.isEmpty() && !contrasena.isEmpty()){
                ipServidor = ip;
                conexion(nombre,contrasena);
            }else{
                loginCliente.mostrarJOptionPane("Por favor ingrese todos los datos solicitados");
            }
        }
    }
}
