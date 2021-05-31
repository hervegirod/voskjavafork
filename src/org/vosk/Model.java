package org.vosk;

import com.sun.jna.PointerType;
import java.io.File;
import java.net.URL;

public class Model extends PointerType implements AutoCloseable {
   public Model() {
   }

   public Model(URL modelDir) {
      super(LibVosk.getVosk().vosk_model_new(new File(modelDir.getFile()).getPath()));
   }

   public Model(File modelDir) {
      super(LibVosk.getVosk().vosk_model_new(modelDir.getPath()));
   }

   public Model(String path) {
      super(LibVosk.getVosk().vosk_model_new(path));
   }

   @Override
   public void close() {
      LibVosk.getVosk().vosk_model_free(this.getPointer());
   }
}
