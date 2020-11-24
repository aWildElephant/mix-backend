package fr.awildelephant.mmix.emulator.engine.state;

public class SignedFiveBytesRegister {

    private Word word = new Word();

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
