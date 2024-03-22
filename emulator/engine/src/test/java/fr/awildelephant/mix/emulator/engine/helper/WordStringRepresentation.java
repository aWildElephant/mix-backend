package fr.awildelephant.mix.emulator.engine.helper;

import fr.awildelephant.mix.emulator.word.Word;

public final class WordStringRepresentation {

    private WordStringRepresentation() {

    }

    public static Word toWord(String representation) {
        final boolean sign = representation.charAt(1) != '-';
        final String zoinks1 = representation.substring(3, representation.length() - 1);
        final String[] cells = zoinks1.split("\\|");

        final Word word = new Word();
        word.sign(sign);
        int index = 0;
        for (String cell : cells) {
            // FIXME: test + handle all cases
            if (cell.startsWith(",")) {
                final int value = Integer.parseInt(cell.substring(1));
                word.setByte(index++, (value >> 6) % 64);
                word.setByte(index++, value % 64);
            } else {
                word.setByte(index++, Integer.parseInt(cell));
            }
        }
        return word;
    }
}
