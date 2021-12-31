package fr.awildelephant.mix.emulator.parser.input;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
public class InputWithLookup {

    private static final int NO_VALUE = -2;

    private final InputStream inputStream;

    private int lookup = NO_VALUE;

    public int lookup() throws IOException {
        if (lookup == -2) {
            consume();
        }
        return lookup;
    }

    public void consume() throws IOException {
        lookup = inputStream.read();
    }
}
