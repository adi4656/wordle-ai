package ai;

import guesser.AbstractGuesser;
import guesser.IOGuesser;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import word.CandidateWordList;
import word.ImmutableWordList;
import word.Word;

public class WordleAI {
  private static final java.nio.file.Path WORDLISTPATH = Paths.get("../../wordlist.txt");

  public static void main(String[] args) {
    ImmutableWordList all_words = new ImmutableWordList();
    CandidateWordList candidates = new CandidateWordList(all_words);
    List<Word> guesses = new ArrayList<>();

    while(true) {
      System.out.println("Number of candidates is " + candidates.size());
      AbstractGuesser guesser = new IOGuesser(all_words.words(), candidates.words());
      Optional<Word> guess = guesser.guess();
      if(guess.isEmpty()) {
        System.out.println("No candidates left!");
        System.exit(0);
      }
      Word concreteGuess = guess.get();
      guesses.add(concreteGuess);
    }
  }

}