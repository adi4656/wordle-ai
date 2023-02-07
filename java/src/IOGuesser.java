import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class IOGuesser extends AbstractGuesser {
  private final FastGuesser fastGuesser;
  private final MainGuesser mainGuesser;

  public IOGuesser(List<Word> allWords, List<Word> candidates) {
    super(allWords, candidates);
    fastGuesser = new FastGuesser(allWords, candidates);
    mainGuesser = new MainGuesser(allWords, candidates);
  }

  @Override
  Optional<Word> guess() {
    AbstractGuesser guesser = mainGuesser.willTakeTooLongToGuess() ? fastGuesser : mainGuesser;
    while(true) {
      Scanner inputter = new Scanner(System.in);
      Optional<Word> guess = guesser.guess();
      if(guess.isEmpty()) { return guess; }
      System.out.println("I guess " + guess.get());
      System.out.print("Accepted? (Y or N)");
      String validity = inputter.nextLine();
      if(validity.equals("N")) {
        guesser.removeInvalidWord(guess.get());
      }
      else {
        return guess;
      }
    }
  }
}
