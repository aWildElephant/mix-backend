package fr.awildelephant.mix.emulator.word;

public record Word(boolean sign, byte b1, byte b2, byte b3, byte b4, byte b5) {

    private static final Word EMPTY_WORD = new Word(false, ByteHelper.b0, ByteHelper.b0, ByteHelper.b0, ByteHelper.b0, ByteHelper.b0);

    public static Word emptyWord() {
        return EMPTY_WORD;
    }

    public Word negate() {
        return new Word(!sign, b1, b2, b3, b4, b5);
    }
}
