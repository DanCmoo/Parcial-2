package Cliente.controller;

import Cliente.view.InterfazUsuario;
import Cliente.view.LoginCliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

public class ControlInterfaz implements ActionListener {
    private ControlCliente control;
    private LoginCliente login;


    public ControlInterfaz(ControlCliente control) {
        this.control = control;
        control.getLoginCliente().getBotonIngresarDatos().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("INGRESAR_DATOS")) {
            control.cargarDatos();


        }
    }
}
