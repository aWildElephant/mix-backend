package fr.awildelephant.mix.emulator.word;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TwoBytesSignedTest {

    @ParameterizedTest
    @MethodSource("addScenarios")
    void add(int left, int right, int expected) {
        TwoBytesSigned a = TwoBytesSigned.fromInt(left);
        TwoBytesSigned b = TwoBytesSigned.fromInt(right);
        assertThat(a.add(b)).isEqualTo(TwoBytesSigned.fromInt(expected));
    }

    private static Stream<Arguments> addScenarios() {
        return Stream.of(
                Arguments.of(1, 1, 2),
                Arguments.of(1, -1, 0),
                Arguments.of(2, 2, 4),
                Arguments.of(63, 2, 65),
                Arguments.of(10, -11, -1)
        );
    }

    @Test
    void sign_should_set_the_sign_to_true() {
        final TwoBytesSigned value = new TwoBytesSigned();

        value.sign(true);

        assertThat(value.sign()).isTrue();
    }

    @Test
    void sign_should_set_the_sign_to_false() {
        final TwoBytesSigned value = new TwoBytesSigned();

        value.sign(false);

        assertThat(value.sign()).isFalse();
    }

    @Test
    void b1_should_set_the_first_byte() {
        final TwoBytesSigned value = new TwoBytesSigned();

        value.b1(32);

        assertThat(value.b1()).isEqualTo(32);
    }

    @Test
    void b2_should_set_the_second_byte() {
        final TwoBytesSigned value = new TwoBytesSigned();

        value.b1(12);

        assertThat(value.b1()).isEqualTo(12);
    }

    @Test
    void fromInt_and_toInt_should_keep_1() {
        assertThat(TwoBytesSigned.fromInt(1).toInt()).isEqualTo(1);
    }

    @Test
    void fromInt_and_toInt_should_keep_minus_238() {
        assertThat(TwoBytesSigned.fromInt(-238).toInt()).isEqualTo(-238);
    }
}