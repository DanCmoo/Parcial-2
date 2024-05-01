package Cliente.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InterfazUsuario extends JFrame {
    private JPanel panelImagen, panelPrincipal, panelAplicativo;
    private JLabel imagenPrograma, titulo, textoSeleccionar;
    private JScrollPane scrollPane;
    private JTextArea areaDeTexto;
    private JButton botonLeer,botonSalir;
    private JComboBox idiomas;

    Color colorFondo = new Color(226,241,255);
    Font fuenteTitulo = new Font("Verdana", Font.BOLD, 20);
    Font fuenteTexto = new Font("Verdana", Font.BOLD, 13);

    public InterfazUsuario(){
        super("Aplicación inclusiva");
        this.setSize(800,700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.white);
        this.setResizable(false);
        this.inicializar();
        this.setVisible(true);
    }

    public JPanel getPanelImagen() {
        return panelImagen;
    }

    public void setPanelImagen(JPanel panelImagen) {
        this.panelImagen = panelImagen;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JPanel getPanelAplicativo() {
        return panelAplicativo;
    }

    public void setPanelAplicativo(JPanel panelAplicativo) {
        this.panelAplicativo = panelAplicativo;
    }

    public JLabel getTitulo() {
        return titulo;
    }

    public JComboBox getIdiomas() {return idiomas;}

    public void setTitulo(JLabel titulo) {
        this.titulo = titulo;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JTextArea getAreaDeTexto() {
        return areaDeTexto;
    }

    public void setAreaDeTexto(JTextArea areaDeTexto) {
        this.areaDeTexto = areaDeTexto;
    }

    public JButton getBotonLeer() {
        return botonLeer;
    }

    public void setBotonLeer(JButton botonLeer) {
        this.botonLeer = botonLeer;
    }

    public JButton getBotonSalir() {
        return botonSalir;
    }

    public void setBotonSalir(JButton botonSalir) {
        this.botonSalir = botonSalir;
    }

    public void inicializar(){
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        panelImagen = new JPanel();
        panelImagen.setPreferredSize(new Dimension(this.getWidth(), 250));
        panelImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        panelImagen.setLayout(null);
        panelImagen.setBackground(Color.WHITE);

        imagenPrograma = new JLabel();
        imagenPrograma.setBounds(50,0,700,250);
        imagenPrograma.setBackground(colorFondo);
        this.setImagenLabel(imagenPrograma, "imagenes\\imagenInterfaz.png", 700, 250);

        panelImagen.add(imagenPrograma);

        panelPrincipal.add(panelImagen, BorderLayout.NORTH);

        panelAplicativo = new JPanel();
        panelAplicativo.setPreferredSize(new Dimension(this.getWidth(), 400));
        panelAplicativo.setLayout(null);
        Border line = BorderFactory.createLineBorder(Color.BLACK, 5);
        panelAplicativo.setBorder(BorderFactory.createTitledBorder(line, "Lector de texto"));
        panelAplicativo.setBackground(colorFondo);

        titulo = new JLabel("TEXTO A LEER:");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(fuenteTitulo);
        titulo.setBounds(20,30,510,40);
        panelAplicativo.add(titulo);

        areaDeTexto = new JTextArea();
        areaDeTexto.setFont(fuenteTexto);

        scrollPane = new JScrollPane(areaDeTexto);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(20, 80,550, 250);
        panelAplicativo.add(scrollPane);

        textoSeleccionar = new JLabel("SELECCIONE UN IDIOMA:");
        textoSeleccionar.setBounds(580,30,200,40);
        textoSeleccionar.setFont(fuenteTexto);
        panelAplicativo.add(textoSeleccionar);

        String[] lenguajes = {"Español", "Inglés", "Japonés", "Alemán", "Francés", "Italiano", "Coreano", "Chino"};
        idiomas = new JComboBox(lenguajes);
        idiomas.setFont(fuenteTexto);
        idiomas.setSelectedIndex(0);
        idiomas.setBounds(580, 80, 180,30);
        panelAplicativo.add(idiomas);


        botonLeer = new JButton("Leer");
        botonLeer.setBounds(67,340,200,40);
        botonLeer.setBackground(Color.white);
        botonLeer.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        botonLeer.setFont(fuenteTexto);
        botonLeer.setActionCommand("LEER");
        panelAplicativo.add(botonLeer);

        botonSalir = new JButton("Salir");
        botonSalir.setBounds(333,340,200,40);
        botonSalir.setBackground(Color.white);
        botonSalir.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        botonSalir.setFont(fuenteTexto);
        botonSalir.setActionCommand("SALIR");
        panelAplicativo.add(botonSalir);

        panelPrincipal.add(panelAplicativo, BorderLayout.SOUTH);

        this.add(panelPrincipal);

    }

    /**
     * Establece una imagen en un JLabel con el tamaño especificado.
     *
     * @param label  El JLabel en el que se establecerá la imagen.
     * @param ruta   La ruta de la imagen.
     * @param width  El ancho deseado de la imagen.
     * @param height La altura deseada de la imagen.
     */
    public void setImagenLabel(JLabel label, String ruta, int width, int height){
        // Crea un ImageIcon a partir de la imagen en la ruta especificada.
        ImageIcon imagen = new ImageIcon(ruta);
        // Escala la imagen al tamaño deseado.
        Icon icono = new ImageIcon(
                imagen.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)
        );
        // Establece el icono escalado en el JLabel.
        label.setIcon(icono);
        // Actualiza el componente para reflejar los cambios.
        this.repaint();
    }

    /**
     * Muestra un mensaje en un cuadro de diálogo utilizando JOptionPane.
     *
     * @param m El mensaje a mostrar en el cuadro de diálogo.
     */
    public void mostrarJOptionPane(String m) {
        // Muestra el mensaje en un cuadro de diálogo utilizando JOptionPane.
        JOptionPane.showMessageDialog(null,m);
    }

}
