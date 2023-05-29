package fr.awildelephant.mix.emulator.word;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TwoBytesSignedTest {

    // TODO: parameterized tests for add
    @Test
    void add_two_plus_two_is_four() {
        assertAdd(2, 2, 4);
    }

    @Test
    void add_one_plus_one_is_two() {
        assertAdd(1, 1, 2);
    }

    @Test
    void add_one_plus_minus_one_is_zero() {
        assertAdd(1, -1, 0);
    }

    @Test
    void add_one_plus_257_is_258() {
        assertAdd(1, 257, 258);
    }

    @Test
    void add_ten_plus_minus_eleven_is_minus_one() {
        assertAdd(10, -11, -1);
    }

    private void assertAdd(int left, int right, int expected) {
        TwoBytesSigned a = TwoBytesSigned.fromInt(left);
        TwoBytesSigned b = TwoBytesSigned.fromInt(right);
        assertThat(a.add(b)).isEqualTo(TwoBytesSigned.fromInt(expected));
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