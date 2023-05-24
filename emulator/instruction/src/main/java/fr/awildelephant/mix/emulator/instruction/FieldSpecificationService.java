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

    public Word store(FieldSpecification value, Word source, Word destination) {
        final int l = value.left();
        final int r = value.right();

        final boolean sign;
        if (l == 0) {
            sign = source.sign();
        } else {
            sign = destination.sign();
        }

        byte b1 = destination.b1();
        byte b2 = destination.b2();
        byte b3 = destination.b3();
        byte b4 = destination.b4();
        byte b5 = destination.b5();

        switch (r) {
            case 1 -> b1 = source.b5();
            case 2 -> {
                switch (l) {
                    case 0:
                    case 1:
                        b1 = source.b4();
                    case 2:
                        b2 = source.b5();
                }
            }
            case 3 -> {
                switch (l) {
                    case 0:
                    case 1:
                        b1 = source.b3();
                    case 2:
                        b2 = source.b4();
                    case 3:
                        b3 = source.b5();
                }
            }
            case 4 -> {
                switch (l) {
                    case 0:
                    case 1:
                        b1 = source.b2();
                    case 2:
                        b2 = source.b3();
                    case 3:
                        b3 = source.b4();
                    case 4:
                        b4 = source.b5();
                }
            }
            case 5 -> {
                switch (l) {
                    case 0:
                    case 1:
                        b1 = source.b1();
                    case 2:
                        b2 = source.b2();
                    case 3:
                        b3 = source.b3();
                    case 4:
                        b4 = source.b4();
                    case 5:
                        b5 = source.b5();
                }
            }
        }

        return new Word(sign, b1, b2, b3, b4, b5);
    }
}
