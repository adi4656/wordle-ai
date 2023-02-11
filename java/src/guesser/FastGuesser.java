package guesser;

import java.util.Set;
import word.Word;
import java.util.List;
import java.util.Optional;

public class FastGuesser extends AbstractGuesser {

  FastGuesser(Set<Word> allWords, Set<Word> candidates) {
    super(allWords, candidates);
  }

  @Override
  public Optional<Word> guess() {
    return Optional.empty();
  }
}
