package top;

import guesser.AbstractGuesser;
import guesser.IOGuesser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import word.CandidateWordList;
import word.Feedback;
import word.ImmutableWordList;
import word.Word;

public class WordleAI {

  public static void main(String[] args) {
    ImmutableWordList all_words = new ImmutableWordList();
    CandidateWordList candidates = new CandidateWordList(all_words);
    List<Word> guesses = new ArrayList<>();

    while(true) {
      System.out.println("Number of candidates is " + candidates.size());

      AbstractGuesser guesser = new IOGuesser(all_words, candidates);
      Optional<Word> guess = guesser.guess();

      if(guess.isEmpty()) {
        System.out.println("No candidates left!");
        System.exit(0);
      }
      Word concreteGuess = guess.get();
      guesses.add(concreteGuess);

      Feedback feedback = new Feedback(concreteGuess);
      if(feedback.all_correct()) {
        System.out.println("Number of guesses: " + guesses.size());
        System.out.println("Guesses were: " + Arrays.toString(guesses.toArray()));
        return;
      }
      candidates.process_feedback(feedback, concreteGuess);
    }
  }

}