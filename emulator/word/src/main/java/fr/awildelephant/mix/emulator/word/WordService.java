package fr.awildelephant.mix.emulator.word;

public class WordService {

    public TwoBytesSigned extract(Word word) {
        final TwoBytesSigned result = new TwoBytesSigned();
        result.sign(word.sign());
        result.b1(word.b1());
        result.b2(word.b2());
        return result;
    }
}
