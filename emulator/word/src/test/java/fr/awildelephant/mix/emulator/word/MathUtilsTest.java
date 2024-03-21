package fr.awildelephant.mix.emulator.word;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static fr.awildelephant.mix.emulator.word.WordHelper.toWord;
import static org.assertj.core.api.Assertions.assertThat;

class MathUtilsTest {

    public static Stream<Arguments> addScenarios() {
        return Stream.of(
                Arguments.of(toWord(0), toWord(0), toWord(0), false),
                Arguments.of(toWord(1), toWord(1), toWord(2), false),
                Arguments.of(toWord(1), toWord(-1), toWord(0), false),
                Arguments.of(toWord(Word.MAX_VALUE), toWord(1), toWord(Word.MIN_VALUE), true),
                Arguments.of(toWord(Word.MIN_VALUE), toWord(-1), toWord(Word.MAX_VALUE), true)
        );
    }

    @ParameterizedTest
    @MethodSource("addScenarios")
    void add_should_behave_properly(Word leftInput, Word rightInput, Word result, boolean isOverflow) {
        assertThat(MathUtils.add(leftInput, rightInput)).isEqualTo(new ComputationResult<>(result, isOverflow));
    }

    public static Stream<Arguments> subtractScenarios() {
        return Stream.of(
                Arguments.of(toWord(1), toWord(1), toWord(0), false)
        );
    }

    @ParameterizedTest
    @MethodSource("subtractScenarios")
    void subtract_should_behave_properly(Word leftInput, Word rightInput, Word result, boolean isOverflow) {
        assertThat(MathUtils.subtract(leftInput, rightInput)).isEqualTo(new ComputationResult<>(result, isOverflow));
    }
}