package guesser;

import ai.Word;
import java.util.List;
import java.util.Optional;
import javax.swing.AbstractAction;

public abstract class AbstractGuesser {
    protected final List<Word> allWords;
    protected final List<Word> candidates;

    protected AbstractGuesser(List<Word> allWords, List<Word> candidates) {
        this.allWords = allWords;
        this.candidates = candidates;
    }
    public abstract Optional<Word> guess();

    protected void removeInvalidWord(Word invalid) {
        allWords.remove(invalid);
        candidates.remove(invalid);
    }
}
