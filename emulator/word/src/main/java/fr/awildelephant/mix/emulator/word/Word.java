package fr.awildelephant.mix.emulator.word;

public final class Word extends AbstractBytesHolder {

    public static final int MAX_VALUE = 1073741823;
    public static final int MIN_VALUE = -1073741823;

    public Word() {
        super(31);
    }

    public Word(Word source) {
        super(source);
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

    public int b1() {
        return getByte(0);
    }

    public void b1(int value) {
        setByte(0, value);
    }

    public int b2() {
        return getByte(1);
    }

    public void b2(int value) {
        setByte(1, value);
    }

    public int b3() {
        return getByte(2);
    }

    public void b3(int value) {
        setByte(2, value);
    }

    public int b4() {
        return getByte(3);
    }

    public void b4(int value) {
        setByte(3, value);
    }

    public int b5() {
        return getByte(4);
    }

    public void b5(int value) {
        setByte(4, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof final Word other)) {
            return false;
        }
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
