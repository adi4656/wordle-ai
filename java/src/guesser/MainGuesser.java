package guesser;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import word.CandidateWordList;
import word.ImmutableWordList;
import word.Word;

public class MainGuesser extends AbstractGuesser {
  private static final int MAX_NUM_CANDIDATES = 1000;

  MainGuesser(ImmutableWordList allWords, CandidateWordList candidates) {
    super(allWords, candidates);
  }

  @Override
  public Optional<Word> guess() {
    return Optional.empty();
  }

  boolean willTakeTooLongToGuess() { return candidates.size() > MAX_NUM_CANDIDATES; }
}
