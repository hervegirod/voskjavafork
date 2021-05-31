# voskjavafork
A fork of the Vosk Java wrapper. The vosk project is at https://github.com/alphacep/vosk-api

Note that the C source code is unchanged. This fork only change the Java wrapper (which is the reason why I did not fork the whole project on github). The Java code fork is beginning from the 0.3.29 version of the Java code and the 0.3.27 version for the dlls.

For more information about the vosk project, go to: https://alphacephei.com/vosk/

# Changes
The changes from the original project are:
* changing the way the libraries are loaded. The initial way was a little cumbersome. This one is simpler
* don't distribute  the dlls inside the jar file. Now it is necessary to specify the directory to use for the native libraries
* distribute the livrary with the JNA libraries
* port the Sphynx mechanism to handle microphone input

# Models
Ready-to-use vosk models are available at https://alphacephei.com/vosk/models

# History
* 0.3.27 : initial version of the fork

# Usage
## Registering the native libraries
  ```
  // register the native libraries
  File libDir = <directory of the native vosk libraries>
  LibVosk.register(libDir);
  ```
or
  ```
  // register the native libraries
  URL urlDir = <directory of the native vosk libraries>
  LibVosk.register(urlDir);
  ```

## Setting the log level
For example:
  ```
  LibVosk.setLogLevel(LogLevel.WARNINGS);
  ```

## Specifying the Model
The path of the model unzipped directory must be used for the model.
  ```
  Model model = new Model("<model path>");
  ```
or 
  ```
  File dir = <directory of the model>
  Model model = new Model(dir);
  ```
or 
  ```
  URL dir = <directory of the model>
  Model model = new Model(dir);
  ```

## Using the microphone

  ```
      Microphone microphone = SpeechSourceProvider.getMicrophone();
      microphone.startRecording();
      try (Model model = new Model("model");
         InputStream ais = microphone.getStream();
         Recognizer recognizer = new Recognizer(model, 16000)) {
         int nbytes;
         byte[] b = new byte[4096];
         while ((nbytes = ais.read(b)) >= 0) {
            if (recognizer.acceptWaveForm(b, nbytes)) {
               System.out.println(recognizer.getResult());
            }
         }
      }
  ```
