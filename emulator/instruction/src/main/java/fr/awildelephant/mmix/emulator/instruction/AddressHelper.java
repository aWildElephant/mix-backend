package fr.awildelephant.mmix.emulator.instruction;

public final class AddressHelper {

    private AddressHelper() {

    }

    public static Address toAddress(int value) {
        final byte byteSign = value >= 0 ? ByteHelper.b1 : ByteHelper.b0;
        final int valueAbs = Math.abs(value);
        final byte secondByte = extractByte(valueAbs);
        final byte firstByte = extractByte(valueAbs >>> 8);

        return new Address(new byte[] { byteSign, firstByte, secondByte });
    }

    private static byte extractByte(int value) {
        return (byte) (value % 256 - 128);
    }

    public static int toInteger(Address address) {
        final byte[] bytes = address.getValue();
        if (bytes.length < 2) {
            return 0;
        } else {
            int result = bytes[1] + 128;
            if (bytes.length > 2) {
                result *= 256;
                result += bytes[2] + 128;
            }

            if (bytes[0] == ByteHelper.b0) {
                result = -result;
            }

            return result;
        }
    }
}
