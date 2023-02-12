package word;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImmutableWordList extends WordList {
  private final Set<Word> wordSet;
  private static final java.nio.file.Path WORDLISTPATH = Paths.get("../wordlist.txt");

  public ImmutableWordList() {
    wordSet = new HashSet<>();
    List<String> lines = null;
    try {
      lines = Files.readAllLines(WORDLISTPATH);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    for(String line : lines) {
      if(line.length() == Word.LENGTH + 1) { // file will have space at the end of word
        wordSet.add(new Word(line.trim()));
      }
    }
  }

  public void removeInvalidWord(Word invalid) {
    wordSet.remove(invalid);
  }

  @Override
  public Set<Word> words() {
    return new HashSet<>(wordSet);
  }
}
