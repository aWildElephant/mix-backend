package fr.awildelephant.mmix.emulator.engine.state;

import fr.awildelephant.mmix.emulator.word.Word;

public class SignedFiveBytesRegister {

    private Word word = Word.emptyWord();

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
