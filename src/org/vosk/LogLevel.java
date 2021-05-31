package org.vosk;

/**
 * The log level.
 */
public enum LogLevel {
   /**
    * Print warning and errors.
    */
   WARNINGS(-1), // Print warning and errors
   /**
    * Print info, along with warning and error messages, but no debug.
    */
   INFO(0), // Print info, along with warning and error messages, but no debug
   /**
    * Print debug info.
    */
   DEBUG(1);      // Print debug info

   private final int value;

   LogLevel(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }
}
