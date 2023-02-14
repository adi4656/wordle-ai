package word;

import static word.Feedback.LetterFeedback.CORRECT_POS;
import static word.Feedback.LetterFeedback.IN_WORD;
import static word.Feedback.LetterFeedback.NOT_IN_WORD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Feedback {

  private final LetterFeedback[] letterFeedbacks;

  public static List<Feedback> allPossibleFeedbacks() {
    List<Feedback> feedbacks = new ArrayList<>();
    int numLetterFeedbacks = LetterFeedback.values().length;
    for(int i = 0; i < Math.pow(numLetterFeedbacks, Word.LENGTH); i++) {
      String base3 = toBase3(i);
      assert base3.length() == Word.LENGTH;
      List<LetterFeedback> letterFeedbacks = new ArrayList<>();
      for(int j = 0; j < Word.LENGTH; j++) {
        int base3Val = Integer.parseInt(String.valueOf(base3.charAt(j)));
        letterFeedbacks.add(LetterFeedback.values()[base3Val]);
      }
      feedbacks.add(new Feedback(letterFeedbacks));
    }
    return feedbacks;
  }

  private static String toBase3(Integer decimal) {
    String unpadded = Integer.toString(decimal, 3);
    if(unpadded.length() < Word.LENGTH) {
      unpadded = "0".repeat(Word.LENGTH - unpadded.length()) + unpadded;
    }
    return unpadded;
  }

  private Feedback(List<LetterFeedback> letterFeedbacks) {
    this.letterFeedbacks = new LetterFeedback[Word.LENGTH];
    for (int i = 0; i < Word.LENGTH; i++) {
      this.letterFeedbacks[i] = letterFeedbacks.get(i);
    }
  }

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
    Map<Character, Map<Integer, Boolean>> correctCharsToTheirPositions = correct.mapCharsToTheirPositions();

    for(int i = 0; i < Word.LENGTH; i++) {
      Character guessCharAtI = guess.charAt(i);
      if(!correct.containsChar(guessCharAtI)) {
        letterFeedbacks[i] = NOT_IN_WORD;
      } else if (!correctCharsToTheirPositions.get(guessCharAtI).containsKey(i)) {
        letterFeedbacks[i] = IN_WORD;
      }
      else {
        letterFeedbacks[i] = CORRECT_POS;
      }
    }
  }

  public boolean eliminated(Word guess, Word candidate) {
    for(int i = 0; i < Word.LENGTH; i++) {
      Character guessCharAtI = guess.charAt(i);
      Character candidateCharAtI = candidate.charAt(i);
      if(letterFeedbacks[i] == CORRECT_POS && candidateCharAtI != guessCharAtI) {
        return true;
      } else if (letterFeedbacks[i] == NOT_IN_WORD && candidate.containsChar(guessCharAtI)) {
        return true;
      } else if (letterFeedbacks[i] == IN_WORD) {
        if (!candidate.containsChar(guessCharAtI)) {
          return true;
        }
        if(candidateCharAtI == guessCharAtI) {
          return true;
        }
      }
    }

    return false;
  }

  public boolean all_correct() {
    for(LetterFeedback letterFeedback : letterFeedbacks) {
      if(!letterFeedback.equals(CORRECT_POS)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    return "Feedback{" +
        "letterFeedbacks=" + Arrays.toString(letterFeedbacks) +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Feedback feedback = (Feedback) o;
    return Arrays.equals(letterFeedbacks, feedback.letterFeedbacks);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(letterFeedbacks);
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