package Servidor.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class VistaServidor extends JFrame{
    public JScrollPane panelScroll;
    public JTextArea cajaTexto;

    public VistaServidor(String title){
        super(title);
        setSize(1200,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    protected void frameInit() {
        super.frameInit();

        cajaTexto = new JTextArea();

        panelScroll = new JScrollPane(cajaTexto);
        add(panelScroll);



    }

    /**
     * Abre un cuadro de diálogo para que el usuario seleccione un archivo.
     *
     * @param textoCaracteristicas La descripción del tipo de archivo que se debe seleccionar.
     * @param textoFiltro La extensión del archivo que se debe filtrar.
     * @return El archivo seleccionado por el usuario.
     * @throws IOException Sí ocurre un error durante la operación de selección de archivo.
     */
    public File pedirArchivo(String textoCaracteristicas, String textoFiltro) throws IOException {
        JFileChooser seleccionArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(textoCaracteristicas, textoFiltro);
        seleccionArchivo.setFileFilter(filtro);
        seleccionArchivo.showOpenDialog(null);
        return seleccionArchivo.getSelectedFile();
    }

    /**
     * Muestra un mensaje en la interfaz de usuario.
     *
     * @param m El mensaje a mostrar.
     */
    public void mostrarMensaje(String m) {
        cajaTexto.append(m + "\n");
    }

    /**
     * Muestra un mensaje emergente en un cuadro de diálogo.
     *
     * @param m El mensaje a mostrar.
     */
    public void mostrarJOptionPane(String m) {
        JOptionPane.showMessageDialog(null, m);
    }


}
