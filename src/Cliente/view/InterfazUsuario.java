package Cliente.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InterfazUsuario extends JFrame {
    private JPanel panelImagen, panelPrincipal, panelAplicativo;
    private JLabel textoTemporalIndicativo, titulo;
    private JScrollPane scrollPane;
    private JTextArea areaDeTexto;
    private JButton botonLeer,botonSalir;
    private JComboBox idiomas;

    public InterfazUsuario(){
        super("Aplicación inclusiva");
        this.setSize(800,700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

    public JLabel getTextoTemporalIndicativo() {
        return textoTemporalIndicativo;
    }

    public void setTextoTemporalIndicativo(JLabel textoTemporalIndicativo) {
        this.textoTemporalIndicativo = textoTemporalIndicativo;
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
        panelImagen.setPreferredSize(new Dimension(Integer.MAX_VALUE, 250));
        panelImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        panelImagen.setLayout(null);
        panelImagen.setBackground(Color.WHITE);

        textoTemporalIndicativo = new JLabel("AQUÍ APARECE LA IMAGEN DONDE DE MANERA IMPLICITA TE DIGO MANCO BOT POR NO VER");
        textoTemporalIndicativo.setBounds(10,10,760,250);

        panelImagen.add(textoTemporalIndicativo);

        panelPrincipal.add(panelImagen, BorderLayout.NORTH);

        panelAplicativo = new JPanel();
        panelAplicativo.setPreferredSize(new Dimension(Integer.MAX_VALUE, 400));
        panelAplicativo.setLayout(null);
        Border line = BorderFactory.createLineBorder(Color.BLACK, 5);
        panelAplicativo.setBorder(BorderFactory.createTitledBorder(line, "Texto a leer"));
        panelAplicativo.setBackground(Color.WHITE);

        titulo = new JLabel("Texto:");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(200,30,400,40);
        panelAplicativo.add(titulo);

        areaDeTexto = new JTextArea();

        scrollPane = new JScrollPane(areaDeTexto);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(20, 80,650, 250);
        panelAplicativo.add(scrollPane);

        String[] lenguajes = {"Español", "Inglés", "Japonés", "Alemán", "Francés", "Italiano", "Coreano", "Chino"};
        idiomas = new JComboBox(lenguajes);
        idiomas.setSelectedIndex(0);
        idiomas.setBounds(680, 80, 100,30);
        panelAplicativo.add(idiomas);


        botonLeer = new JButton("Leer");
        botonLeer.setBounds(150,340,200,40);
        botonLeer.setActionCommand("LEER");
        panelAplicativo.add(botonLeer);

        botonSalir = new JButton("Salir");
        botonSalir.setBounds(450,340,200,40);
        botonSalir.setActionCommand("SALIR");
        panelAplicativo.add(botonSalir);

        panelPrincipal.add(panelAplicativo, BorderLayout.SOUTH);

        this.add(panelPrincipal);

    }

    public void mostrarJOptionPane(String m) {
        JOptionPane.showMessageDialog(null,m);

    }
}
