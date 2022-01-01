package fr.awildelephant.mix.emulator.word;

public class WordService {

    public TwoBytesSigned extract(Word word) {
        return new TwoBytesSigned(word.getSign(), word.getB1(), word.getB2());
    }
}
