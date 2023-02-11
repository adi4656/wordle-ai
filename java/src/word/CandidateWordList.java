package word;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CandidateWordList extends WordList {
  private Set<Word> wordSet;

  public CandidateWordList(ImmtableWordList immtableWordList) {
    wordSet = immtableWordList.words();
  }

  @Override
  public Set<Word> words() {
    return wordSet;
  }
}
