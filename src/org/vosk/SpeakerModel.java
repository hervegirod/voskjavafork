package org.vosk;

import com.sun.jna.PointerType;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * The speaker model.
 *
 * @version 0.3.32.1
 */
public class SpeakerModel extends PointerType implements AutoCloseable {
   public SpeakerModel() {
   }

   /**
    * Creates the model.
    *
    * @param modelDir the model library directory
    * @throws java.io.IOException throws an IOException if the associated pointer is null
    */
   public SpeakerModel(URL modelDir) throws IOException {
      super(LibVosk.getVosk().vosk_spk_model_new(new File(modelDir.getFile()).getPath()));

      if (getPointer() == null) {
         throw new IOException("Failed to create a speaker model");
      }
   }

   /**
    * Creates the model.
    *
    * @param modelDir the model library directory
    * @throws java.io.IOException throws an IOException if the associated pointer is null
    */
   public SpeakerModel(File modelDir) throws IOException {
      super(LibVosk.getVosk().vosk_spk_model_new(modelDir.getPath()));

      if (getPointer() == null) {
         throw new IOException("Failed to create a speaker model");
      }
   }

   /**
    * Creates the model.
    *
    * @param path the model library directory path
    * @throws java.io.IOException throws an IOException if the associated pointer is null
    */
   public SpeakerModel(String path) throws IOException {
      super(LibVosk.getVosk().vosk_spk_model_new(path));

      if (getPointer() == null) {
         throw new IOException("Failed to create a speaker model");
      }
   }

   @Override
   public void close() {
      LibVosk.getVosk().vosk_spk_model_free(this.getPointer());
   }
}
