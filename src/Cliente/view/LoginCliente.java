package Cliente.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoginCliente extends JFrame {
    private JPanel panelPrincipal, panelVisual, panelDatos;
    private JLabel textoBienvenido, imagenLogin, textoMayor, textoIngresarIP, textoIngresarNombreDeUsuario, textoIngresarContrasenia, tituloPanelVisual;
    private JLabel imagenEscudo, imagenIP, imagenUsername, imagenPassword;
    private JTextField cajaIP, cajaNombreDeUsuario,cajaPassword;
    private JButton botonIngresarDatos;

    Color colorFondo = new Color(226,241,255);
    Font fuenteTitulo = new Font("Verdana", Font.BOLD, 20);
    Font fuenteTexto = new Font("Verdana", Font.BOLD, 15);
    Border borde = BorderFactory.createLineBorder(Color.black, 2) ;

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
        panelVisual.setPreferredSize(new Dimension(800, this.getHeight()));
        panelVisual.setLayout(null);
        panelVisual.setBackground(colorFondo);
        panelVisual.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        tituloPanelVisual = new JLabel("LOGIN DE USUARIO");
        tituloPanelVisual.setBounds(100, 20, 300, 50);
        tituloPanelVisual.setBackground(colorFondo);
        tituloPanelVisual.setFont(fuenteTitulo);
        tituloPanelVisual.setForeground(Color.black);
        panelVisual.add(tituloPanelVisual);

        imagenLogin = new JLabel();
        imagenLogin.setBounds(100,150,400, 200);
        imagenLogin.setBorder(BorderFactory.createLineBorder(colorFondo, 5));
        this.setImagenLabel(imagenLogin, "imagenes\\passwordImagenLogin.png", 400, 200);
        panelVisual.add(imagenLogin);

        imagenEscudo = new JLabel();
        imagenEscudo.setBounds(10,20,50,50);
        this.setImagenLabel(imagenEscudo, "imagenes\\iconoSeguridad.png", 50,50);
        panelVisual.add(imagenEscudo);

        textoBienvenido = new JLabel("¡BIENVENIDO/A!");
        textoBienvenido.setBounds(200,400,400,100);
        textoBienvenido.setFont(fuenteTitulo);
        panelVisual.add(textoBienvenido);

        panelPrincipal.add(panelVisual, BorderLayout.CENTER);

        panelDatos = new JPanel();
        panelDatos.setPreferredSize(new Dimension(400, this.getHeight()));
        panelDatos.setLayout(null);
        panelDatos.setBackground(colorFondo);
        panelDatos.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        textoMayor = new JLabel("INGRESE SUS DATOS");
        textoMayor.setBounds(50, 20, 300, 50);
        textoMayor.setFont(fuenteTitulo);
        textoMayor.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(textoMayor);

        textoIngresarIP = new JLabel("Ingrese su IP:");
        textoIngresarIP.setBounds(50, 80, 300, 30);
        textoIngresarIP.setFont(fuenteTexto);
        panelDatos.add(textoIngresarIP);

        imagenIP = new JLabel();
        imagenIP.setBounds(50,120,35,35);
        imagenIP.setBackground(Color.LIGHT_GRAY);
        imagenIP.setBorder(borde);
        this.setImagenLabel(imagenIP, "imagenes\\ip.png", 35, 35);
        panelDatos.add(imagenIP);

        cajaIP = new JTextField();
        cajaIP.setBounds(85, 120, 265, 35);
        cajaIP.setFont(fuenteTexto);
        cajaIP.setBorder(borde);
        panelDatos.add(cajaIP);

        textoIngresarNombreDeUsuario = new JLabel("Nombre de usuario:");
        textoIngresarNombreDeUsuario.setBounds(50, 160, 300, 30);
        textoIngresarNombreDeUsuario.setFont(fuenteTexto);
        panelDatos.add(textoIngresarNombreDeUsuario);

        imagenUsername = new JLabel();
        imagenUsername.setBounds(50,200,35,35);
        imagenUsername.setBackground(Color.lightGray);
        imagenUsername.setBorder(borde);
        this.setImagenLabel(imagenUsername, "imagenes\\username.png", 35,35);
        panelDatos.add(imagenUsername);

        cajaNombreDeUsuario = new JTextField();
        cajaNombreDeUsuario.setBounds(85, 200, 265, 35);
        cajaNombreDeUsuario.setBorder(borde);
        cajaNombreDeUsuario.setFont(fuenteTexto);
        panelDatos.add(cajaNombreDeUsuario);

        textoIngresarContrasenia = new JLabel("Contraseña:");
        textoIngresarContrasenia.setBounds(50, 240, 300, 30);
        textoIngresarContrasenia.setFont(fuenteTexto);
        panelDatos.add(textoIngresarContrasenia);

        imagenPassword = new JLabel();
        imagenPassword.setBounds(50,280,35,35);
        imagenPassword.setBackground(Color.lightGray);
        imagenPassword.setBorder(borde);
        this.setImagenLabel(imagenPassword, "imagenes\\password.png", 35,35);
        panelDatos.add(imagenPassword);

        cajaPassword = new JTextField();
        cajaPassword.setBounds(85, 280, 265, 35);
        cajaPassword.setBorder(borde);
        cajaPassword.setFont(fuenteTexto);
        panelDatos.add(cajaPassword);

        botonIngresarDatos = new JButton("INGRESAR");
        botonIngresarDatos.setBounds(100, 400, 200, 50);
        botonIngresarDatos.setBackground(Color.white);
        botonIngresarDatos.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        botonIngresarDatos.setFont(fuenteTexto);
        botonIngresarDatos.setActionCommand("INGRESAR_DATOS");
        panelDatos.add(botonIngresarDatos);

        panelPrincipal.add(panelDatos, BorderLayout.EAST);

        this.add(panelPrincipal);
    }

    public void setImagenLabel(JLabel label, String ruta, int width, int height){
        ImageIcon imagen = new ImageIcon(ruta);
        Icon icono = new ImageIcon(
                imagen.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)
        );
        label.setIcon(icono);
        this.repaint();
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
