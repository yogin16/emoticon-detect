# emoticon-detect
Emoticon Detector in Java. Given string identifies emojis present best optimum computation. Could be used for any keyword matching.

It would iterate over the complete string only once to figure out all the emojis present in the string.

# Usage
```java
Set<String> emojisToDetect = new HashSet<>(); //All the emoticons you want to detect 
EmoticonDetector detector = new EmoticonDetector(emojisToDetect);

Set<Emoticon> result = detector.detect("Hello World :D");
```
# TODO
Add default EmoticonDetectorFactory for list of all commonly used emoticons.
