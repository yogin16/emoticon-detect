package com.emoticon;


import java.util.HashSet;
import java.util.Set;

/**
 * Date: 05/12/16
 * Time: 12:33 AM
 *
 * @author yogin
 */
public class EmoticonDetectorTest {

    public static void main(String[] args) {
        EmoticonDetector detector = new EmoticonDetector(getEmojisToDetect());
        Set<Emoticon> detected = detector.detect("Hello World!");
        System.out.println(detected);

        detected = detector.detect("Hello World :)");
        System.out.println(detected);

        detected = detector.detect("Hello World :) :D");
        System.out.println(detected);
    }

    private static Set<String> getEmojisToDetect() {
        Set<String> emoticons = new HashSet<>();
        emoticons.add(":)");
        emoticons.add(":(");
        emoticons.add(":D");
        emoticons.add(":P");
        return emoticons;
    }
}