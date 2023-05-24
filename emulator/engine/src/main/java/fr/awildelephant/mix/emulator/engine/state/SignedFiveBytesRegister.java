package fr.awildelephant.mix.emulator.engine.state;

import fr.awildelephant.mix.emulator.word.Word;

public final class SignedFiveBytesRegister {

    private Word content = Word.emptyWord();

    public Word content() {
        return content;
    }

    public void content(Word word) {
        this.content = word;
    }
}
