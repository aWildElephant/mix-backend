package fr.awildelephant.mmix.emulator.instruction;

import fr.awildelephant.mmix.emulator.word.ByteHelper;
import fr.awildelephant.mmix.emulator.word.Word;

public final class FieldSpecificationHelper {

    private FieldSpecificationHelper() {

    }

    public static Word applySpecification(byte value, Word word) {
        final int l = value >>> 3;
        final int r = value % 8;

        final byte sign;
        if (l == 0) {
            sign = word.getSign();
        } else {
            sign = ByteHelper.b1;
        }

        final byte b1;
        if (l <= 1 && r >= 1) {
            b1 = word.getB1();
        } else {
            b1 = ByteHelper.b0;
        }

        final byte b2;
        if (l <= 2 && r >= 2) {
            b2 = word.getB2();
        } else {
            b2 = ByteHelper.b0;
        }

        final byte b3;
        if (l <= 3 && r >= 3) {
            b3 = word.getB3();
        } else {
            b3 = ByteHelper.b0;
        }

        final byte b4;
        if (l <= 4 && r >= 4) {
            b4 = word.getB4();
        } else {
            b4 = ByteHelper.b0;
        }

        final byte b5;
        if (l <= 5 && r >= 5) {
            b5 = word.getB5();
        } else {
            b5 = ByteHelper.b0;
        }

        return new Word(sign, b1, b2, b3, b4, b5);
    }
}
