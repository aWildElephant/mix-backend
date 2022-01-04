package fr.awildelephant.mix.emulator.word;

public final class TwoBytesSignedService {

    public TwoBytesSigned fromInt(int value) {
        final boolean sign = value < 0;
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

        if (value.sign()) {
            result = -result;
        }

        return result;
    }
}
