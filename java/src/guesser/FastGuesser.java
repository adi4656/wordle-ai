package guesser;

import word.Word;
import java.util.List;
import java.util.Optional;

public class FastGuesser extends AbstractGuesser {

  FastGuesser(List<Word> allWords, List<Word> candidates) {
    super(allWords, candidates);
  }

  @Override
  public Optional<Word> guess() {
    return Optional.empty();
  }
}
