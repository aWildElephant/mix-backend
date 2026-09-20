package fr.awildelephant.mix.emulator.engine.comparison;

import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

import java.util.Comparator;

public final class TwoBytesSignedComparator implements Comparator<TwoBytesSigned> {

    @Override
    public int compare(TwoBytesSigned first, TwoBytesSigned second) {
        return first.toInt() - second.toInt();
    }
}
