package fr.awildelephant.mix.emulator.word;

public final class TwoBytesSigned extends AbstractBytesHolder {

    public TwoBytesSigned() {
        super(13);
    }

    public static TwoBytesSigned from(boolean sign, int b1, int b2) {
        final TwoBytesSigned result = new TwoBytesSigned();
        result.sign(sign);
        result.b1(b1);
        result.b2(b2);
        return result;
    }

    public static TwoBytesSigned fromInt(int value) {
        final boolean sign = value >= 0;
        final int valueAbs = Math.abs(value);
        final int firstByte = extractByte(valueAbs >>> 6);
        final int secondByte = extractByte(valueAbs);

        final TwoBytesSigned result = new TwoBytesSigned();
        result.sign(sign);
        result.b1(firstByte);
        result.b2(secondByte);
        return result;
    }

    private static int extractByte(int value) {
        return value % 64; // TODO: use bitwise operators
    }

    public int toInt() {
        int result = b1();
        result *= 64;
        result += b2();

        if (!sign()) {
            result = -result;
        }

        return result;
    }

    public void b1(int value) {
        setByte(0, value);
    }

    public int b1() {
        return getByte(0);
    }

    public void b2(int value) {
        setByte(1, value);
    }

    public int b2() {
        return getByte(1);
    }

    // TODO: test overflow
    public TwoBytesSigned add(TwoBytesSigned other) {
        final int intResult = toInt() + other.toInt();
        return TwoBytesSigned.fromInt(intResult % 4_095);
    }

    public Word toWord() {
        return Word.from(sign(), 0, 0, 0, b1(), b2());
    }

    @Override
    public String toString() {
        return "TwoBytesSigned[" + (sign() ? '+' : '-') + ',' + b1() + ',' + b2() + ']';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof final TwoBytesSigned other)) {
            return false;
        }

        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
