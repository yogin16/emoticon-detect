# emoticon-detect
Emoticon Detector in Java. Given string identifies emojis present best optimum computation. Could be used for any keyword matching.

It would iterate over the complete string only once to figure out all the emojis present in the string.

# usage
```java
  public static void main(String[] args) {
        EmoticonDetector detector = new EmoticonDetector(getEmojisToDetect());
        Set<Emoticon> detected = detector.detect("Hello World!");
        assert detected.isEmpty();

        detected = detector.detect("Hello World :)");
        assert detected.contains(new Emoticon(":)", 12, 13));
    }

    private static Set<String> getEmojisToDetect() {
        Set<String> emoticons = new HashSet<>();
        emoticons.add(":)");
        emoticons.add(":(");
        emoticons.add(":D");
        emoticons.add(":P");
        return emoticons;
    }
```
# todo
Add default EmoticonDetectorFactory for Pre-constructed EmoticonDetector with list of all commonly used emoticons.

Make this generic as KeywordDetector and extend Emoticon to have any specific handling if needed.
