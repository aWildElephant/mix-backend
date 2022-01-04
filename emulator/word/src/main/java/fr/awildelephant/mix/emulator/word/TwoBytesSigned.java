package fr.awildelephant.mix.emulator.word;

public record TwoBytesSigned(boolean sign, byte b1, byte b2) {

    private static final TwoBytesSigned EMPTY = new TwoBytesSigned(false, ByteHelper.b0, ByteHelper.b0);

    public static TwoBytesSigned empty() {
        return EMPTY;
    }
}
