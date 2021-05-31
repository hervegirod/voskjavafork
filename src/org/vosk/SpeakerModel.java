package org.vosk;

import com.sun.jna.PointerType;
import java.io.File;
import java.net.URL;

/**
 * The speaker model.
 */
public class SpeakerModel extends PointerType implements AutoCloseable {
   public SpeakerModel() {
   }

   /**
    * Creates the model.
    *
    * @param modelDir the model library directory
    */
   public SpeakerModel(URL modelDir) {
      super(LibVosk.getVosk().vosk_spk_model_new(new File(modelDir.getFile()).getPath()));
   }

   /**
    * Creates the model.
    *
    * @param modelDir the model library directory
    */
   public SpeakerModel(File modelDir) {
      super(LibVosk.getVosk().vosk_spk_model_new(modelDir.getPath()));
   }

   public SpeakerModel(String path) {
      super(LibVosk.getVosk().vosk_spk_model_new(path));
   }

   @Override
   public void close() {
      LibVosk.getVosk().vosk_spk_model_free(this.getPointer());
   }
}
