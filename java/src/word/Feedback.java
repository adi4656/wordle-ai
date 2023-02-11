package word;

import java.util.Scanner;

public class Feedback {

  private final Word guess;
  private final LetterFeedback[] letterFeedbacks;

  public Feedback(Word guess) {
    this.guess = guess;
    Scanner inputter = new Scanner(System.in);
    System.out.println("Feedback:");
    String feedback = inputter.nextLine();
    this.letterFeedbacks = new LetterFeedback[Word.LENGTH];
    for (int i = 0; i < Word.LENGTH; i++) {
      letterFeedbacks[i] = LetterFeedback.ctor(feedback.charAt(i));
    }
  }

  private enum LetterFeedback {
    NOT_IN_WORD,
    IN_WORD,
    CORRECT_POS;

    private static LetterFeedback ctor(char c) {
      switch (c) {
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