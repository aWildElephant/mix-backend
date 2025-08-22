package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

public record Address(TwoBytesSigned value) {

    public static Address fromInt(final int value) {
        return new Address(TwoBytesSigned.fromInt(value));
    }

    public int toInt() {
        return value.toInt();
    }
}
