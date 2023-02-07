import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WordleAI {
  private static final java.nio.file.Path WORDLISTPATH = Paths.get("../../wordlist.txt");

  public static void main(String[] args) {
    List<Word> all_words = buildWordList();
    List<Word> candidates = new ArrayList<>(all_words);

    while(true) {
      System.out.println("Number of candidates is " + candidates.size());
    }
  }

  private static List<Word> buildWordList() {
    List<Word> wordList = new ArrayList<>();
    List<String> lines = null;
    try {
      lines = Files.readAllLines(WORDLISTPATH);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    for(String line : lines) {
      if(line.length() == Word.LENGTH + 1) { // file will have space at the end of word
        wordList.add(new Word(line.trim()));
      }
    }
    return wordList;
  }
}