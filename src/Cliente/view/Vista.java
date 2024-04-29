package Cliente.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class Vista {

    public Vista(){

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
    public String autores(){
        return "Hecho por:" + "\nDaniel Esteban Camacho Ospina" + "\nGiovanni Alexander Vargas Castañeda" + "\nEdwin Alejandro Orjuela Olarte";
    }

    /**
     * Abre un cuadro de diálogo para solicitar al usuario un nombre de usuario.
     *
     * @param texto El texto a mostrar en el cuadro de diálogo.
     * @return El dato ingresado por el usuario.
     */
    public String pedirDatos(String texto){
        return JOptionPane.showInputDialog(null,texto);
    }


    public void mostrarJOptionPane(String s) {
        JOptionPane.showMessageDialog(null,s);
    }
}
