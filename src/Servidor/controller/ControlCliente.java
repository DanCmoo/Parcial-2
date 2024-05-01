package Servidor.controller;


import Servidor.model.PersonaVO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ControlCliente extends Thread {
    private ControlServidor controlServidor;
    private Lectura lectura;
    private PersonaVO usuario;
    private Socket socketComunicacion;
    private Socket socketMensaje;
    private DataInputStream entrada;
    private DataOutputStream salidaComunicacion;
    private DataOutputStream salidaMensaje;

    public ControlCliente(ControlServidor controlServidor, Socket socketComunicacion, Socket socketMensaje, PersonaVO usuario) {
        this.usuario = usuario;
        this.controlServidor = controlServidor;
        this.socketComunicacion = socketComunicacion;
        this.socketMensaje = socketMensaje;
        this.controlServidor.getVistaServidor().mostrarMensaje("Cliente agregado" + this);
        this.lectura = new Lectura();
    }

    /**
     * Ejecuta el hilo del servidor, esperando mensajes de los clientes.
     */
    public void run() {
        controlServidor.getVistaServidor().mostrarMensaje("Esperando mensajes...");
        try {
            entrada = new DataInputStream(socketComunicacion.getInputStream());
            salidaComunicacion = new DataOutputStream((socketComunicacion.getOutputStream()));
            salidaMensaje = new DataOutputStream(socketMensaje.getOutputStream());
            controlServidor.getVistaServidor().mostrarMensaje("El usuario " + usuario.getNombre() + " se ha conectado");
            while (true) {
                try {
                    acciones();
                } catch (IOException ex) {
                    controlServidor.getVistaServidor().mostrarMensaje("El usuario se ha desconectado");
                    break;
                }
            }
            controlServidor.getVistaServidor().mostrarMensaje("Se removió un usuario");
            try {
                socketComunicacion.close();
            } catch (Exception e) {
                controlServidor.getVistaServidor().mostrarMensaje("Ha ocurrido un error al cerrar el socket");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Realiza las acciones requeridas según la opción recibida desde el cliente.
     *
     * @throws IOException Si ocurre un error de E/S durante la ejecución.
     */
    public void acciones() throws IOException {
        int opcion = 0;
        String mensaje, idioma = "";

        // Lee la opción enviada por el cliente.
        opcion = entrada.readInt();

        // Ejecuta las acciones correspondientes según la opción recibida.
        switch (opcion) {
            case 1:
                // En caso de la opción 1, lee el idioma y el mensaje del cliente.
                idioma = entrada.readUTF();
                mensaje = entrada.readUTF();

                // Muestra el mensaje recibido en la vista del servidor.
                controlServidor.getVistaServidor().mostrarMensaje(mensaje);

                try {
                    // Intenta leer el mensaje en el idioma especificado.
                    lectura.leer(idioma, mensaje);
                } catch (NullPointerException e) {
                    // Maneja la excepción si el idioma especificado no está instalado en el dispositivo.
                    controlServidor.getVistaServidor().mostrarJOptionPane("No se encuentra el idioma " + idioma + " instalado en el dispositivo");
                } catch (Exception ex) {
                    // Maneja cualquier otra excepción que pueda ocurrir durante la lectura.
                    ex.printStackTrace();
                }
                break;
            case 2:
                // En caso de la opción 2, lee solo el idioma del cliente.
                idioma = entrada.readUTF();

                try {
                    // Intenta enviar un mensaje de despedida en el idioma especificado.
                    lectura.despedirse(idioma, usuario.getNombre());
                } catch (NullPointerException e) {
                    // Maneja la excepción si el idioma especificado no está instalado en el dispositivo.
                    controlServidor.getVistaServidor().mostrarJOptionPane("No se encuentra el idioma " + idioma + " instalado en el dispositivo");
                } catch (Exception ex) {
                    // Maneja cualquier otra excepción que pueda ocurrir durante el proceso de despedida.
                    ex.printStackTrace();
                }
                break;
        }
    }

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
