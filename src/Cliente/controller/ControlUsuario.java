package Cliente.controller;

import Cliente.view.InterfazUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlUsuario extends Thread implements ActionListener {

    private InterfazUsuario interfazUsuario;
    private ControlCliente controlCliente;
    private String usuario;
    private String contrasena;

    public ControlUsuario(ControlCliente controlCliente,String usuario, String contrasena) {
        this.controlCliente = controlCliente;
        this.usuario = usuario;
        this.contrasena = contrasena;
        interfazUsuario = new InterfazUsuario();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("LEER")){
            try{
            leerTexto();
            }catch(IOException ex){
                controlCliente.getVista().mostrarJOptionPane("no fue posible realizar la lectura");
            }
        }
        if (e.getActionCommand().equals("SALIR")){

            try {
                finalizar();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void finalizar() throws IOException {
        controlCliente.escribirservidor("Hasta luego"+usuario);
        System.exit(0);
    }

    private void leerTexto() throws IOException {
        // Se trae el texto de la caja
        // Se vacía la caja
        // Se ejecuta algún metodo del Control Cliente que envie este dato al servidor
        controlCliente.escribirservidor(interfazUsuario.getTextoTextoALeer().toString());
        interfazUsuario.getTextoTextoALeer().setText("");
    }
}
