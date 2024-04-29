package Cliente.controller;

import Cliente.view.InterfazUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        if (e.getActionCommand().equals("Leer")){
            leerTexto();
        }
        if (e.getActionCommand().equals("Salir")){
            finalizar();
        }
    }

    private void finalizar() {
        // Se ejecuta un metodo que mande un texto especifico despidiendose del usuario
    }

    private void leerTexto() {
        // Se trae el texto de la caja
        // Se vacía la caja
        // Se ejecuta algún metodo del Control Cliente que envie este dato al servidor

    }
}
