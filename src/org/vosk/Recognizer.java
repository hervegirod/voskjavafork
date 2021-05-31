package org.vosk;

import com.sun.jna.PointerType;

public class Recognizer extends PointerType implements AutoCloseable {
    public Recognizer(Model model, float sampleRate) {
        super(LibVosk.getVosk().vosk_recognizer_new(model, sampleRate));
    }

    public Recognizer(Model model, float sampleRate, SpeakerModel spkModel) {
        super(LibVosk.getVosk().vosk_recognizer_new_spk(model.getPointer(), sampleRate, spkModel.getPointer()));
    }

    public Recognizer(Model model, float sampleRate, String grammar) {
        super(LibVosk.getVosk().vosk_recognizer_new_grm(model.getPointer(), sampleRate, grammar));
    }

    public boolean acceptWaveForm(byte[] data, int len) {
        return LibVosk.getVosk().vosk_recognizer_accept_waveform(this.getPointer(), data, len);
    }

    public boolean acceptWaveForm(short[] data, int len) {
        return LibVosk.getVosk().vosk_recognizer_accept_waveform_s(this.getPointer(), data, len);
    }

    public boolean acceptWaveForm(float[] data, int len) {
        return LibVosk.getVosk().vosk_recognizer_accept_waveform_f(this.getPointer(), data, len);
    }

    public String getResult() {
        return LibVosk.getVosk().vosk_recognizer_result(this.getPointer());
    }

    public String getPartialResult() {
        return LibVosk.getVosk().vosk_recognizer_partial_result(this.getPointer());
    }

    public String getFinalResult() {
        return LibVosk.getVosk().vosk_recognizer_final_result(this.getPointer());
    }

    @Override
    public void close() {
        LibVosk.getVosk().vosk_recognizer_free(this.getPointer());
    }
}
