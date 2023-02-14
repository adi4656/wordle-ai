package word;

import static word.Feedback.LetterFeedback.CORRECT_POS;
import static word.Feedback.LetterFeedback.IN_WORD;
import static word.Feedback.LetterFeedback.NOT_IN_WORD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Feedback {

  private final LetterFeedback[] letterFeedbacks;

  public Feedback(Word guess) {
    // IO constructor.
    Scanner inputter = new Scanner(System.in);
    System.out.println("Feedback:");
    String feedback = inputter.nextLine();
    letterFeedbacks = new LetterFeedback[Word.LENGTH];
    for (int i = 0; i < Word.LENGTH; i++) {
      letterFeedbacks[i] = LetterFeedback.ctor(feedback.charAt(i));
    }
  }

  public Feedback(Word guess, Word correct) {
    // Constructor that computes feedback.
    letterFeedbacks = new LetterFeedback[Word.LENGTH];

    // Build map of chars in correct to where they occur
    Map<Character, Map<Integer, Boolean>> correctCharsToTheirPositions = new HashMap<>();
    for(int i = 0; i < Word.LENGTH; i++) {
      Character correctCharAtI = correct.charAt(i);
      if(correctCharsToTheirPositions.containsKey(correctCharAtI)) {
        Map<Integer, Boolean> positions = correctCharsToTheirPositions.get(i);
        positions.put(i, true);
        correctCharsToTheirPositions.put(correctCharAtI, positions);
      }
      else {
        correctCharsToTheirPositions.put(correctCharAtI, Map.of(i, true));
      }
    }

    for(int i = 0; i < Word.LENGTH; i++) {
      Character guessCharAtI = guess.charAt(i);
      if(!correctCharsToTheirPositions.containsKey(guessCharAtI)) {
        letterFeedbacks[i] = NOT_IN_WORD;
      } else if (!correctCharsToTheirPositions.get(guessCharAtI).get(i)) {
        letterFeedbacks[i] = IN_WORD;
      }
      else {
        letterFeedbacks[i] = CORRECT_POS;
      }
    }
  }

  public boolean all_correct() {
    for(LetterFeedback letterFeedback : letterFeedbacks) {
      if(!letterFeedback.equals(CORRECT_POS)) {
        return false;
      }
    }
    return true;
  }

  enum LetterFeedback {
    NOT_IN_WORD,
    IN_WORD,
    CORRECT_POS;

    private static LetterFeedback ctor(Character c) {
      switch (c.toString().toUpperCase().charAt(0)) {
        case 'B' -> {
          return NOT_IN_WORD;
        }
        case 'Y' -> {
          return IN_WORD;
        }
        case 'G' -> {
          return CORRECT_POS;
        }
        default -> {
          throw new RuntimeException("Invalid feedback!");
        }
      }
    }
    }
}