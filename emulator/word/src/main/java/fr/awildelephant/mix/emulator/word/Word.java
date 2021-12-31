package fr.awildelephant.mix.emulator.word;

import lombok.Value;

@Value
public class Word {

    private static final Word EMPTY_WORD = new Word(ByteHelper.b0, ByteHelper.b0, ByteHelper.b0, ByteHelper.b0, ByteHelper.b0, ByteHelper.b0);

    public static Word emptyWord() {
        return EMPTY_WORD;
    }

    byte sign;
    byte b1;
    byte b2;
    byte b3;
    byte b4;
    byte b5;
}
