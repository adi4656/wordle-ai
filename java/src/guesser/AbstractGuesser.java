package guesser;

import java.util.Set;
import word.CandidateWordList;
import word.ImmutableWordList;
import word.Word;
import java.util.List;
import java.util.Optional;

public abstract class AbstractGuesser {
    protected final ImmutableWordList allWords;
    protected final CandidateWordList candidates;

    protected AbstractGuesser(ImmutableWordList allWords, CandidateWordList candidates) {
        this.allWords = allWords;
        this.candidates = candidates;
    }
    public abstract Optional<Word> guess();

    protected void removeInvalidWord(Word invalid) {
        allWords.removeInvalidWord(invalid);
        candidates.removeInvalidWord(invalid);
    }
}
