package word;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Word {
  public static final int LENGTH = 5;
  private final String word;
  private final Map<Character, Map<Integer, Boolean>> charsToPositions;
  public Word(String word) {
    this.word = word.toUpperCase();

    charsToPositions = new HashMap<>();
    for(int i = 0; i < Word.LENGTH; i++) {
      Character correctCharAtI = this.charAt(i);
      if(charsToPositions.containsKey(correctCharAtI)) {
        Map<Integer, Boolean> positions = charsToPositions.get(i);
        positions.put(i, true);
        charsToPositions.put(correctCharAtI, positions);
      }
      else {
        charsToPositions.put(correctCharAtI, Map.of(i, true));
      }
    }
  }

  @Override
  public String toString() {
    return word;
  }

  Character charAt(int i) {
    return word.charAt(i);
  }

  Map<Character, Map<Integer, Boolean>> mapCharsToTheirPositions() {
    return Collections.unmodifiableMap(charsToPositions);
  }

  boolean containsChar(char c) {
    return charsToPositions.containsKey(c);
  }
}
