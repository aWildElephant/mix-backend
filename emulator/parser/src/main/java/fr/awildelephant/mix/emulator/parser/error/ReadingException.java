package fr.awildelephant.mix.emulator.parser.error;

import java.io.IOException;

public final class ReadingException extends ParseError {

    public ReadingException(IOException cause) {
        super(cause);
    }
}
