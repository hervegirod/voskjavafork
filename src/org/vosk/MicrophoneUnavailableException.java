package org.vosk;

/**
 * This exception is thrown if the microphone is not available.
 */
public class MicrophoneUnavailableException extends RuntimeException {
   public MicrophoneUnavailableException() {
   }

   public MicrophoneUnavailableException(Exception cause) {
      super(cause);
   }
}
