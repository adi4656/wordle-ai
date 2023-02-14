package word;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Word {
  public static final int LENGTH = 5;
  private final String word;
  private final Map<Character, Map<Integer, Boolean>> charsToPositions;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Word word1 = (Word) o;
    return word.equals(word1.word);
  }

  @Override
  public int hashCode() {
    return Objects.hash(word);
  }

  public Word(String word) {
    this.word = word.toUpperCase();

    charsToPositions = new HashMap<>();
    for(int i = 0; i < Word.LENGTH; i++) {
      Character correctCharAtI = this.charAt(i);
      if(charsToPositions.containsKey(correctCharAtI)) {
        Map<Integer, Boolean> positions = charsToPositions.get(correctCharAtI);
        positions.put(i, true);
        charsToPositions.put(correctCharAtI, positions);
      }
      else {
        charsToPositions.put(correctCharAtI, new HashMap<>(Map.of(i, true)));
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
