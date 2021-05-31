package org.vosk;

import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 * An adapter for the microphone. Note that its simpler to use directly the {@link SpeechSourceProvider#getMicrophone()}
 * method.
 */
public class Microphone {
   private final TargetDataLine line;
   private final InputStream inputStream;

   public Microphone(float sampleRate, int sampleSize, boolean signed, boolean bigEndian) {
      AudioFormat format = new AudioFormat(sampleRate, sampleSize, 1, signed, bigEndian);
      try {
         line = AudioSystem.getTargetDataLine(format);
         line.open();
      } catch (IllegalArgumentException | LineUnavailableException e) {
         throw new MicrophoneUnavailableException(e);
      }
      inputStream = new AudioInputStream(line);
   }

   /**
    * Start the recording.
    */
   public void startRecording() {
      line.start();
   }

   /**
    * End the recording.
    */
   public void stopRecording() {
      line.stop();
   }

   /**
    * Close the connection.
    */
   public void closeConnection() {
      line.close();
   }

   /**
    * Return the associated stream.
    *
    * @return the stream
    */
   public InputStream getStream() {
      return inputStream;
   }
}
