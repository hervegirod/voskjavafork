package org.vosk;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioSystem;

public class DecoderDemo {

   public static void main(String[] argv) throws Exception {
      File dir = new File(System.getProperty("user.dir"));
      File lib = new File(dir, "native/win32-x86-64/");
      LibVosk.register(lib);
      LibVosk.setLogLevel(LogLevel.WARNINGS);

      URL testURL = DecoderDemo.class.getResource("test.wav");
      try (Model model = new Model("model");
         InputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(testURL.openStream()));
         Recognizer recognizer = new Recognizer(model, 16000)) {
         int nbytes;
         byte[] b = new byte[4096];
         while ((nbytes = ais.read(b)) >= 0) {
            if (recognizer.acceptWaveForm(b, nbytes)) {
               System.out.println(recognizer.getResult());
            }
         }

      }
   }
}
