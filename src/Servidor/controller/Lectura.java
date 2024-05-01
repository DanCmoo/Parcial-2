package Servidor.controller;

import javax.speech.Central;
import javax.speech.EngineModeDesc;
import javax.speech.synthesis.Synthesizer;
import java.util.Locale;

public class Lectura {


    public Lectura() {

    }

    /**
     * Lee el mensaje especificado en el idioma indicado utilizando síntesis de voz.
     *
     * @param idioma  El idioma en el que se leerá el mensaje.
     * @param mensaje El mensaje que se leerá en voz alta.
     * @throws Exception Sí ocurre un error durante el proceso de síntesis de voz.
     */
    public void leer(String idioma, String mensaje) throws Exception {
        // Establece el motor de síntesis de voz a utilizar.
        System.setProperty("FreeTTSSynthEngineCentral", "com.cloudgarden.speech.CGEngineCentral");

        // Crea una descripción del modo del motor de síntesis.
        EngineModeDesc desc = new EngineModeDesc(verificarIdioma(idioma));

        // Crea un sintetizador de voz utilizando la descripción del modo del motor.
        Synthesizer synthesizer = Central.createSynthesizer(desc);

        // Asigna y reanuda los recursos del sintetizador.
        synthesizer.allocate();
        synthesizer.resume();

        // Configura la velocidad y el volumen del habla.
        synthesizer.getSynthesizerProperties().setSpeakingRate(130);
        synthesizer.getSynthesizerProperties().setVolume(500);

        // Lee el mensaje en voz alta.
        synthesizer.speakPlainText(mensaje, null);

        // Espera hasta que la cola del sintetizador esté vacía.
        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

        // Libera los recursos del sintetizador.
        synthesizer.deallocate();
    }


    /**
     * Verifica y devuelve el objeto Locale correspondiente al idioma especificado.
     *
     * @param idioma El idioma para el cual se desea obtener el objeto Locale.
     * @return El objeto Locale correspondiente al idioma especificado.
     */
    public Locale verificarIdioma(String idioma) {
        switch (idioma) {
            // Devuelve el objeto Locale correspondiente al idioma especificado.
            case "Inglés":
                return Locale.ENGLISH;
            case "Japonés":
                return Locale.JAPANESE;
            case "Alemán":
                return Locale.GERMANY;
            case "Francés":
                return Locale.FRENCH;
            case "Italiano":
                return Locale.ITALIAN;
            case "Coreano":
                return Locale.KOREAN;
            case "Chino":
                return Locale.CHINESE;
            default:
                // Si no se reconoce el idioma, devuelve el objeto Locale por defecto (ROOT).
                return Locale.ROOT;
        }
    }


    /**
     * Despide al usuario en el idioma especificado.
     *
     * @param idioma El idioma en el que se realizará la despedida.
     * @param nombre El nombre del usuario.
     * @throws Exception Sí ocurre un error durante el proceso de despedida.
     */
    public void despedirse(String idioma, String nombre) throws Exception {
        switch (idioma) {
            // Realiza la despedida en el idioma correspondiente.
            case "Inglés":
                leer(idioma, "See you later " + nombre);
                break;
            case "Japonés":
                leer(idioma, "また後で " + nombre);
                break;
            case "Alemán":
                leer(idioma, "Bis später " + nombre);
                break;
            case "Francés":
                leer(idioma, "À plus tard " + nombre);
                break;
            case "Italiano":
                leer(idioma, "Arrivederci " + nombre);
                break;
            case "Coreano":
                leer(idioma, "나중에 봐요 " + nombre);
                break;
            case "Chino":
                leer(idioma, "回头见 " + nombre);
                break;
            default:
                leer(idioma, "Hasta luego " + nombre);
        }
    }


    public static void main(String[] args) {
        Lectura lectura = new Lectura();
        try {
            lectura.leer("Inglés", "HOLAPUTITAS");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}