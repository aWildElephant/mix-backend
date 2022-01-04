package fr.awildelephant.mix.emulator.word;

public final class TwoBytesSignedService {

    public TwoBytesSigned fromInt(int value) {
        final byte sign = value >= 0 ? ByteHelper.b0 : ByteHelper.b1;
        final int valueAbs = Math.abs(value);
        final byte secondByte = extractByte(valueAbs);
        final byte firstByte = extractByte(valueAbs >>> 8);

        return new TwoBytesSigned(sign, firstByte, secondByte);
    }

    private byte extractByte(int value) {
        return (byte) (value % 256 - 128);
    }

    public int toInt(TwoBytesSigned value) {
        int result = value.b1() + 128;
        result *= 256;
        result += value.b2() + 128;

        if (value.sign() % 2 == 1) {
            result = -result;
        }

        return result;
    }
}
