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

    /**
     * Create a 10-byte number from a long value. Zero always has positive sign.
     */
    public static TenBytesSigned toWord(long value) {
        if (value > TenBytesSigned.MAX_VALUE || value < TenBytesSigned.MIN_VALUE) {
            throw new IllegalArgumentException();
        }

        final boolean sign = value >= 0;

        final long absValue = Math.abs(value);

        return TenBytesSigned.from(
                sign,
                (int) (absValue >> 54) & byteMask,
                (int) (absValue >> 48) & byteMask,
                (int) (absValue >> 42) & byteMask,
                (int) (absValue >> 36) & byteMask,
                (int) (absValue >> 30) & byteMask,
                (int) (absValue >> 24) & byteMask,
                (int) (absValue >> 18) & byteMask,
                (int) (absValue >> 12) & byteMask,
                (int) (absValue >> 6) & byteMask,
                (int) absValue & byteMask
        );
    }

    public static long toLong(TenBytesSigned word) {
        final long absResult = ((long) word.getByte(0) << 54)
                + ((long) word.getByte(1) << 48)
                + ((long) word.getByte(2) << 42)
                + ((long) word.getByte(3) << 36)
                + ((long) word.getByte(4) << 30)
                + ((long) word.getByte(5) << 24)
                + ((long) word.getByte(6) << 18)
                + ((long) word.getByte(7) << 12)
                + ((long) word.getByte(8) << 6)
                + word.getByte(9);
        return word.sign() ? absResult : -absResult;
    }
}
