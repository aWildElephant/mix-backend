package fr.awildelephant.mix.emulator.engine.state;

import fr.awildelephant.mix.emulator.word.Word;

public class SignedFiveBytesRegister {

    private Word word = Word.emptyWord();

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
