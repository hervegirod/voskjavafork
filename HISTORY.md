# History

## 0.3.27.2
The Java code fork is beginning from the 0.3.29 version of the Java code and the 0.3.27 version for the dlls. The changes from the
original project are:
* changing the way the libraries are loaded. The initial way was a little cumbersome. This one is simpler
* don't distribute  the dlls inside the jar file. Now it is necessary to specify the directory to use for the native libraries
* distribute the library with the JNA libraries
* port the Sphynx mechanism to handle microphone input
* add new enum states for the log level

## 0.3.30.1
* Use the 0.3.30 version of the vosk project
* Add the support for several methods which were added in the 0.3.30 of vosk

## 0.3.31.1
* Use the 0.3.31 version of the vosk project (Fixed RNNLM rescoring strategy, good with en-us-0.21 model)

## 0.3.31.2
* Make sure that the logger doesn't log anything after the library is closed

## 0.3.32.1
* Wrap exceptions on C++ size and raise them properly
* Fbank support
* Improved rescoring accuracy

## 0.3.32.2
* Get the last MAC OS X version of the native libraries

## 0.3.45.1
* Use Netbeans 12.5 for the development
* Use the 0.3.45 version of the vosk project:
** Small updates to model loading
** Fixes in lattice construction
** Transcriber with a server
** Transcriber tool and model autodownload API
** Add support for incremental decoding and partial lattices
* Add the vosk_recognizer_set_partial_words and vosk_recognizer_set_grm methods in the Java Vosk interface
* Use JNA 5.13.0
