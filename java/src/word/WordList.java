package word;

import java.util.Iterator;
import java.util.Set;

public abstract class WordList {
  public abstract Set<Word> words();

  public int size() {
    return words().size();
  }
}
