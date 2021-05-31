package org.vosk;

public class MicrophoneUnavailableException extends RuntimeException {
   public MicrophoneUnavailableException() {      
   }
   
   public MicrophoneUnavailableException(Exception cause) {
      super(cause);
   }
}
