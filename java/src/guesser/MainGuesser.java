package guesser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import word.CandidateWordList;
import word.Feedback;
import word.ImmutableWordList;
import word.Word;
import word.WordList;

public class MainGuesser extends AbstractGuesser {
  private static final int MAX_NUM_CANDIDATES = 1000;

  MainGuesser(ImmutableWordList allWords, CandidateWordList candidates) {
    super(allWords, candidates);
  }

  @Override
  public Optional<Word> guess() {
    WordList allowedGuesses = candidates;
    Map<ImmutablePair<Word, Feedback>, Double> guessFeedbackToGain = buildGuessFeedbackToGain(allowedGuesses);

    Optional<Word> finalGuess = Optional.empty();
    double highestExpectedGain = 0;
    for(Word possibleGuess : allowedGuesses) {
      double total = 0;
      for(Word candidate : candidates) {
        total += guessFeedbackToGain.get(new ImmutablePair<>(possibleGuess, new Feedback(possibleGuess, candidate)));
      }
      double gain = total / candidates.size();
      if(gain > highestExpectedGain) {
        highestExpectedGain = gain;
        finalGuess = Optional.of(possibleGuess);
      }
    }

    return finalGuess;
  }

  private Map<ImmutablePair<Word, Feedback>, Double> buildGuessFeedbackToGain(WordList allowedGuesses) {
    Map<ImmutablePair<Word, Feedback>, Double> guessFeedbackToGain = new HashMap<>();
    for(Word guess : allowedGuesses) {
      for(Feedback feedback : Feedback.allPossibleFeedbacks()) {
        ImmutablePair<Word, Feedback> key = new ImmutablePair<>(guess, feedback);
        guessFeedbackToGain.put(key, (double) 0);
        for(Word candidate : candidates) {
          int gain = feedback.eliminated(guess, candidate) ? 1 : 0;
          guessFeedbackToGain.put(key, guessFeedbackToGain.get(key) + gain);
        }
      }
    }
    return guessFeedbackToGain;
  }

  boolean willTakeTooLongToGuess() { return candidates.size() > MAX_NUM_CANDIDATES; }
}
