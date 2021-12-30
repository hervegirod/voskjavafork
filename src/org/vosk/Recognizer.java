package org.vosk;

import com.sun.jna.PointerType;
import java.io.IOException;

/**
 * The recognizer.
 *
 * @version 0.3.32.1
 */
public class Recognizer extends PointerType implements AutoCloseable {
   /**
    * Constructor.
    *
    * @param model the model
    * @param sampleRate the sample rate
    * @throws java.io.IOException throws an IOException if the associated pointer is null
    */
   public Recognizer(Model model, float sampleRate) throws IOException {
      super(LibVosk.getVosk().vosk_recognizer_new(model, sampleRate));

      if (getPointer() == null) {
         throw new IOException("Failed to create a recognizer");
      }
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
    * Set the maximum number of alternatives.
    *
    * @param maxAlternatives the maximum number of alternatives
    */
   public void setMaxAlternatives(int maxAlternatives) {
      LibVosk.getVosk().vosk_recognizer_set_max_alternatives(this.getPointer(), maxAlternatives);
   }

   /**
    * Set if the recognized words are included in the result. By default only the final result is provided in the JSON result, and
    * words confidence is not provided.
    *
    * @param words true if the recognized words are included in the result
    */
   public void setWords(boolean words) {
      LibVosk.getVosk().vosk_recognizer_set_words(this.getPointer(), words);
   }

   /**
    * Set the speaker model to this Recognizer.
    *
    * @param spkModel the speaker model
    */
   public void setSpeakerModel(SpeakerModel spkModel) {
      LibVosk.getVosk().vosk_recognizer_set_spk_model(this.getPointer(), spkModel.getPointer());
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
    * Reset the recognizer partial result..
    */
   public void reset() {
      LibVosk.getVosk().vosk_recognizer_reset(this.getPointer());
   }

   /**
    * Close the recognizer.
    */
   @Override
   public void close() {
      LibVosk.getVosk().vosk_recognizer_free(this.getPointer());
   }
}
