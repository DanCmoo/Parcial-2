package Cliente.view;

import javax.swing.*;
import java.awt.*;

public class LoginCliente extends JFrame {
    private JPanel panelPrincipal, panelVisual, panelDatos;
    private JLabel textoMayor,textoIngresarIP, textoIngresarNombreDeUsuario, textoIngresarContrasenia;
    private JTextField cajaIP, cajaNombreDeUsuario;
    private JPasswordField cajaContrasenia;
    private JButton botonIngresarDatos;

    public LoginCliente(){
        super("Login de Usuario");
        this.setSize(1000,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.inicializar();
        this.setVisible(true);
    }

    public void inicializar(){
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        panelVisual = new JPanel();
        panelVisual.setPreferredSize(new Dimension(800, Integer.MAX_VALUE));
        panelVisual.setBackground(Color.WHITE);
        panelVisual.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        panelPrincipal.add(panelVisual, BorderLayout.CENTER);

        panelDatos = new JPanel();
        panelDatos.setPreferredSize(new Dimension(400, Integer.MAX_VALUE));
        panelDatos.setLayout(null);
        panelDatos.setBackground(Color.WHITE);
        panelDatos.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));

        textoMayor = new JLabel("Ingrese sus datos");
        textoMayor.setBounds(50,20,300,50);
        textoMayor.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(textoMayor);

        textoIngresarIP = new JLabel("Ingrese su IP:");
        textoIngresarIP.setBounds(50,80,300,30);
        panelDatos.add(textoIngresarIP);

        cajaIP = new JTextField();
        cajaIP.setBounds(50,120,300,30);
        panelDatos.add(cajaIP);

        textoIngresarNombreDeUsuario = new JLabel("Nombre de usuario:");
        textoIngresarNombreDeUsuario.setBounds(50,160,300,30);
        panelDatos.add(textoIngresarNombreDeUsuario);

        cajaNombreDeUsuario = new JTextField();
        cajaNombreDeUsuario.setBounds(50,200,300,30);
        panelDatos.add(cajaNombreDeUsuario);

        textoIngresarContrasenia = new JLabel("Contraseña:");
        textoIngresarContrasenia.setBounds(50,240,300,30);
        panelDatos.add(textoIngresarContrasenia);

        cajaContrasenia = new JPasswordField();
        cajaContrasenia.setBounds(50,280,300,30);
        panelDatos.add(cajaContrasenia);

        botonIngresarDatos = new JButton("INGRESAR");
        botonIngresarDatos.setBounds(100,400,200,50);
        botonIngresarDatos.setActionCommand("INGRESAR_DATOS");
        panelDatos.add(botonIngresarDatos);

        panelPrincipal.add(panelDatos, BorderLayout.EAST);

        this.add(panelPrincipal);
    }
}
