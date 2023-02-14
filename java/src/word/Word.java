package word;

import java.util.HashMap;
import java.util.Map;

public class Word {
  public static final int LENGTH = 5;
  private final String word;
  public Word(String word) {
    this.word = word.toUpperCase();
  }

  @Override
  public String toString() {
    return word;
  }

  Character charAt(int i) {
    return word.charAt(i);
  }

  Map<Character, Map<Integer, Boolean>> mapCharsToTheirPositions() {
    Map<Character, Map<Integer, Boolean>> correctCharsToTheirPositions = new HashMap<>();
    for(int i = 0; i < Word.LENGTH; i++) {
      Character correctCharAtI = this.charAt(i);
      if(correctCharsToTheirPositions.containsKey(correctCharAtI)) {
        Map<Integer, Boolean> positions = correctCharsToTheirPositions.get(i);
        positions.put(i, true);
        correctCharsToTheirPositions.put(correctCharAtI, positions);
      }
      else {
        correctCharsToTheirPositions.put(correctCharAtI, Map.of(i, true));
      }
    }
    return correctCharsToTheirPositions;
  }
}
