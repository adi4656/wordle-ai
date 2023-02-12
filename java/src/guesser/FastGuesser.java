package guesser;

import java.util.Set;
import word.CandidateWordList;
import word.ImmutableWordList;
import word.Word;
import java.util.List;
import java.util.Optional;

public class FastGuesser extends AbstractGuesser {

  FastGuesser(ImmutableWordList allWords, CandidateWordList candidates) {
    super(allWords, candidates);
  }

  @Override
  public Optional<Word> guess() {
    return Optional.empty();
  }
}
