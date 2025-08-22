package fr.awildelephant.mix.emulator.engine.testutil;

import fr.awildelephant.mix.emulator.word.Word;

public class CommonWords {

    private static final Word MINUS_ZERO = Word.from(false, 0, 0, 0, 0, 0);

    public static Word minusZero() {
        return new Word(MINUS_ZERO);
    }
}
