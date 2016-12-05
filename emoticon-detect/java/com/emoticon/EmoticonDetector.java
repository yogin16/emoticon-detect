package com.emoticon;

import java.util.*;

/**
 * Date: 05/12/16
 * Time: 12:07 AM
 *
 * @author yogin
 */
public class EmoticonDetector {
    private static final Character DUMMY_CHAR = '\u2063';
    private static final String LABEL_FOR_DUMMY_STATE = "DummyState";

    private final Set<String> emoticons;

    private Map<String, State> stateByLabel;
    private Set<Character> characters;

    public EmoticonDetector(Set<String> emoticons) {
        this.emoticons = emoticons;
        init();
    }

    public Set<Emoticon> detect(String text) {
        if (text == null) {
            return Collections.emptySet();
        }
        if (this.characters == null || this.stateByLabel == null) {
            throw new IllegalStateException("Un-Initialized state machine");
        }

        Set<Emoticon> emoticons = new HashSet<>();

        State currentState;
        State previousState = stateByLabel.get(LABEL_FOR_DUMMY_STATE);
        char[] textChars = text.toCharArray();
        for (int i = 0; i < textChars.length; i++) {
            char inputChar = textChars[i];
            if (!this.characters.contains(inputChar)) {
                inputChar = DUMMY_CHAR;
            }
            String currentStateLabel = previousState.getTransitionMap().get(inputChar);
            currentState = this.stateByLabel.get(currentStateLabel);
            if (currentState == null) {
                throw new IllegalStateException("Next state undefined");
            }
            String emoticon = currentState.getEmoticon();
            if (emoticon != null) {
                int start = i - (emoticon.length() - 1);
                int end = i;
                emoticons.add(new Emoticon(emoticon, start, end));
            }

            previousState = currentState;
        }
        return emoticons;
    }

    private void init() {
        characters = identifyCharacters();
        Set<String> stateLabels = identifyStates();
        stateByLabel = createStates(stateLabels);
    }

    private Set<Character> identifyCharacters() {
        Set<Character> emoticonCharacters = new HashSet<>();
        for (String emoticon : this.emoticons) {
            char[] charSymbols = emoticon.toCharArray();
            for (char charSymbol : charSymbols) {
                emoticonCharacters.add(charSymbol);
            }
        }
        if (emoticonCharacters.contains(DUMMY_CHAR)) {
            throw new IllegalArgumentException("Dummy character cannot occur in any of the emoticons");
        }
        emoticonCharacters.add(DUMMY_CHAR);
        return emoticonCharacters;
    }

    private Set<String> identifyStates() {
        Set<String> allStateLabels = new HashSet<>();
        for (String emoticon : this.emoticons) {
            allStateLabels.add(emoticon);
            for (int i = emoticon.length() - 1; i > 0; i--) {
                String s = emoticon.substring(0, i);
                allStateLabels.add(s);
            }

        }
        allStateLabels.add(LABEL_FOR_DUMMY_STATE);
        return allStateLabels;
    }

    private Map<String, State> createStates(Set<String> stateLabels) {
        Map<String, State> states = new HashMap<>();
        for (String stateLabel : stateLabels) {
            State state = new State();
            state.setLabel(stateLabel);
            state.setTransitionMap(createTransitionMap(stateLabel, stateLabels));
            state.setEmoticon(applicableEmoticon(stateLabel));
            states.put(stateLabel, state);
        }
        return states;
    }

    private Map<Character, String> createTransitionMap(String label, Set<String> allStateLabels) {
        Map<Character, String> transitionMap = new HashMap<>();
        for (Character character : characters) {
            String nextStateLabel = label + character.toString();
            if (allStateLabels.contains(nextStateLabel)) {
                transitionMap.put(character, nextStateLabel);
            } else {
                String nextState = findNextStateLabel(character, label, allStateLabels);
                transitionMap.put(character, nextState);
            }
        }
        return transitionMap;
    }

    private String applicableEmoticon(String stateLabel) {
        for (String emoticon : this.emoticons) {
            if (stateLabel.equals(emoticon)) {
                return emoticon;
            }
        }
        return null;
    }

    private String findNextStateLabel(Character character, String label, Set<String> allStateLabels) {
        if (character.equals(DUMMY_CHAR)) {
            return LABEL_FOR_DUMMY_STATE;
        }
        label = label + character.toString();
        for (int i = 1; i < label.length(); i++) {
            String nextTransition = label.substring(i, label.length());
            if (allStateLabels.contains(nextTransition)) {
                return nextTransition;
            }
        }
        return LABEL_FOR_DUMMY_STATE;
    }

    private static class State {
        private String label;
        private Map<Character, String> transitionMap;
        private String emoticon;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Map<Character, String> getTransitionMap() {
            return transitionMap;
        }

        public void setTransitionMap(Map<Character, String> transitionMap) {
            this.transitionMap = transitionMap;
        }

        public String getEmoticon() {
            return emoticon;
        }

        public void setEmoticon(String emoticon) {
            this.emoticon = emoticon;
        }
    }
}
