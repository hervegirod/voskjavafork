package org.vosk;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

/**
 * The vosk native library wrapper.
 *
 * @version 0.3.30.1
 */
public interface Vosk extends Library {
   /**
    * Sets the log level.
    *
    * @param level the level
    */
   public void vosk_set_log_level(int level);

   /**
    * Creates a native model. Note that it is preferable not to use this method directly, but to use the
    * {@link Model} constructors.
    *
    * @param path the model directory path
    * @return the associated Pointer
    */
   public Pointer vosk_model_new(String path);

   /**
    * Free the native Model. Note that it is preferable not to use this method directly, but to use the
    * {@link Model#close()} method.
    *
    * @param model the model Pointer
    */
   public void vosk_model_free(Pointer model);

   /**
    * Creates a native speaker model. Note that it is preferable not to use this method directly, but to use the
    * {@link SpeakerModel} constructors.
    *
    * @param path the model directory path
    * @return the associated Pointer
    */
   public Pointer vosk_spk_model_new(String path);

   /**
    * Free the native speaker Model. Note that it is preferable not to use this method directly, but to use the
    * {@link SpeakerModel#close()} method.
    *
    * @param model the model Pointer
    */
   public void vosk_spk_model_free(Pointer model);

   /**
    * Creates a recognizer. Note that it is preferable not to use this method directly, but to use the
    * {@link Recognizer#Recognizer(org.vosk.Model, float)} constructor.
    *
    * @param model the model
    * @param sample_rate the sample rate
    * @return the Pointer
    */
   public Pointer vosk_recognizer_new(Model model, float sample_rate);

   /**
    * Creates a recognizer with a model and a speaker model. Note that it is preferable not to use this method directly, but to use the
    * {@link Recognizer#Recognizer(org.vosk.Model, float, org.vosk.SpeakerModel)} constructor.
    *
    * @param model the model
    * @param sample_rate the sample rate
    * @param spk_model the speaker model
    * @return the Pointer
    */
   public Pointer vosk_recognizer_new_spk(Pointer model, float sample_rate, Pointer spk_model);

   /**
    * Creates a recognizer with a model and a grammar. Note that it is preferable not to use this method directly, but to use the
    * {@link Recognizer#Recognizer(org.vosk.Model, float, java.lang.String) } constructor.
    *
    * @param model the model
    * @param sample_rate the sample rate
    * @param grammar the grammar
    * @return the Pointer
    */
   public Pointer vosk_recognizer_new_grm(Pointer model, float sample_rate, String grammar);

   /**
    * Set the maximum number of alternatives. Note that it is preferable not to use this method directly, but to use the
    * {@link Recognizer#setMaxAlternatives(int)} method.
    *
    * @param model the model
    * @param max_alternatives the maximum number of alternatives
    */
   public void vosk_recognizer_set_max_alternatives(Pointer model, int max_alternatives);

   /**
    * Set if the recognized words are included in the result. Note that it is preferable not to use this method directly, but to use the
    * {@link Recognizer#setWords(boolean)} method.
    *
    * @param model the model
    * @param words true if the recognized words are included in the result
    */
   public void vosk_recognizer_set_words(Pointer model, boolean words);

   /**
    * Set the speaker model to an existing Recognizer. Note that it is preferable not to use this method directly, but to use the
    * {@link Recognizer#setSpeakerModel(org.vosk.SpeakerModel)} method.
    *
    * @param model the model
    * @param spk_model the speaker model
    */
   public void vosk_recognizer_set_spk_model(Pointer model, Pointer spk_model);

   /**
    * Try a recognition. Note that it is preferable not to use this method directly, but to use the
    * {@link Recognizer#acceptWaveForm(byte[], int)} method.
    *
    * @param recognizer the recognizer pointer
    * @param data the data array
    * @param len the data array length
    * @return true if the recognition detected some content
    */
   public boolean vosk_recognizer_accept_waveform(Pointer recognizer, byte[] data, int len);

   /**
    * Try a recognition. Note that it is preferable not to use this method directly, but to use the
    * {@link Recognizer#acceptWaveForm(short[], int)} method.
    *
    * @param recognizer the recognizer pointer
    * @param data the data array
    * @param len the data array length
    * @return true if the recognition detected some content
    */
   public boolean vosk_recognizer_accept_waveform_s(Pointer recognizer, short[] data, int len);

   /**
    * Try a recognition. Note that it is preferable not to use this method directly, but to use the
    * {@link Recognizer#acceptWaveForm(float[], int)} method.
    *
    * @param recognizer the recognizer pointer
    * @param data the data array
    * @param len the data array length
    * @return true if the recognition detected some content
    */
   public boolean vosk_recognizer_accept_waveform_f(Pointer recognizer, float[] data, int len);

   /**
    * Return the recognizer result. Note that it is preferable not to use this method directly, but to use the
    * {@link Recognizer#getResult()} method.
    *
    * @param recognizer the recognizer pointer
    * @return the result
    */
   public String vosk_recognizer_result(Pointer recognizer);

   /**
    * Return the recognizer final result. Note that it is preferable not to use this method directly, but to use the
    * {@link Recognizer#getFinalResult()} method.
    *
    * @param recognizer the recognizer pointer
    * @return the result
    */
   public String vosk_recognizer_final_result(Pointer recognizer);

   /**
    * Return the recognizer partial result. Note that it is preferable not to use this method directly, but to use the
    * {@link Recognizer#getPartialResult()} method.
    *
    * @param recognizer the recognizer pointer
    * @return the result
    */
   public String vosk_recognizer_partial_result(Pointer recognizer);

   /**
    * Reset the recognizer partial result. Note that it is preferable not to use this method directly, but to use the
    * {@link Recognizer#reset()} method.
    *
    * @param recognizer the recognizer pointer
    */
   public void vosk_recognizer_reset(Pointer recognizer);

   /**
    * Free the recognizer. Note that it is preferable not to use this method directly, but to use the
    * {@link Recognizer#close()} method.
    *
    * @param recognizer the recognizer pointer
    */
   public void vosk_recognizer_free(Pointer recognizer);

   /**
    * Set the Log level. Identical to using {@link LibVosk#setLogLevel(org.vosk.LogLevel)}.
    *
    * @param value the log level value
    */
   public void setLogLevel(int value);
}
