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

    public ControlUsuario(DataOutputStream salida,String nombre,ControlCliente controlCliente) {
        this.controlCliente = controlCliente;
        this.salida = salida;
        this.nombre = nombre;
        interfazUsuario = new InterfazUsuario();
        interfazUsuario.getBotonLeer().addActionListener(this);
        interfazUsuario.getBotonSalir().addActionListener(this);

    }

    public void run() {

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

    private void finalizar() throws IOException {
        salida.writeInt(2);
        System.exit(0);
    }

    private void leerTexto() throws IOException {
        salida.writeInt(1);
        salida.writeUTF(interfazUsuario.getAreaDeTexto().getText());
        interfazUsuario.getAreaDeTexto().setText("");
    }
}
