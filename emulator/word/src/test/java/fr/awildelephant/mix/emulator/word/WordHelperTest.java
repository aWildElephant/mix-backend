package fr.awildelephant.mix.emulator.word;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static fr.awildelephant.mix.emulator.word.WordHelper.toInt;
import static fr.awildelephant.mix.emulator.word.WordHelper.toLong;
import static fr.awildelephant.mix.emulator.word.WordHelper.toWord;
import static org.assertj.core.api.Assertions.assertThat;

class WordHelperTest {


    @ParameterizedTest
    @MethodSource("intConversionScenarios")
    void toWord_then_toInt_is_identity(int value) {
        final Word word = toWord(value);
        final int intFromWord = toInt(word);
        assertThat(intFromWord).as(value + " was converted to " + word + " then back to " + intFromWord).isEqualTo(value);
    }

    public static Stream<Arguments> intConversionScenarios() {
        return Stream.of(
                Arguments.of(Word.MIN_VALUE),
                Arguments.of(-16_777_216),
                Arguments.of(-262_144),
                Arguments.of(-4_096),
                Arguments.of(-64),
                Arguments.of(-1),
                Arguments.of(0),
                Arguments.of(1),
                Arguments.of(64),
                Arguments.of(4_096),
                Arguments.of(262_144),
                Arguments.of(16_777_216),
                Arguments.of(Word.MAX_VALUE)
        );
    }

    @ParameterizedTest
    @MethodSource("longConversionScenarios")
    void toWord_then_toLong_is_identity(long value) {
        final TenBytesSigned word = toWord(value);
        final long longFromWord = toLong(word);
        assertThat(longFromWord).as(value + " was converted to " + word + " then back to " + longFromWord).isEqualTo(value);
    }

    public static Stream<Arguments> longConversionScenarios() {
        return Stream.of(
                Arguments.of(TenBytesSigned.MIN_VALUE),
                Arguments.of(-16_777_216),
                Arguments.of(-262_144),
                Arguments.of(-4_096),
                Arguments.of(-64),
                Arguments.of(-1),
                Arguments.of(0),
                Arguments.of(1),
                Arguments.of(64),
                Arguments.of(4_096),
                Arguments.of(262_144),
                Arguments.of(16_777_216),
                Arguments.of(TenBytesSigned.MAX_VALUE)
        );
    }
}