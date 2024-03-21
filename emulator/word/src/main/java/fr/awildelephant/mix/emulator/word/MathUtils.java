package fr.awildelephant.mix.emulator.word;

public final class MathUtils {

    private MathUtils() {

    }

    public static ComputationResult<Word> add(Word first, Word second) {
        final int addition = WordHelper.toInt(first) + WordHelper.toInt(second);

        if (addition < Word.MIN_VALUE) {
            final int remainder = Word.MAX_VALUE + addition - Word.MIN_VALUE + 1;
            return new ComputationResult<>(WordHelper.toWord(remainder), true);
        } else if (addition > Word.MAX_VALUE) {
            final int remainder = Word.MIN_VALUE + addition - Word.MAX_VALUE - 1;
            return new ComputationResult<>(WordHelper.toWord(remainder), true);
        } else {
            return new ComputationResult<>(WordHelper.toWord(addition), false);
        }
    }
}
