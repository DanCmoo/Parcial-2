package Cliente.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoginCliente extends JFrame {
    private JPanel panelPrincipal, panelVisual, panelDatos;
    private JLabel textoMayor, textoIngresarIP, textoIngresarNombreDeUsuario, textoIngresarContrasenia;
    private JTextField cajaIP, cajaNombreDeUsuario,cajaPassword;
    private JButton botonIngresarDatos;

    public LoginCliente() {
        super("Login de Usuario");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.inicializar();
        this.setVisible(true);
    }

    public void inicializar() {
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
        panelDatos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        textoMayor = new JLabel("Ingrese sus datos");
        textoMayor.setBounds(50, 20, 300, 50);
        textoMayor.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(textoMayor);

        textoIngresarIP = new JLabel("Ingrese su IP:");
        textoIngresarIP.setBounds(50, 80, 300, 30);
        panelDatos.add(textoIngresarIP);

        cajaIP = new JTextField();
        cajaIP.setBounds(50, 120, 300, 30);
        panelDatos.add(cajaIP);

        textoIngresarNombreDeUsuario = new JLabel("Nombre de usuario:");
        textoIngresarNombreDeUsuario.setBounds(50, 160, 300, 30);
        panelDatos.add(textoIngresarNombreDeUsuario);

        cajaNombreDeUsuario = new JTextField();
        cajaNombreDeUsuario.setBounds(50, 200, 300, 30);
        panelDatos.add(cajaNombreDeUsuario);

        textoIngresarContrasenia = new JLabel("Contraseña:");
        textoIngresarContrasenia.setBounds(50, 240, 300, 30);
        panelDatos.add(textoIngresarContrasenia);

        cajaPassword = new JTextField();
        cajaPassword.setBounds(50, 280, 300, 30);
        panelDatos.add(cajaPassword);

        botonIngresarDatos = new JButton("INGRESAR");
        botonIngresarDatos.setBounds(100, 400, 200, 50);
        botonIngresarDatos.setActionCommand("INGRESAR_DATOS");
        panelDatos.add(botonIngresarDatos);

        panelPrincipal.add(panelDatos, BorderLayout.EAST);

        this.add(panelPrincipal);
    }

    public JTextField getCajaPassword() {
        return cajaPassword;
    }

    public void setCajaPassword(JTextField cajaPassword) {
        this.cajaPassword = cajaPassword;
    }

    public JPanel getPanelVisual() {
        return panelVisual;
    }

    public void setPanelVisual(JPanel panelVisual) {
        this.panelVisual = panelVisual;
    }

    public JPanel getPanelDatos() {
        return panelDatos;
    }

    public void setPanelDatos(JPanel panelDatos) {
        this.panelDatos = panelDatos;
    }

    public JLabel getTextoMayor() {
        return textoMayor;
    }

    public void setTextoMayor(JLabel textoMayor) {
        this.textoMayor = textoMayor;
    }

    public JLabel getTextoIngresarIP() {
        return textoIngresarIP;
    }

    public void setTextoIngresarIP(JLabel textoIngresarIP) {
        this.textoIngresarIP = textoIngresarIP;
    }

    public JLabel getTextoIngresarNombreDeUsuario() {
        return textoIngresarNombreDeUsuario;
    }

    public void setTextoIngresarNombreDeUsuario(JLabel textoIngresarNombreDeUsuario) {
        this.textoIngresarNombreDeUsuario = textoIngresarNombreDeUsuario;
    }

    public JLabel getTextoIngresarContrasenia() {
        return textoIngresarContrasenia;
    }

    public void setTextoIngresarContrasenia(JLabel textoIngresarContrasenia) {
        this.textoIngresarContrasenia = textoIngresarContrasenia;
    }

    public JTextField getCajaIP() {
        return cajaIP;
    }

    public void setCajaIP(JTextField cajaIP) {
        this.cajaIP = cajaIP;
    }

    public JTextField getCajaNombreDeUsuario() {
        return cajaNombreDeUsuario;
    }

    public void setCajaNombreDeUsuario(JTextField cajaNombreDeUsuario) {
        this.cajaNombreDeUsuario = cajaNombreDeUsuario;
    }

    public JButton getBotonIngresarDatos() {
        return botonIngresarDatos;
    }

    public void setBotonIngresarDatos(JButton botonIngresarDatos) {
        this.botonIngresarDatos = botonIngresarDatos;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Abre un cuadro de diálogo para que el usuario seleccione un archivo.
     *
     * @param textoCaracteristicas La descripción de las características del archivo a seleccionar.
     * @param textoFiltro          El filtro para los tipos de archivo a mostrar.
     * @return El archivo seleccionado por el usuario.
     * @throws IOException Sí ocurre un error de E/S durante la selección del archivo.
     */
    public File pedirArchivo(String textoCaracteristicas, String textoFiltro) throws IOException {
        JFileChooser seleccionArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(textoCaracteristicas, textoFiltro);
        seleccionArchivo.setFileFilter(filtro);
        seleccionArchivo.showOpenDialog(null);
        return seleccionArchivo.getSelectedFile();
    }

    /**
     * Devuelve los nombres de los autores del programa.
     *
     * @return Los nombres de los autores del programa.
     */
    public String autores() {
        return "Hecho por:" + "\nDaniel Esteban Camacho Ospina" + "\nGiovanni Alexander Vargas Castañeda" + "\nEdwin Alejandro Orjuela Olarte";
    }

    /**
     * Abre un cuadro de diálogo para solicitar al usuario un nombre de usuario.
     *
     * @param texto El texto a mostrar en el cuadro de diálogo.
     * @return El dato ingresado por el usuario.
     */
    public String pedirDatos(String texto) {
        return JOptionPane.showInputDialog(null, texto);
    }


    public void mostrarJOptionPane(String s) {
        JOptionPane.showMessageDialog(null, s);
    }
}
