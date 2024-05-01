package Servidor.controller;

import Servidor.controller.DAO.PersonaDAO;
import Servidor.model.PersonaVO;
import Servidor.model.conexion.ConexionProperties;
import Servidor.model.conexion.Servidor;
import Servidor.view.VistaServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class ControlServidor {
    private Servidor servidor;
    private PersonaDAO personaDAO;
    private int puertoComunicacion;
    private int puertoMensaje;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private VistaServidor vistaServidor;
    private ConexionProperties conexionProperties;

    public ControlServidor() {
        vistaServidor = new VistaServidor("Consola del servidor");
        personaDAO = new PersonaDAO();
        cargarDatos();
        iniciarServidor();


    }

    /**
     * Carga los datos del archivo de propiedades del servidor, incluidos los puertos de comunicación y mensaje, así como las palabras prohibidas.
     */
    public void cargarDatos() {
        try {
            conexionProperties = new ConexionProperties(vistaServidor.pedirArchivo("Archivo de propiedades del servidor", "properties"));
            conexionProperties.cargarDatosIniciales();
            puertoComunicacion = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("servidor.puertoComunicacion"));
            puertoMensaje = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("servidor.puertoMensaje"));
            vistaServidor.mostrarJOptionPane("Las propiedades han sido cargadas con éxito");
        } catch (FileNotFoundException e) {
            vistaServidor.mostrarJOptionPane("El archivo no se ha encontrado");
        } catch (IOException e) {
            vistaServidor.mostrarJOptionPane("El archivo no se ha leído correctamente");
        }
    }


    /**
     * Inicia el servidor para aceptar conexiones entrantes de clientes.
     */
    public void iniciarServidor() {
        try {
            // Crea una instancia del servidor con los puertos de comunicación y mensaje especificados.
            servidor = new Servidor(new ServerSocket(puertoComunicacion), new ServerSocket(puertoMensaje));
            servidor.setEscuchando(true);
            // Muestra un mensaje indicando que el servidor se ha activado.
            vistaServidor.mostrarMensaje("SERVIDOR ACTIVADO");

            // Ciclo para escuchar conexiones entrantes mientras el servidor esté activo.
            while (servidor.isEscuchando()) {
                try {
                    // Muestra un mensaje indicando que el servidor está esperando usuarios.
                    vistaServidor.mostrarMensaje("ESPERANDO USUARIOS");

                    // Acepta las conexiones de comunicación y mensaje de un cliente.
                    Socket comunicacion = servidor.getServidorComunicacion().accept();
                    Socket mensaje = servidor.getServidorMensaje().accept();
                    servidor.setSocketComunicacion(comunicacion);
                    servidor.setSocketMensaje(mensaje);

                    // Configura los flujos de entrada y salida de datos para la comunicación con el cliente.
                    entrada = new DataInputStream(servidor.getSocketComunicacion().getInputStream());
                    salida = new DataOutputStream(servidor.getSocketComunicacion().getOutputStream());

                    // Lee el nombre de usuario y la contraseña del cliente.
                    String usuario = entrada.readUTF();
                    String contrasena = entrada.readUTF();

                    // Verifica si el usuario y la contraseña son válidos.
                    if (verificarNombre(usuario, contrasena)) {
                        // Envía una señal de autenticación al cliente.
                        salida.writeBoolean(true);
                        vistaServidor.mostrarJOptionPane("Servidor >> Conexion recibida de : localhost");

                        // Inicia un hilo de control para manejar la interacción con el cliente.
                        ControlCliente controlCliente = new ControlCliente(this, servidor.getSocketComunicacion(), servidor.getSocketMensaje(), new PersonaVO(usuario, contrasena));
                        controlCliente.start();
                    } else {
                        // Envía una señal de no autenticación al cliente.
                        salida.writeBoolean(false);
                        vistaServidor.mostrarJOptionPane("Servidor >> Cliente NO existe");
                    }

                } catch (IOException ex2) {
                    // Maneja errores de E/S al aceptar conexiones de clientes.
                    ex2.printStackTrace();
                }
            }

        } catch (IOException ex1) {
            // Maneja errores de E/S al iniciar el servidor.
            ex1.printStackTrace();
        }
    }


    /**
     * Verifica si un nombre de usuario ya está en uso por otro usuario activo.
     *
     * @param usuario    El nombre de usuario a verificar.
     * @param contrasena Contraseña del usuario a verificar.
     * @return true sí se hayó en la base de datos, false de lo contrario.
     */
    public boolean verificarNombre(String usuario, String contrasena) {
        boolean valor = false;
        try {
            PersonaVO persona = personaDAO.verificarUsuario(usuario, contrasena);
            if (persona != null) {
                valor = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return valor;
    }


    public VistaServidor getVistaServidor() {
        return vistaServidor;
    }


}
