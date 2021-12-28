package fr.awildelephant.mmix.emulator.instruction;

import fr.awildelephant.mmix.emulator.word.Word;

public final class FieldSpecificationHelper {

    private FieldSpecificationHelper() {

    }

    public static Word applySpecification(byte value, Word newContent) {
        return applySpecification(value, newContent, Word.emptyWord());
    }

    public static Word applySpecification(byte value, Word newContent, Word originalContent) {
        final int l = value >>> 3;
        final int r = value % 8;

        final byte sign;
        if (l == 0) {
            sign = newContent.getSign();
        } else {
            sign = originalContent.getSign();
        }

        final byte b1;
        if (l <= 1 && r >= 1) {
            b1 = newContent.getB1();
        } else {
            b1 = originalContent.getB1();
        }

        final byte b2;
        if (l <= 2 && r >= 2) {
            b2 = newContent.getB2();
        } else {
            b2 = originalContent.getB2();
        }

        final byte b3;
        if (l <= 3 && r >= 3) {
            b3 = newContent.getB3();
        } else {
            b3 = originalContent.getB3();
        }

        final byte b4;
        if (l <= 4 && r >= 4) {
            b4 = newContent.getB4();
        } else {
            b4 = originalContent.getB4();
        }

        final byte b5;
        if (l <= 5 && r >= 5) {
            b5 = newContent.getB5();
        } else {
            b5 = originalContent.getB5();
        }

        return new Word(sign, b1, b2, b3, b4, b5);

    }
}
