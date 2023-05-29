package fr.awildelephant.mix.emulator.word;

import java.util.BitSet;

// TODO: common abstract class for TwoBytesSigned and Word
public final class Word {

    private final BitSet bitset;

    public Word() {
        bitset = new BitSet(31);
    }

    public Word(Word source) {
        bitset = (BitSet) source.bitset.clone();
    }

    public static Word from(boolean sign, int b1, int b2, int b3, int b4, int b5) {
        final Word result = new Word();
        result.sign(sign);
        result.b1(b1);
        result.b2(b2);
        result.b3(b3);
        result.b4(b4);
        result.b5(b5);
        return result;
    }

    public static Word emptyWord() {
        return new Word();
    }

    public boolean sign() {
        return !bitset.get(30);
    }

    public void sign(boolean value) {
        bitset.set(30, !value);
    }

    public int b1() {
        return getByte(0);
    }

    public void b1(int value) {
        setByte(0, value);
    }

    public int b2() {
        return getByte(6);
    }

    public void b2(int value) {
        setByte(6, value);
    }

    public int b3() {
        return getByte(12);
    }

    public void b3(int value) {
        setByte(12, value);
    }

    public int b4() {
        return getByte(18);
    }

    public void b4(int value) {
        setByte(18, value);
    }

    public int b5() {
        return getByte(24);
    }

    public void b5(int value) {
        setByte(24, value);
    }

    private int getByte(int startIndex) {
        final BitSet region = bitset.get(startIndex, startIndex + 6);
        if (region.length() == 0) {
            return 0;
        }
        return region.toByteArray()[0];
    }

    private void setByte(int startIndex, int value) {
        for (int i = startIndex; i < startIndex + 6; i++) {
            bitset.set(i, value % 2 != 0);

            value = value >>> 1;
        }
    }

    public Word negate() {
        final Word result = new Word(this);
        result.sign(!sign());
        return result;
    }

    @Override
    public String toString() {
        return "Word[" + (sign() ? '+' : '-')
                + ',' + b1()
                + ',' + b2()
                + ',' + b3()
                + ',' + b4()
                + ',' + b5()
                + ']';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof final Word other)) {
            return false;
        }
        return bitset.equals(other.bitset);
    }

    @Override
    public int hashCode() {
        return bitset.hashCode();
    }
}
