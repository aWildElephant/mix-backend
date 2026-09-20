package fr.awildelephant.mix.emulator.engine.comparison;

import fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

import java.util.Comparator;

import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.State.EQUAL;
import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.State.GREATER;
import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.State.LESS;

public final class ComparisonUtils {

    private static final Comparator<Word> WORD_COMPARATOR = new WordComparator();
    private static final Comparator<TwoBytesSigned> TWO_BYTES_SIGNED_COMPARATOR = new TwoBytesSignedComparator();

    private ComparisonUtils() {

    }

    public static Comparator<Word> getWordComparator() {
        return WORD_COMPARATOR;
    }

    public static Comparator<TwoBytesSigned> getTwoBytesSignedComparator() {
        return TWO_BYTES_SIGNED_COMPARATOR;
    }

    public static ComparisonIndicator.State compare(Word first, Word second) {
        final int result = WORD_COMPARATOR.compare(first, second);
        if (result > 0) {
            return GREATER;
        } else if (result < 0) {
            return LESS;
        } else {
            return EQUAL;
        }
    }
}
