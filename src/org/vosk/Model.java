package org.vosk;

import com.sun.jna.PointerType;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Represents the model.
 *
 * @version 0.3.32.1
 */
public class Model extends PointerType implements AutoCloseable {
   public Model() {
   }

   /**
    * Creates the model.
    *
    * @param modelDir the model library directory
    * @throws java.io.IOException throws an IOException if the associated pointer is null
    */
   public Model(URL modelDir) throws IOException {
      super(LibVosk.getVosk().vosk_model_new(new File(modelDir.getFile()).getPath()));

      if (getPointer() == null) {
         throw new IOException("Failed to create a model");
      }
   }

   /**
    * Creates the model.
    *
    * @param modelDir the model library directory
    * @throws java.io.IOException throws an IOException if the associated pointer is null
    */
   public Model(File modelDir) throws IOException {
      super(LibVosk.getVosk().vosk_model_new(modelDir.getPath()));

      if (getPointer() == null) {
         throw new IOException("Failed to create a model");
      }
   }

   /**
    * Creates the model.
    *
    * @param path the model library path
    * @throws java.io.IOException throws an IOException if the associated pointer is null
    */
   public Model(String path) throws IOException {
      super(LibVosk.getVosk().vosk_model_new(path));

      if (getPointer() == null) {
         throw new IOException("Failed to create a model");
      }
   }

   /**
    * Close the model.
    */
   @Override
   public void close() {
      LibVosk.getVosk().vosk_model_free(this.getPointer());
   }
}
