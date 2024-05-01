package Cliente.controller;

import Cliente.view.InterfazUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

public class ControlUsuario extends Thread implements ActionListener {

    private DataOutputStream salida;
    private InterfazUsuario interfazUsuario;
    private ControlCliente controlCliente;
    private String nombre;

    public ControlUsuario(DataOutputStream salida, String nombre, ControlCliente controlCliente) {
        this.controlCliente = controlCliente;
        this.salida = salida;
        this.nombre = nombre;
        interfazUsuario = new InterfazUsuario();
        interfazUsuario.getBotonLeer().addActionListener(this);
        interfazUsuario.getBotonSalir().addActionListener(this);
        interfazUsuario.getIdiomas().addActionListener(this);
    }

    public void run(){
        try {
            salida.writeInt(1);
            salida.writeUTF("Español");
            salida.writeUTF("Un saludo " + nombre);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("LEER")) {
            try {
                leerTexto();
            } catch (IOException ex) {
                interfazUsuario.mostrarJOptionPane("No fue posible realizar la lectura");
            }
        }
        if (e.getActionCommand().equals("SALIR")) {
            try {
                finalizar();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Finaliza la aplicación enviando un mensaje al servidor para indicar el cierre y luego termina el programa.
     *
     * @throws IOException Sí ocurre un error de E/S durante la comunicación con el servidor.
     */
    private void finalizar() throws IOException {
        // Obtiene el idioma seleccionado por el usuario.
        String idioma = (String) interfazUsuario.getIdiomas().getSelectedItem();
        // Envía un mensaje al servidor para indicar el cierre.
        salida.writeInt(2);
        salida.writeUTF(idioma);
        // Termina la ejecución del programa.
        System.exit(0);
    }

    /**
     * Lee el texto ingresado por el usuario en la interfaz de usuario, lo envía al servidor junto con el idioma seleccionado.
     *
     * @throws IOException Sí ocurre un error de E/S durante la comunicación con el servidor.
     */
    private void leerTexto() throws IOException {
        // Obtiene el texto ingresado por el usuario.
        String texto = interfazUsuario.getAreaDeTexto().getText();
        // Limpia el área de texto después de leer el texto.
        interfazUsuario.getAreaDeTexto().setText("");
        // Obtiene el idioma seleccionado por el usuario.
        String idioma = (String) interfazUsuario.getIdiomas().getSelectedItem();
        // Envía un mensaje al servidor con el texto y el idioma seleccionado.
        salida.writeInt(1);
        salida.writeUTF(idioma);
        salida.writeUTF(texto);
    }

}
