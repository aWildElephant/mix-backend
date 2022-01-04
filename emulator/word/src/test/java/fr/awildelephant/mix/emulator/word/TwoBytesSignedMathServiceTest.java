package fr.awildelephant.mix.emulator.word;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TwoBytesSignedMathServiceTest {

    private final TwoBytesSignedService helperService = new TwoBytesSignedService();
    private final TwoBytesSignedMathService service = new TwoBytesSignedMathService(helperService);

    @Test
    void one_plus_one_is_two() {
        assertAdd(1, 1, 2);
    }

    @Test
    void one_plus_minus_one_is_zero() {
        assertAdd(1, -1, 0);
    }

    @Test
    void one_plus_257_is_258() {
        assertAdd(1, 257, 258);
    }

    @Test
    void ten_plus_minus_eleven_is_minus_one() {
        assertAdd(10, -11, -1);
    }

    private void assertAdd(int left, int right, int expected) {
        assertThat(service.add(helperService.fromInt(left), helperService.fromInt(right))).isEqualTo(helperService.fromInt(expected));
    }

    // TODO: overflow of both bytes up
    // TODO: overflow of both bytes down

}