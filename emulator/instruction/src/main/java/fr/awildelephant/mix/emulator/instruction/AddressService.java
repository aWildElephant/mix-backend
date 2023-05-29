package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

public final class AddressService {

    public Address toAddress(int value) {
        return new Address(TwoBytesSigned.fromInt(value));
    }

    public int toInteger(Address address) {
        return address.value().toInt();
    }
}
