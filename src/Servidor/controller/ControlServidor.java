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

    public ControlServidor(){
        vistaServidor= new VistaServidor("Consola del servidor");
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
     * Inicia el servidor y espera a que los usuarios se conecten.
     */
    public void iniciarServidor() {
        try {
            servidor = new Servidor(new ServerSocket(puertoComunicacion), new ServerSocket(puertoMensaje));
            servidor.setEscuchando(true);
            vistaServidor.mostrarMensaje("SERVIDOR ACTIVADO");
            while (servidor.isEscuchando()) {
                try {
                    vistaServidor.mostrarMensaje("ESPERANDO USUARIOS");
                    Socket comunicacion = servidor.getServidorComunicacion().accept();
                    Socket mensaje = servidor.getServidorMensaje().accept();
                    servidor.setSocketComunicacion(comunicacion);
                    servidor.setSocketMensaje(mensaje);
                    entrada = new DataInputStream(servidor.getSocketComunicacion().getInputStream());
                    salida = new DataOutputStream(servidor.getSocketComunicacion().getOutputStream());
                    String usuario = entrada.readUTF();
                    String contrasena = entrada.readUTF();
                    if (verificarNombre(usuario,contrasena)){
                        salida.writeBoolean(true);
                        vistaServidor.mostrarJOptionPane("Servidor >> Conexion recibida de : localhost");
                        ControlCliente controlCliente = new ControlCliente(this,servidor.getSocketComunicacion(),servidor.getSocketMensaje(),new PersonaVO(usuario,contrasena));
                        controlCliente.start();
                    }else{
                        salida.writeBoolean(false);
                        vistaServidor.mostrarJOptionPane("Servidor >> Cliente NO existe");
                    }

                } catch (IOException ex2) {
                    ex2.printStackTrace();
                }
            }

        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
    }

    /**
     * Verifica si un nombre de usuario ya está en uso por otro usuario activo.
     *
     * @param usuario El nombre de usuario a verificar.
     * @param contrasena Contraseña del usuario a verificar.
     * @return true sí se hayó en la base de datos, false de lo contrario.
     */
    public boolean verificarNombre(String usuario,String contrasena) {
        boolean valor = false;
        try{
            PersonaVO persona = personaDAO.verificarUsuario(usuario,contrasena);
            if(persona != null){
                valor = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return valor;
    }


    public VistaServidor getVistaServidor() {
        return vistaServidor;
    }


}
