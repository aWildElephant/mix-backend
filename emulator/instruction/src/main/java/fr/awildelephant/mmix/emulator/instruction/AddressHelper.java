package fr.awildelephant.mmix.emulator.instruction;

import fr.awildelephant.mmix.emulator.word.ByteHelper;

public final class AddressHelper {

    private AddressHelper() {

    }

    public static Address toAddress(int value) {
        final byte sign = value >= 0 ? ByteHelper.b1 : ByteHelper.b0;
        final int valueAbs = Math.abs(value);
        final byte secondByte = extractByte(valueAbs);
        final byte firstByte = extractByte(valueAbs >>> 8);

        return new Address(sign, firstByte, secondByte);
    }

    private static byte extractByte(int value) {
        return (byte) (value % 256 - 128);
    }

    public static int toInteger(Address address) {
        int result = address.getB1() + 128;
        result *= 256;
        result += address.getB2() + 128;

        if (address.getSign() == ByteHelper.b0) {
            result = -result;
        }

        return result;
    }
}
