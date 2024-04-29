package Cliente.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InterfazUsuario extends JFrame {
    private JPanel panelImagen, panelPrincipal, panelAplicativo;
    private JLabel textoTemporalIndicativo, textoTextoALeer;
    private JScrollPane scrollPane;
    private JTextArea areaDeTexto;
    private JButton botonLeer,botonSalir;

    public InterfazUsuario(){
        super("Aplicación inclusiva");
        this.setSize(800,700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.inicializar();
        this.setVisible(true);
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

        textoTextoALeer = new JLabel("Texto:");
        textoTextoALeer.setHorizontalAlignment(SwingConstants.CENTER);
        textoTextoALeer.setBounds(200,30,400,40);
        panelAplicativo.add(textoTextoALeer);

        areaDeTexto = new JTextArea();

        scrollPane = new JScrollPane(areaDeTexto);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(20, 80,760, 250);
        panelAplicativo.add(scrollPane);

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

}
