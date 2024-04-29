package Cliente.model.conexion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConexionProperties {

    private Properties datosServidor;
    private static InputStream entrada;


    /**
     * Crea una nueva instancia de ConexionProperties utilizando un archivo.
     *
     * @param archivo El archivo del que se cargarán las propiedades.
     * @throws FileNotFoundException Si no se encuentra el archivo especificado.
     */
    public ConexionProperties(File archivo) throws FileNotFoundException {
        datosServidor = new Properties();
        entrada = new FileInputStream(archivo);
    }

    /**
     * Carga los datos iniciales desde el archivo proporcionado.
     *
     * @throws IOException Si ocurre un error durante la lectura del archivo.
     */
    public void cargarDatosIniciales() throws IOException {
        datosServidor.load(entrada);
    }


    public Properties getDatosServidor() {
        return datosServidor;
    }

    public InputStream getEntrada() {
        return entrada;
    }
}

