package word;

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
}
