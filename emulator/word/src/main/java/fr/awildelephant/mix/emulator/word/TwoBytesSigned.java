package fr.awildelephant.mix.emulator.word;

import java.util.BitSet;

public final class TwoBytesSigned {

    private final BitSet bitset;

    public static TwoBytesSigned empty() {
        return new TwoBytesSigned();
    }

    public TwoBytesSigned() {
        bitset = new BitSet(13);

        bitset.clear();
    }

    public static TwoBytesSigned fromInt(int value) {
        final boolean sign = value > 0;
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

    public void sign(boolean value) {
        bitset.set(12, !value);
    }

    public boolean sign() {
        return !bitset.get(12);
    }

    public void b1(int value) {
        for (int i = 0; i < 6; i++) {
            bitset.set(i, value % 2 != 0);

            value = value >>> 1;
        }
    }

    public int b1() {
        return extract(0, 6);
    }

    public void b2(int value) {
        for (int i = 6; i < 12; i++) {
            bitset.set(i, value % 2 != 0);

            value = value >>> 1;
        }
    }

    public int b2() {
        return extract(6, 12);
    }

    private int extract(int fromIncluded, int toExcluded) {
        final BitSet region = bitset.get(fromIncluded, toExcluded);
        if (region.length() == 0) {
            return 0;
        }
        return region.toByteArray()[0];
    }

    // TODO: test overflow
    public TwoBytesSigned add(TwoBytesSigned other) {
        final int intResult = toInt() + other.toInt();
        return TwoBytesSigned.fromInt(intResult % 4_095);
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

        return bitset.equals(other.bitset);
    }

    @Override
    public int hashCode() {
        return bitset.hashCode();
    }
}
