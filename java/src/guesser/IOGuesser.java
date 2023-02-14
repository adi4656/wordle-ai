package guesser;

import java.util.Set;
import word.CandidateWordList;
import word.ImmutableWordList;
import word.Word;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class IOGuesser extends AbstractGuesser {
  private final FastGuesser fastGuesser;
  private final MainGuesser mainGuesser;
  private final HardCodedGuesser hardCodedGuesser;

  public IOGuesser(ImmutableWordList allWords, CandidateWordList candidates) {
    super(allWords, candidates);
    fastGuesser = new FastGuesser(allWords, candidates);
    mainGuesser = new MainGuesser(allWords, candidates);
    hardCodedGuesser = new HardCodedGuesser(allWords, candidates);
  }

  @Override
  public Optional<Word> guess() {
    AbstractGuesser guesser = (candidates.size() == allWords.size()) ? hardCodedGuesser
                                                                    : mainGuesser.willTakeTooLongToGuess() ? fastGuesser : mainGuesser;
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
