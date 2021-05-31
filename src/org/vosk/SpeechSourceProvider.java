package org.vosk;


public class SpeechSourceProvider {
   
   private  SpeechSourceProvider() {
   }
      
    public static Microphone getMicrophone() {
        return new Microphone(16000, 16, true, false);
    }
}
