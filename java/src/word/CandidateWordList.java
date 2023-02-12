package word;

import java.util.Set;

public class CandidateWordList extends WordList {
  private Set<Word> wordSet;

  public CandidateWordList(ImmutableWordList immutableWordList) {
    wordSet = immutableWordList.words();
  }

  public void removeInvalidWord(Word invalid) {
    wordSet.remove(invalid);
  }

  @Override
  public Set<Word> words() {
    return wordSet;
  }
}
