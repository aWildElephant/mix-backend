package fr.awildelephant.mix.emulator.engine.state;

import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

public final class SignedTwoBytesRegister {

    private TwoBytesSigned content = TwoBytesSigned.empty();

    public TwoBytesSigned content() {
        return content;
    }

    public void content(TwoBytesSigned content) {
        this.content = content;
    }
}
