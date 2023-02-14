package guesser;

import java.util.Optional;
import word.CandidateWordList;
import word.ImmutableWordList;
import word.Word;

public class HardCodedGuesser extends AbstractGuesser {

  protected HardCodedGuesser(ImmutableWordList allWords, CandidateWordList candidates) {
    super(allWords, candidates);
  }

  @Override
  public Optional<Word> guess() {
    return Optional.of(new Word("TARES"));
  }
}
