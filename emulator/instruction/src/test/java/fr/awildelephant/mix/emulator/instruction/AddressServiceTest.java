package fr.awildelephant.mix.emulator.instruction;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AddressServiceTest {

    private final AddressService addressService = new AddressService();

    @Test
    void it_should_transform_0() {
        assertValidTransformation(0);
    }

    @Test
    void it_should_transform_2000() {
        assertValidTransformation(2000);
    }

    @Test
    void it_should_transform_minus_2000() {
        assertValidTransformation(-2000);
    }

    private void assertValidTransformation(int value) {
        assertThat(addressService.toInteger(addressService.toAddress(value))).isEqualTo(value);
    }
}