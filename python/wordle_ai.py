import numpy, copy, sys
from itertools import product
from collections import defaultdict


LENGTH = 5
GREEN = "G"
YELLOW = "Y"
REJECT = "B"
MAX_ITERS = 15917 ** 2


def process_feedback(words, feedback):
    # Feedback format: feedback = [("t", REJECT), ("a", GREEN), ("p", YELLOW), ("a", YELLOW), ("s", GREEN)]
    feedback_dict = {letter : res for (letter, res) in feedback}
    yellows = [letter for (letter, res) in feedback if res == YELLOW]
    positions = {i : (letter, res) for (i, (letter, res)) in enumerate(feedback)}
    new_words = set()
    for word in words:
        seen = {}
        valid = True
        for position, letter in enumerate(word):
            if letter in feedback_dict and feedback_dict[letter] == REJECT:
                valid = False
                break
            if positions[position][1] == GREEN and letter != positions[position][0]:
                valid = False
                break
            if positions[position][1] == YELLOW and letter == positions[position][0]:
                valid = False
                break
            seen[letter] = True
        for yellow in yellows:
            if yellow not in seen:
                valid = False
                break
        if valid:
            new_words.add(word)
    return new_words


def build_info(words):
    info = {chr(letterASCII) : [0, numpy.array(LENGTH * [0])] for letterASCII in range(65,91)}
    for word in words:
        seen = {}
        for position, letter in enumerate(word):
            info[letter][1][position] += 1
            if letter not in seen:
                info[letter][0] += 1
            seen[letter] = True

    return info


def fast_guess(current_words, WORDS):
    num_words = len(current_words)
    if num_words == 0:
        print("0 words left.")
        sys.exit([0])
    info = build_info(current_words)
    highest_gain = ("", -1)
    packaged_params = (current_words, num_words, info)
    for word in current_words:
        gain = 0
        seen = {}
        for position, letter in enumerate(word):
            gains = gain_for_letter(letter, position, packaged_params)
            if letter not in seen:
                gain += gains[0]
            gain += gains[1]
            seen[letter] = True
        if gain > highest_gain[-1]:
            highest_gain = (word, gain)
    return highest_gain[0]


def guess(current_words, WORDS, use_all_words = False):
    if len(current_words) ** 2 > MAX_ITERS:
        print("using fast_guess")
        return fast_guess(current_words, WORDS)
        
    all_guesses = WORDS if use_all_words else current_words
    feedbacks = product(GREEN + YELLOW + REJECT, repeat = LENGTH)
    guesses_and_feedbacks = [(guess, feedback) for guess in all_guesses for feedback in feedbacks]
    guess_feedback_to_gain = defaultdict(int)
    c = 0
    for (guess, feedback) in guesses_and_feedbacks:
        for correct in current_words:
            if eliminated(guess, feedback, correct):
                guess_feedback_to_gain["".join((guess, "".join(feedback)))] += 1
        c += 1
    elimination_totals = defaultdict(int)
    for guess in all_guesses:
        for candidate in current_words:
            elimination_totals[guess] += guess_feedback_to_gain["".join((guess, compute_feedback(guess, candidate)))]
    if len(elimination_totals) == 0:
        print("0 words left.")
        sys.exit([0])
    return keywithmaxval(elimination_totals)


def compute_feedback(guess, correct):
    correct_info = get_correct_info(correct)
    feedback = ""
    for i, char in enumerate(guess):
        if char not in correct_info:
            feedback += REJECT
        elif i not in correct_info[char]:
            feedback += YELLOW
        else:
            feedback += GREEN
    return feedback

 
def keywithmaxval(d):
     """ a) create a list of the dict's keys and values; 
         b) return the key with the max value"""  
     v=list(d.values())
     k=list(d.keys())
     return k[v.index(max(v))]


def get_correct_info(correct):
    correct_info = {}
    for i, char in enumerate(correct):
        if char in correct_info:
            correct_info[char][i] = True
        else:
            correct_info[char] = {i: True}
    return correct_info


def eliminated(guess, feedback, correct):
    """Note this uses corrected feedback, not game feedback."""
    correct_info = get_correct_info(correct)
    for j in range(LENGTH):
        guess_char = guess[j]
        feedback_char = feedback[j]
        if feedback_char == REJECT and guess_char in correct_info:
            return True
        if feedback_char == GREEN and guess_char not in correct_info:
            return True
        if guess_char not in correct_info or j in correct_info[guess_char]:
            return True

    return False
        

def gain_for_letter(letter, position, packaged_params, toPrint = False):
    words, num_words, info = packaged_params
    word_info, positions_info = info[letter]
    with_anywhere = word_info
    without_anywhere = num_words - with_anywhere
    with_positioned = positions_info[position]
    p_right_position = with_positioned / with_anywhere

    a_gain = 2 * with_anywhere * without_anywhere / (num_words)
    b_gain = (with_anywhere / num_words) * (p_right_position * (with_anywhere - with_positioned) + (1 - p_right_position) * with_positioned)
    if toPrint:
        print((a_gain,b_gain),end=" ")
    return (a_gain, b_gain)

def correct_feedback(feedback):
    """ If a letter is green, but letter somewhere else in the word is a red, turn that red to a yellow. """
    non_rejects = {letter : True for (letter, res) in feedback if res == GREEN or res == YELLOW}
    for position, (letter, res) in enumerate(feedback):
        if res == REJECT and letter in non_rejects:
            feedback[position] = (letter, YELLOW)
    return feedback

def main():
    with open("wordlist.txt") as words_file:
        words = words_file.readlines()
    WORDS = set([word[:-1].upper() for word in words if len(word) == LENGTH + 1])
    if LENGTH == 5 and "ARIES" in WORDS:
        WORDS.remove("ARIES")

    current_words = copy.deepcopy(WORDS)
    guesses = []
    while True:
        print(len(current_words))
        valid_guess = False
        while not valid_guess:
            guesser = fast_guess if len(current_words) > 1000 else guess
            new_guess = guesser(current_words, WORDS) 
            print(f"My guess is {new_guess}")
            validity = input("Accepted?")
            if validity.upper() in ["N", "NO", "F", "FALSE"]:
                WORDS.remove(new_guess)
                if new_guess in current_words:
                    current_words.remove(new_guess)
            else:
                valid_guess = True
        guesses.append(new_guess)
        new_feedback = []
        res = [c.upper() for c in input("Feedback:")]
        if res == 5 * [GREEN]:
            print(f"Words guessed were: {guesses}.")
            print(f"{len(guesses)} guesses.")
            break
        for position, guessed_letter in enumerate(new_guess):
            new_feedback.append((guessed_letter, res[position]))
        new_current_words = process_feedback(current_words, correct_feedback(new_feedback))
        current_words = current_words.intersection(new_current_words)
         
if __name__ == "__main__":             
    main()
