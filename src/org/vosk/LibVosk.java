package org.vosk;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LibVosk {
   private static LibVosk libVosk = null;
   /**
    * The Vosk instance.
    */
   public Vosk vosk;

   private LibVosk() {
   }

   /**
    * Register the libraries. Use the default encoding of the Java platform.
    *
    * @param file the directory
    * @return the LibVosk instance
    */
   public static LibVosk register(File file) {
      return register(file, null);
   }

   /**
    * Register the libraries.
    *
    * @param file the directory
    * @param encoding the encoding
    * @return the LibVosk instance
    */
   public static LibVosk register(File file, String encoding) {
      if (libVosk == null) {
         libVosk = new LibVosk();
         libVosk.registerImpl(file, encoding);
      }
      return libVosk;
   }

   /**
    * Register the libraries. Use the default encoding of the Java platform.
    *
    * @param url the directory
    * @return the LibVosk instance
    */
   public static LibVosk register(URL url) {
      return register(url, null);
   }

   /**
    * Register the libraries.
    *
    * @param url the directory
    * @param encoding the encoding
    * @return the LibVosk instance
    */
   public static LibVosk register(URL url, String encoding) {
      if (libVosk == null) {
         libVosk = new LibVosk();
         libVosk.registerImpl(url, encoding);
      }
      return libVosk;
   }

   /**
    * Return the unique instance.
    *
    * @return the unique instance
    */
   public static LibVosk getInstance() {
      return libVosk;
   }

   /**
    * Return the internal vosk element which wrap the native functions.
    *
    * @return the vosk element
    */
   public static Vosk getVosk() {
      return libVosk.vosk;
   }

   private void registerImpl(File file, String encoding) {
      Native.setProtected(true);
      String path = file.getPath();
      short type = SystemUtils.getPlatformType();
      if (type == SystemUtils.OS_WINDOWS) {
         if (SystemUtils.is64Bits()) {
            NativeLibrary.addSearchPath("libgcc_s_seh-1", path);
            NativeLibrary.addSearchPath("libstdc++-6", path);
            NativeLibrary.addSearchPath("libvosk", path);
            NativeLibrary.addSearchPath("libwinpthread-1", path);
            vosk = Native.loadLibrary("libvosk", Vosk.class);
         } else {
            NativeLibrary.addSearchPath("libgcc_s_sjlj-1", path);
            NativeLibrary.addSearchPath("libstdc++-6", path);
            NativeLibrary.addSearchPath("libvosk", path);
            NativeLibrary.addSearchPath("libwinpthread-1", path);
            vosk = Native.loadLibrary("libvosk", Vosk.class);
         }
      } else if (type == SystemUtils.OS_LINUX) {
         NativeLibrary.addSearchPath("libvosk", path);
         Map<String, Object> options = new HashMap<>();
         if (encoding != null) {
            options.put(Library.OPTION_STRING_ENCODING, encoding);
            vosk = Native.load("libvosk", Vosk.class, options);
         } else {
            vosk = Native.load("libvosk", Vosk.class);
         }

      }
   }

   private void registerImpl(URL url, String encoding) {
      File file = new File(url.getFile());
      registerImpl(file, encoding);
   }

   public static void end(Model model) {
      libVosk.vosk.vosk_set_log_level(99999);
      libVosk.vosk.vosk_model_free(model.getPointer());
   }

   public static void setLogLevel(LogLevel loglevel) {
      libVosk.vosk.vosk_set_log_level(loglevel.getValue());
   }
}
