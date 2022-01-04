package fr.awildelephant.mix.emulator.word;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TwoBytesSignedServiceTest {

    private final TwoBytesSignedService service = new TwoBytesSignedService();

    @Test
    void it_should_transform_one_to_TwoBytesSigned_and_back() {
        assertThat(service.toInt(service.fromInt(1))).isEqualTo(1);
    }
}