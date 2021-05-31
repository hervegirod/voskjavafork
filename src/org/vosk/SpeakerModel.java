package org.vosk;

import com.sun.jna.PointerType;

public class SpeakerModel extends PointerType implements AutoCloseable {
    public SpeakerModel() {
    }

    public SpeakerModel(String path) {
        super(LibVosk.getVosk().vosk_spk_model_new(path));
    }

    @Override
    public void close() {
        LibVosk.getVosk().vosk_spk_model_free(this.getPointer());
    }
}
