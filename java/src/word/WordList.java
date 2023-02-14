package word;

import java.util.Iterator;
import java.util.Set;

public abstract class WordList implements Iterable<Word> {
  protected abstract Set<Word> words();

  @Override
  public Iterator<Word> iterator() {
    return words().iterator();
  }

  public abstract void removeInvalidWord(Word invalid);

  public int size() {
    return words().size();
  }
}
