package Cliente.controller;

import Cliente.view.LoginCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlInterfaz implements ActionListener {
    private ControlCliente control;
    private LoginCliente login;

    public ControlInterfaz(ControlCliente control) {
        this.control = control;
        login=new LoginCliente();
        login.getBotonIngresarDatos().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("INGRESAR_DATOS")) {
            control.cargarDatos();

        }
    }
}
