package fr.awildelephant.mix.emulator.word;

public final class WordHelper {

    /**
     * 000000 000000 000000 000000 111111
     */
    private static final int byteMask = 63;

    private WordHelper() {

    }

    /**
     * Create a word from an integer value. Zero always has positive sign.
     */
    public static Word toWord(int value) {
        if (value > Word.MAX_VALUE || value < Word.MIN_VALUE) {
            throw new IllegalArgumentException();
        }

        final boolean sign = value >= 0;

        final int absValue = Math.abs(value);

        return Word.from(
                sign,
                (absValue >> 24) & byteMask,
                (absValue >> 18) & byteMask,
                (absValue >> 12) & byteMask,
                (absValue >> 6) & byteMask,
                absValue & byteMask
        );
    }

    public static int toInt(Word word) {
        final int absResult = (word.b1() << 24) + (word.b2() << 18) + (word.b3() << 12) + (word.b4() << 6) + word.b5();
        return word.sign() ? absResult : -absResult;
    }
}
