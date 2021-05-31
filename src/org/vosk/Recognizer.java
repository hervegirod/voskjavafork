package org.vosk;

import com.sun.jna.PointerType;

/**
 * The recognizer.
 */
public class Recognizer extends PointerType implements AutoCloseable {
   /**
    * Constructor.
    *
    * @param model the model
    * @param sampleRate the sample rate
    */
   public Recognizer(Model model, float sampleRate) {
      super(LibVosk.getVosk().vosk_recognizer_new(model, sampleRate));
   }

   /**
    * Constructor.
    *
    * @param model the model
    * @param spkModel the speaker model
    * @param sampleRate the sample rate
    */
   public Recognizer(Model model, float sampleRate, SpeakerModel spkModel) {
      super(LibVosk.getVosk().vosk_recognizer_new_spk(model.getPointer(), sampleRate, spkModel.getPointer()));
   }

   /**
    * Constructor.
    *
    * @param model the model
    * @param grammar the grammar
    * @param sampleRate the sample rate
    */
   public Recognizer(Model model, float sampleRate, String grammar) {
      super(LibVosk.getVosk().vosk_recognizer_new_grm(model.getPointer(), sampleRate, grammar));
   }

   /**
    * Try a recognition.
    *
    * @param data the data array
    * @param len the data array length
    * @return true if the recognition detected some content
    */
   public boolean acceptWaveForm(byte[] data, int len) {
      return LibVosk.getVosk().vosk_recognizer_accept_waveform(this.getPointer(), data, len);
   }

   /**
    * Try a recognition.
    *
    * @param data the data array
    * @param len the data array length
    * @return true if the recognition detected some content
    */
   public boolean acceptWaveForm(short[] data, int len) {
      return LibVosk.getVosk().vosk_recognizer_accept_waveform_s(this.getPointer(), data, len);
   }

   /**
    * Try a recognition.
    *
    * @param data the data array
    * @param len the data array length
    * @return true if the recognition detected some content
    */
   public boolean acceptWaveForm(float[] data, int len) {
      return LibVosk.getVosk().vosk_recognizer_accept_waveform_f(this.getPointer(), data, len);
   }

   /**
    * Return the result. It is a JSON string.
    *
    * @return the result
    */
   public String getResult() {
      return LibVosk.getVosk().vosk_recognizer_result(this.getPointer());
   }

   /**
    * Return the partial result. It is a JSON string.
    *
    * @return the result
    */
   public String getPartialResult() {
      return LibVosk.getVosk().vosk_recognizer_partial_result(this.getPointer());
   }

   /**
    * Return the final result. It is a JSON string.
    *
    * @return the result
    */
   public String getFinalResult() {
      return LibVosk.getVosk().vosk_recognizer_final_result(this.getPointer());
   }

   /**
    * Close the recognizer.
    */
   @Override
   public void close() {
      LibVosk.getVosk().vosk_recognizer_free(this.getPointer());
   }
}
