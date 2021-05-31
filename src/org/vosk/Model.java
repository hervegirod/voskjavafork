package org.vosk;

import com.sun.jna.PointerType;
import java.io.File;
import java.net.URL;

/**
 * Represents the model.
 */
public class Model extends PointerType implements AutoCloseable {
   public Model() {
   }

   /**
    * Creates the model.
    *
    * @param modelDir the model library directory
    */
   public Model(URL modelDir) {
      super(LibVosk.getVosk().vosk_model_new(new File(modelDir.getFile()).getPath()));
   }

   /**
    * Creates the model.
    *
    * @param modelDir the model library directory
    */
   public Model(File modelDir) {
      super(LibVosk.getVosk().vosk_model_new(modelDir.getPath()));
   }

   /**
    * Creates the model.
    *
    * @param path the model library path
    */
   public Model(String path) {
      super(LibVosk.getVosk().vosk_model_new(path));
   }

   /**
    * Close the model.
    */
   @Override
   public void close() {
      LibVosk.getVosk().vosk_model_free(this.getPointer());
   }
}
