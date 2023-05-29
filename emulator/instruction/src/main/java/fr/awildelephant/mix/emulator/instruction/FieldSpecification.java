package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.Word;

public record FieldSpecification(int left, int right) {

    public Word load(Word source) {
        final boolean sign = left > 0 || source.sign();

        int b1 = source.b1();
        int b2 = source.b2();
        int b3 = source.b3();
        int b4 = source.b4();
        int b5 = source.b5();

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

        return Word.from(sign, b1, b2, b3, b4, b5);
    }

    public Word store(Word source, Word destination) {
        final boolean sign;
        if (left == 0) {
            sign = source.sign();
        } else {
            sign = destination.sign();
        }

        int b1 = destination.b1();
        int b2 = destination.b2();
        int b3 = destination.b3();
        int b4 = destination.b4();
        int b5 = destination.b5();

        switch (right) {
            case 1 -> b1 = source.b5();
            case 2 -> {
                switch (left) {
                    case 0:
                    case 1:
                        b1 = source.b4();
                    case 2:
                        b2 = source.b5();
                }
            }
            case 3 -> {
                switch (left) {
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
                switch (left) {
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
                switch (left) {
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

        return Word.from(sign, b1, b2, b3, b4, b5);
    }
}
