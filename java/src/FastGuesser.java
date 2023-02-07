import java.util.List;
import java.util.Optional;

public class FastGuesser extends AbstractGuesser {

  public FastGuesser(List<Word> allWords, List<Word> candidates) {
    super(allWords, candidates);
  }

  @Override
  Optional<Word> guess() {
    return Optional.empty();
  }
}
