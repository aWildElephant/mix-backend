package fr.awildelephant.mix.emulator.engine.comparison;

import fr.awildelephant.mix.emulator.word.Word;
import fr.awildelephant.mix.emulator.word.WordHelper;

import java.util.Comparator;

public final class WordComparator implements Comparator<Word> {

    @Override
    public int compare(Word first, Word second) {
        return WordHelper.toInt(first) - WordHelper.toInt(second);
    }
}
