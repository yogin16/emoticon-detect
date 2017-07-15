# emoticon-detect
Emoticon Detector in Java. Given string identifies emojis present best optimum computation. Could be used for any keyword matching.

It would iterate over the complete string only once to figure out all the emojis present in the string.

# usage
```java
Set<String> emojisToDetect = new HashSet<>(); //All the emoticons you want to detect 
EmoticonDetector detector = new EmoticonDetector(emojisToDetect);

Set<Emoticon> result = detector.detect("Hello World :D");
```
# todo
Add default EmoticonDetectorFactory for Pre-constructed EmoticonDetector with list of all commonly used emoticons.
Make this generic as KeywordDetector and extend Emoticon to have any specific handling if needed.
