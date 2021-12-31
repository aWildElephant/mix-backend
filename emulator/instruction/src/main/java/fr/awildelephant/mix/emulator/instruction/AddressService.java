package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.ByteHelper;

public final class AddressService {

    public Address toAddress(int value) {
        final byte sign = value >= 0 ? ByteHelper.b0 : ByteHelper.b1;
        final int valueAbs = Math.abs(value);
        final byte secondByte = extractByte(valueAbs);
        final byte firstByte = extractByte(valueAbs >>> 8);

        return new Address(sign, firstByte, secondByte);
    }

    private byte extractByte(int value) {
        return (byte) (value % 256 - 128);
    }

    public int toInteger(Address address) {
        int result = address.getB1() + 128;
        result *= 256;
        result += address.getB2() + 128;

        if (address.getSign() % 2 == 1) {
            result = -result;
        }

        return result;
    }
}
