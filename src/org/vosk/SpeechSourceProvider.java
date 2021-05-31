package org.vosk;

/**
 * This class allows to get the Microphone stream provider.
 */
public class SpeechSourceProvider {

   private SpeechSourceProvider() {
   }

   /**
    * Return the Microphone stream provider.
    *
    * @return the Microphone
    */
   public static Microphone getMicrophone() {
      return new Microphone(16000, 16, true, false);
   }
}
