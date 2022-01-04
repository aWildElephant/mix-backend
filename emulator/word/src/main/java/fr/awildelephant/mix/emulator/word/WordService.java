package fr.awildelephant.mix.emulator.word;

public class WordService {

    public TwoBytesSigned extract(Word word) {
        return new TwoBytesSigned(word.sign(), word.b1(), word.b2());
    }
}
