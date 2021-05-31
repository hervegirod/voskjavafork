/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vosk;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

/**
 *
 * @since 
 */
public interface Vosk extends Library {
   public void vosk_set_log_level(int level);

   public Pointer vosk_model_new(String path);

   public void vosk_model_free(Pointer model);

   public Pointer vosk_spk_model_new(String path);

   public void vosk_spk_model_free(Pointer model);

   public Pointer vosk_recognizer_new(Model model, float sample_rate);

   public Pointer vosk_recognizer_new_spk(Pointer model, float sample_rate, Pointer spk_model);

   public Pointer vosk_recognizer_new_grm(Pointer model, float sample_rate, String grammar);

   public boolean vosk_recognizer_accept_waveform(Pointer recognizer, byte[] data, int len);

   public boolean vosk_recognizer_accept_waveform_s(Pointer recognizer, short[] data, int len);

   public boolean vosk_recognizer_accept_waveform_f(Pointer recognizer, float[] data, int len);

   public String vosk_recognizer_result(Pointer recognizer);

   public String vosk_recognizer_final_result(Pointer recognizer);

   public String vosk_recognizer_partial_result(Pointer recognizer);

   public void vosk_recognizer_free(Pointer recognizer);

   public void setLogLevel(int value);
}
