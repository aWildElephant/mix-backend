package fr.awildelephant.mix.emulator.word;

import java.util.BitSet;
import java.util.Objects;

public abstract class AbstractBytesHolder {

    private final BitSet bitSet;

    protected AbstractBytesHolder(int size) {
        this.bitSet = new BitSet(size);
    }

    protected AbstractBytesHolder(AbstractBytesHolder source) {
        this.bitSet = (BitSet) source.bitSet.clone();
    }

    public boolean sign() {
        return !bitSet.get(0);
    }

    public void sign(boolean value) {
        bitSet.set(0, !value);
    }

    protected int getByte(int index) {
        final BitSet region = bitSet.get(bitSetIndex(index), bitSetIndex(index + 1));
        if (region.length() == 0) {
            return 0;
        }
        return region.toByteArray()[0];
    }

    private static int bitSetIndex(int byteIndex) {
        return byteIndex * 6 + 1;
    }

    protected void setByte(int index, int value) {
        for (int i = bitSetIndex(index); i < bitSetIndex(index + 1); i++) {
            bitSet.set(i, value % 2 != 0);

            value = value >>> 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof final AbstractBytesHolder other)) {
            return false;
        }

        return Objects.equals(bitSet, other.bitSet);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bitSet);
    }
}
