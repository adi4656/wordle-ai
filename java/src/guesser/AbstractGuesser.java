package guesser;

import java.util.Set;
import word.Word;
import java.util.List;
import java.util.Optional;

public abstract class AbstractGuesser {
    protected final Set<Word> allWords;
    protected final Set<Word> candidates;

    protected AbstractGuesser(Set<Word> allWords, Set<Word> candidates) {
        this.allWords = allWords;
        this.candidates = candidates;
    }
    public abstract Optional<Word> guess();

    protected void removeInvalidWord(Word invalid) {
        allWords.remove(invalid);
        candidates.remove(invalid);
    }
}
