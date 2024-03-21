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

    private static ComputationResult<Word> handleOverflow(int substraction) {
        if (substraction < Word.MIN_VALUE) {
            final int remainder = Word.MAX_VALUE + substraction - Word.MIN_VALUE + 1;
            return new ComputationResult<>(WordHelper.toWord(remainder), true);
        } else if (substraction > Word.MAX_VALUE) {
            final int remainder = Word.MIN_VALUE + substraction - Word.MAX_VALUE - 1;
            return new ComputationResult<>(WordHelper.toWord(remainder), true);
        } else {
            return new ComputationResult<>(WordHelper.toWord(substraction), false);
        }
    }
}
