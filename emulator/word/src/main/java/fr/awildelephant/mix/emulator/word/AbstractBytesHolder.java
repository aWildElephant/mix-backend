package fr.awildelephant.mix.emulator.word;

import java.util.BitSet;
import java.util.Objects;

public abstract class AbstractBytesHolder {

    private final BitSet bitSet;
    private final int size;

    protected AbstractBytesHolder(int size) {
        this.bitSet = new BitSet(size);
        this.size = size / 6;
    }

    protected AbstractBytesHolder(AbstractBytesHolder source) {
        this.bitSet = (BitSet) source.bitSet.clone();
        this.size = source.size;
    }

    public boolean sign() {
        return !bitSet.get(0);
    }

    public void sign(boolean value) {
        bitSet.set(0, !value);
    }

    public void negate() {
        bitSet.set(0, !sign());
    }

    public int getByte(int index) {
        final BitSet region = bitSet.get(bitSetIndex(index), bitSetIndex(index + 1));
        if (region.isEmpty()) {
            return 0;
        }
        return region.toByteArray()[0];
    }

    private static int bitSetIndex(int byteIndex) {
        return byteIndex * 6 + 1;
    }

    public void setByte(int index, int value) {
        for (int i = bitSetIndex(index); i < bitSetIndex(index + 1); i++) {
            bitSet.set(i, value % 2 != 0);

            value = value >>> 1;
        }
    }

    private int size() {
        return size;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final int size = size();
        sb.append(size);
        sb.append("-byte word [");
        if (sign()) {
            sb.append("+");
        } else {
            sb.append("-");
        }
        for (int i = 0; i < size - 1; i++) {
            sb.append(getByte(i));
            sb.append(",");
        }
        sb.append(getByte(size - 1));
        sb.append("]");
        return sb.toString();
    }
}
