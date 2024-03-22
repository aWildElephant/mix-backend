package fr.awildelephant.mix.emulator.word;

public final class TenBytesSigned extends AbstractBytesHolder {

    public static final long MAX_VALUE = 1_152_921_504_606_846_975L;
    public static final long MIN_VALUE = -1_152_921_504_606_846_975L;

    public TenBytesSigned() {
        super(61);
    }

    public static TenBytesSigned from(boolean sign, int b1, int b2, int b3, int b4, int b5, int b6, int b7, int b8, int b9, int b10) {
        final TenBytesSigned result = new TenBytesSigned();
        result.sign(sign);
        result.setByte(0, b1);
        result.setByte(1, b2);
        result.setByte(2, b3);
        result.setByte(3, b4);
        result.setByte(4, b5);
        result.setByte(5, b6);
        result.setByte(6, b7);
        result.setByte(7, b8);
        result.setByte(8, b9);
        result.setByte(9, b10);
        return result;
    }
}
