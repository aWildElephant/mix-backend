package fr.awildelephant.mix.emulator.word;

public final class MathUtils {

    private MathUtils() {

    }

    public static ComputationResult<Word> add(Word first, Word second) {
        final int addition = WordHelper.toInt(first) + WordHelper.toInt(second);

        return handleOverflow(addition);
    }

    public static ComputationResult<Word> subtract(Word first, Word second) {
        final int subtraction = WordHelper.toInt(first) - WordHelper.toInt(second);

        return handleOverflow(subtraction);
    }

    private static ComputationResult<Word> handleOverflow(int value) {
        if (value < Word.MIN_VALUE) {
            final int remainder = Word.MAX_VALUE + value - Word.MIN_VALUE + 1;
            return new ComputationResult<>(WordHelper.toWord(remainder), true);
        } else if (value > Word.MAX_VALUE) {
            final int remainder = Word.MIN_VALUE + value - Word.MAX_VALUE - 1;
            return new ComputationResult<>(WordHelper.toWord(remainder), true);
        } else {
            return new ComputationResult<>(WordHelper.toWord(value), false);
        }
    }

    public static ComputationResult<TenBytesSigned> multiply(Word first, Word second) {
        final long multiplication = (long) WordHelper.toInt(first) * WordHelper.toInt(second);

        return handleOverflow(multiplication);
    }

    private static ComputationResult<TenBytesSigned> handleOverflow(long value) {
        if (value < TenBytesSigned.MIN_VALUE) {
            final long remainder = TenBytesSigned.MAX_VALUE + value - TenBytesSigned.MIN_VALUE + 1;
            return new ComputationResult<>(WordHelper.toWord(remainder), true);
        } else if (value > TenBytesSigned.MAX_VALUE) {
            final long remainder = TenBytesSigned.MIN_VALUE + value - TenBytesSigned.MAX_VALUE - 1;
            return new ComputationResult<>(WordHelper.toWord(remainder), true);
        } else {
            return new ComputationResult<>(WordHelper.toWord(value), false);
        }
    }
}
