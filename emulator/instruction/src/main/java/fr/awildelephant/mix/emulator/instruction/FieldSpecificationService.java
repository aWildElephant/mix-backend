package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.Word;

public final class FieldSpecificationService {

    public Word applySpecification(byte value, Word newContent) {
        return applySpecification(value, newContent, Word.emptyWord());
    }

    public Word applySpecification(byte value, Word newContent, Word originalContent) {
        final int l = value >>> 3;
        final int r = value % 8;

        final boolean sign;
        if (l == 0) {
            sign = newContent.sign();
        } else {
            sign = originalContent.sign();
        }

        final byte b1;
        if (l <= 1 && r >= 1) {
            b1 = newContent.b1();
        } else {
            b1 = originalContent.b1();
        }

        final byte b2;
        if (l <= 2 && r >= 2) {
            b2 = newContent.b2();
        } else {
            b2 = originalContent.b2();
        }

        final byte b3;
        if (l <= 3 && r >= 3) {
            b3 = newContent.b3();
        } else {
            b3 = originalContent.b3();
        }

        final byte b4;
        if (l <= 4 && r >= 4) {
            b4 = newContent.b4();
        } else {
            b4 = originalContent.b4();
        }

        final byte b5;
        if (l <= 5 && r >= 5) {
            b5 = newContent.b5();
        } else {
            b5 = originalContent.b5();
        }

        return new Word(sign, b1, b2, b3, b4, b5);
    }
}
