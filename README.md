# voskjavafork
A fork of the Vosk Java wrapper. The vosk project is at https://github.com/alphacep/vosk-api

Note that the C source code is unchanged. This fork only change the Java wrapper (which is the reason why I did not fork the whole project on github).

For more information about the vosk project, go to: https://alphacephei.com/vosk/

# Models
Ready-to-use vosk models are available at https://alphacephei.com/vosk/models. The default model directory only contains
the simple vosk-model-small-en-us-0.15 model. You should use the model best suited to your needs.

# History
* 0.3.27 : initial version of the fork
* 0.3.27.1 : add new enum states for the log level
* 0.3.27.2 : allow to specify the String encoding to use
* 0.3.30.1 : use the 0.3.30 vosk version
* 0.3.45.1 : use the 0.3.45 vosk version

See [HISTORY.md](HISTORY.md)

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

## Specifying the String encoding
  It is possible to specify the String when registering the native libraries. For example:
  ```
  // register the native libraries
  File libDir = <directory of the native vosk libraries>
  LibVosk.register(libDir, "UTF-8");
  ```
or
  ```
  // register the native libraries
  URL urlDir = <directory of the native vosk libraries>
  LibVosk.register(urlDir, "UTF-8");
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

## Result
The recognizer result is a JSON file containing the detected words. The last "text" node is the finally recognized text.

With the default recognizer configuration:
  ```
{
  "text" : "this is a tester"
}
  ```

With `Recognizer.setWords(true)`:
  ```
{
  "result" : [{
      "conf" : 0.944314,
      "end" : 1.020000,
      "start" : 0.660000,
      "word" : "this"
    }, {
      "conf" : 0.944314,
      "end" : 1.200000,
      "start" : 1.020000,
      "word" : "is"
    }, {
      "conf" : 0.944314,
      "end" : 1.290000,
      "start" : 1.200000,
      "word" : "a"
    }, {
      "conf" : 0.367768,
      "end" : 2.070000,
      "start" : 1.290000,
      "word" : "tester"
    }],
  "text" : "this is a tester"
}
  ```

## Closing the native library
To close the library, you can call the 'free' method:
  ```
  LibVosk.free(model);
  ```
