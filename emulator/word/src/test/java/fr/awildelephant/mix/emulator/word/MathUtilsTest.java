package fr.awildelephant.mix.emulator.word;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MathUtilsTest {

    public static Stream<Arguments> addScenarios() {
        return Stream.of(
                Arguments.of(WordHelper.toWord(0), WordHelper.toWord(0), WordHelper.toWord(0), false),
                Arguments.of(WordHelper.toWord(1), WordHelper.toWord(1), WordHelper.toWord(2), false),
                Arguments.of(WordHelper.toWord(1), WordHelper.toWord(-1), WordHelper.toWord(0), false),
                Arguments.of(WordHelper.toWord(Word.MAX_VALUE), WordHelper.toWord(1), WordHelper.toWord(Word.MIN_VALUE), true),
                Arguments.of(WordHelper.toWord(Word.MIN_VALUE), WordHelper.toWord(-1), WordHelper.toWord(Word.MAX_VALUE), true)
        );
    }

    @ParameterizedTest
    @MethodSource("addScenarios")
    void add_should_behave_properly(Word leftInput, Word rightInput, Word result, boolean isOverflow) {
        assertThat(MathUtils.add(leftInput, rightInput)).isEqualTo(new ComputationResult<>(result, isOverflow));
    }
}