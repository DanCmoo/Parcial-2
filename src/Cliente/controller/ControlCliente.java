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
            loginCliente.mostrarJOptionPane("Las propiedades han sido cargadas con éxito");
        } catch (FileNotFoundException e) {
            loginCliente.mostrarJOptionPane("El archivo no se ha encontrado");
        } catch (IOException e) {
            loginCliente.mostrarJOptionPane("El archivo no se ha leído correctamente");
        }
    }


    /**
     * Establece una conexión con el servidor y realiza el proceso de inicio de sesión del cliente.
     *
     * @param nombre     El nombre de usuario para iniciar sesión.
     * @param contrasena La contraseña del usuario para iniciar sesión.
     */
    public void conexion(String nombre, String contrasena) {
        try {
            // Crea una instancia del cliente con los sockets de comunicación y mensaje.
            cliente = new Cliente(new Socket(ipServidor, puertoComunicacion), new Socket(ipServidor, puertoMensaje));

            // Configura los flujos de entrada y salida de datos para la comunicación con el servidor.
            entradaComunicacion = new DataInputStream(cliente.getSocketComunication().getInputStream());
            salida = new DataOutputStream(cliente.getSocketComunication().getOutputStream());
            entradaMensaje = new DataInputStream(cliente.getSocketMensaje().getInputStream());

            // Envía el nombre de usuario y la contraseña al servidor.
            salida.writeUTF(nombre);
            salida.writeUTF(contrasena);

            // Verifica si el inicio de sesión fue exitoso.
            if (entradaComunicacion.readBoolean()) {
                // Si el inicio de sesión fue exitoso, cierra la ventana de inicio de sesión y crea un nuevo controlador de usuario.
                loginCliente.dispose();
                ControlUsuario controlUsuario = new ControlUsuario(salida, nombre, this);
                controlUsuario.start();
            } else {
                // Si el inicio de sesión no fue exitoso, muestra un mensaje de error y finaliza la aplicación.
                loginCliente.mostrarJOptionPane("El cliente no está registrado");
                System.exit(0);
            }

        } catch (IOException e) {
            // Si hay un error de E/S durante la conexión, muestra un mensaje de error.
            loginCliente.mostrarJOptionPane("\tEl servidor no está levantado");
        }
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
