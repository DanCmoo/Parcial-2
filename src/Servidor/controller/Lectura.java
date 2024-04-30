package Servidor.controller;

import javax.speech.Central;
import javax.speech.EngineModeDesc;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

public class Lectura {


    public Lectura(){

    }
    public void leer(String mensaje) {
        try {
            // Configurar el motor de síntesis de voz
            System.setProperty("FreeTTSSynthEngineCentral", "com.cloudgarden.speech.CGEngineCentral");

            // Crear un objeto de síntesis de voz
            EngineModeDesc desc = new EngineModeDesc(Locale.ROOT);
            Synthesizer synthesizer = Central.createSynthesizer(desc);

            // Configurar la voz y el idioma
            SynthesizerModeDesc modeDesc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);
            synthesizer.allocate();
            synthesizer.resume();

            synthesizer.getSynthesizerProperties().setSpeakingRate(120);
            synthesizer.getSynthesizerProperties().setVolume(500);

            // Convertir el texto a voz
            synthesizer.speakPlainText(mensaje, null);

            // Esperar a que se complete la síntesis de voz
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

            // Liberar recursos
            synthesizer.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void despedirse(String nombre) {
        leer("Hasta luego" + nombre);
    }
}
