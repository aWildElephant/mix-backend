package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.Word;

public final class FieldSpecificationService {

    public Word load(FieldSpecification specification, Word source) {
        final int left = specification.left();
        final int right = specification.right();
        final boolean sign = left > 0 || source.sign();

        byte b1 = source.b1();
        byte b2 = source.b2();
        byte b3 = source.b3();
        byte b4 = source.b4();
        byte b5 = source.b5();

        switch (left) {
            case 5:
                b4 = 0;
            case 4:
                b3 = 0;
            case 3:
                b2 = 0;
            case 2:
                b1 = 0;
        }

        switch (right) {
            case 4 -> {
                b5 = b4;
                b4 = b3;
                b3 = b2;
                b2 = b1;
                b1 = 0;
            }
            case 3 -> {
                b5 = b3;
                b4 = b2;
                b3 = b1;
                b2 = 0;
                b1 = 0;
            }
            case 2 -> {
                b5 = b2;
                b4 = b1;
                b3 = 0;
                b2 = 0;
                b1 = 0;
            }
            case 1 -> {
                b5 = b1;
                b4 = 0;
                b3 = 0;
                b2 = 0;
                b1 = 0;
            }
            case 0 -> {
                b5 = 0;
                b4 = 0;
                b3 = 0;
                b2 = 0;
                b1 = 0;
            }
        }

        return new Word(sign, b1, b2, b3, b4, b5);
    }

    public Word applySpecification(FieldSpecification value, Word newContent, Word originalContent) {
        final int l = value.left();
        final int r = value.right();

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
