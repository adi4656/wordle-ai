package word;

import java.util.HashSet;
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

  public void process_feedback(Feedback feedback, Word guess) {
    Set<Word> wordSetNew = new HashSet<>();
    for(Word candidate : wordSet) {
      if(!feedback.eliminated(guess, candidate)) {
        wordSetNew.add(candidate);
      }
    }
    this.wordSet = wordSetNew;
  }
}
