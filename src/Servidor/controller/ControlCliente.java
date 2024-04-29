package Servidor.controller;


import Servidor.model.PersonaVO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ControlCliente extends Thread{
    private ControlServidor controlServidor;
    private PersonaVO usuario;
    private Socket socketComunicacion;
    private Socket socketMensaje;
    private DataInputStream entrada;
    private DataOutputStream salidaComunicacion;
    private DataOutputStream salidaMensaje;

    public ControlCliente(ControlServidor controlServidor, Socket socketComunicacion, Socket socketMensaje, PersonaVO usuario){
        this.usuario = usuario;
        this.controlServidor = controlServidor;
        this.socketComunicacion = socketComunicacion;
        this.socketMensaje = socketMensaje;
        this.controlServidor.getVistaServidor().mostrarMensaje("Cliente agregado"+ this);

    }

    /**
     * Ejecuta el hilo del servidor, esperando mensajes de los clientes.
     */
//    public void run() {
//        controlServidor.getVistaServidor().mostrarMensaje("Esperando mensajes...");
//        try {
//            entrada = new DataInputStream(socketComunicacion.getInputStream());
//            salidaComunicacion = new DataOutputStream((socketComunicacion.getOutputStream()));
//            salidaMensaje = new DataOutputStream(socketMensaje.getOutputStream());
//            controlServidor.getVistaServidor().mostrarMensaje("El usuario " + usuario.getNombre() + " se ha conectado");
//            while (true) {
//                try {
//                    acciones();
//                } catch (IOException ex) {
//                    controlServidor.getVistaServidor().mostrarMensaje("El usuario se ha desconectado");
//                    break;
//                }
//            }
//            controlServidor.getVistaServidor().mostrarMensaje("Se removió un usuario");
//            try {
//                socketComunicacion.close();
//            } catch (Exception e) {
//                controlServidor.getVistaServidor().mostrarMensaje("Ha ocurrido un error al cerrar el socket");
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }





    /**
     * Realiza diferentes acciones según la opción recibida del cliente.
     *
     * @throws IOException Sí ocurre un error de entrada o salida durante la ejecución.
     */
//    public void acciones() throws IOException {
//        int opcion = 0, cantidadUsuarios = 0;
//        String nombreAmigo = "", mensaje = "";
//        opcion = entrada.readInt();
//        switch (opcion) {
//            case 1:
//                mensaje = entrada.readUTF();
//                if (!verificaMensaje(mensaje)) {
//                    enviaMensaje(mensaje);
//                }
//                break;
//            case 2:
//                cantidadUsuarios = usuariosActivos.size();
//                salidaComunicacion.writeInt(2);
//                for (int i = 0; i < cantidadUsuarios; i++)
//                    salidaComunicacion.writeUTF(usuariosActivos.get(i).getUsuarioActual().getNombre());
//                break;
//            case 3:
//                String nombre = entrada.readUTF();
//                nombreAmigo = entrada.readUTF();
//                mensaje = entrada.readUTF();
//                if (!verificaMensaje(mensaje)) {
//                    enviaMensaje(nombre, nombreAmigo, mensaje);
//                }
//                break;
//            case 4:
//                nombreAmigo = entrada.readUTF();
//                ControlUsuario usuario = null;
//                for (int i = 0; i < usuariosActivos.size(); i++) {
//                    try {
//                        usuario = usuariosActivos.get(i);
//                        if (usuario == this) {
//                            usuario.getSalidaMensaje().writeInt(2);
//                            usuario.getSalidaMensaje().writeUTF(nombreAmigo);
//                        }
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//        }
//    }


    public Socket getSocketComunicacion() {
        return socketComunicacion;
    }

    public Socket getSocketMensaje() {
        return socketMensaje;
    }

    public ControlServidor getControlServidor() {
        return controlServidor;
    }

    public DataInputStream getEntrada() {
        return entrada;
    }

    public DataOutputStream getSalidaComunicacion() {
        return salidaComunicacion;
    }

    public DataOutputStream getSalidaMensaje() {
        return salidaMensaje;
    }
}
