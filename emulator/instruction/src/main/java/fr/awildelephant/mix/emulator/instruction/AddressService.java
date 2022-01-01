package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.ByteHelper;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

public final class AddressService {

    public Address toAddress(int value) {
        final byte sign = value >= 0 ? ByteHelper.b0 : ByteHelper.b1;
        final int valueAbs = Math.abs(value);
        final byte secondByte = extractByte(valueAbs);
        final byte firstByte = extractByte(valueAbs >>> 8);

        return new Address(new TwoBytesSigned(sign, firstByte, secondByte));
    }

    private byte extractByte(int value) {
        return (byte) (value % 256 - 128);
    }

    public int toInteger(Address address) {
        final TwoBytesSigned value = address.value();
        int result = value.b1() + 128;
        result *= 256;
        result += value.b2() + 128;

        if (value.sign() % 2 == 1) {
            result = -result;
        }

        return result;
    }
}
