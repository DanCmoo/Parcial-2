package Servidor.controller;

import javax.speech.Central;
import javax.speech.EngineModeDesc;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

public class Lectura {


    public Lectura(){

    }
    public void leer(String idioma, String mensaje) throws Exception {

            // Configurar el motor de síntesis de voz
            System.setProperty("FreeTTSSynthEngineCentral", "com.cloudgarden.speech.CGEngineCentral");

            // Crear un objeto de síntesis de voz
            EngineModeDesc desc = new EngineModeDesc(verificarIdioma(idioma));
            Synthesizer synthesizer = Central.createSynthesizer(desc);

            // Configurar la voz y el idioma
            SynthesizerModeDesc modeDesc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);
            synthesizer.allocate();
            synthesizer.resume();

            synthesizer.getSynthesizerProperties().setSpeakingRate(130);
            synthesizer.getSynthesizerProperties().setVolume(500);

            // Convertir el texto a voz
            synthesizer.speakPlainText(mensaje, null);

            // Esperar a que se complete la síntesis de voz
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

            // Liberar recursos
            synthesizer.deallocate();

    }

    public Locale verificarIdioma(String idioma){
        switch (idioma){
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
                return Locale.ROOT;
        }

    }

    public void despedirse(String idioma, String nombre) throws Exception {
        switch (idioma){
            case "Inglés":
                leer(idioma,"See you later "+ nombre);
            case "Japonés":
                leer(idioma,"また後で "+ nombre);
            case "Alemán":
                leer(idioma,"Bis später "+ nombre);
            case "Francés":
                leer(idioma,"À plus tard "+ nombre);
            case "Italiano":
                leer(idioma,"Arrivederci "+ nombre);
            case "Coreano":
                leer(idioma,"나중에 봐요 "+ nombre);
            case "Chino":
                leer(idioma,"回头见 "+ nombre);
            default:
                leer(idioma,"Hasta luego "+ nombre);
        }


    }

    public static void main(String[] args){
        Lectura lectura = new Lectura();
        try {
            lectura.leer("Alemán","fliege");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}