package guesser;

import java.util.List;
import java.util.Optional;
import word.Word;

public class MainGuesser extends AbstractGuesser {
  private static final int MAX_NUM_CANDIDATES = 1000;

  MainGuesser(List<Word> allWords, List<Word> candidates) {
    super(allWords, candidates);
  }

  @Override
  public Optional<Word> guess() {
    return Optional.empty();
  }

  boolean willTakeTooLongToGuess() { return candidates.size() > MAX_NUM_CANDIDATES; }
}
